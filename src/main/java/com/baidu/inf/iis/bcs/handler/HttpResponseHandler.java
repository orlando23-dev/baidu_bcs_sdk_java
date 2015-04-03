/*    */ package com.baidu.inf.iis.bcs.handler;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.http.BCSHttpResponse;
/*    */ import com.baidu.inf.iis.bcs.model.BCSClientException;
/*    */ import com.baidu.inf.iis.bcs.model.ObjectMetadata;
/*    */ import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;
/*    */ import com.baidu.inf.iis.bcs.utils.ServiceUtils;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.text.ParseException;
/*    */ import java.util.HashSet;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.Set;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public abstract class HttpResponseHandler<T>
/*    */ {
/* 24 */   private static final Log log = LogFactory.getLog(HttpResponseHandler.class);
/* 25 */   private static final Set<String> ignoredHeaders = new HashSet();
/*    */ 
/*    */   protected String getResponseContentByStr(BCSHttpResponse paramBCSHttpResponse)
/*    */   {
/* 33 */     if (null != paramBCSHttpResponse.getContent()) {
/* 34 */       byte[] arrayOfByte = new byte[1024];
/* 35 */       StringBuilder localStringBuilder = new StringBuilder();
/*    */       try
/*    */       {
/*    */         int i;
/* 38 */         while ((i = paramBCSHttpResponse.getContent().read(arrayOfByte)) > 0)
/* 39 */           localStringBuilder.append(new String(arrayOfByte, 0, i));
/*    */       }
/*    */       catch (IOException localIOException) {
/* 42 */         throw new BCSClientException("Read http response body error.", localIOException);
/*    */       }
/* 44 */       return localStringBuilder.toString();
/*    */     }
/* 46 */     return "";
/*    */   }
/*    */ 
/*    */   public abstract BaiduBCSResponse<T> handle(BCSHttpResponse paramBCSHttpResponse);
/*    */ 
/*    */   protected BaiduBCSResponse<T> parseResponseMetadata(BCSHttpResponse paramBCSHttpResponse)
/*    */   {
/* 53 */     BaiduBCSResponse localBaiduBCSResponse = new BaiduBCSResponse();
/* 54 */     String str = (String)paramBCSHttpResponse.getHeaders().get("x-bs-request-id");
/* 55 */     localBaiduBCSResponse.setRequestId(str);
/* 56 */     log.info("Bcs requestId:" + str);
/* 57 */     return localBaiduBCSResponse;
/*    */   }
/*    */ 
/*    */   protected void populateObjectMetadata(BCSHttpResponse paramBCSHttpResponse, ObjectMetadata paramObjectMetadata) {
/* 61 */     for (Map.Entry localEntry : paramBCSHttpResponse.getHeaders().entrySet()) {
/* 62 */       String str = (String)localEntry.getKey();
/* 63 */       if (str.startsWith("x-bs-meta-")) {
/* 64 */         str = str.substring("x-bs-meta-".length());
/* 65 */         paramObjectMetadata.addUserMetadata(str, (String)localEntry.getValue());
/* 66 */       } else if (str.equals("Last-Modified")) {
/*    */         try {
/* 68 */           paramObjectMetadata.setHeader(str, ServiceUtils.parseRfc822Date((String)localEntry.getValue()));
/*    */         } catch (ParseException localParseException) {
/* 70 */           log.warn("Unable to parse last modified date: " + (String)localEntry.getValue(), localParseException);
/*    */         }
/* 72 */       } else if (!ignoredHeaders.contains(str)) {
/* 73 */         paramObjectMetadata.setHeader(str, localEntry.getValue());
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public boolean isNeedsConnectionLeftOpen()
/*    */   {
/* 82 */     return false;
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/* 27 */     ignoredHeaders.add("Date");
/* 28 */     ignoredHeaders.add("Server");
/* 29 */     ignoredHeaders.add("x-bs-request-id");
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.handler.HttpResponseHandler
 * JD-Core Version:    0.6.2
 */