/*    */ package com.baidu.inf.iis.bcs.handler;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.http.BCSHttpResponse;
/*    */ import com.baidu.inf.iis.bcs.policy.Policy;
/*    */ import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;
/*    */ 
/*    */ public class PolicyResponseHandler extends HttpResponseHandler<Policy>
/*    */ {
/*    */   public BaiduBCSResponse<Policy> handle(BCSHttpResponse paramBCSHttpResponse)
/*    */   {
/* 16 */     String str = getResponseContentByStr(paramBCSHttpResponse);
/* 17 */     Policy localPolicy = new Policy().buildJsonStr(str);
/* 18 */     BaiduBCSResponse localBaiduBCSResponse = parseResponseMetadata(paramBCSHttpResponse);
/* 19 */     localBaiduBCSResponse.setResult(localPolicy);
/* 20 */     return localBaiduBCSResponse;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.handler.PolicyResponseHandler
 * JD-Core Version:    0.6.2
 */