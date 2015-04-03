/*    */ package com.baidu.inf.iis.bcs.handler;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.http.BCSHttpResponse;
/*    */ import com.baidu.inf.iis.bcs.model.ObjectMetadata;
/*    */ import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;
/*    */ 
/*    */ public class ObjectMetadataResponseHandler extends HttpResponseHandler<ObjectMetadata>
/*    */ {
/*    */   public BaiduBCSResponse<ObjectMetadata> handle(BCSHttpResponse paramBCSHttpResponse)
/*    */   {
/* 16 */     ObjectMetadata localObjectMetadata = new ObjectMetadata();
/* 17 */     populateObjectMetadata(paramBCSHttpResponse, localObjectMetadata);
/*    */ 
/* 19 */     BaiduBCSResponse localBaiduBCSResponse = parseResponseMetadata(paramBCSHttpResponse);
/* 20 */     localBaiduBCSResponse.setResult(localObjectMetadata);
/* 21 */     return localBaiduBCSResponse;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.handler.ObjectMetadataResponseHandler
 * JD-Core Version:    0.6.2
 */