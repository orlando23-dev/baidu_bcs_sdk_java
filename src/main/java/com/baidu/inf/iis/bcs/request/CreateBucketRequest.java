/*    */ package com.baidu.inf.iis.bcs.request;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.http.HttpMethodName;
/*    */ import com.baidu.inf.iis.bcs.model.X_BS_ACL;
/*    */ 
/*    */ public class CreateBucketRequest extends BaiduBCSRequest
/*    */ {
/* 13 */   private X_BS_ACL acl = null;
/*    */ 
/*    */   public CreateBucketRequest(String paramString) {
/* 16 */     super(paramString, HttpMethodName.PUT);
/*    */   }
/*    */ 
/*    */   public CreateBucketRequest(String paramString, X_BS_ACL paramX_BS_ACL) {
/* 20 */     super(paramString, HttpMethodName.PUT);
/* 21 */     this.acl = paramX_BS_ACL;
/*    */   }
/*    */ 
/*    */   public X_BS_ACL getAcl() {
/* 25 */     return this.acl;
/*    */   }
/*    */ 
/*    */   public void setAcl(X_BS_ACL paramX_BS_ACL) {
/* 29 */     this.acl = paramX_BS_ACL;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.request.CreateBucketRequest
 * JD-Core Version:    0.6.2
 */