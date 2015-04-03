/*    */ package com.baidu.inf.iis.bcs.response;
/*    */ 
/*    */ public class BaiduBCSResponse<T>
/*    */ {
/*    */   private T result;
/*    */   private String requestId;
/*    */ 
/*    */   public String getRequestId()
/*    */   {
/* 19 */     return this.requestId;
/*    */   }
/*    */ 
/*    */   public T getResult() {
/* 23 */     return this.result;
/*    */   }
/*    */ 
/*    */   public void setRequestId(String paramString) {
/* 27 */     this.requestId = paramString;
/*    */   }
/*    */ 
/*    */   public void setResult(T paramT) {
/* 31 */     this.result = paramT;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.response.BaiduBCSResponse
 * JD-Core Version:    0.6.2
 */