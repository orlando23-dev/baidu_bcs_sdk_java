/*    */ package com.baidu.inf.iis.bcs.model;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class DownloadObject
/*    */ {
/*    */   private String object;
/*    */   private String bucket;
/* 19 */   private ObjectMetadata objectMetadata = new ObjectMetadata();
/*    */   private InputStream content;
/*    */ 
/*    */   public String getBucket()
/*    */   {
/* 23 */     return this.bucket;
/*    */   }
/*    */ 
/*    */   public InputStream getContent() {
/* 27 */     return this.content;
/*    */   }
/*    */ 
/*    */   public String getObject() {
/* 31 */     return this.object;
/*    */   }
/*    */ 
/*    */   public ObjectMetadata getObjectMetadata() {
/* 35 */     return this.objectMetadata;
/*    */   }
/*    */ 
/*    */   public void setBucket(String paramString) {
/* 39 */     this.bucket = paramString;
/*    */   }
/*    */ 
/*    */   public void setContent(InputStream paramInputStream) {
/* 43 */     this.content = paramInputStream;
/*    */   }
/*    */ 
/*    */   public void setObject(String paramString) {
/* 47 */     this.object = paramString;
/*    */   }
/*    */ 
/*    */   public void setObjectMetadata(ObjectMetadata paramObjectMetadata) {
/* 51 */     this.objectMetadata = paramObjectMetadata;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.model.DownloadObject
 * JD-Core Version:    0.6.2
 */