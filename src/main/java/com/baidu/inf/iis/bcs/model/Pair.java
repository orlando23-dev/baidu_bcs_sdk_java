/*    */ package com.baidu.inf.iis.bcs.model;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class Pair<T>
/*    */ {
/*    */   private T first;
/*    */   private T second;
/*    */ 
/*    */   public Pair(T paramT1, T paramT2)
/*    */   {
/* 17 */     this.first = paramT1;
/* 18 */     this.second = paramT2;
/*    */   }
/*    */ 
/*    */   public T getFirst() {
/* 22 */     return this.first;
/*    */   }
/*    */ 
/*    */   public T getSecond() {
/* 26 */     return this.second;
/*    */   }
/*    */ 
/*    */   public void setFirst(T paramT) {
/* 30 */     this.first = paramT;
/*    */   }
/*    */ 
/*    */   public void setSecond(T paramT) {
/* 34 */     this.second = paramT;
/*    */   }
/*    */ 
/*    */   public List<T> toArrayList() {
/* 38 */     ArrayList localArrayList = new ArrayList();
/* 39 */     localArrayList.add(this.first);
/* 40 */     localArrayList.add(this.second);
/* 41 */     return localArrayList;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.model.Pair
 * JD-Core Version:    0.6.2
 */