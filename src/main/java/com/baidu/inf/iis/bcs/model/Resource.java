/*    */ package com.baidu.inf.iis.bcs.model;
/*    */ 
/*    */ public class Resource
/*    */ {
/*    */   private String bucket;
/*    */   private String object;
/*    */ 
/*    */   public Resource(String paramString1, String paramString2)
/*    */   {
/* 20 */     this.bucket = paramString1;
/* 21 */     this.object = paramString2;
/*    */   }
/*    */ 
/*    */   public String getBucket() {
/* 25 */     return this.bucket;
/*    */   }
/*    */ 
/*    */   public String getObject() {
/* 29 */     return this.object;
/*    */   }
/*    */ 
/*    */   public void setBucket(String paramString) {
/* 33 */     this.bucket = paramString;
/*    */   }
/*    */ 
/*    */   public void setObject(String paramString) {
/* 37 */     this.object = paramString;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.model.Resource
 * JD-Core Version:    0.6.2
 */