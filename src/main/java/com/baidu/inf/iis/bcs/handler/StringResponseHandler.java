/*    */ package com.baidu.inf.iis.bcs.handler;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.http.BCSHttpResponse;
/*    */ import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;
/*    */ 
/*    */ public class StringResponseHandler extends HttpResponseHandler<String>
/*    */ {
/*    */   public BaiduBCSResponse<String> handle(BCSHttpResponse paramBCSHttpResponse)
/*    */   {
/* 15 */     BaiduBCSResponse localBaiduBCSResponse = parseResponseMetadata(paramBCSHttpResponse);
/* 16 */     localBaiduBCSResponse.setResult(getResponseContentByStr(paramBCSHttpResponse));
/* 17 */     return localBaiduBCSResponse;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.handler.StringResponseHandler
 * JD-Core Version:    0.6.2
 */