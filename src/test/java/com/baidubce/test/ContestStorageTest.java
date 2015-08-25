/**
 * 
 */
package com.baidubce.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Collector;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;

/**
 * @author llv23
 * @see how-to store pdf template information into bcs bucket
 */
public class ContestStorageTest {

	static String APIKEY = "appidKey";
	static String ACCESSKEY = "secretKey";
	static String bucketName = "tcnotify-storage";
	// see url https://www.topcoder.com/challenge-details/30049526/?type=develop
	static String challengeUrlFormat = "https://www.topcoder.com/challenge-details/%s/?type=%s";
	static String challengeId = "30049526";
	static String challengeType = "develop";
	static String contestOverviewId = "contest-overview";

	/**
	 * @throws MalformedURLException
	 * @see reference in http://console.bce.baidu.com/doc/#/doc/product~serviceType=BOS&file=Java-SDK
	 * @see client.putObject 1, File 2, FileStream 3, byte[] 4, String
	 * @see try-resource in https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
	 */
	@Test
	@Category(com.baidubce.test.category.IContestStorageTest.class)
	public void testContestStoreOperation() throws MalformedURLException, IOException {
		Properties defaultProps = new Properties();
		try(FileInputStream in = new FileInputStream("src/main/resources/META-INF/application.properties")){
			defaultProps.load(in);
		}
		
		String ACCESS_KEY_ID = System.getProperty(APIKEY);
		String SECRET_ACCESS_KEY = System.getProperty(ACCESSKEY);
		BosClientConfiguration config = new BosClientConfiguration();
		String strProxyHost = System.getProperty("http.proxyHost");
		if (strProxyHost != null) {
			config.setProxyHost(strProxyHost);
		}
		String strProxyPort = System.getProperty("http.proxyPort");
		if (strProxyPort != null) {
			try {
				config.setProxyPort(Integer.parseInt(strProxyPort));
			} catch (Exception ex) {
				throw ex;
			}
		}
		config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
		config.setMaxConnections(5);
		config.setConnectionTimeoutInMillis(5000);
		config.setSocketTimeoutInMillis(2000);

		BosClient client = new BosClient(config);
		boolean exists = client.doesBucketExist(bucketName);
		Assert.assertEquals(String.format("bucket (%s) must exist", bucketName), exists, true);
		/**
		 * streaming to fetch contest content
		URL requestStreamUrl = new URL(String.format(challengeUrlFormat, challengeId, challengeType));
		HttpURLConnection urlConn = (HttpURLConnection) requestStreamUrl.openConnection();
		try (InputStream in = urlConn.getInputStream()) {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
				String line = "";
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
			}
		}
		 */
		// see : start-tag in html
		// <div ng-if="!CD.isDesign" id="contest-overview" class="tableWrap  tab">
		// </div>
		Document docContest = Jsoup.connect(String.format(challengeUrlFormat, challengeId, challengeType)).get();
		Elements elementsContest = Collector.collect(new Evaluator.Id(contestOverviewId), docContest);
		// see : only design and nondesign contests
		assert(elementsContest.size() == 2);
		// see : parse elemContest for core content to Map<String, String>
		Map<String, Element> coreContents = new HashMap<String, Element>();
		for(Element element: elementsContest) {
			String keyforContestType = element.attr("ng-if");
			if(keyforContestType.equalsIgnoreCase("!CD.isDesign")) {
				coreContents.put("nondesign", element);
			}
			else if(keyforContestType.equalsIgnoreCase("CD.isDesign")) {
				coreContents.put("design", element);
			}
		}
		Element targetElement = null;
		// see get html content of design/develop contest 
		if(challengeType.equalsIgnoreCase("design")) {
			targetElement = coreContents.get("design");
		}
		else {
			targetElement = coreContents.get("nondesign");
		}
		assert(targetElement != null);
		//TODO : parse develop
		Elements metasContest = docContest.select("meta");
		for(Element metaElement : metasContest) {
			System.out.println(metaElement);
		}
	}
}
