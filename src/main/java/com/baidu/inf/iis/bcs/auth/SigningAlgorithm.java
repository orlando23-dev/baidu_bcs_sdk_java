/*    */ package com.baidu.inf.iis.bcs.auth;
/*    */ 
/*    */ public enum SigningAlgorithm
/*    */ {
/*  9 */   HmacSHA1("HmacSHA1"), HmacSHA256("HmacSHA256");
/*    */ 
/*    */   private final String name;
/*    */ 
/* 13 */   private SigningAlgorithm(String paramString) { this.name = paramString; }
/*    */ 
/*    */ 
/*    */   public String toString()
/*    */   {
/* 18 */     return this.name;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.auth.SigningAlgorithm
 * JD-Core Version:    0.6.2
 */