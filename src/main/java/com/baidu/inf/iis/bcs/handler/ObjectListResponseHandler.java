/*    */ package com.baidu.inf.iis.bcs.handler;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.http.BCSHttpResponse;
/*    */ import com.baidu.inf.iis.bcs.model.ObjectListing;
/*    */ import com.baidu.inf.iis.bcs.model.ObjectSummary;
/*    */ import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;
/*    */ import flexjson.JSONDeserializer;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class ObjectListResponseHandler extends HttpResponseHandler<ObjectListing>
/*    */ {
/*    */   public BaiduBCSResponse<ObjectListing> handle(BCSHttpResponse paramBCSHttpResponse)
/*    */   {
/* 21 */     String str = getResponseContentByStr(paramBCSHttpResponse);
/* 22 */     JSONDeserializer localJSONDeserializer = new JSONDeserializer();
/* 23 */     HashMap localHashMap1 = (HashMap)localJSONDeserializer.deserialize(str);
/*    */ 
/* 25 */     ObjectListing localObjectListing = new ObjectListing();
/*    */ 
/* 27 */     localObjectListing.setObjectTotal(((Integer)localHashMap1.get("object_total")).intValue());
/*    */ 
/* 29 */     if ((localHashMap1.get("start") instanceof String))
/* 30 */       localObjectListing.setStart(Integer.valueOf((String)localHashMap1.get("start")).intValue());
/*    */     else {
/* 32 */       localObjectListing.setStart(((Integer)localHashMap1.get("start")).intValue());
/*    */     }
/*    */ 
/* 35 */     if ((localHashMap1.get("limit") instanceof String))
/* 36 */       localObjectListing.setLimit(Integer.valueOf((String)localHashMap1.get("limit")).intValue());
/*    */     else {
/* 38 */       localObjectListing.setLimit(((Integer)localHashMap1.get("limit")).intValue());
/*    */     }
/*    */ 
/* 41 */     localObjectListing.setBucket((String)localHashMap1.get("bucket"));
/*    */ 
/* 43 */     if (localHashMap1.get("prefix") != null) {
/* 44 */       localObjectListing.setPrefix((String)localHashMap1.get("prefix"));
/*    */     }
/*    */ 
/* 47 */     ArrayList localArrayList = (ArrayList)localHashMap1.get("object_list");
/* 48 */     for (Object localObject = localArrayList.iterator(); ((Iterator)localObject).hasNext(); ) { HashMap localHashMap2 = (HashMap)((Iterator)localObject).next();
/* 49 */       ObjectSummary localObjectSummary = new ObjectSummary();
/* 50 */       localObjectSummary.setName((String)localHashMap2.get("object"));
/* 51 */       localObjectSummary.setVersionKey((String)localHashMap2.get("version_key"));
/* 52 */       localObjectSummary.setIsDir(((String)localHashMap2.get("is_dir")).equals("1"));
/* 53 */       localObjectSummary.setSize(Long.valueOf((String)localHashMap2.get("size")));
/* 54 */       localObjectSummary.setLastModifiedTime(Long.valueOf((String)localHashMap2.get("mdatetime")));
/* 55 */       localObjectSummary.setParentDir((String)localHashMap2.get("parent_dir"));
/* 56 */       localObjectListing.addObjectSummary(localObjectSummary);
/*    */     }
/* 58 */     BaiduBCSResponse<ObjectListing> localObject = parseResponseMetadata(paramBCSHttpResponse);
/* 59 */     ((BaiduBCSResponse)localObject).setResult(localObjectListing);
/* 60 */     return localObject;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.handler.ObjectListResponseHandler
 * JD-Core Version:    0.6.2
 */