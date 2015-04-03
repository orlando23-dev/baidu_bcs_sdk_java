/*    */ package com.baidu.inf.iis.bcs.utils;
/*    */ 
/*    */ public class StringUtils
/*    */ {
/*    */   public static String trimSlash(String paramString)
/*    */   {
/* 11 */     String str = paramString.replaceAll("//", "/");
/* 12 */     if (!paramString.equals(str)) {
/* 13 */       return trimSlash(str);
/*    */     }
/* 15 */     return str;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.utils.StringUtils
 * JD-Core Version:    0.6.2
 */