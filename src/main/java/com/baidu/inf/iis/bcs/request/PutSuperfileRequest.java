/*    */ package com.baidu.inf.iis.bcs.request;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.http.HttpMethodName;
/*    */ import com.baidu.inf.iis.bcs.model.ObjectMetadata;
/*    */ import com.baidu.inf.iis.bcs.model.SuperfileSubObject;
/*    */ import com.baidu.inf.iis.bcs.model.X_BS_ACL;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PutSuperfileRequest extends BaiduBCSRequest
/*    */ {
/* 17 */   private List<SuperfileSubObject> subObjectList = null;
/* 18 */   private ObjectMetadata objectMetadata = null;
/* 19 */   private X_BS_ACL acl = null;
/*    */ 
/*    */   public PutSuperfileRequest(String paramString1, String paramString2, List<SuperfileSubObject> paramList) {
/* 22 */     super(paramString1, paramString2, HttpMethodName.PUT);
/* 23 */     this.subObjectList = paramList;
/*    */   }
/*    */ 
/*    */   public PutSuperfileRequest(String paramString1, String paramString2, ObjectMetadata paramObjectMetadata, List<SuperfileSubObject> paramList) {
/* 27 */     super(paramString1, paramString2, HttpMethodName.PUT);
/* 28 */     this.subObjectList = paramList;
/* 29 */     this.objectMetadata = paramObjectMetadata;
/*    */   }
/*    */ 
/*    */   public X_BS_ACL getAcl() {
/* 33 */     return this.acl;
/*    */   }
/*    */ 
/*    */   public ObjectMetadata getObjectMetadata() {
/* 37 */     return this.objectMetadata;
/*    */   }
/*    */ 
/*    */   public List<SuperfileSubObject> getSubObjectList() {
/* 41 */     return this.subObjectList;
/*    */   }
/*    */ 
/*    */   public void setAcl(X_BS_ACL paramX_BS_ACL) {
/* 45 */     this.acl = paramX_BS_ACL;
/*    */   }
/*    */ 
/*    */   public void setObjectMetadata(ObjectMetadata paramObjectMetadata) {
/* 49 */     this.objectMetadata = paramObjectMetadata;
/*    */   }
/*    */ 
/*    */   public void setSubObjectList(List<SuperfileSubObject> paramList) {
/* 53 */     this.subObjectList = paramList;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.request.PutSuperfileRequest
 * JD-Core Version:    0.6.2
 */