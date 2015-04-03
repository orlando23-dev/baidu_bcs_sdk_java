/*    */ package com.baidu.inf.iis.bcs.model;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ObjectListing
/*    */ {
/* 18 */   private List<ObjectSummary> objectSummaries = new ArrayList();
/*    */   private String bucket;
/*    */   private int objectTotal;
/*    */   private String prefix;
/*    */   private int start;
/*    */   private int limit;
/*    */ 
/*    */   public ObjectListing addObjectSummary(ObjectSummary paramObjectSummary)
/*    */   {
/* 26 */     this.objectSummaries.add(paramObjectSummary);
/* 27 */     return this;
/*    */   }
/*    */ 
/*    */   public String getBucket() {
/* 31 */     return this.bucket;
/*    */   }
/*    */ 
/*    */   public int getLimit() {
/* 35 */     return this.limit;
/*    */   }
/*    */ 
/*    */   public List<ObjectSummary> getObjectSummaries() {
/* 39 */     return this.objectSummaries;
/*    */   }
/*    */ 
/*    */   public int getObjectTotal() {
/* 43 */     return this.objectTotal;
/*    */   }
/*    */ 
/*    */   public String getPrefix() {
/* 47 */     return this.prefix;
/*    */   }
/*    */ 
/*    */   public int getStart() {
/* 51 */     return this.start;
/*    */   }
/*    */ 
/*    */   public void setBucket(String paramString) {
/* 55 */     this.bucket = paramString;
/*    */   }
/*    */ 
/*    */   public void setLimit(int paramInt) {
/* 59 */     this.limit = paramInt;
/*    */   }
/*    */ 
/*    */   public void setObjectSummaries(List<ObjectSummary> paramList) {
/* 63 */     this.objectSummaries = paramList;
/*    */   }
/*    */ 
/*    */   public void setObjectTotal(int paramInt) {
/* 67 */     this.objectTotal = paramInt;
/*    */   }
/*    */ 
/*    */   public void setPrefix(String paramString) {
/* 71 */     this.prefix = paramString;
/*    */   }
/*    */ 
/*    */   public void setStart(int paramInt) {
/* 75 */     this.start = paramInt;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.model.ObjectListing
 * JD-Core Version:    0.6.2
 */