/*    */ package com.baidu.inf.iis.bcs.request;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.http.HttpMethodName;
/*    */ import com.baidu.inf.iis.bcs.utils.StringUtils;
/*    */ 
/*    */ public abstract class BaiduBCSRequest
/*    */ {
/* 13 */   protected String bucket = null;
/* 14 */   protected String object = null;
/* 15 */   protected HttpMethodName httpMethod = null;
/*    */ 
/*    */   public BaiduBCSRequest(String paramString, HttpMethodName paramHttpMethodName) {
/* 18 */     this.bucket = paramString;
/* 19 */     this.object = "/";
/* 20 */     this.httpMethod = paramHttpMethodName;
/*    */   }
/*    */ 
/*    */   public BaiduBCSRequest(String paramString1, String paramString2, HttpMethodName paramHttpMethodName) {
/* 24 */     this.bucket = paramString1;
/* 25 */     this.object = StringUtils.trimSlash(paramString2);
/* 26 */     this.httpMethod = paramHttpMethodName;
/*    */   }
/*    */ 
/*    */   public String getBucket() {
/* 30 */     return this.bucket;
/*    */   }
/*    */ 
/*    */   public HttpMethodName getHttpMethod() {
/* 34 */     return this.httpMethod;
/*    */   }
/*    */ 
/*    */   public String getObject() {
/* 38 */     return this.object;
/*    */   }
/*    */ 
/*    */   public void setBucket(String paramString) {
/* 42 */     this.bucket = paramString;
/*    */   }
/*    */ 
/*    */   public void setHttpMethod(HttpMethodName paramHttpMethodName) {
/* 46 */     this.httpMethod = paramHttpMethodName;
/*    */   }
/*    */ 
/*    */   public void setObject(String paramString) {
/* 50 */     this.object = StringUtils.trimSlash(paramString);
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.request.BaiduBCSRequest
 * JD-Core Version:    0.6.2
 */