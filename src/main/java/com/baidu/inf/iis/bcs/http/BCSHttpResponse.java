/*    */ package com.baidu.inf.iis.bcs.http;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class BCSHttpResponse
/*    */ {
/*    */   private BCSHttpRequest request;
/*    */   private String statusText;
/*    */   private int statusCode;
/*    */   private InputStream content;
/* 17 */   private Map<String, String> headers = new HashMap();
/*    */ 
/*    */   public void addHeader(String paramString1, String paramString2) {
/* 20 */     this.headers.put(paramString1, paramString2);
/*    */   }
/*    */ 
/*    */   public InputStream getContent() {
/* 24 */     return this.content;
/*    */   }
/*    */ 
/*    */   public Map<String, String> getHeaders() {
/* 28 */     return this.headers;
/*    */   }
/*    */ 
/*    */   public BCSHttpRequest getRequest() {
/* 32 */     return this.request;
/*    */   }
/*    */ 
/*    */   public int getStatusCode() {
/* 36 */     return this.statusCode;
/*    */   }
/*    */ 
/*    */   public String getStatusText() {
/* 40 */     return this.statusText;
/*    */   }
/*    */ 
/*    */   public void setContent(InputStream paramInputStream) {
/* 44 */     this.content = paramInputStream;
/*    */   }
/*    */ 
/*    */   public void setRequest(BCSHttpRequest paramBCSHttpRequest) {
/* 48 */     this.request = paramBCSHttpRequest;
/*    */   }
/*    */ 
/*    */   public void setStatusCode(int paramInt) {
/* 52 */     this.statusCode = paramInt;
/*    */   }
/*    */ 
/*    */   public void setStatusText(String paramString) {
/* 56 */     this.statusText = paramString;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.http.BCSHttpResponse
 * JD-Core Version:    0.6.2
 */