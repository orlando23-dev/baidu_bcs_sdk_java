/*    */ package com.baidu.inf.iis.bcs.handler;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.http.BCSHttpResponse;
/*    */ import com.baidu.inf.iis.bcs.model.BucketSummary;
/*    */ import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;
/*    */ import flexjson.JSONDeserializer;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ public class BucketListResponseHandler extends HttpResponseHandler<List<BucketSummary>>
/*    */ {
/*    */   public BaiduBCSResponse<List<BucketSummary>> handle(BCSHttpResponse paramBCSHttpResponse)
/*    */   {
/* 23 */     String str = getResponseContentByStr(paramBCSHttpResponse);
/*    */ 
/* 25 */     JSONDeserializer localJSONDeserializer = new JSONDeserializer();
/*    */ 
/* 27 */     List localList = (List)localJSONDeserializer.deserialize(str);
/*    */ 
/* 29 */     ArrayList localArrayList = new ArrayList();
/* 30 */     for (Object localObject = localList.iterator(); ((Iterator)localObject).hasNext(); ) { HashMap localHashMap = (HashMap)((Iterator)localObject).next();
/* 31 */       BucketSummary localBucketSummary = new BucketSummary((String)localHashMap.get("bucket_name"));
/* 32 */       localBucketSummary.setCdatatime(Long.valueOf((String)localHashMap.get("cdatetime")));
/* 33 */       localBucketSummary.setTotalCapacity(Long.valueOf((String)localHashMap.get("total_capacity")));
/* 34 */       localBucketSummary.setUsedCapacity(Long.valueOf((String)localHashMap.get("used_capacity")));
/* 35 */       localArrayList.add(localBucketSummary);
/*    */     }
/* 37 */     BaiduBCSResponse<List<BucketSummary>> localObject = parseResponseMetadata(paramBCSHttpResponse);
/* 38 */     ((BaiduBCSResponse)localObject).setResult(localArrayList);
/* 39 */     return localObject;
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.handler.BucketListResponseHandler
 * JD-Core Version:    0.6.2
 */