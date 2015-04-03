/*     */ package com.baidu.inf.iis.bcs.policy;
/*     */ 
/*     */ import com.baidu.inf.iis.bcs.model.BCSClientException;
/*     */ import com.baidu.inf.iis.bcs.model.Pair;

/*     */ import flexjson.JSONDeserializer;
/*     */ import flexjson.JSONSerializer;

/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;

/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class Policy
/*     */ {
/*  23 */   private static final Log log = LogFactory.getLog(Policy.class);
/*     */ 
/*  67 */   List<Statement> statements = new ArrayList();
/*     */   private String originalJsonStr;
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  26 */     Policy localPolicy = new Policy();
/*  27 */     Statement localStatement1 = new Statement();
/*  28 */     localStatement1.addAction(PolicyAction.get_bucket_policy).addAction(PolicyAction.put_bucket_policy).addAction(PolicyAction.all);
/*  29 */     localStatement1.addResource("/1").addResource("/2").addResource("/3");
/*  30 */     localStatement1.addUser("user1").addUser("user2");
/*  31 */     localStatement1.setEffect(PolicyEffect.allow);
/*  32 */     PolicyTime localPolicyTime = new PolicyTime();
/*  33 */     localPolicyTime.addSingleTime("2012-01-04D12:12:1").addSingleTime("2012-01-04D12:12:2").addTimeRange(new Pair("2012-01-04D12:12:3", "2012-01-04D12:12:4"));
/*     */ 
/*  35 */     localStatement1.setTime(localPolicyTime);
/*  36 */     PolicyIP localPolicyIP = new PolicyIP();
/*  37 */     localPolicyIP.addSingleIp("1.1.1.1").addCidrIp("2.2.2.2/16").addIpRange(new Pair("3.3.3.3", "4.4.4.4"));
/*  38 */     localStatement1.setIp(localPolicyIP);
/*     */ 
/*  40 */     Statement localStatement2 = new Statement();
/*  41 */     localStatement2.addAction(PolicyAction.get_bucket_policy).addAction(PolicyAction.put_bucket_policy);
/*  42 */     localStatement2.addResource("/1").addResource("/2").addResource("/3");
/*  43 */     localStatement2.addUser("user1").addUser("user2");
/*  44 */     localStatement2.setEffect(PolicyEffect.deny);
/*     */ 
/*  46 */     localPolicy.addStatements(localStatement1);
/*  47 */     localPolicy.addStatements(localStatement2);
/*     */ 
/*  50 */     String str1 = localPolicy.toJson();
/*  51 */     log.info("Policy object to json str:\n" + str1);
/*     */ 
/*  54 */     localPolicy = new Policy().buildJsonStr(str1);
/*     */ 
/*  57 */     String str2 = localPolicy.toJson();
/*  58 */     log.info("Json str 2 policy object 2 json str:\n" + str2);
/*     */ 
/*  60 */     if (str1.equals(str2))
/*  61 */       log.info("Correct");
/*     */     else
/*  63 */       log.info("Invalid");
/*     */   }
/*     */ 
/*     */   public Policy addStatements(Statement paramStatement)
/*     */   {
/*  72 */     this.statements.add(paramStatement);
/*  73 */     return this;
/*     */   }
/*     */ 
/*     */   public Policy buildJsonStr(String paramString)
/*     */   {
/*  78 */     this.originalJsonStr = paramString;
/*  79 */     JSONDeserializer localJSONDeserializer = new JSONDeserializer();
/*  80 */     HashMap localHashMap1 = (HashMap)localJSONDeserializer.deserialize(paramString);
/*  81 */     ArrayList<HashMap> localArrayList = (ArrayList<HashMap>)localHashMap1.get("statements");
/*     */ 
/*  83 */     for (HashMap localHashMap2 : localArrayList) {
/*  84 */       Statement localStatement = new Statement();
/*     */ 
/*  86 */       for (Object localObject1 = ((ArrayList)localHashMap2.get("action")).iterator(); ((Iterator)localObject1).hasNext(); ) { String localObject2 = (String)((Iterator)localObject1).next();
/*  87 */         localStatement.addAction(PolicyAction.toPolicyAction((String)localObject2));
/*     */       }
/*  90 */       Object localObject2;
Iterator localObject1;
/*  90 */       for (localObject1 = ((ArrayList)localHashMap2.get("user")).iterator(); ((Iterator)localObject1).hasNext(); ) { localObject2 = (String)((Iterator)localObject1).next();
/*  91 */         localStatement.addUser((String)localObject2);
/*     */       }
/*     */ 
/*  94 */       for (localObject1 = ((ArrayList)localHashMap2.get("resource")).iterator(); ((Iterator)localObject1).hasNext(); ) { localObject2 = (String)((Iterator)localObject1).next();
/*  95 */         localStatement.addResource((String)localObject2);
/*     */       }
/*     */ 
/*  98 */       localStatement.setEffect(PolicyEffect.valueOf((String)localHashMap2.get("effect")));
/*     */       Object localObject3;
/* 100 */       if (null != localHashMap2.get("time")) {
/* 101 */         localObject1 = (Iterator) new PolicyTime();
/* 102 */         for (localObject2 = ((ArrayList)localHashMap2.get("time")).iterator(); ((Iterator)localObject2).hasNext(); ) { localObject3 = ((Iterator)localObject2).next();
/* 103 */           if ((localObject3 instanceof String))
/* 104 */             ((PolicyTime)localObject1).addSingleTime((String)localObject3);
/* 105 */           else if ((localObject3 instanceof List))
/* 106 */             ((PolicyTime)localObject1).addTimeRange(new Pair((String)((List)localObject3).get(0), (String)((List)localObject3).get(1)));
/*     */           else {
/* 108 */             throw new BCSClientException("Analyze policy time failed.");
/*     */           }
/*     */         }
/* 111 */         localStatement.setTime((PolicyTime)localObject1);
/*     */       }
/*     */ 
/* 114 */       if (null != localHashMap2.get("ip")) {
/* 115 */         localObject1 = (Iterator) new PolicyIP();
/* 116 */         for (localObject2 = ((ArrayList)localHashMap2.get("ip")).iterator(); ((Iterator)localObject2).hasNext(); ) { localObject3 = ((Iterator)localObject2).next();
/* 117 */           if ((localObject3 instanceof String)) {
/* 118 */             if (((String)localObject3).indexOf("/") != -1)
/* 119 */               ((PolicyIP)localObject1).addCidrIp((String)localObject3);
/*     */             else
/* 121 */               ((PolicyIP)localObject1).addSingleIp((String)localObject3);
/*     */           }
/* 123 */           else if ((localObject3 instanceof List))
/* 124 */             ((PolicyIP)localObject1).addIpRange(new Pair((String)((List)localObject3).get(0), (String)((List)localObject3).get(1)));
/*     */           else {
/* 126 */             throw new BCSClientException("Analyze policy time failed.");
/*     */           }
/*     */         }
/* 129 */         localStatement.setIp((PolicyIP)localObject1);
/*     */       }
/*     */ 
/* 132 */       addStatements(localStatement);
/*     */     }
/*     */ 
/* 135 */     return this;
/*     */   }
/*     */ 
/*     */   public String getOriginalJsonStr() {
/* 139 */     return this.originalJsonStr;
/*     */   }
/*     */ 
/*     */   public List<Statement> getStatements() {
/* 143 */     return this.statements;
/*     */   }
/*     */ 
/*     */   public void setStatements(List<Statement> paramList) {
/* 147 */     this.statements = paramList;
/*     */   }
/*     */ 
/*     */   public String toJson() {
/* 151 */     if (this.statements.size() == 0) {
/* 152 */       return "";
/*     */     }
/* 154 */     JSONSerializer localJSONSerializer = new JSONSerializer();
/* 155 */     HashMap localHashMap1 = new HashMap();
/* 156 */     localHashMap1.put("statements", new ArrayList());
/* 157 */     for (Statement localStatement : this.statements) {
/* 158 */       HashMap localHashMap2 = new HashMap();
/*     */ 
/* 160 */       localHashMap2.put("user", localStatement.getUser());
/*     */ 
/* 162 */       localHashMap2.put("resource", localStatement.getResource());
/*     */ 
/* 164 */       ArrayList localArrayList = new ArrayList();
/* 165 */       for (Object localObject1 = localStatement.getAction().iterator(); ((Iterator)localObject1).hasNext(); ) { PolicyAction localObject2 = (PolicyAction)((Iterator)localObject1).next();
/* 166 */         localArrayList.add(((PolicyAction)localObject2).toString());
/*     */       }
/*     */       Object localObject2;
/* 168 */       localHashMap2.put("action", localArrayList);
/*     */ 
/* 170 */       localHashMap2.put("effect", localStatement.getEffect().toString());
/*     */       Pair localPair;
ArrayList localObject1;
/* 172 */       if ((null != localStatement.getTime()) && (!localStatement.getTime().isEmpty())) {
/* 173 */         localObject1 = new ArrayList();
/* 174 */         ((List)localObject1).addAll(localStatement.getTime().getSingleTimeList());
/* 175 */         for (localObject2 = localStatement.getTime().getTimeRangeList().iterator(); ((Iterator)localObject2).hasNext(); ) { localPair = (Pair)((Iterator)localObject2).next();
/* 176 */           ((List)localObject1).add(localPair.toArrayList());
/*     */         }
/* 178 */         localHashMap2.put("time", localObject1);
/*     */       }
/*     */ 
/* 181 */       if ((null != localStatement.getIp()) && (!localStatement.getIp().isEmpty())) {
/* 182 */         localObject1 = new ArrayList();
/* 183 */         ((List)localObject1).addAll(localStatement.getIp().getSingleIpList());
/* 184 */         ((List)localObject1).addAll(localStatement.getIp().getCidrIpList());
/* 185 */         for (localObject2 = localStatement.getIp().getIpRangeList().iterator(); ((Iterator)localObject2).hasNext(); ) { localPair = (Pair)((Iterator)localObject2).next();
/* 186 */           ((List)localObject1).add(localPair.toArrayList());
/*     */         }
/* 188 */         localHashMap2.put("ip", localObject1);
/*     */       }
/*     */ 
/* 191 */       ((ArrayList)localHashMap1.get("statements")).add(localHashMap2);
/*     */     }
/*     */ 
/* 194 */     return localJSONSerializer.deepSerialize(localHashMap1);
/*     */   }
/*     */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.policy.Policy
 * JD-Core Version:    0.6.2
 */