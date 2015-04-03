/*    */ package com.baidu.inf.iis.bcs.auth;
/*    */ 
/*    */ public class BCSSignCondition
/*    */ {
/*  9 */   private Long time = Long.valueOf(0L);
/* 10 */   private String ip = "";
/* 11 */   private Long size = Long.valueOf(0L);
/*    */ 
/*    */   public String getIp() {
/* 14 */     return this.ip;
/*    */   }
/*    */ 
/*    */   public Long getSize() {
/* 18 */     return this.size;
/*    */   }
/*    */ 
/*    */   public Long getTime() {
/* 22 */     return this.time;
/*    */   }
/*    */ 
/*    */   public void setIp(String paramString) {
/* 26 */     this.ip = paramString;
/*    */   }
/*    */ 
/*    */   public void setSize(Long paramLong) {
/* 30 */     this.size = paramLong;
/*    */   }
/*    */ 
/*    */   public void setTime(Long paramLong) {
/* 34 */     this.time = paramLong;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.auth.BCSSignCondition
 * JD-Core Version:    0.6.2
 */