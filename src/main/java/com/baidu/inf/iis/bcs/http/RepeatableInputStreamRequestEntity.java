/*    */ package com.baidu.inf.iis.bcs.http;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.apache.http.entity.BasicHttpEntity;
/*    */ import org.apache.http.entity.InputStreamEntity;
/*    */ 
/*    */ class RepeatableInputStreamRequestEntity extends BasicHttpEntity
/*    */ {
/* 18 */   private boolean firstAttempt = true;
/*    */   private InputStreamEntity inputStreamRequestEntity;
/*    */   private InputStream content;
/* 21 */   private static final Log log = LogFactory.getLog(RepeatableInputStreamRequestEntity.class);
/*    */ 
/*    */   RepeatableInputStreamRequestEntity(BCSHttpRequest paramBCSHttpRequest) {
/* 24 */     setChunked(false);
/*    */ 
/* 26 */     long l = -1L;
/*    */     try {
/* 28 */       String str1 = (String)paramBCSHttpRequest.getHeaders().get("Content-Length");
/* 29 */       if (str1 != null)
/* 30 */         l = Long.parseLong(str1);
/*    */     }
/*    */     catch (NumberFormatException localNumberFormatException) {
/* 33 */       log.warn("Unable to parse content length from request.  Buffering contents in memory.");
/*    */     }
/*    */ 
/* 36 */     String str2 = (String)paramBCSHttpRequest.getHeaders().get("Content-Type");
/*    */ 
/* 38 */     this.inputStreamRequestEntity = new InputStreamEntity(paramBCSHttpRequest.getContent(), l);
/* 39 */     this.inputStreamRequestEntity.setContentType(str2);
/* 40 */     this.content = paramBCSHttpRequest.getContent();
/*    */ 
/* 42 */     setContent(this.content);
/* 43 */     setContentType(str2);
/* 44 */     setContentLength(l);
/*    */   }
/*    */ 
/*    */   public boolean isChunked()
/*    */   {
/* 49 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean isRepeatable()
/*    */   {
/* 54 */     return (this.content.markSupported()) || (this.inputStreamRequestEntity.isRepeatable());
/*    */   }
/*    */ 
/*    */   public void writeTo(OutputStream paramOutputStream) throws IOException
/*    */   {
/* 59 */     if ((!this.firstAttempt) && (isRepeatable())) {
/* 60 */       this.content.reset();
/*    */     }
/*    */ 
/* 63 */     this.firstAttempt = false;
/* 64 */     this.inputStreamRequestEntity.writeTo(paramOutputStream);
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.http.RepeatableInputStreamRequestEntity
 * JD-Core Version:    0.6.2
 */