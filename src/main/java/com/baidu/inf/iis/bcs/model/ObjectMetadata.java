/*     */ package com.baidu.inf.iis.bcs.model;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ObjectMetadata
/*     */ {
/*  20 */   private Map<String, String> userMetadata = new HashMap();
/*  21 */   private Map<String, Object> metadata = new HashMap();
/*     */ 
/*     */   public void addUserMetadata(String paramString1, String paramString2) {
/*  24 */     this.userMetadata.put(paramString1, paramString2);
/*     */   }
/*     */ 
/*     */   public String getCacheControl() {
/*  28 */     return (String)this.metadata.get("Cache-Control");
/*     */   }
/*     */ 
/*     */   public String getContentDisposition() {
/*  32 */     return (String)this.metadata.get("Content-Disposition");
/*     */   }
/*     */ 
/*     */   public String getContentEncoding() {
/*  36 */     return (String)this.metadata.get("Content-Encoding");
/*     */   }
/*     */ 
/*     */   public long getContentLength() {
/*  40 */     if ((null == this.metadata.get("Content-Length")) || (null == Long.decode((String)this.metadata.get("Content-Length")))) {
/*  41 */       return -1L;
/*     */     }
/*  43 */     return Long.decode((String)this.metadata.get("Content-Length")).longValue();
/*     */   }
/*     */ 
/*     */   public Date getLastModified() {
/*  47 */     return (Date)this.metadata.get("Last-Modified");
/*     */   }
/*     */ 
/*     */   public void setLastModified(Date paramDate) {
/*  51 */     this.metadata.put("Last-Modified", paramDate);
/*     */   }
/*     */ 
/*     */   public String getContentMD5() {
/*  55 */     return (String)this.metadata.get("Content-MD5");
/*     */   }
/*     */ 
/*     */   public String getContentType() {
/*  59 */     return (String)this.metadata.get("Content-Type");
/*     */   }
/*     */ 
/*     */   public String getETag() {
/*  63 */     return (String)this.metadata.get("ETag");
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getRawMetadata() {
/*  67 */     return Collections.unmodifiableMap(this.metadata);
/*     */   }
/*     */ 
/*     */   public Map<String, String> getUserMetadata() {
/*  71 */     return this.userMetadata;
/*     */   }
/*     */ 
/*     */   public String getVersionId() {
/*  75 */     return (String)this.metadata.get("x-bs-version");
/*     */   }
/*     */ 
/*     */   public void setCacheControl(String paramString) {
/*  79 */     this.metadata.put("Cache-Control", paramString);
/*     */   }
/*     */ 
/*     */   public void setContentDisposition(String paramString) {
/*  83 */     this.metadata.put("Content-Disposition", paramString);
/*     */   }
/*     */ 
/*     */   public void setContentEncoding(String paramString) {
/*  87 */     this.metadata.put("Content-Encoding", paramString);
/*     */   }
/*     */ 
/*     */   public void setContentLength(long paramLong) {
/*  91 */     this.metadata.put("Content-Length", String.valueOf(paramLong));
/*     */   }
/*     */ 
/*     */   public void setContentMD5(String paramString) {
/*  95 */     this.metadata.put("Content-MD5", paramString);
/*     */   }
/*     */ 
/*     */   public void setContentType(String paramString) {
/*  99 */     this.metadata.put("Content-Type", paramString);
/*     */   }
/*     */ 
/*     */   public void setHeader(String paramString, Object paramObject) {
/* 103 */     this.metadata.put(paramString, paramObject);
/*     */   }
/*     */ 
/*     */   public void setUserMetadata(Map<String, String> paramMap) {
/* 107 */     this.userMetadata = paramMap;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 112 */     return "ObjectMetadata [userMetadata=" + this.userMetadata + ", metadata=" + this.metadata + "]";
/*     */   }
/*     */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.model.ObjectMetadata
 * JD-Core Version:    0.6.2
 */