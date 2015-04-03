/*     */ package com.baidu.inf.iis.bcs.http;
/*     */ 
/*     */ public class ClientConfiguration
/*     */ {
/*  10 */   private String userAgent = "baidu-bcs-java-sdk/1";
/*     */ 
/*  12 */   private int maxErrorRetry = 3;
/*     */ 
/*  14 */   private Protocol protocol = Protocol.HTTP;
/*     */ 
/*  16 */   private String proxyHost = null;
/*     */ 
/*  18 */   private int proxyPort = -1;
/*     */ 
/*  20 */   private String proxyUsername = null;
/*     */ 
/*  22 */   private String proxyPassword = null;
/*     */ 
/*  24 */   private String proxyDomain = null;
/*     */ 
/*  26 */   private String proxyWorkstation = null;
/*     */ 
/*  28 */   private int maxConnections = 200;
/*     */ 
/*  30 */   private int maxConnectionsPerRoute = 100;
/*     */ 
/*  32 */   private int socketTimeout = 50000;
/*     */ 
/*  34 */   private int connectionTimeout = 50000;
/*     */ 
/*  36 */   private int socketSendBufferSizeHint = 0;
/*     */ 
/*  38 */   private int socketReceiveBufferSizeHint = 0;
/*     */ 
/*     */   public int getConnectionTimeout() {
/*  41 */     return this.connectionTimeout;
/*     */   }
/*     */ 
/*     */   public int getMaxConnections() {
/*  45 */     return this.maxConnections;
/*     */   }
/*     */ 
/*     */   public int getMaxConnectionsPerRoute() {
/*  49 */     return this.maxConnectionsPerRoute;
/*     */   }
/*     */ 
/*     */   public int getMaxErrorRetry() {
/*  53 */     return this.maxErrorRetry;
/*     */   }
/*     */ 
/*     */   public Protocol getProtocol() {
/*  57 */     return this.protocol;
/*     */   }
/*     */ 
/*     */   public String getProxyDomain() {
/*  61 */     return this.proxyDomain;
/*     */   }
/*     */ 
/*     */   public String getProxyHost() {
/*  65 */     return this.proxyHost;
/*     */   }
/*     */ 
/*     */   public String getProxyPassword() {
/*  69 */     return this.proxyPassword;
/*     */   }
/*     */ 
/*     */   public int getProxyPort() {
/*  73 */     return this.proxyPort;
/*     */   }
/*     */ 
/*     */   public String getProxyUsername() {
/*  77 */     return this.proxyUsername;
/*     */   }
/*     */ 
/*     */   public String getProxyWorkstation() {
/*  81 */     return this.proxyWorkstation;
/*     */   }
/*     */ 
/*     */   public int[] getSocketBufferSizeHints() {
/*  85 */     return new int[] { this.socketSendBufferSizeHint, this.socketReceiveBufferSizeHint };
/*     */   }
/*     */ 
/*     */   public int getSocketTimeout() {
/*  89 */     return this.socketTimeout;
/*     */   }
/*     */ 
/*     */   public String getUserAgent() {
/*  93 */     return this.userAgent;
/*     */   }
/*     */ 
/*     */   public void setUserAgent(String paramString) {
/*  97 */     this.userAgent = paramString;
/*     */   }
/*     */ 
/*     */   public void setConnectionTimeout(int paramInt) {
/* 101 */     this.connectionTimeout = paramInt;
/*     */   }
/*     */ 
/*     */   public void setMaxConnections(int paramInt) {
/* 105 */     this.maxConnections = paramInt;
/*     */   }
/*     */ 
/*     */   public void setMaxConnectionsPerRoute(int paramInt) {
/* 109 */     this.maxConnectionsPerRoute = paramInt;
/*     */   }
/*     */ 
/*     */   public void setMaxErrorRetry(int paramInt) {
/* 113 */     this.maxErrorRetry = paramInt;
/*     */   }
/*     */ 
/*     */   public void setProtocol(Protocol paramProtocol) {
/* 117 */     this.protocol = paramProtocol;
/*     */   }
/*     */ 
/*     */   public void setProxyDomain(String paramString) {
/* 121 */     this.proxyDomain = paramString;
/*     */   }
/*     */ 
/*     */   public void setProxyHost(String paramString) {
/* 125 */     this.proxyHost = paramString;
/*     */   }
/*     */ 
/*     */   public void setProxyPassword(String paramString) {
/* 129 */     this.proxyPassword = paramString;
/*     */   }
/*     */ 
/*     */   public void setProxyPort(int paramInt) {
/* 133 */     this.proxyPort = paramInt;
/*     */   }
/*     */ 
/*     */   public void setProxyUsername(String paramString) {
/* 137 */     this.proxyUsername = paramString;
/*     */   }
/*     */ 
/*     */   public void setProxyWorkstation(String paramString) {
/* 141 */     this.proxyWorkstation = paramString;
/*     */   }
/*     */ 
/*     */   public void setSocketBufferSizeHints(int paramInt1, int paramInt2) {
/* 145 */     this.socketSendBufferSizeHint = paramInt1;
/* 146 */     this.socketReceiveBufferSizeHint = paramInt2;
/*     */   }
/*     */ 
/*     */   public void setSocketTimeout(int paramInt) {
/* 150 */     this.socketTimeout = paramInt;
/*     */   }
/*     */ 
/*     */   public ClientConfiguration withConnectionTimeout(int paramInt) {
/* 154 */     setConnectionTimeout(paramInt);
/* 155 */     return this;
/*     */   }
/*     */ 
/*     */   public ClientConfiguration withMaxConnections(int paramInt) {
/* 159 */     setMaxConnections(paramInt);
/* 160 */     return this;
/*     */   }
/*     */ 
/*     */   public ClientConfiguration withMaxConnectionsPerRoute(int paramInt) {
/* 164 */     setMaxConnectionsPerRoute(paramInt);
/* 165 */     return this;
/*     */   }
/*     */ 
/*     */   public ClientConfiguration withMaxErrorRetry(int paramInt) {
/* 169 */     setMaxErrorRetry(paramInt);
/* 170 */     return this;
/*     */   }
/*     */ 
/*     */   public ClientConfiguration withProtocol(Protocol paramProtocol) {
/* 174 */     setProtocol(paramProtocol);
/* 175 */     return this;
/*     */   }
/*     */ 
/*     */   public ClientConfiguration withProxyDomain(String paramString) {
/* 179 */     setProxyDomain(paramString);
/* 180 */     return this;
/*     */   }
/*     */ 
/*     */   public ClientConfiguration withProxyHost(String paramString) {
/* 184 */     setProxyHost(paramString);
/* 185 */     return this;
/*     */   }
/*     */ 
/*     */   public ClientConfiguration withProxyPassword(String paramString) {
/* 189 */     setProxyPassword(paramString);
/* 190 */     return this;
/*     */   }
/*     */ 
/*     */   public ClientConfiguration withProxyPort(int paramInt) {
/* 194 */     setProxyPort(paramInt);
/* 195 */     return this;
/*     */   }
/*     */ 
/*     */   public ClientConfiguration withProxyUsername(String paramString) {
/* 199 */     setProxyUsername(paramString);
/* 200 */     return this;
/*     */   }
/*     */ 
/*     */   public ClientConfiguration withProxyWorkstation(String paramString) {
/* 204 */     setProxyWorkstation(paramString);
/* 205 */     return this;
/*     */   }
/*     */ 
/*     */   public ClientConfiguration withSocketBufferSizeHints(int paramInt1, int paramInt2) {
/* 209 */     setSocketBufferSizeHints(paramInt1, paramInt2);
/* 210 */     return this;
/*     */   }
/*     */ 
/*     */   public ClientConfiguration withSocketTimeout(int paramInt) {
/* 214 */     setSocketTimeout(paramInt);
/* 215 */     return this;
/*     */   }
/*     */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.http.ClientConfiguration
 * JD-Core Version:    0.6.2
 */