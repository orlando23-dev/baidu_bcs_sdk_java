/*    */ package com.baidu.inf.iis.bcs.http;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.model.BCSClientException;
/*    */ import com.baidu.inf.iis.bcs.utils.Constants;

/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.Set;

/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.apache.http.HttpEntity;
/*    */ import org.apache.http.client.methods.HttpDelete;
/*    */ import org.apache.http.client.methods.HttpGet;
/*    */ import org.apache.http.client.methods.HttpHead;
/*    */ import org.apache.http.client.methods.HttpPut;
/*    */ import org.apache.http.client.methods.HttpRequestBase;
/*    */ import org.apache.http.client.utils.URLEncodedUtils;
/*    */ import org.apache.http.message.BasicNameValuePair;
/*    */ 
/*    */ public class HttpRequestFactory
/*    */ {
/* 28 */   private static final Log log = LogFactory.getLog(HttpRequestFactory.class);
/*    */ 
/*    */   public HttpRequestBase createHttpRequestBase(ClientConfiguration paramClientConfiguration, BCSHttpRequest paramBCSHttpRequest)
/*    */   {
/* 32 */     String str = buildUri(paramClientConfiguration, paramBCSHttpRequest);
/* 33 */     log.debug(paramBCSHttpRequest.getHttpMethod().toString() + "  " + str);
/*    */     Object localObject1;
/*    */     Object localObject3;
/* 36 */     if (paramBCSHttpRequest.getHttpMethod() == HttpMethodName.GET) {
/* 37 */       localObject1 = new HttpGet(str);
/* 38 */     } else if (paramBCSHttpRequest.getHttpMethod() == HttpMethodName.PUT) {
/* 39 */       HttpPut localObject2 = new HttpPut(str);
/* 40 */       localObject1 = localObject2;
/* 41 */       if (null != paramBCSHttpRequest.getContent()) {
/* 42 */         localObject3 = new RepeatableInputStreamRequestEntity(paramBCSHttpRequest);
/* 43 */         ((HttpPut)localObject2).setEntity((HttpEntity)localObject3);
/*    */       }
/* 45 */     } else if (paramBCSHttpRequest.getHttpMethod() == HttpMethodName.DELETE) {
/* 46 */       localObject1 = new HttpDelete(str);
/* 47 */     } else if (paramBCSHttpRequest.getHttpMethod() == HttpMethodName.HEAD) {
/* 48 */       localObject1 = new HttpHead(str);
/*    */     } else {
/* 50 */       throw new BCSClientException("Unknown HTTP method name:" + paramBCSHttpRequest.getHttpMethod().toString());
/*    */     }
/*    */ 
/* 54 */     for (Object localObject2 = paramBCSHttpRequest.getHeaders().entrySet().iterator(); ((Iterator)localObject2).hasNext(); ) { localObject3 = (Map.Entry)((Iterator)localObject2).next();
/* 55 */       if (!((String)((Map.Entry)localObject3).getKey()).equalsIgnoreCase("Content-Length"))
/*    */       {
/* 58 */         ((HttpRequestBase)localObject1).addHeader((String)((Map.Entry)localObject3).getKey(), (String)((Map.Entry)localObject3).getValue());
/*    */       } }
/* 60 */     if ((((HttpRequestBase)localObject1).getHeaders("Content-Type") == null) || (((HttpRequestBase)localObject1).getHeaders("Content-Type").length == 0)) {
/* 61 */       ((HttpRequestBase)localObject1).addHeader("Content-Type", "application/x-www-form-urlencoded; charset=" + Constants.DEFAULT_ENCODING.toLowerCase());
/*    */     }
/*    */ 
/* 64 */     return (HttpRequestBase) localObject1;
/*    */   }
/*    */ 
/*    */   public String buildUri(ClientConfiguration paramClientConfiguration, BCSHttpRequest paramBCSHttpRequest) {
/* 68 */     StringBuilder localStringBuilder = new StringBuilder();
/* 69 */     localStringBuilder.append(paramClientConfiguration.getProtocol().toString());
/* 70 */     localStringBuilder.append("://");
/* 71 */     localStringBuilder.append(paramBCSHttpRequest.getEndpoint());
/* 72 */     if ((paramBCSHttpRequest.getResourcePath() != null) && (paramBCSHttpRequest.getResourcePath().length() > 0)) {
/* 73 */       if (!paramBCSHttpRequest.getResourcePath().startsWith("/")) {
/* 74 */         localStringBuilder.append("/");
/*    */       }
/* 76 */       localStringBuilder.append(paramBCSHttpRequest.getResourcePath());
/*    */     }
/* 78 */     String str = encodeParameters(paramBCSHttpRequest);
/* 79 */     localStringBuilder.append("?").append(str);
/* 80 */     return localStringBuilder.toString();
/*    */   }
/*    */ 
/*    */   private String encodeParameters(BCSHttpRequest paramBCSHttpRequest) {
/* 84 */     ArrayList localArrayList = null;
/* 85 */     if (paramBCSHttpRequest.getParameters().size() > 0) {
/* 86 */       localArrayList = new ArrayList(paramBCSHttpRequest.getParameters().size());
/* 87 */       for (Iterator<Entry<String, String>> localObject = paramBCSHttpRequest.getParameters().entrySet().iterator(); ((Iterator)localObject).hasNext(); ) { Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
/* 88 */         localArrayList.add(new BasicNameValuePair((String)localEntry.getKey(), (String)localEntry.getValue()));
/*    */       }
/*    */     }
/* 91 */     Object localObject = null;
/* 92 */     if (localArrayList != null) {
/* 93 */       localObject = URLEncodedUtils.format(localArrayList, Constants.DEFAULT_ENCODING);
/*    */     }
/* 95 */     return (String) localObject;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.http.HttpRequestFactory
 * JD-Core Version:    0.6.2
 */