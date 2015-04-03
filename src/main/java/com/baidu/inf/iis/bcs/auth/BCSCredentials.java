/*    */ package com.baidu.inf.iis.bcs.auth;
/*    */ 
/*    */ public class BCSCredentials
/*    */ {
/*    */   private String accessKey;
/*    */   private String secretKey;
/*    */ 
/*    */   public BCSCredentials(String paramString1, String paramString2)
/*    */   {
/* 17 */     this.accessKey = paramString1;
/* 18 */     this.secretKey = paramString2;
/*    */   }
/*    */ 
/*    */   public String getAccessKey() {
/* 22 */     return this.accessKey;
/*    */   }
/*    */ 
/*    */   public String getSecretKey() {
/* 26 */     return this.secretKey;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.auth.BCSCredentials
 * JD-Core Version:    0.6.2
 */