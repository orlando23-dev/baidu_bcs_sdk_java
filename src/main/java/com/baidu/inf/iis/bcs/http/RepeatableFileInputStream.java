/*     */ package com.baidu.inf.iis.bcs.http;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class RepeatableFileInputStream extends InputStream
/*     */ {
/*  18 */   private static final Log log = LogFactory.getLog(RepeatableFileInputStream.class);
/*     */ 
/*  20 */   private File file = null;
/*  21 */   private FileInputStream fis = null;
/*  22 */   private long bytesReadPastMarkPoint = 0L;
/*  23 */   private long markPoint = 0L;
/*     */ 
/*     */   public RepeatableFileInputStream(File paramFile) throws FileNotFoundException {
/*  26 */     if (paramFile == null) {
/*  27 */       throw new IllegalArgumentException("File cannot be null");
/*     */     }
/*  29 */     this.fis = new FileInputStream(paramFile);
/*  30 */     this.file = paramFile;
/*     */   }
/*     */ 
/*     */   public int available() throws IOException
/*     */   {
/*  35 */     return this.fis.available();
/*     */   }
/*     */ 
/*     */   public void close() throws IOException
/*     */   {
/*  40 */     this.fis.close();
/*     */   }
/*     */ 
/*     */   public InputStream getWrappedInputStream() {
/*  44 */     return this.fis;
/*     */   }
/*     */ 
/*     */   public void mark(int paramInt)
/*     */   {
/*  49 */     this.markPoint += this.bytesReadPastMarkPoint;
/*  50 */     this.bytesReadPastMarkPoint = 0L;
/*  51 */     if (log.isDebugEnabled())
/*  52 */       log.debug("Input stream marked at " + this.markPoint + " bytes");
/*     */   }
/*     */ 
/*     */   public boolean markSupported()
/*     */   {
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */   public int read() throws IOException
/*     */   {
/*  63 */     int i = this.fis.read();
/*  64 */     if (i != -1) {
/*  65 */       this.bytesReadPastMarkPoint += 1L;
/*  66 */       return i;
/*     */     }
/*  68 */     return -1;
/*     */   }
/*     */ 
/*     */   public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2) throws IOException
/*     */   {
/*  73 */     int i = this.fis.read(paramArrayOfByte, paramInt1, paramInt2);
/*  74 */     this.bytesReadPastMarkPoint += i;
/*  75 */     return i;
/*     */   }
/*     */ 
/*     */   public void reset() throws IOException
/*     */   {
/*  80 */     this.fis.close();
/*  81 */     this.fis = new FileInputStream(this.file);
/*     */ 
/*  83 */     long l1 = 0L;
/*  84 */     long l2 = this.markPoint;
/*  85 */     while (l2 > 0L) {
/*  86 */       l1 = this.fis.skip(l2);
/*  87 */       l2 -= l1;
/*     */     }
/*     */ 
/*  90 */     if (log.isDebugEnabled()) {
/*  91 */       log.debug("Reset to mark point " + this.markPoint + " after returning " + this.bytesReadPastMarkPoint + " bytes");
/*     */     }
/*     */ 
/*  94 */     this.bytesReadPastMarkPoint = 0L;
/*     */   }
/*     */ 
/*     */   public long skip(long paramLong) throws IOException
/*     */   {
/*  99 */     long l = this.fis.skip(paramLong);
/* 100 */     this.bytesReadPastMarkPoint += l;
/* 101 */     return l;
/*     */   }
/*     */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.http.RepeatableFileInputStream
 * JD-Core Version:    0.6.2
 */