/*    */ package com.baidu.inf.iis.bcs.model;
/*    */ 
/*    */ public enum X_BS_ACL
/*    */ {
/* 18 */   Private("private"), PublicRead("public-read"), PublicWrite("public-write"), PublicReadWrite("public-read-write"), PublicControl("public-control");
/*    */ 
/*    */   private final String x_bs_acl;
/*    */ 
/*    */   private X_BS_ACL(String paramString) {
/* 23 */     this.x_bs_acl = paramString;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 28 */     return this.x_bs_acl;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.model.X_BS_ACL
 * JD-Core Version:    0.6.2
 */