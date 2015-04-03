/*     */ package com.baidu.inf.iis.bcs.utils;
/*     */ 
/*     */ import com.baidu.inf.iis.bcs.http.BCSHttpRequest;
/*     */ import com.baidu.inf.iis.bcs.model.BCSClientException;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLEncoder;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.text.ParseException;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.codec.binary.Base64;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class ServiceUtils
/*     */ {
/*  31 */   private static final Log log = LogFactory.getLog(ServiceUtils.class);
/*     */ 
/*  33 */   protected static final DateUtils dateUtils = new DateUtils();
/*     */ 
/*     */   public static byte[] computeMD5Hash(byte[] paramArrayOfByte) throws NoSuchAlgorithmException, IOException {
/*  36 */     return computeMD5Hash(new ByteArrayInputStream(paramArrayOfByte));
/*     */   }
/*     */ 
/*     */   public static byte[] computeMD5Hash(InputStream paramInputStream) throws NoSuchAlgorithmException, IOException {
/*  40 */     BufferedInputStream localBufferedInputStream = new BufferedInputStream(paramInputStream);
/*     */     try {
/*  42 */       MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
/*  43 */       byte[] arrayOfByte1 = new byte[16384];
/*  44 */       int i = -1;
/*  45 */       while ((i = localBufferedInputStream.read(arrayOfByte1, 0, arrayOfByte1.length)) != -1) {
/*  46 */         localMessageDigest.update(arrayOfByte1, 0, i);
/*     */       }
/*  48 */       byte[] arrayOfByte2 = localMessageDigest.digest();
/*  49 */       return arrayOfByte2;
/*     */     } finally {
/*     */       try {
/*  52 */         localBufferedInputStream.close();
/*     */       } catch (Exception localException2) {
/*  54 */         log.warn("Unable to close input stream of hash candidate: " + localException2);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static URL convertRequestToUrl(BCSHttpRequest paramBCSHttpRequest) {
/*  60 */     String str1 = paramBCSHttpRequest.getEndpoint() + "/" + paramBCSHttpRequest.getResourcePath();
/*     */ 
/*  62 */     int i = 1;
/*  63 */     for (String str2 : paramBCSHttpRequest.getParameters().keySet()) {
/*  64 */       if (i != 0) {
/*  65 */         str1 = str1 + "?";
/*  66 */         i = 0;
/*     */       } else {
/*  68 */         str1 = str1 + "&";
/*     */       }
/*     */ 
/*  71 */       String str3 = (String)paramBCSHttpRequest.getParameters().get(str2);
/*  72 */       str1 = str1 + str2 + "=" + urlEncode(str3);
/*     */     }
/*     */     try {
/*  75 */       return new URL(str1);
/*     */     } catch (MalformedURLException localMalformedURLException) {
/*  77 */       throw new BCSClientException("Unable to convert request to well formed URL: " + localMalformedURLException.getMessage(), localMalformedURLException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String formatIso8601Date(Date paramDate)
/*     */   {
/*  83 */     return dateUtils.formatIso8601Date(paramDate);
/*     */   }
/*     */ 
/*     */   public static String formatRfc822Date(Date paramDate) {
/*  87 */     return dateUtils.formatRfc822Date(paramDate);
/*     */   }
/*     */ 
/*     */   public static byte[] fromBase64(String paramString) {
/*     */     byte[] arrayOfByte;
/*     */     try {
/*  93 */       arrayOfByte = Base64.decodeBase64(paramString.getBytes(Constants.DEFAULT_ENCODING));
/*     */     } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/*  95 */       log.warn("Tried to Base64-decode a String with the wrong encoding: ", localUnsupportedEncodingException);
/*  96 */       arrayOfByte = Base64.decodeBase64(paramString.getBytes());
/*     */     }
/*  98 */     return arrayOfByte;
/*     */   }
/*     */ 
/*     */   public static byte[] fromHex(String paramString) {
/* 102 */     byte[] arrayOfByte = new byte[(paramString.length() + 1) / 2];
/* 103 */     String str = null;
/* 104 */     int i = 0;
/* 105 */     int j = 0;
/* 106 */     while (i < paramString.length()) {
/* 107 */       str = paramString.substring(i, i + 2);
/* 108 */       i += 2;
/* 109 */       arrayOfByte[(j++)] = ((byte)Integer.parseInt(str, 16));
/*     */     }
/* 111 */     return arrayOfByte;
/*     */   }
/*     */ 
/*     */   public static String join(List<String> paramList) {
/* 115 */     String str1 = "";
/*     */ 
/* 117 */     int i = 1;
/* 118 */     for (String str2 : paramList) {
/* 119 */       if (i == 0) {
/* 120 */         str1 = str1 + ", ";
/*     */       }
/* 122 */       str1 = str1 + str2;
/* 123 */       i = 0;
/*     */     }
/*     */ 
/* 126 */     return str1;
/*     */   }
/*     */ 
/*     */   public static Date parseIso8601Date(String paramString) throws ParseException {
/* 130 */     return dateUtils.parseIso8601Date(paramString);
/*     */   }
/*     */ 
/*     */   public static Date parseRfc822Date(String paramString) throws ParseException {
/* 134 */     return dateUtils.parseRfc822Date(paramString);
/*     */   }
/*     */ 
/*     */   public static String removeQuotes(String paramString) {
/* 138 */     if (paramString == null) {
/* 139 */       return null;
/*     */     }
/*     */ 
/* 142 */     paramString = paramString.trim();
/* 143 */     if (paramString.startsWith("\"")) {
/* 144 */       paramString = paramString.substring(1);
/*     */     }
/* 146 */     if (paramString.endsWith("\"")) {
/* 147 */       paramString = paramString.substring(0, paramString.length() - 1);
/*     */     }
/*     */ 
/* 150 */     return paramString;
/*     */   }
/*     */ 
/*     */   public static String toBase64(byte[] paramArrayOfByte) {
/* 154 */     byte[] arrayOfByte = Base64.encodeBase64(paramArrayOfByte);
/* 155 */     return new String(arrayOfByte);
/*     */   }
/*     */ 
/*     */   public static byte[] toByteArray(String paramString) {
/*     */     try {
/* 160 */       return paramString.getBytes(Constants.DEFAULT_ENCODING);
/*     */     } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/* 162 */       log.warn("Encoding " + Constants.DEFAULT_ENCODING + " is not supported", localUnsupportedEncodingException);
/*     */     }
/* 164 */     return paramString.getBytes();
/*     */   }
/*     */ 
/*     */   public static String toHex(byte[] paramArrayOfByte) {
/* 168 */     StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length * 2);
/* 169 */     for (int i = 0; i < paramArrayOfByte.length; i++) {
/* 170 */       String str = Integer.toHexString(paramArrayOfByte[i]);
/* 171 */       if (str.length() == 1)
/* 172 */         localStringBuilder.append("0");
/* 173 */       else if (str.length() == 8) {
/* 174 */         str = str.substring(6);
/*     */       }
/* 176 */       localStringBuilder.append(str);
/*     */     }
/* 178 */     return localStringBuilder.toString().toLowerCase(Locale.getDefault());
/*     */   }
/*     */ 
/*     */   public static String urlEncode(String paramString) {
/* 182 */     if (paramString == null) {
/* 183 */       return null;
/*     */     }
/*     */     try
/*     */     {
/* 187 */       String str = URLEncoder.encode(paramString, Constants.DEFAULT_ENCODING);
/*     */ 
/* 189 */       return str.replaceAll("\\+", "%20");
/*     */     }
/*     */     catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/* 192 */       throw new BCSClientException("Unable to encode path: " + paramString, localUnsupportedEncodingException);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.utils.ServiceUtils
 * JD-Core Version:    0.6.2
 */