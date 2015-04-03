/*    */ package com.baidu.inf.iis.bcs.handler;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.http.BCSHttpResponse;
/*    */ import com.baidu.inf.iis.bcs.model.Empty;
/*    */ import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;
/*    */ 
/*    */ public class VoidResponseHandler extends HttpResponseHandler<Empty>
/*    */ {
/*    */   public BaiduBCSResponse<Empty> handle(BCSHttpResponse paramBCSHttpResponse)
/*    */   {
/* 16 */     BaiduBCSResponse localBaiduBCSResponse = parseResponseMetadata(paramBCSHttpResponse);
/* 17 */     localBaiduBCSResponse.setResult(new Empty());
/* 18 */     return localBaiduBCSResponse;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.handler.VoidResponseHandler
 * JD-Core Version:    0.6.2
 */