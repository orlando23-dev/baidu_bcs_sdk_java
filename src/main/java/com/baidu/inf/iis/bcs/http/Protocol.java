/*    */ package com.baidu.inf.iis.bcs.http;
/*    */ 
/*    */ public enum Protocol
/*    */ {
/*  9 */   HTTP("http"), HTTPS("https");
/*    */ 
/*    */   private final String protocol;
/*    */ 
/*    */   private Protocol(String paramString) {
/* 14 */     this.protocol = paramString;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 19 */     return this.protocol;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.http.Protocol
 * JD-Core Version:    0.6.2
 */