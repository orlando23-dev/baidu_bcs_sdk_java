/*    */ package com.baidu.inf.iis.bcs.request;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.http.HttpMethodName;
/*    */ import com.baidu.inf.iis.bcs.model.ObjectMetadata;
/*    */ import com.baidu.inf.iis.bcs.model.Resource;
/*    */ 
/*    */ public class CopyObjectRequest extends BaiduBCSRequest
/*    */ {
/*    */   private ObjectMetadata destMetadata;
/*    */   private Resource source;
/*    */   private Resource dest;
/*    */   private String sourceEtag;
/*    */   private String sourceDirective;
/*    */ 
/*    */   public CopyObjectRequest(Resource paramResource1, Resource paramResource2)
/*    */   {
/* 20 */     super(paramResource2.getBucket(), paramResource2.getObject(), HttpMethodName.PUT);
/* 21 */     this.source = paramResource1;
/* 22 */     this.dest = paramResource2;
/*    */   }
/*    */ 
/*    */   public CopyObjectRequest(Resource paramResource1, Resource paramResource2, ObjectMetadata paramObjectMetadata) {
/* 26 */     super(paramResource2.getBucket(), paramResource2.getObject(), HttpMethodName.PUT);
/* 27 */     this.destMetadata = paramObjectMetadata;
/* 28 */     this.source = paramResource1;
/* 29 */     this.dest = paramResource2;
/*    */   }
/*    */ 
/*    */   public Resource getDest() {
/* 33 */     return this.dest;
/*    */   }
/*    */ 
/*    */   public ObjectMetadata getDestMetadata() {
/* 37 */     return this.destMetadata;
/*    */   }
/*    */ 
/*    */   public Resource getSource() {
/* 41 */     return this.source;
/*    */   }
/*    */ 
/*    */   public void setDest(Resource paramResource) {
/* 45 */     this.dest = paramResource;
/*    */   }
/*    */ 
/*    */   public void setDestMetadata(ObjectMetadata paramObjectMetadata) {
/* 49 */     this.destMetadata = paramObjectMetadata;
/*    */   }
/*    */ 
/*    */   public void setSource(Resource paramResource) {
/* 53 */     this.source = paramResource;
/*    */   }
/*    */ 
/*    */   public String getSourceEtag()
/*    */   {
/* 60 */     return this.sourceEtag;
/*    */   }
/*    */ 
/*    */   public void setSourceEtag(String paramString)
/*    */   {
/* 68 */     this.sourceEtag = paramString;
/*    */   }
/*    */ 
/*    */   public String getSourceDirective()
/*    */   {
/* 75 */     return this.sourceDirective;
/*    */   }
/*    */ 
/*    */   public void setSourceDirective(String paramString)
/*    */   {
/* 88 */     this.sourceDirective = paramString;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.request.CopyObjectRequest
 * JD-Core Version:    0.6.2
 */