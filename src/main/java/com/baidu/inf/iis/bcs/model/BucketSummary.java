/*    */ package com.baidu.inf.iis.bcs.model;
/*    */ 
/*    */ public class BucketSummary
/*    */ {
/*    */   private String bucket;
/*    */   private Long cdatatime;
/*    */   private Long usedCapacity;
/*    */   private Long totalCapacity;
/*    */ 
/*    */   public BucketSummary(String paramString)
/*    */   {
/* 21 */     this.bucket = paramString;
/*    */   }
/*    */ 
/*    */   public String getBucket() {
/* 25 */     return this.bucket;
/*    */   }
/*    */ 
/*    */   public Long getCdatatime() {
/* 29 */     return this.cdatatime;
/*    */   }
/*    */ 
/*    */   public Long getTotalCapacity() {
/* 33 */     return this.totalCapacity;
/*    */   }
/*    */ 
/*    */   public Long getUsedCapacity() {
/* 37 */     return this.usedCapacity;
/*    */   }
/*    */ 
/*    */   public void setBucket(String paramString) {
/* 41 */     this.bucket = paramString;
/*    */   }
/*    */ 
/*    */   public void setCdatatime(Long paramLong) {
/* 45 */     this.cdatatime = paramLong;
/*    */   }
/*    */ 
/*    */   public void setTotalCapacity(Long paramLong) {
/* 49 */     this.totalCapacity = paramLong;
/*    */   }
/*    */ 
/*    */   public void setUsedCapacity(Long paramLong) {
/* 53 */     this.usedCapacity = paramLong;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 58 */     return "BCSBucket [bucket=" + this.bucket + ", cdatatime=" + this.cdatatime + ", usedCapacity=" + this.usedCapacity + ", totalCapacity=" + this.totalCapacity + "]";
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.model.BucketSummary
 * JD-Core Version:    0.6.2
 */