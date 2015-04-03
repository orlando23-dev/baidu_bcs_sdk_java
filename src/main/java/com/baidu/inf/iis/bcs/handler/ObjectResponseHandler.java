/*    */ package com.baidu.inf.iis.bcs.handler;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.http.BCSHttpRequest;
/*    */ import com.baidu.inf.iis.bcs.http.BCSHttpResponse;
/*    */ import com.baidu.inf.iis.bcs.model.DownloadObject;
/*    */ import com.baidu.inf.iis.bcs.model.ObjectMetadata;
/*    */ import com.baidu.inf.iis.bcs.request.BaiduBCSRequest;
/*    */ import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;
/*    */ 
/*    */ public class ObjectResponseHandler extends HttpResponseHandler<DownloadObject>
/*    */ {
/*    */   public BaiduBCSResponse<DownloadObject> handle(BCSHttpResponse paramBCSHttpResponse)
/*    */   {
/* 17 */     DownloadObject localDownloadObject = new DownloadObject();
/* 18 */     localDownloadObject.setBucket(paramBCSHttpResponse.getRequest().getOriginalRequest().getBucket());
/* 19 */     localDownloadObject.setObject(paramBCSHttpResponse.getRequest().getOriginalRequest().getObject());
/*    */ 
/* 22 */     ObjectMetadata localObjectMetadata = new ObjectMetadata();
/* 23 */     populateObjectMetadata(paramBCSHttpResponse, localObjectMetadata);
/* 24 */     localDownloadObject.setObjectMetadata(localObjectMetadata);
/*    */ 
/* 26 */     BaiduBCSResponse localBaiduBCSResponse = parseResponseMetadata(paramBCSHttpResponse);
/*    */ 
/* 28 */     localDownloadObject.setContent(paramBCSHttpResponse.getContent());
/*    */ 
/* 30 */     localBaiduBCSResponse.setResult(localDownloadObject);
/* 31 */     return localBaiduBCSResponse;
/*    */   }
/*    */ 
/*    */   public boolean isNeedsConnectionLeftOpen()
/*    */   {
/* 43 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.handler.ObjectResponseHandler
 * JD-Core Version:    0.6.2
 */