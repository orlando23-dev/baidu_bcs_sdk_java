/*    */ package com.baidu.inf.iis.bcs.policy;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.model.Pair;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PolicyTime
/*    */ {
/* 28 */   private List<String> singleTimeList = new ArrayList();
/* 29 */   private List<Pair<String>> timeRangeList = new ArrayList();
/*    */ 
/*    */   public PolicyTime addSingleTime(String paramString) {
/* 32 */     this.singleTimeList.add(paramString);
/* 33 */     return this;
/*    */   }
/*    */ 
/*    */   public PolicyTime addTimeRange(Pair<String> paramPair) {
/* 37 */     this.timeRangeList.add(paramPair);
/* 38 */     return this;
/*    */   }
/*    */ 
/*    */   public List<String> getSingleTimeList() {
/* 42 */     return this.singleTimeList;
/*    */   }
/*    */ 
/*    */   public List<Pair<String>> getTimeRangeList() {
/* 46 */     return this.timeRangeList;
/*    */   }
/*    */ 
/*    */   public boolean isEmpty() {
/* 50 */     if ((this.singleTimeList.size() > 0) || (this.timeRangeList.size() > 0)) {
/* 51 */       return false;
/*    */     }
/* 53 */     return true;
/*    */   }
/*    */ 
/*    */   public void setSingleTimeList(List<String> paramList) {
/* 57 */     this.singleTimeList = paramList;
/*    */   }
/*    */ 
/*    */   public void setTimeRangeList(List<Pair<String>> paramList) {
/* 61 */     this.timeRangeList = paramList;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.policy.PolicyTime
 * JD-Core Version:    0.6.2
 */