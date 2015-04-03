/*    */ package com.baidu.inf.iis.bcs.utils;
/*    */ 
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import java.util.Locale;
/*    */ import java.util.SimpleTimeZone;
/*    */ 
/*    */ public class DateUtils
/*    */ {
/* 15 */   protected final SimpleDateFormat iso8601DateParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
/*    */ 
/* 17 */   protected final SimpleDateFormat alternateIo8601DateParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
/*    */ 
/* 19 */   protected final SimpleDateFormat rfc822DateParser = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
/*    */ 
/*    */   public DateUtils() {
/* 22 */     this.iso8601DateParser.setTimeZone(new SimpleTimeZone(0, "GMT"));
/* 23 */     this.rfc822DateParser.setTimeZone(new SimpleTimeZone(0, "GMT"));
/* 24 */     this.alternateIo8601DateParser.setTimeZone(new SimpleTimeZone(0, "GMT"));
/*    */   }
/*    */ 
/*    */   public String formatIso8601Date(Date paramDate) {
/* 28 */     synchronized (this.iso8601DateParser) {
/* 29 */       return this.iso8601DateParser.format(paramDate);
/*    */     }
/*    */   }
/*    */ 
/*    */   public String formatRfc822Date(Date paramDate) {
/* 34 */     synchronized (this.rfc822DateParser) {
/* 35 */       return this.rfc822DateParser.format(paramDate);
/*    */     }
/*    */   }
/*    */ 
/*    */   public Date parseIso8601Date(String paramString) throws ParseException {
/*    */     try {
/* 41 */       synchronized (this.iso8601DateParser) {
/* 42 */         return this.iso8601DateParser.parse(paramString);
/*    */       }
/*    */     } catch (ParseException localParseException) {
/* 45 */       synchronized (this.alternateIo8601DateParser) {
/* 46 */         return this.alternateIo8601DateParser.parse(paramString);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public Date parseRfc822Date(String paramString) throws ParseException {
/* 52 */     synchronized (this.rfc822DateParser) {
/* 53 */       return this.rfc822DateParser.parse(paramString);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.utils.DateUtils
 * JD-Core Version:    0.6.2
 */