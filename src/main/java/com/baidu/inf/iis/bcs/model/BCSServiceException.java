/*    */ package com.baidu.inf.iis.bcs.model;
/*    */ 
/*    */ public class BCSServiceException extends BCSClientException
/*    */ {
/*    */   private static final long serialVersionUID = -6120510420311024191L;
/*    */   private String requestId;
/*    */   private int httpErrorCode;
/*    */   private int bcsErrorCode;
/*    */   private String bcsErrorMessage;
/*    */ 
/*    */   public BCSServiceException(String paramString)
/*    */   {
/* 30 */     super(paramString);
/*    */   }
/*    */ 
/*    */   public BCSServiceException(String paramString, Throwable paramThrowable) {
/* 34 */     super(paramString, paramThrowable);
/*    */   }
/*    */ 
/*    */   public int getBcsErrorCode() {
/* 38 */     return this.bcsErrorCode;
/*    */   }
/*    */ 
/*    */   public String getBcsErrorMessage() {
/* 42 */     return this.bcsErrorMessage;
/*    */   }
/*    */ 
/*    */   public String getRequestId() {
/* 46 */     return this.requestId;
/*    */   }
/*    */ 
/*    */   public void setBcsErrorCode(int paramInt) {
/* 50 */     this.bcsErrorCode = paramInt;
/*    */   }
/*    */ 
/*    */   public void setBcsErrorMessage(String paramString) {
/* 54 */     this.bcsErrorMessage = paramString;
/*    */   }
/*    */ 
/*    */   public void setRequestId(String paramString) {
/* 58 */     this.requestId = paramString;
/*    */   }
/*    */ 
/*    */   public int getHttpErrorCode() {
/* 62 */     return this.httpErrorCode;
/*    */   }
/*    */ 
/*    */   public void setHttpErrorCode(int paramInt) {
/* 66 */     this.httpErrorCode = paramInt;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.model.BCSServiceException
 * JD-Core Version:    0.6.2
 */