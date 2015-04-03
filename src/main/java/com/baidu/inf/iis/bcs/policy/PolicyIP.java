/*    */ package com.baidu.inf.iis.bcs.policy;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.model.Pair;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PolicyIP
/*    */ {
/* 27 */   private List<String> singleIpList = new ArrayList();
/* 28 */   private List<String> cidrIpList = new ArrayList();
/* 29 */   private List<Pair<String>> ipRangeList = new ArrayList();
/*    */ 
/*    */   public PolicyIP addCidrIp(String paramString) {
/* 32 */     this.cidrIpList.add(paramString);
/* 33 */     return this;
/*    */   }
/*    */ 
/*    */   public PolicyIP addIpRange(Pair<String> paramPair) {
/* 37 */     this.ipRangeList.add(paramPair);
/* 38 */     return this;
/*    */   }
/*    */ 
/*    */   public PolicyIP addSingleIp(String paramString) {
/* 42 */     this.singleIpList.add(paramString);
/* 43 */     return this;
/*    */   }
/*    */ 
/*    */   public List<String> getCidrIpList() {
/* 47 */     return this.cidrIpList;
/*    */   }
/*    */ 
/*    */   public List<Pair<String>> getIpRangeList() {
/* 51 */     return this.ipRangeList;
/*    */   }
/*    */ 
/*    */   public List<String> getSingleIpList() {
/* 55 */     return this.singleIpList;
/*    */   }
/*    */ 
/*    */   public boolean isEmpty() {
/* 59 */     if ((this.singleIpList.size() > 0) || (this.cidrIpList.size() > 0) || (this.ipRangeList.size() > 0)) {
/* 60 */       return false;
/*    */     }
/* 62 */     return true;
/*    */   }
/*    */ 
/*    */   public void setCidrIpList(List<String> paramList) {
/* 66 */     this.cidrIpList = paramList;
/*    */   }
/*    */ 
/*    */   public void setIpRangeList(List<Pair<String>> paramList) {
/* 70 */     this.ipRangeList = paramList;
/*    */   }
/*    */ 
/*    */   public void setSingleIpList(List<String> paramList) {
/* 74 */     this.singleIpList = paramList;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.policy.PolicyIP
 * JD-Core Version:    0.6.2
 */