/*    */ package com.baidu.inf.iis.bcs.request;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.http.HttpMethodName;
/*    */ import com.baidu.inf.iis.bcs.model.ObjectMetadata;
/*    */ import com.baidu.inf.iis.bcs.model.X_BS_ACL;
/*    */ import java.io.File;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class PutObjectRequest extends BaiduBCSRequest
/*    */ {
/* 17 */   private File file = null;
/* 18 */   private InputStream objectContent = null;
/* 19 */   private ObjectMetadata metadata = null;
/* 20 */   private X_BS_ACL acl = null;
/*    */ 
/*    */   public PutObjectRequest(String paramString1, String paramString2, File paramFile) {
/* 23 */     super(paramString1, paramString2, HttpMethodName.PUT);
/* 24 */     this.file = paramFile;
/*    */   }
/*    */ 
/*    */   public PutObjectRequest(String paramString1, String paramString2, InputStream paramInputStream, ObjectMetadata paramObjectMetadata) {
/* 28 */     super(paramString1, paramString2, HttpMethodName.PUT);
/* 29 */     this.objectContent = paramInputStream;
/* 30 */     this.metadata = paramObjectMetadata;
/*    */   }
/*    */ 
/*    */   public X_BS_ACL getAcl() {
/* 34 */     return this.acl;
/*    */   }
/*    */ 
/*    */   public File getFile() {
/* 38 */     return this.file;
/*    */   }
/*    */ 
/*    */   public ObjectMetadata getMetadata() {
/* 42 */     return this.metadata;
/*    */   }
/*    */ 
/*    */   public InputStream getObjectContent() {
/* 46 */     return this.objectContent;
/*    */   }
/*    */ 
/*    */   public void setAcl(X_BS_ACL paramX_BS_ACL) {
/* 50 */     this.acl = paramX_BS_ACL;
/*    */   }
/*    */ 
/*    */   public void setFile(File paramFile) {
/* 54 */     this.file = paramFile;
/*    */   }
/*    */ 
/*    */   public void setMetadata(ObjectMetadata paramObjectMetadata) {
/* 58 */     this.metadata = paramObjectMetadata;
/*    */   }
/*    */ 
/*    */   public void setObjectContent(InputStream paramInputStream) {
/* 62 */     this.objectContent = paramInputStream;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.request.PutObjectRequest
 * JD-Core Version:    0.6.2
 */