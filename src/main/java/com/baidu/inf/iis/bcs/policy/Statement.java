/*    */ package com.baidu.inf.iis.bcs.policy;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class Statement
/*    */ {
/* 15 */   private List<String> user = new ArrayList();
/* 16 */   private List<String> resource = new ArrayList();
/* 17 */   private List<PolicyAction> action = new ArrayList();
/*    */   private PolicyEffect effect;
/* 19 */   private PolicyTime time = null;
/* 20 */   private PolicyIP ip = null;
/*    */ 
/*    */   public Statement addAction(PolicyAction paramPolicyAction) {
/* 23 */     this.action.add(paramPolicyAction);
/* 24 */     return this;
/*    */   }
/*    */ 
/*    */   public Statement addResource(String paramString) {
/* 28 */     this.resource.add(paramString);
/* 29 */     return this;
/*    */   }
/*    */ 
/*    */   public Statement addUser(String paramString) {
/* 33 */     this.user.add(paramString);
/* 34 */     return this;
/*    */   }
/*    */ 
/*    */   public List<PolicyAction> getAction() {
/* 38 */     return this.action;
/*    */   }
/*    */ 
/*    */   public PolicyEffect getEffect() {
/* 42 */     return this.effect;
/*    */   }
/*    */ 
/*    */   public PolicyIP getIp() {
/* 46 */     return this.ip;
/*    */   }
/*    */ 
/*    */   public List<String> getResource() {
/* 50 */     return this.resource;
/*    */   }
/*    */ 
/*    */   public PolicyTime getTime() {
/* 54 */     return this.time;
/*    */   }
/*    */ 
/*    */   public List<String> getUser() {
/* 58 */     return this.user;
/*    */   }
/*    */ 
/*    */   public void setAction(List<PolicyAction> paramList) {
/* 62 */     this.action = paramList;
/*    */   }
/*    */ 
/*    */   public void setEffect(PolicyEffect paramPolicyEffect) {
/* 66 */     this.effect = paramPolicyEffect;
/*    */   }
/*    */ 
/*    */   public void setIp(PolicyIP paramPolicyIP) {
/* 70 */     this.ip = paramPolicyIP;
/*    */   }
/*    */ 
/*    */   public void setResource(List<String> paramList) {
/* 74 */     this.resource = paramList;
/*    */   }
/*    */ 
/*    */   public void setTime(PolicyTime paramPolicyTime) {
/* 78 */     this.time = paramPolicyTime;
/*    */   }
/*    */ 
/*    */   public void setUser(List<String> paramList) {
/* 82 */     this.user = paramList;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.policy.Statement
 * JD-Core Version:    0.6.2
 */