/*    */ package com.baidu.inf.iis.bcs.model;
/*    */ 
/*    */ public class SuperfileSubObject
/*    */ {
/*    */   private String bucket;
/*    */   private String object;
/*    */   private String etag;
/*    */ 
/*    */   public SuperfileSubObject(String paramString1, String paramString2, String paramString3)
/*    */   {
/* 20 */     this.bucket = paramString1;
/* 21 */     this.object = paramString2;
/* 22 */     this.etag = paramString3;
/*    */   }
/*    */ 
/*    */   public String getBucket() {
/* 26 */     return this.bucket;
/*    */   }
/*    */ 
/*    */   public String getEtag() {
/* 30 */     return this.etag;
/*    */   }
/*    */ 
/*    */   public String getObject() {
/* 34 */     return this.object;
/*    */   }
/*    */ 
/*    */   public void setBucket(String paramString) {
/* 38 */     this.bucket = paramString;
/*    */   }
/*    */ 
/*    */   public void setEtag(String paramString) {
/* 42 */     this.etag = paramString;
/*    */   }
/*    */ 
/*    */   public void setObject(String paramString) {
/* 46 */     this.object = paramString;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.model.SuperfileSubObject
 * JD-Core Version:    0.6.2
 */