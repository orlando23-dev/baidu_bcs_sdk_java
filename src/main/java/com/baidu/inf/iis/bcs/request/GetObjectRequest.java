/*    */ package com.baidu.inf.iis.bcs.request;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.http.HttpMethodName;
/*    */ import com.baidu.inf.iis.bcs.model.ObjectMetadata;
/*    */ import com.baidu.inf.iis.bcs.model.Pair;
/*    */ 
/*    */ public class GetObjectRequest extends BaiduBCSRequest
/*    */ {
/*    */   private String versionKey;
/*    */   private Pair<Long> range;
/*    */   private ObjectMetadata objectMetadata;
/*    */ 
/*    */   public GetObjectRequest(String paramString1, String paramString2)
/*    */   {
/* 24 */     super(paramString1, paramString2, HttpMethodName.GET);
/*    */   }
/*    */ 
/*    */   public GetObjectRequest(String paramString1, String paramString2, String paramString3) {
/* 28 */     super(paramString1, paramString2, HttpMethodName.GET);
/* 29 */     this.versionKey = paramString3;
/*    */   }
/*    */ 
/*    */   public ObjectMetadata getObjectMetadata() {
/* 33 */     return this.objectMetadata;
/*    */   }
/*    */ 
/*    */   public void setObjectMetadata(ObjectMetadata paramObjectMetadata) {
/* 37 */     this.objectMetadata = paramObjectMetadata;
/*    */   }
/*    */ 
/*    */   public Pair<Long> getRange()
/*    */   {
/* 44 */     return this.range;
/*    */   }
/*    */ 
/*    */   public void setRange(Pair<Long> paramPair)
/*    */   {
/* 70 */     this.range = paramPair;
/*    */   }
/*    */ 
/*    */   public String getVersionKey()
/*    */   {
/* 77 */     return this.versionKey;
/*    */   }
/*    */ 
/*    */   public void setVersionKey(String paramString)
/*    */   {
/* 84 */     this.versionKey = paramString;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.request.GetObjectRequest
 * JD-Core Version:    0.6.2
 */