/*     */ package com.baidu.inf.iis.bcs.http;
/*     */ 
/*     */ import com.baidu.inf.iis.bcs.request.BaiduBCSRequest;
/*     */ import java.io.InputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class DefaultBCSHttpRequest
/*     */   implements BCSHttpRequest
/*     */ {
/*     */   private String resourcePath;
/*  16 */   private Map<String, String> parameters = new HashMap();
/*  17 */   private Map<String, String> headers = new HashMap();
/*     */   private String endpoint;
/*     */   private String serviceName;
/*     */   private final BaiduBCSRequest originalRequest;
/*     */   private HttpMethodName httpMethod;
/*     */   private InputStream content;
/*     */ 
/*     */   public DefaultBCSHttpRequest()
/*     */   {
/*  25 */     this(null);
/*     */   }
/*     */ 
/*     */   public DefaultBCSHttpRequest(BaiduBCSRequest paramBaiduBCSRequest) {
/*  29 */     this.originalRequest = paramBaiduBCSRequest;
/*     */   }
/*     */ 
/*     */   public void addHeader(String paramString1, String paramString2) {
/*  33 */     this.headers.put(paramString1, paramString2);
/*     */   }
/*     */ 
/*     */   public void addParameter(String paramString1, String paramString2) {
/*  37 */     this.parameters.put(paramString1, paramString2);
/*     */   }
/*     */ 
/*     */   public InputStream getContent() {
/*  41 */     return this.content;
/*     */   }
/*     */ 
/*     */   public String getEndpoint() {
/*  45 */     return this.endpoint;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getHeaders() {
/*  49 */     return this.headers;
/*     */   }
/*     */ 
/*     */   public HttpMethodName getHttpMethod() {
/*  53 */     return this.httpMethod;
/*     */   }
/*     */ 
/*     */   public BaiduBCSRequest getOriginalRequest() {
/*  57 */     return this.originalRequest;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getParameters() {
/*  61 */     return this.parameters;
/*     */   }
/*     */ 
/*     */   public String getResourcePath() {
/*  65 */     return this.resourcePath;
/*     */   }
/*     */ 
/*     */   public String getServiceName() {
/*  69 */     return this.serviceName;
/*     */   }
/*     */ 
/*     */   public void setContent(InputStream paramInputStream) {
/*  73 */     this.content = paramInputStream;
/*     */   }
/*     */ 
/*     */   public void setEndpoint(String paramString) {
/*  77 */     this.endpoint = paramString;
/*     */   }
/*     */ 
/*     */   public void setHttpMethod(HttpMethodName paramHttpMethodName) {
/*  81 */     this.httpMethod = paramHttpMethodName;
/*     */   }
/*     */ 
/*     */   public void setResourcePath(String paramString) {
/*  85 */     this.resourcePath = paramString;
/*     */   }
/*     */ 
/*     */   public String toString() {
/*  89 */     StringBuilder localStringBuilder = new StringBuilder();
/*     */ 
/*  91 */     localStringBuilder.append(getHttpMethod().toString() + " ");
/*  92 */     localStringBuilder.append(getEndpoint().toString() + " ");
/*     */ 
/*  94 */     localStringBuilder.append("/" + (getResourcePath() != null ? getResourcePath() : "") + " ");
/*     */     Iterator localIterator;
/*     */     String str1;
/*     */     String str2;
/*  96 */     if (!getParameters().isEmpty()) {
/*  97 */       localStringBuilder.append("Parameters: (");
/*  98 */       for (localIterator = getParameters().keySet().iterator(); localIterator.hasNext(); ) { str1 = (String)localIterator.next();
/*  99 */         str2 = (String)getParameters().get(str1);
/* 100 */         localStringBuilder.append(str1 + ": " + str2 + ", ");
/*     */       }
/* 102 */       localStringBuilder.append(") ");
/*     */     }
/*     */ 
/* 105 */     if (!getHeaders().isEmpty()) {
/* 106 */       localStringBuilder.append("Headers: (");
/* 107 */       for (localIterator = getHeaders().keySet().iterator(); localIterator.hasNext(); ) { str1 = (String)localIterator.next();
/* 108 */         str2 = (String)getHeaders().get(str1);
/* 109 */         localStringBuilder.append(str1 + ": " + str2 + ", ");
/*     */       }
/* 111 */       localStringBuilder.append(") ");
/*     */     }
/*     */ 
/* 114 */     return localStringBuilder.toString();
/*     */   }
/*     */ 
/*     */   public BCSHttpRequest withParameter(String paramString1, String paramString2) {
/* 118 */     addParameter(paramString1, paramString2);
/* 119 */     return this;
/*     */   }
/*     */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.http.DefaultBCSHttpRequest
 * JD-Core Version:    0.6.2
 */