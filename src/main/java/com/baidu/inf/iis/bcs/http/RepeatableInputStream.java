/*     */ package com.baidu.inf.iis.bcs.http;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class RepeatableInputStream extends InputStream
/*     */ {
/*  15 */   private static final Log log = LogFactory.getLog(RepeatableInputStream.class);
/*     */ 
/*  17 */   private InputStream is = null;
/*  18 */   private int bufferSize = 0;
/*  19 */   private int bufferOffset = 0;
/*  20 */   private long bytesReadPastMark = 0L;
/*  21 */   private byte[] buffer = null;
/*     */ 
/*     */   public RepeatableInputStream(InputStream paramInputStream, int paramInt) {
/*  24 */     if (paramInputStream == null) {
/*  25 */       throw new IllegalArgumentException("InputStream cannot be null");
/*     */     }
/*     */ 
/*  28 */     this.is = paramInputStream;
/*  29 */     this.bufferSize = paramInt;
/*  30 */     this.buffer = new byte[this.bufferSize];
/*     */ 
/*  32 */     if (log.isDebugEnabled())
/*  33 */       log.debug("Underlying input stream will be repeatable up to " + this.buffer.length + " bytes");
/*     */   }
/*     */ 
/*     */   public int available()
/*     */     throws IOException
/*     */   {
/*  39 */     return this.is.available();
/*     */   }
/*     */ 
/*     */   public void close() throws IOException
/*     */   {
/*  44 */     this.is.close();
/*     */   }
/*     */ 
/*     */   public InputStream getWrappedInputStream() {
/*  48 */     return this.is;
/*     */   }
/*     */ 
/*     */   public synchronized void mark(int paramInt)
/*     */   {
/*  53 */     if (log.isDebugEnabled()) {
/*  54 */       log.debug("Input stream marked at " + this.bytesReadPastMark + " bytes");
/*     */     }
/*  56 */     if ((this.bytesReadPastMark <= this.bufferSize) && (this.buffer != null)) {
/*  57 */       byte[] arrayOfByte = new byte[this.bufferSize];
/*  58 */       System.arraycopy(this.buffer, this.bufferOffset, arrayOfByte, 0, (int)(this.bytesReadPastMark - this.bufferOffset));
/*  59 */       this.buffer = arrayOfByte;
/*  60 */       this.bytesReadPastMark -= this.bufferOffset;
/*  61 */       this.bufferOffset = 0;
/*     */     } else {
/*  63 */       this.bufferOffset = 0;
/*  64 */       this.bytesReadPastMark = 0L;
/*  65 */       this.buffer = new byte[this.bufferSize];
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean markSupported()
/*     */   {
/*  71 */     return true;
/*     */   }
/*     */ 
/*     */   public int read() throws IOException
/*     */   {
/*  76 */     byte[] arrayOfByte = new byte[1];
/*  77 */     int i = read(arrayOfByte);
/*  78 */     if (i != -1) {
/*  79 */       return arrayOfByte[0];
/*     */     }
/*  81 */     return i;
/*     */   }
/*     */ 
/*     */   public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2) throws IOException
/*     */   {
/*  86 */     byte[] arrayOfByte = new byte[paramInt2];
/*     */ 
/*  88 */     if ((this.bufferOffset < this.bytesReadPastMark) && (this.buffer != null)) {
/*  89 */       int i = arrayOfByte.length;
/*  90 */       if (this.bufferOffset + i > this.bytesReadPastMark) {
/*  91 */         i = (int)this.bytesReadPastMark - this.bufferOffset;
/*     */       }
/*     */ 
/*  94 */       System.arraycopy(this.buffer, this.bufferOffset, paramArrayOfByte, paramInt1, i);
/*  95 */       this.bufferOffset += i;
/*  96 */       return i;
/*     */     }
/*     */ 
/*  99 */     int i = this.is.read(arrayOfByte);
/*     */ 
/* 101 */     if (i <= 0) {
/* 102 */       return i;
/*     */     }
/*     */ 
/* 105 */     if (this.bytesReadPastMark + i <= this.bufferSize) {
/* 106 */       System.arraycopy(arrayOfByte, 0, this.buffer, (int)this.bytesReadPastMark, i);
/* 107 */       this.bufferOffset += i;
/* 108 */     } else if (this.buffer != null) {
/* 109 */       if (log.isDebugEnabled()) {
/* 110 */         log.debug("Buffer size " + this.bufferSize + " has been exceeded and the input stream " + "will not be repeatable until the next mark. Freeing buffer memory");
/*     */       }
/*     */ 
/* 114 */       this.buffer = null;
/*     */     }
/*     */ 
/* 117 */     System.arraycopy(arrayOfByte, 0, paramArrayOfByte, paramInt1, i);
/* 118 */     this.bytesReadPastMark += i;
/*     */ 
/* 120 */     return i;
/*     */   }
/*     */ 
/*     */   public void reset() throws IOException
/*     */   {
/* 125 */     if (this.bytesReadPastMark <= this.bufferSize) {
/* 126 */       if (log.isDebugEnabled()) {
/* 127 */         log.debug("Reset after reading " + this.bytesReadPastMark + " bytes.");
/*     */       }
/* 129 */       this.bufferOffset = 0;
/*     */     } else {
/* 131 */       throw new IOException("Input stream cannot be reset as " + this.bytesReadPastMark + " bytes have been written, exceeding the available buffer size of " + this.bufferSize);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.http.RepeatableInputStream
 * JD-Core Version:    0.6.2
 */