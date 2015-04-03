/*     */ package com.baidu.inf.iis.bcs.http;
/*     */ 
/*     */ import com.baidu.inf.iis.bcs.model.BCSClientException;
/*     */ import java.io.IOException;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.Socket;
/*     */ import java.net.UnknownHostException;
/*     */ import java.security.KeyManagementException;
/*     */ import java.security.KeyStore;
/*     */ import java.security.KeyStoreException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.UnrecoverableKeyException;
/*     */ import java.security.cert.CertificateException;
/*     */ import java.security.cert.X509Certificate;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.SSLSocket;
/*     */ import javax.net.ssl.TrustManager;
/*     */ import javax.net.ssl.X509TrustManager;
/*     */ import org.apache.http.HttpHost;
/*     */ import org.apache.http.HttpVersion;
/*     */ import org.apache.http.auth.AuthScope;
/*     */ import org.apache.http.auth.NTCredentials;
/*     */ import org.apache.http.client.CredentialsProvider;
/*     */ import org.apache.http.client.HttpClient;
/*     */ import org.apache.http.conn.ConnectTimeoutException;
/*     */ import org.apache.http.conn.params.ConnManagerParams;
/*     */ import org.apache.http.conn.params.ConnPerRouteBean;
/*     */ import org.apache.http.conn.scheme.LayeredSchemeSocketFactory;
/*     */ import org.apache.http.conn.scheme.PlainSocketFactory;
/*     */ import org.apache.http.conn.scheme.Scheme;
/*     */ import org.apache.http.conn.scheme.SchemeRegistry;
/*     */ import org.apache.http.conn.scheme.SchemeSocketFactory;
/*     */ import org.apache.http.impl.client.DefaultHttpClient;
/*     */ import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
/*     */ import org.apache.http.params.BasicHttpParams;
/*     */ import org.apache.http.params.HttpConnectionParams;
/*     */ import org.apache.http.params.HttpParams;
/*     */ import org.apache.http.params.HttpProtocolParams;
/*     */ 
/*     */ public class HttpClientFactory
/*     */ {
/*     */   public HttpClient createHttpClient(ClientConfiguration paramClientConfiguration)
/*     */   {
/*  53 */     BasicHttpParams localBasicHttpParams = new BasicHttpParams();
/*  54 */     HttpProtocolParams.setUserAgent(localBasicHttpParams, paramClientConfiguration.getUserAgent());
/*  55 */     HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
/*  56 */     HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, paramClientConfiguration.getConnectionTimeout());
/*  57 */     HttpConnectionParams.setSoTimeout(localBasicHttpParams, paramClientConfiguration.getSocketTimeout());
/*  58 */     HttpConnectionParams.setStaleCheckingEnabled(localBasicHttpParams, true);
/*  59 */     HttpConnectionParams.setTcpNoDelay(localBasicHttpParams, true);
/*     */ 
/*  61 */     int i = paramClientConfiguration.getSocketBufferSizeHints()[0];
/*  62 */     int j = paramClientConfiguration.getSocketBufferSizeHints()[1];
/*  63 */     if ((i > 0) || (j > 0)) {
/*  64 */       HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, Math.max(i, j));
/*     */     }
/*     */ 
/*  67 */     SchemeRegistry localSchemeRegistry = new SchemeRegistry();
/*     */ 
/*  69 */     localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
/*     */     try
/*     */     {
/*  72 */       KeyStore localKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
/*  73 */       localKeyStore.load(null, null);
/*  74 */       localSchemeRegistry.register(new Scheme("https", new TrustAllSSLSocketFactory(localKeyStore), 443));
/*     */     } catch (Exception localException) {
/*  76 */       throw new BCSClientException("Can not enable ssl.", localException);
/*     */     }
/*     */ 
/*  82 */     ConnManagerParams.setMaxTotalConnections(localBasicHttpParams, paramClientConfiguration.getMaxConnections());
/*  83 */     ConnManagerParams.setMaxConnectionsPerRoute(localBasicHttpParams, new ConnPerRouteBean(paramClientConfiguration.getMaxConnectionsPerRoute()));
/*  84 */     ThreadSafeClientConnManager localThreadSafeClientConnManager = new ThreadSafeClientConnManager(localBasicHttpParams, localSchemeRegistry);
/*     */ 
/*  89 */     DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localThreadSafeClientConnManager, localBasicHttpParams);
/*     */ 
/*  95 */     String str1 = paramClientConfiguration.getProxyHost();
/*  96 */     int k = paramClientConfiguration.getProxyPort();
/*  97 */     if ((str1 != null) && (k > 0)) {
/*  98 */       HttpHost localHttpHost = new HttpHost(str1, k);
/*  99 */       localDefaultHttpClient.getParams().setParameter("http.route.default-proxy", localHttpHost);
/*     */ 
/* 101 */       String str2 = paramClientConfiguration.getProxyUsername();
/* 102 */       String str3 = paramClientConfiguration.getProxyPassword();
/* 103 */       String str4 = paramClientConfiguration.getProxyDomain();
/* 104 */       String str5 = paramClientConfiguration.getProxyWorkstation();
/*     */ 
/* 106 */       if ((str2 != null) && (str3 != null)) {
/* 107 */         localDefaultHttpClient.getCredentialsProvider().setCredentials(new AuthScope(str1, k), new NTCredentials(str2, str3, str5, str4));
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 112 */     return localDefaultHttpClient;
/*     */   }
/*     */ 
/*     */   private class TrustAllSSLSocketFactory extends org.apache.http.conn.ssl.SSLSocketFactory
/*     */   {
/* 188 */     SSLContext sslContext = SSLContext.getInstance("TLS");
/*     */ 
/*     */     public TrustAllSSLSocketFactory(KeyStore arg2) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
/*     */     {
/* 192 */       super(arg2);
/*     */ 
/* 194 */       HttpClientFactory.TrustingX509TrustManager localTrustingX509TrustManager = new HttpClientFactory.TrustingX509TrustManager();
/*     */ 
/* 196 */       this.sslContext.init(null, new TrustManager[] { localTrustingX509TrustManager }, null);
/*     */     }
/*     */ 
/*     */     public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean) throws IOException, UnknownHostException
/*     */     {
/* 201 */       return this.sslContext.getSocketFactory().createSocket(paramSocket, paramString, paramInt, paramBoolean);
/*     */     }
/*     */ 
/*     */     public Socket createSocket() throws IOException
/*     */     {
/* 206 */       return this.sslContext.getSocketFactory().createSocket();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class TrustingX509TrustManager
/*     */     implements X509TrustManager
/*     */   {
/* 172 */     private static final X509Certificate[] X509_CERTIFICATES = new X509Certificate[0];
/*     */ 
/*     */     public X509Certificate[] getAcceptedIssuers() {
/* 175 */       return X509_CERTIFICATES;
/*     */     }
/*     */ 
/*     */     public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
/*     */       throws CertificateException
/*     */     {
/*     */     }
/*     */ 
/*     */     public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
/*     */       throws CertificateException
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class TrustingSocketFactory
/*     */     implements SchemeSocketFactory, LayeredSchemeSocketFactory
/*     */   {
/* 122 */     private SSLContext sslcontext = null;
/*     */ 
/*     */     private static SSLContext createSSLContext() throws IOException {
/*     */       try {
/* 126 */         SSLContext localSSLContext = SSLContext.getInstance("TLS");
/* 127 */         localSSLContext.init(null, new TrustManager[] { new HttpClientFactory.TrustingX509TrustManager() }, null);
/* 128 */         return localSSLContext;
/*     */       } catch (Exception localException) {
/* 130 */         throw new IOException(localException.getMessage());
/*     */       }
/*     */     }
/*     */ 
/*     */     private SSLContext getSSLContext() throws IOException {
/* 135 */       if (this.sslcontext == null)
/* 136 */         this.sslcontext = createSSLContext();
/* 137 */       return this.sslcontext;
/*     */     }
/*     */ 
/*     */     public Socket createSocket(HttpParams paramHttpParams) throws IOException {
/* 141 */       return getSSLContext().getSocketFactory().createSocket();
/*     */     }
/*     */ 
/*     */     public Socket connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress1, InetSocketAddress paramInetSocketAddress2, HttpParams paramHttpParams) throws IOException, UnknownHostException, ConnectTimeoutException
/*     */     {
/* 146 */       int i = HttpConnectionParams.getConnectionTimeout(paramHttpParams);
/* 147 */       int j = HttpConnectionParams.getSoTimeout(paramHttpParams);
/*     */ 
/* 149 */       SSLSocket localSSLSocket = (SSLSocket)(paramSocket != null ? paramSocket : createSocket(paramHttpParams));
/* 150 */       if (paramInetSocketAddress2 != null) {
/* 151 */         localSSLSocket.bind(paramInetSocketAddress2);
/*     */       }
/* 153 */       localSSLSocket.connect(paramInetSocketAddress1, i);
/* 154 */       localSSLSocket.setSoTimeout(j);
/* 155 */       return localSSLSocket;
/*     */     }
/*     */ 
/*     */     public boolean isSecure(Socket paramSocket) throws IllegalArgumentException {
/* 159 */       return true;
/*     */     }
/*     */ 
/*     */     public Socket createLayeredSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean) throws IOException, UnknownHostException {
/* 163 */       return getSSLContext().getSocketFactory().createSocket();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.http.HttpClientFactory
 * JD-Core Version:    0.6.2
 */