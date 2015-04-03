/*    */ package com.baidu.inf.iis.bcs.model;
/*    */ 
/*    */ public class ObjectSummary
/*    */ {
/*    */   private String name;
/*    */   private Long size;
/*    */   private Long lastModifiedTime;
/*    */   private String versionKey;
/*    */   private boolean isSuperfile;
/*    */   private String parentDir;
/*    */   private boolean isDir;
/*    */ 
/*    */   public Long getLastModifiedTime()
/*    */   {
/* 23 */     return this.lastModifiedTime;
/*    */   }
/*    */ 
/*    */   public String getName() {
/* 27 */     return this.name;
/*    */   }
/*    */ 
/*    */   public String getParentDir() {
/* 31 */     return this.parentDir;
/*    */   }
/*    */ 
/*    */   public Long getSize() {
/* 35 */     return this.size;
/*    */   }
/*    */ 
/*    */   public String getVersionKey() {
/* 39 */     return this.versionKey;
/*    */   }
/*    */ 
/*    */   public boolean isDir() {
/* 43 */     return this.isDir;
/*    */   }
/*    */ 
/*    */   public boolean isSuperfile() {
/* 47 */     return this.isSuperfile;
/*    */   }
/*    */ 
/*    */   public void setIsDir(boolean paramBoolean) {
/* 51 */     this.isDir = paramBoolean;
/*    */   }
/*    */ 
/*    */   public void setLastModifiedTime(Long paramLong) {
/* 55 */     this.lastModifiedTime = paramLong;
/*    */   }
/*    */ 
/*    */   public void setName(String paramString) {
/* 59 */     this.name = paramString;
/*    */   }
/*    */ 
/*    */   public void setParentDir(String paramString) {
/* 63 */     this.parentDir = paramString;
/*    */   }
/*    */ 
/*    */   public void setSize(Long paramLong) {
/* 67 */     this.size = paramLong;
/*    */   }
/*    */ 
/*    */   public void setSuperfile(boolean paramBoolean) {
/* 71 */     this.isSuperfile = paramBoolean;
/*    */   }
/*    */ 
/*    */   public void setVersionKey(String paramString) {
/* 75 */     this.versionKey = paramString;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 80 */     return "ObjectSummary [name=" + this.name + ", size=" + this.size + ", lastModifiedTime=" + this.lastModifiedTime + ", versionKey=" + this.versionKey + ", isSuperfile=" + this.isSuperfile + ", parentDir=" + this.parentDir + ", isDir=" + this.isDir + "]";
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.model.ObjectSummary
 * JD-Core Version:    0.6.2
 */