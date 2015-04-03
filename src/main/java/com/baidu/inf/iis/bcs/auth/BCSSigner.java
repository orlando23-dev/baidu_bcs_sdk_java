/*    */ package com.baidu.inf.iis.bcs.auth;
/*    */ 
/*    */ import com.baidu.inf.iis.bcs.http.BCSHttpRequest;
/*    */ import com.baidu.inf.iis.bcs.http.DefaultBCSHttpRequest;
/*    */ import com.baidu.inf.iis.bcs.http.HttpMethodName;
/*    */ import com.baidu.inf.iis.bcs.model.BCSClientException;
/*    */ import com.baidu.inf.iis.bcs.request.BaiduBCSRequest;
/*    */ import com.baidu.inf.iis.bcs.utils.ServiceUtils;
/*    */ import java.io.PrintStream;
/*    */ import java.net.URISyntaxException;
/*    */ import java.security.InvalidKeyException;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ import javax.crypto.Mac;
/*    */ import javax.crypto.spec.SecretKeySpec;
/*    */ 
/*    */ public class BCSSigner
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */     throws URISyntaxException
/*    */   {
/* 25 */     BaiduBCSRequest local1 = new BaiduBCSRequest("bucket", "object", HttpMethodName.GET)
/*    */     {
/*    */     };
/* 28 */     BCSSignCondition localBCSSignCondition = new BCSSignCondition();
/* 29 */     localBCSSignCondition.setIp("192.168.1.1");
/* 30 */     localBCSSignCondition.setSize(Long.valueOf(1234L));
/* 31 */     localBCSSignCondition.setTime(Long.valueOf(4321L));
/*    */ 
/* 33 */     BCSCredentials localBCSCredentials = new BCSCredentials("akakak", "sksksk");
/*    */ 
/* 35 */     DefaultBCSHttpRequest localDefaultBCSHttpRequest = new DefaultBCSHttpRequest();
/* 36 */     localDefaultBCSHttpRequest.setHttpMethod(local1.getHttpMethod());
/* 37 */     localDefaultBCSHttpRequest.setEndpoint("10.81.2.114:8685");
/*    */ 
/* 39 */     sign(local1, localDefaultBCSHttpRequest, localBCSCredentials, localBCSSignCondition);
/* 40 */     System.out.println(localDefaultBCSHttpRequest.toString());
/*    */   }
/*    */ 
/*    */   public static void sign(BaiduBCSRequest paramBaiduBCSRequest, BCSHttpRequest paramBCSHttpRequest, BCSCredentials paramBCSCredentials) {
/* 44 */     sign(paramBaiduBCSRequest, paramBCSHttpRequest, paramBCSCredentials, null);
/*    */   }
/*    */ 
/*    */   public static void sign(BaiduBCSRequest paramBaiduBCSRequest, BCSHttpRequest paramBCSHttpRequest, BCSCredentials paramBCSCredentials, BCSSignCondition paramBCSSignCondition) {
/* 48 */     StringBuilder localStringBuilder1 = new StringBuilder();
/* 49 */     StringBuilder localStringBuilder2 = new StringBuilder();
/*    */ 
/* 51 */     if (null == paramBaiduBCSRequest.getHttpMethod()) {
/* 52 */       throw new BCSClientException("Sign failed! Param: httpMethod, bucket, object can not be empty!");
/*    */     }
/* 54 */     if (null == paramBaiduBCSRequest.getBucket()) {
/* 55 */       throw new BCSClientException("Sign failed! Param: httpMethod, bucket, object can not be empty!");
/*    */     }
/* 57 */     if ((null == paramBaiduBCSRequest.getObject()) || (0 == paramBaiduBCSRequest.getObject().length())) {
/* 58 */       throw new BCSClientException("Sign failed! Param: httpMethod, bucket, object can not be empty!");
/*    */     }
/* 60 */     localStringBuilder1.append("MBO");
/* 61 */     localStringBuilder2.append("Method=").append(paramBaiduBCSRequest.getHttpMethod().toString()).append("\n");
/* 62 */     localStringBuilder2.append("Bucket=").append(paramBaiduBCSRequest.getBucket()).append("\n");
/* 63 */     localStringBuilder2.append("Object=").append(paramBaiduBCSRequest.getObject()).append("\n");
/*    */ 
/* 65 */     if (paramBCSSignCondition != null) {
/* 66 */       if (0 != paramBCSSignCondition.getIp().length()) {
/* 67 */         localStringBuilder1.append("I");
/* 68 */         localStringBuilder2.append("Ip=").append(paramBCSSignCondition.getIp()).append("\n");
/*    */       }
/* 70 */       if (paramBCSSignCondition.getTime().longValue() > 0L) {
/* 71 */         localStringBuilder1.append("T");
/* 72 */         localStringBuilder2.append("Time=").append(paramBCSSignCondition.getTime()).append("\n");
/* 73 */         paramBCSHttpRequest.addParameter("time", String.valueOf(paramBCSSignCondition.getTime()));
/*    */       }
/* 75 */       if (paramBCSSignCondition.getSize().longValue() > 0L) {
/* 76 */         localStringBuilder1.append("S");
/* 77 */         localStringBuilder2.append("Size=").append(paramBCSSignCondition.getSize()).append("\n");
/* 78 */         paramBCSHttpRequest.addParameter("size", String.valueOf(paramBCSSignCondition.getSize()));
/*    */       }
/*    */     }
/* 81 */     localStringBuilder2.insert(0, "\n");
/* 82 */     localStringBuilder2.insert(0, localStringBuilder1.toString());
/*    */ 
/* 84 */     byte[] arrayOfByte = new byte[0];
/*    */     try {
/* 86 */       SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramBCSCredentials.getSecretKey().getBytes(), SigningAlgorithm.HmacSHA1.toString());
/*    */ 
/* 88 */       Mac localMac = Mac.getInstance(localSecretKeySpec.getAlgorithm());
/* 89 */       localMac.init(localSecretKeySpec);
/* 90 */       arrayOfByte = localMac.doFinal(ServiceUtils.toByteArray(localStringBuilder2.toString()));
/* 91 */       paramBCSHttpRequest.addParameter("sign", ":" + paramBCSCredentials.getAccessKey() + ":" + ServiceUtils.toBase64(arrayOfByte));
/*    */     }
/*    */     catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
/* 94 */       throw new BCSClientException("NoSuchAlgorithmException. Sign bcs failed!", localNoSuchAlgorithmException);
/*    */     } catch (InvalidKeyException localInvalidKeyException) {
/* 96 */       throw new BCSClientException("InvalidKeyException. Sign bcs failed!", localInvalidKeyException);
/*    */     } catch (RuntimeException localRuntimeException) {
/* 98 */       throw new BCSClientException("Sign bcs failed!", localRuntimeException);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.auth.BCSSigner
 * JD-Core Version:    0.6.2
 */