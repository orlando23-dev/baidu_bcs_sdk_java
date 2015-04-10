/**
 * 
 */
package com.baidubce.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

	static String APIKEY = "APIKEY";
	static String ACCESSKEY = "ACCESSKEY";
	static String bucketName = "tcnotify-storage";
	static String challengeUrlFormat = "https://www.topcoder.com/challenge-details/%s/?type=%s";
	static String challengeId = "30049526";
	static String challengeType = "develop";

	/**
	 * @throws MalformedURLException
	 * @see reference in 
	 *      http://console.bce.baidu.com/doc/#/doc/product~serviceType=BOS&file=Java-SDK
	 * @see client.putObject 1, File 2, FileStream 3, byte[] 4, String
	 * @see try-resource in 
	 *      https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
	 */
	@Test
	@Category(com.baidubce.test.category.IContestStorageTest.class)
	public void testContestStoreOperation() throws MalformedURLException, IOException {
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
		config.setMaxConnections(10);
		config.setConnectionTimeoutInMillis(5000);
		config.setSocketTimeoutInMillis(2000);

		BosClient client = new BosClient(config);
		boolean exists = client.doesBucketExist(bucketName);
		Assert.assertEquals(String.format("bucket (%s) must exist", bucketName), exists, true);
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
	}
}
