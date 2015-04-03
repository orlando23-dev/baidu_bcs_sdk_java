/*    */ package com.baidu.inf.iis.bcs.request;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.auth.BCSSignCondition;
/*    */ import com.baidu.inf.iis.bcs.http.HttpMethodName;
/*    */ 
/*    */ public class GenerateUrlRequest extends BaiduBCSRequest
/*    */ {
/*    */   BCSSignCondition bcsSignCondition;
/*    */ 
/*    */   public GenerateUrlRequest(HttpMethodName paramHttpMethodName, String paramString1, String paramString2)
/*    */   {
/* 21 */     super(paramString1, paramString2, paramHttpMethodName);
/*    */   }
/*    */ 
/*    */   public BCSSignCondition getBcsSignCondition() {
/* 25 */     return this.bcsSignCondition;
/*    */   }
/*    */ 
/*    */   public void setBcsSignCondition(BCSSignCondition paramBCSSignCondition) {
/* 29 */     this.bcsSignCondition = paramBCSSignCondition;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.request.GenerateUrlRequest
 * JD-Core Version:    0.6.2
 */