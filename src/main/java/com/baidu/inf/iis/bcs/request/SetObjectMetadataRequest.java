/*    */ package com.baidu.inf.iis.bcs.request;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.http.HttpMethodName;
/*    */ import com.baidu.inf.iis.bcs.model.BCSServiceException;
/*    */ import com.baidu.inf.iis.bcs.model.ObjectMetadata;
/*    */ 
/*    */ public class SetObjectMetadataRequest extends BaiduBCSRequest
/*    */ {
/*    */   private ObjectMetadata metadata;
/*    */ 
/*    */   public SetObjectMetadataRequest(String paramString1, String paramString2, ObjectMetadata paramObjectMetadata)
/*    */   {
/* 16 */     super(paramString1, paramString2, HttpMethodName.PUT);
/* 17 */     if (null == paramObjectMetadata) {
/* 18 */       throw new BCSServiceException("Metadata should not be null.");
/*    */     }
/* 20 */     this.metadata = paramObjectMetadata;
/*    */   }
/*    */ 
/*    */   public ObjectMetadata getMetadata() {
/* 24 */     return this.metadata;
/*    */   }
/*    */ 
/*    */   public void setMetadata(ObjectMetadata paramObjectMetadata) {
/* 28 */     this.metadata = paramObjectMetadata;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.request.SetObjectMetadataRequest
 * JD-Core Version:    0.6.2
 */