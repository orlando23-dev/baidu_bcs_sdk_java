/*    */ package com.baidu.inf.iis.bcs.http;
/*    */ 
/*    */ import java.io.FilterInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.security.MessageDigest;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ 
/*    */ public class MD5DigestCalculatingInputStream extends FilterInputStream
/*    */ {
/*    */   private MessageDigest digest;
/*    */ 
/*    */   public MD5DigestCalculatingInputStream(InputStream paramInputStream)
/*    */     throws NoSuchAlgorithmException
/*    */   {
/* 18 */     super(paramInputStream);
/*    */ 
/* 20 */     this.digest = MessageDigest.getInstance("MD5");
/*    */   }
/*    */ 
/*    */   public byte[] getMd5Digest() {
/* 24 */     return this.digest.digest();
/*    */   }
/*    */ 
/*    */   public int read() throws IOException
/*    */   {
/* 29 */     int i = this.in.read();
/* 30 */     if (i != -1) {
/* 31 */       this.digest.update((byte)i);
/*    */     }
/* 33 */     return i;
/*    */   }
/*    */ 
/*    */   public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2) throws IOException
/*    */   {
/* 38 */     int i = this.in.read(paramArrayOfByte, paramInt1, paramInt2);
/* 39 */     if (i != -1) {
/* 40 */       this.digest.update(paramArrayOfByte, paramInt1, i);
/*    */     }
/* 42 */     return i;
/*    */   }
/*    */ 
/*    */   public synchronized void reset() throws IOException
/*    */   {
/*    */     try {
/* 48 */       this.digest = MessageDigest.getInstance("MD5");
/*    */     }
/*    */     catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
/*    */     }
/* 52 */     this.in.reset();
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.http.MD5DigestCalculatingInputStream
 * JD-Core Version:    0.6.2
 */