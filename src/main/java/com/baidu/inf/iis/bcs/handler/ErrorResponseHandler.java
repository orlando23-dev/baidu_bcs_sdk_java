/*    */ package com.baidu.inf.iis.bcs.handler;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.http.BCSHttpResponse;
/*    */ import com.baidu.inf.iis.bcs.model.BCSServiceException;
/*    */ import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;
/*    */ import flexjson.JSONDeserializer;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class ErrorResponseHandler extends HttpResponseHandler<BCSServiceException>
/*    */ {
/* 20 */   private static final Log log = LogFactory.getLog(ErrorResponseHandler.class);
/*    */ 
/*    */   public BaiduBCSResponse<BCSServiceException> handle(BCSHttpResponse paramBCSHttpResponse)
/*    */   {
/* 24 */     BaiduBCSResponse localBaiduBCSResponse = new BaiduBCSResponse();
/*    */ 
/* 26 */     String str1 = getResponseContentByStr(paramBCSHttpResponse);
/* 27 */     int i = -1;
/* 28 */     String str2 = "";
/*    */ 
/* 30 */     if (0 != str1.length()) {
/*    */       try {
/* 32 */         JSONDeserializer localJSONDeserializer = new JSONDeserializer();
/*    */ 
/* 35 */         HashMap localHashMap1 = (HashMap)localJSONDeserializer.deserialize(str1);
/* 36 */         HashMap localHashMap2 = (HashMap)localHashMap1.get("Error");
/* 37 */         i = Integer.valueOf((String)localHashMap2.get("code")).intValue();
/* 38 */         str2 = (String)localHashMap2.get("Message");
/*    */       } catch (Exception localException) {
/* 40 */         log.warn("analyze bcs error response json failed.", localException);
/*    */       }
/*    */     }
/* 43 */     BCSServiceException localBCSServiceException = new BCSServiceException("[StatusCode:" + paramBCSHttpResponse.getStatusCode() + "] [ErrorMsg:" + str1 + "]");
/* 44 */     localBCSServiceException.setHttpErrorCode(paramBCSHttpResponse.getStatusCode());
/* 45 */     localBCSServiceException.setRequestId((String)paramBCSHttpResponse.getHeaders().get("x-bs-request-id"));
/* 46 */     localBCSServiceException.setBcsErrorCode(i);
/* 47 */     localBCSServiceException.setBcsErrorMessage(str2);
/* 48 */     localBaiduBCSResponse.setResult(localBCSServiceException);
/* 49 */     return localBaiduBCSResponse;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.handler.ErrorResponseHandler
 * JD-Core Version:    0.6.2
 */