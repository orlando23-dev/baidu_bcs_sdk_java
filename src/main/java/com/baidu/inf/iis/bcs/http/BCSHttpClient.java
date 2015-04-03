/*     */ package com.baidu.inf.iis.bcs.http;
/*     */ 
/*     */ import com.baidu.inf.iis.bcs.handler.ErrorResponseHandler;
/*     */ import com.baidu.inf.iis.bcs.handler.HttpResponseHandler;
/*     */ import com.baidu.inf.iis.bcs.model.BCSClientException;
/*     */ import com.baidu.inf.iis.bcs.model.BCSServiceException;
/*     */ import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;

/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.SocketException;
/*     */ import java.net.SocketTimeoutException;
/*     */ import java.util.concurrent.TimeUnit;

/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.http.Header;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.NoHttpResponseException;
/*     */ import org.apache.http.StatusLine;
/*     */ import org.apache.http.client.ClientProtocolException;
/*     */ import org.apache.http.client.HttpClient;
/*     */ import org.apache.http.client.methods.HttpRequestBase;
/*     */ import org.apache.http.conn.ClientConnectionManager;
/*     */ 
/*     */ public class BCSHttpClient
/*     */ {
/*     */   private static final int MAX_BACKOFF_IN_MILLISECONDS = 20000;
/*  34 */   private static final Log log = LogFactory.getLog(BCSHttpClient.class);
/*     */ 
/*  36 */   private HttpRequestFactory httpRequestFactory = new HttpRequestFactory();
/*  37 */   private HttpClientFactory httpClientFactory = new HttpClientFactory();
/*     */   private final ClientConfiguration config;
/*     */   private HttpClient httpClient;
/*  41 */   private ErrorResponseHandler errorResponseHandler = new ErrorResponseHandler();
/*     */ 
/*     */   public BCSHttpClient(ClientConfiguration paramClientConfiguration) {
/*  44 */     this.config = paramClientConfiguration;
/*  45 */     this.httpClient = this.httpClientFactory.createHttpClient(this.config);
/*     */   }
/*     */ 
/*     */   private BCSHttpResponse createBCSHttpResponse(HttpResponse paramHttpResponse) throws IllegalStateException, IOException {
/*  49 */     BCSHttpResponse localBCSHttpResponse = new BCSHttpResponse();
/*  50 */     if (null != paramHttpResponse.getEntity()) {
/*  51 */       localBCSHttpResponse.setContent(paramHttpResponse.getEntity().getContent());
/*     */     }
/*  53 */     localBCSHttpResponse.setStatusCode(paramHttpResponse.getStatusLine().getStatusCode());
/*  54 */     localBCSHttpResponse.setStatusText(paramHttpResponse.getStatusLine().getReasonPhrase());
/*     */ 
/*  56 */     for (Header localHeader : paramHttpResponse.getAllHeaders()) {
/*  57 */       localBCSHttpResponse.addHeader(localHeader.getName(), localHeader.getValue());
/*     */     }
/*  59 */     return localBCSHttpResponse;
/*     */   }
/*     */ 
/*     */   public <T> BaiduBCSResponse<T> execute(BCSHttpRequest paramBCSHttpRequest, HttpResponseHandler<T> paramHttpResponseHandler) throws Throwable {
/*  63 */     HttpRequestBase localHttpRequestBase = this.httpRequestFactory.createHttpRequestBase(this.config, paramBCSHttpRequest);
/*     */ 
/*  72 */     boolean bool = false;
/*     */ 
/*  77 */     this.httpClient.getConnectionManager().closeIdleConnections(30L, TimeUnit.SECONDS);
/*     */ 
/*  79 */     int i = 0;
/*     */     while (true) {
/*  81 */       HttpResponse localHttpResponse = null;
/*     */       try {
/*  83 */         pauseExponentially(i);
/*  84 */         i++;
/*  85 */         localHttpResponse = this.httpClient.execute(localHttpRequestBase);
/*  86 */         log.info("Send Request Finish: " + localHttpResponse.getStatusLine() + ", " + localHttpRequestBase.getURI());
/*  87 */         if (isRequestSuccessful(localHttpResponse))
/*     */         {
/*  89 */           bool = paramHttpResponseHandler.isNeedsConnectionLeftOpen();
/*  90 */           return handleHttpResponse(paramBCSHttpRequest, localHttpResponse, paramHttpResponseHandler);
/*     */         }
/*     */ 
/*  93 */         Object localObject1 = (BCSServiceException)handleErrorHttpResponse(paramBCSHttpRequest, localHttpResponse, this.errorResponseHandler).getResult();
/*  94 */         if (!shouldRetry((Exception)localObject1, i))
/*  95 */           throw ((Throwable)localObject1);
/*     */       }
/*     */       catch (ClientProtocolException localClientProtocolException)
/*     */       {
/*  99 */         log.warn("Unable to execute HTTP request: " + localClientProtocolException.getMessage());
/* 100 */         if (!shouldRetry(localClientProtocolException, i))
/* 101 */           throw new BCSClientException("Send to server failed: " + localClientProtocolException.getMessage(), localClientProtocolException);
/*     */       }
/*     */       catch (IOException localIOException) {
/* 104 */         log.warn("Unable to execute HTTP request: " + localIOException.getMessage());
/* 105 */         if (!shouldRetry(localIOException, i)) {
/* 106 */           throw new BCSClientException("Send to server failed: " + localIOException.getMessage(), localIOException);
/*     */         }
/*     */ 
/*     */       }
/*     */       finally
/*     */       {
/* 116 */         if (!bool)
/*     */           try {
/* 118 */             localHttpResponse.getEntity().getContent().close();
/*     */           }
/*     */           catch (Throwable localThrowable5)
/*     */           {
/*     */           }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private BaiduBCSResponse<BCSServiceException> handleErrorHttpResponse(BCSHttpRequest paramBCSHttpRequest, HttpResponse paramHttpResponse, HttpResponseHandler<BCSServiceException> paramHttpResponseHandler) throws IllegalStateException, IOException
/*     */   {
/* 129 */     BCSHttpResponse localBCSHttpResponse = createBCSHttpResponse(paramHttpResponse);
/* 130 */     localBCSHttpResponse.setRequest(paramBCSHttpRequest);
/* 131 */     return paramHttpResponseHandler.handle(localBCSHttpResponse);
/*     */   }
/*     */ 
/*     */   private <T> BaiduBCSResponse<T> handleHttpResponse(BCSHttpRequest paramBCSHttpRequest, HttpResponse paramHttpResponse, HttpResponseHandler<T> paramHttpResponseHandler) throws IllegalStateException, IOException
/*     */   {
/* 136 */     BCSHttpResponse localBCSHttpResponse = createBCSHttpResponse(paramHttpResponse);
/* 137 */     localBCSHttpResponse.setRequest(paramBCSHttpRequest);
/* 138 */     return paramHttpResponseHandler.handle(localBCSHttpResponse);
/*     */   }
/*     */ 
/*     */   private boolean isRequestSuccessful(HttpResponse paramHttpResponse) {
/* 142 */     int i = paramHttpResponse.getStatusLine().getStatusCode();
/* 143 */     return i / 100 == 2;
/*     */   }
/*     */ 
/*     */   public HttpRequestFactory getHttpRequestFactory() {
/* 147 */     return this.httpRequestFactory;
/*     */   }
/*     */ 
/*     */   public void setHttpRequestFactory(HttpRequestFactory paramHttpRequestFactory) {
/* 151 */     this.httpRequestFactory = paramHttpRequestFactory;
/*     */   }
/*     */ 
/*     */   public ClientConfiguration getConfig() {
/* 155 */     return this.config;
/*     */   }
/*     */ 
/*     */   public boolean shouldRetry(Exception paramException, int paramInt) {
/* 159 */     if (paramInt > this.config.getMaxErrorRetry()) {
/* 160 */       log.warn("Max error retry is[" + this.config.getMaxErrorRetry() + "]. Stop retry.");
/* 161 */       return false;
/*     */     }
/*     */ 
/* 164 */     if (((paramException instanceof NoHttpResponseException)) || ((paramException instanceof SocketException)) || ((paramException instanceof SocketTimeoutException))) {
/* 165 */       log.debug("Retrying on " + paramException.getClass().getName() + ": " + paramException.getMessage());
/* 166 */       return true;
/*     */     }
/*     */ 
/* 169 */     if ((paramException instanceof BCSServiceException)) {
/* 170 */       BCSServiceException localBCSServiceException = (BCSServiceException)paramException;
/*     */ 
/* 174 */       if ((localBCSServiceException.getBcsErrorCode() == 500) || (localBCSServiceException.getBcsErrorCode() == 503))
/*     */       {
/* 176 */         log.debug("Retrying on server response[" + localBCSServiceException.getBcsErrorCode() + "]");
/* 177 */         return true;
/*     */       }
/*     */     }
/* 180 */     log.warn("Should not retry.");
/* 181 */     return false;
/*     */   }
/*     */ 
/*     */   private void pauseExponentially(int paramInt)
/*     */   {
/* 193 */     if (0 == paramInt) {
/* 194 */       return;
/*     */     }
/* 196 */     long l1 = 300L;
/* 197 */     long l2 = (long) (Math.pow(2.0D, paramInt) * l1);
/*     */ 
/* 199 */     l2 = Math.min(l2, 20000L);
/* 200 */     log.debug("Retriable error detected, will retry in " + l2 + "ms, attempt number: " + paramInt);
/*     */     try
/*     */     {
/* 203 */       Thread.sleep(l2);
/*     */     } catch (InterruptedException localInterruptedException) {
/* 205 */       throw new BCSClientException(localInterruptedException.getMessage(), localInterruptedException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void shutdown() {
/* 210 */     this.httpClient.getConnectionManager().shutdown();
/*     */   }
/*     */ 
/*     */   protected void finalize() throws Throwable
/*     */   {
/* 215 */     shutdown();
/* 216 */     super.finalize();
/*     */   }
/*     */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.http.BCSHttpClient
 * JD-Core Version:    0.6.2
 */