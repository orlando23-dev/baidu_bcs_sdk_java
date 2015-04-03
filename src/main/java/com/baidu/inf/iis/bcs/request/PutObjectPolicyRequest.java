/*    */ package com.baidu.inf.iis.bcs.request;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.http.HttpMethodName;
/*    */ import com.baidu.inf.iis.bcs.model.X_BS_ACL;
/*    */ import com.baidu.inf.iis.bcs.policy.Policy;
/*    */ 
/*    */ public class PutObjectPolicyRequest extends BaiduBCSRequest
/*    */ {
/*    */   private Policy policy;
/*    */   private X_BS_ACL acl;
/*    */ 
/*    */   public PutObjectPolicyRequest(String paramString1, String paramString2, Policy paramPolicy)
/*    */   {
/* 18 */     super(paramString1, paramString2, HttpMethodName.PUT);
/* 19 */     this.policy = paramPolicy;
/*    */   }
/*    */ 
/*    */   public PutObjectPolicyRequest(String paramString1, String paramString2, X_BS_ACL paramX_BS_ACL) {
/* 23 */     super(paramString1, paramString2, HttpMethodName.PUT);
/* 24 */     this.acl = paramX_BS_ACL;
/*    */   }
/*    */ 
/*    */   public X_BS_ACL getAcl() {
/* 28 */     return this.acl;
/*    */   }
/*    */ 
/*    */   public Policy getPolicy() {
/* 32 */     return this.policy;
/*    */   }
/*    */ 
/*    */   public void setAcl(X_BS_ACL paramX_BS_ACL) {
/* 36 */     this.acl = paramX_BS_ACL;
/*    */   }
/*    */ 
/*    */   public void setPolicy(Policy paramPolicy) {
/* 40 */     this.policy = paramPolicy;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.request.PutObjectPolicyRequest
 * JD-Core Version:    0.6.2
 */