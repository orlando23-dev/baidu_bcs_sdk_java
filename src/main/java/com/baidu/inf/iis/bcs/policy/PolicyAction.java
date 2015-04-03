/*    */ package com.baidu.inf.iis.bcs.policy;
/*    */ 
/*    */ public enum PolicyAction
/*    */ {
/*  9 */   all("*"), put_bucket_policy("put_bucket_policy"), get_bucket_policy("get_bucket_policy"), list_object("list_object"), delete_bucket("delete_bucket"), 
/* 10 */   get_object("get_object"), put_object("put_object"), delete_object("delete_object"), put_object_policy("put_object_policy"), 
/* 11 */   get_object_policy("get_object_policy");
/*    */ 
/*    */   private final String value;
/*    */ 
/* 14 */   public static PolicyAction toPolicyAction(String paramString) { if (paramString.equals("*")) {
/* 15 */       return all;
/*    */     }
/* 17 */     return valueOf(paramString);
/*    */   }
/*    */ 
/*    */   private PolicyAction(String paramString)
/*    */   {
/* 23 */     this.value = paramString;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 28 */     return this.value;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.policy.PolicyAction
 * JD-Core Version:    0.6.2
 */