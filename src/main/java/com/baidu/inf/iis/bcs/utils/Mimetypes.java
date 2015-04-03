/*    */ package com.baidu.inf.iis.bcs.utils;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.util.HashMap;
/*    */ import java.util.Set;
/*    */ import java.util.StringTokenizer;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class Mimetypes
/*    */ {
/* 20 */   private static final Log log = LogFactory.getLog(Mimetypes.class);
/*    */   public static final String MIMETYPE_XML = "application/xml";
/*    */   public static final String MIMETYPE_HTML = "text/html";
/*    */   public static final String MIMETYPE_OCTET_STREAM = "application/octet-stream";
/*    */   public static final String MIMETYPE_GZIP = "application/x-gzip";
/* 25 */   private static Mimetypes mimetypes = null;
/*    */ 
/* 52 */   private HashMap<String, String> extensionToMimetypeMap = new HashMap();
/*    */ 
/*    */   public static synchronized Mimetypes getInstance()
/*    */   {
/* 28 */     if (mimetypes != null) {
/* 29 */       return mimetypes;
/*    */     }
/*    */ 
/* 32 */     mimetypes = new Mimetypes();
/* 33 */     InputStream localInputStream = mimetypes.getClass().getResourceAsStream("/mime.types");
/* 34 */     if (localInputStream != null) {
/* 35 */       if (log.isDebugEnabled())
/* 36 */         log.info("Loading mime types from file in the classpath: mime.types");
/*    */       try
/*    */       {
/* 39 */         mimetypes.loadAndReplaceMimetypes(localInputStream);
/*    */       } catch (IOException localIOException) {
/* 41 */         if (log.isErrorEnabled())
/* 42 */           log.error("Failed to load mime types from file in the classpath: mime.types", localIOException);
/*    */       }
/*    */     }
/* 45 */     else if (log.isWarnEnabled()) {
/* 46 */       log.warn("Unable to find 'mime.types' file in classpath");
/*    */     }
/*    */ 
/* 49 */     return mimetypes;
/*    */   }
/*    */ 
/*    */   public String getMimetype(File paramFile)
/*    */   {
/* 55 */     return getMimetype(paramFile.getName());
/*    */   }
/*    */ 
/*    */   public String getMimetype(String paramString) {
/* 59 */     int i = paramString.lastIndexOf(".");
/* 60 */     if ((i > 0) && (i + 1 < paramString.length())) {
/* 61 */       String str1 = paramString.substring(i + 1);
/* 62 */       if (this.extensionToMimetypeMap.keySet().contains(str1)) {
/* 63 */         String str2 = (String)this.extensionToMimetypeMap.get(str1);
/* 64 */         if (log.isDebugEnabled()) {
/* 65 */           log.info("Recognised extension '" + str1 + "', mimetype is: '" + str2 + "'");
/*    */         }
/* 67 */         return str2;
/*    */       }
/* 69 */       if (log.isDebugEnabled()) {
/* 70 */         log.info("Extension '" + str1 + "' is unrecognized in mime type listing" + ", using default mime type: '" + "application/octet-stream" + "'");
/*    */       }
/*    */ 
/*    */     }
/* 74 */     else if (log.isDebugEnabled()) {
/* 75 */       log.info("File name has no extension, mime type cannot be recognised for: " + paramString);
/*    */     }
/*    */ 
/* 78 */     return "application/octet-stream";
/*    */   }
/*    */ 
/*    */   public void loadAndReplaceMimetypes(InputStream paramInputStream) throws IOException {
/* 82 */     BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
/* 83 */     String str1 = null;
/*    */ 
/* 85 */     while ((str1 = localBufferedReader.readLine()) != null) {
/* 86 */       str1 = str1.trim();
/*    */ 
/* 88 */       if ((!str1.startsWith("#")) && (str1.length() != 0))
/*    */       {
/* 91 */         StringTokenizer localStringTokenizer = new StringTokenizer(str1, " \t");
/* 92 */         if (localStringTokenizer.countTokens() > 1) {
/* 93 */           String str2 = localStringTokenizer.nextToken();
/* 94 */           while (localStringTokenizer.hasMoreTokens()) {
/* 95 */             String str3 = localStringTokenizer.nextToken();
/* 96 */             this.extensionToMimetypeMap.put(str3, str2);
/* 97 */             if (log.isDebugEnabled())
/* 98 */               log.info("Setting mime type for extension '" + str3 + "' to '" + str2 + "'");
/*    */           }
/*    */         }
/* 101 */         else if (log.isDebugEnabled()) {
/* 102 */           log.info("Ignoring mimetype with no associated file extensions: '" + str1 + "'");
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.utils.Mimetypes
 * JD-Core Version:    0.6.2
 */