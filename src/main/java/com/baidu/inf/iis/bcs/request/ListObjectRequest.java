/*    */ package com.baidu.inf.iis.bcs.request;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.http.HttpMethodName;
/*    */ 
/*    */ public class ListObjectRequest extends BaiduBCSRequest
/*    */ {
/* 12 */   private String prefix = null;
/* 13 */   private int start = -1;
/* 14 */   private int limit = -1;
/* 15 */   private int listModel = 0;
/*    */ 
/*    */   public ListObjectRequest(String paramString) {
/* 18 */     super(paramString, HttpMethodName.GET);
/*    */   }
/*    */ 
/*    */   public int getLimit() {
/* 22 */     return this.limit;
/*    */   }
/*    */ 
/*    */   public int getListModel() {
/* 26 */     return this.listModel;
/*    */   }
/*    */ 
/*    */   public String getPrefix() {
/* 30 */     return this.prefix;
/*    */   }
/*    */ 
/*    */   public int getStart() {
/* 34 */     return this.start;
/*    */   }
/*    */ 
/*    */   public void setLimit(int paramInt) {
/* 38 */     this.limit = paramInt;
/*    */   }
/*    */ 
/*    */   public void setListModel(int paramInt) {
/* 42 */     this.listModel = paramInt;
/*    */   }
/*    */ 
/*    */   public void setPrefix(String paramString) {
/* 46 */     this.prefix = paramString;
/*    */   }
/*    */ 
/*    */   public void setStart(int paramInt) {
/* 50 */     this.start = paramInt;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.request.ListObjectRequest
 * JD-Core Version:    0.6.2
 */