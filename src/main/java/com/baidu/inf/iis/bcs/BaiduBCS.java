/*      */ package com.baidu.inf.iis.bcs;
/*      */ 
/*      */ import com.baidu.inf.iis.bcs.auth.BCSCredentials;
/*      */ import com.baidu.inf.iis.bcs.auth.BCSSigner;
/*      */ import com.baidu.inf.iis.bcs.handler.BucketListResponseHandler;
/*      */ import com.baidu.inf.iis.bcs.handler.ObjectListResponseHandler;
/*      */ import com.baidu.inf.iis.bcs.handler.ObjectMetadataResponseHandler;
/*      */ import com.baidu.inf.iis.bcs.handler.ObjectResponseHandler;
/*      */ import com.baidu.inf.iis.bcs.handler.PolicyResponseHandler;
/*      */ import com.baidu.inf.iis.bcs.handler.VoidResponseHandler;
/*      */ import com.baidu.inf.iis.bcs.http.BCSHttpClient;
/*      */ import com.baidu.inf.iis.bcs.http.BCSHttpRequest;
/*      */ import com.baidu.inf.iis.bcs.http.ClientConfiguration;
/*      */ import com.baidu.inf.iis.bcs.http.DefaultBCSHttpRequest;
/*      */ import com.baidu.inf.iis.bcs.http.HttpRequestFactory;
/*      */ import com.baidu.inf.iis.bcs.http.MD5DigestCalculatingInputStream;
/*      */ import com.baidu.inf.iis.bcs.http.RepeatableFileInputStream;
/*      */ import com.baidu.inf.iis.bcs.model.BCSClientException;
/*      */ import com.baidu.inf.iis.bcs.model.BCSServiceException;
/*      */ import com.baidu.inf.iis.bcs.model.BucketSummary;
/*      */ import com.baidu.inf.iis.bcs.model.DownloadObject;
/*      */ import com.baidu.inf.iis.bcs.model.Empty;
/*      */ import com.baidu.inf.iis.bcs.model.ObjectListing;
/*      */ import com.baidu.inf.iis.bcs.model.ObjectMetadata;
/*      */ import com.baidu.inf.iis.bcs.model.Pair;
/*      */ import com.baidu.inf.iis.bcs.model.Resource;
/*      */ import com.baidu.inf.iis.bcs.model.SuperfileSubObject;
/*      */ import com.baidu.inf.iis.bcs.model.X_BS_ACL;
/*      */ import com.baidu.inf.iis.bcs.policy.Policy;
/*      */ import com.baidu.inf.iis.bcs.request.BaiduBCSRequest;
/*      */ import com.baidu.inf.iis.bcs.request.CopyObjectRequest;
/*      */ import com.baidu.inf.iis.bcs.request.CreateBucketRequest;
/*      */ import com.baidu.inf.iis.bcs.request.DeleteBucketRequest;
/*      */ import com.baidu.inf.iis.bcs.request.DeleteObjectRequest;
/*      */ import com.baidu.inf.iis.bcs.request.GenerateUrlRequest;
/*      */ import com.baidu.inf.iis.bcs.request.GetBucketPolicyRequest;
/*      */ import com.baidu.inf.iis.bcs.request.GetObjectMetadataRequest;
/*      */ import com.baidu.inf.iis.bcs.request.GetObjectPolicyRequest;
/*      */ import com.baidu.inf.iis.bcs.request.GetObjectRequest;
/*      */ import com.baidu.inf.iis.bcs.request.ListBucketRequest;
/*      */ import com.baidu.inf.iis.bcs.request.ListObjectRequest;
/*      */ import com.baidu.inf.iis.bcs.request.PutBucketPolicyRequest;
/*      */ import com.baidu.inf.iis.bcs.request.PutObjectPolicyRequest;
/*      */ import com.baidu.inf.iis.bcs.request.PutObjectRequest;
/*      */ import com.baidu.inf.iis.bcs.request.PutSuperfileRequest;
/*      */ import com.baidu.inf.iis.bcs.request.SetObjectMetadataRequest;
/*      */ import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;
/*      */ import com.baidu.inf.iis.bcs.utils.Constants;
/*      */ import com.baidu.inf.iis.bcs.utils.Mimetypes;
/*      */ import com.baidu.inf.iis.bcs.utils.ServiceUtils;
/*      */ import flexjson.JSONSerializer;
/*      */ import java.io.BufferedOutputStream;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.security.NoSuchAlgorithmException;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ 
/*      */ public class BaiduBCS
/*      */ {
/*   82 */   private static final Log log = LogFactory.getLog(BaiduBCS.class);
/*   83 */   private BCSHttpClient bcsHttpClient = null;
/*   84 */   private BCSCredentials credentials = null;
/*   85 */   private String endpoint = null;
/*      */ 
/*      */   public BaiduBCS(BCSCredentials paramBCSCredentials, String paramString)
/*      */   {
/*  101 */     this.credentials = paramBCSCredentials;
/*  102 */     setEndpoint(paramString);
/*  103 */     this.bcsHttpClient = new BCSHttpClient(new ClientConfiguration());
/*      */   }
/*      */ 
/*      */   public BaiduBCS(BCSCredentials paramBCSCredentials, String paramString, ClientConfiguration paramClientConfiguration)
/*      */   {
/*  116 */     this.credentials = paramBCSCredentials;
/*  117 */     setEndpoint(paramString);
/*  118 */     this.bcsHttpClient = new BCSHttpClient(paramClientConfiguration);
/*      */   }
/*      */ 
/*      */   public void setCredentials(BCSCredentials paramBCSCredentials)
/*      */   {
/*  129 */     this.credentials = paramBCSCredentials;
/*      */   }
/*      */ 
/*      */   public BCSCredentials getCredentials()
/*      */   {
/*  140 */     return this.credentials;
/*      */   }
/*      */ 
/*      */   public void setDefaultEncoding(String paramString)
/*      */   {
/*  151 */     Constants.DEFAULT_ENCODING = paramString;
/*      */   }
/*      */ 
/*      */   public String getDefaultEncoding()
/*      */   {
/*  162 */     return Constants.DEFAULT_ENCODING;
/*      */   }
/*      */ 
/*      */   public void setEndpoint(String paramString)
/*      */   {
/*  173 */     if (paramString.contains("://")) {
/*  174 */       throw new IllegalArgumentException("Endpoint should not contains '://'.");
/*      */     }
/*  176 */     this.endpoint = paramString;
/*      */   }
/*      */ 
/*      */   public String getEndpoint()
/*      */   {
/*  187 */     return this.endpoint;
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<Empty> copyObject(CopyObjectRequest paramCopyObjectRequest)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  210 */     assertParameterNotNull(paramCopyObjectRequest, "The request parameter can be null.");
/*  211 */     assertParameterNotNull(paramCopyObjectRequest.getHttpMethod(), "The http method parameter in Request must be specified.");
/*  212 */     assertParameterNotNull(paramCopyObjectRequest.getSource().getBucket(), "The bucket parameter of source must be specified when copy an object.");
/*      */ 
/*  214 */     assertParameterNotNull(paramCopyObjectRequest.getSource().getObject(), "The object parameter of source must be specified when copy an object.");
/*      */ 
/*  216 */     assertParameterNotNull(paramCopyObjectRequest.getDest().getBucket(), "The bucket parameter of dest must be specified when copy an object.");
/*  217 */     assertParameterNotNull(paramCopyObjectRequest.getDest().getObject(), "The object parameter of dest must be specified when copy an object.");
/*  218 */     log.debug("copy object, src[Bucket:" + paramCopyObjectRequest.getSource().getBucket() + "][Object:" + paramCopyObjectRequest.getSource().getObject() + "] to dest[Bucket" + paramCopyObjectRequest.getDest().getBucket() + "][Object" + paramCopyObjectRequest.getDest().getObject() + "]");
/*      */ 
/*  221 */     BCSHttpRequest localBCSHttpRequest = createHttpRequest(paramCopyObjectRequest);
/*      */ 
/*  223 */     localBCSHttpRequest.addHeader("x-bs-copy-source", "bs://" + paramCopyObjectRequest.getSource().getBucket() + paramCopyObjectRequest.getSource().getObject());
/*      */ 
/*  225 */     if (null != paramCopyObjectRequest.getSourceEtag()) {
/*  226 */       localBCSHttpRequest.addHeader("x-bs-copy-source-tag", paramCopyObjectRequest.getSourceEtag());
/*      */     }
/*      */ 
/*  229 */     if (null != paramCopyObjectRequest.getSourceDirective()) {
/*  230 */       localBCSHttpRequest.addHeader("x-bs-copy-source-directive", paramCopyObjectRequest.getSourceDirective());
/*      */     }
/*      */ 
/*  233 */     populateRequestMetadata(localBCSHttpRequest, paramCopyObjectRequest.getDestMetadata());
/*      */ 
/*  235 */     return this.bcsHttpClient.execute(localBCSHttpRequest, new VoidResponseHandler());
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<Empty> copyObject(Resource paramResource1, Resource paramResource2)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  259 */     return copyObject(new CopyObjectRequest(paramResource1, paramResource2));
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<Empty> copyObject(Resource paramResource1, Resource paramResource2, ObjectMetadata paramObjectMetadata)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  285 */     return copyObject(new CopyObjectRequest(paramResource1, paramResource2, paramObjectMetadata));
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<Empty> createBucket(CreateBucketRequest paramCreateBucketRequest)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  313 */     assertParameterNotNull(paramCreateBucketRequest, "The request parameter can be null.");
/*  314 */     assertParameterNotNull(paramCreateBucketRequest.getHttpMethod(), "The http method parameter in Request must be specified.");
/*  315 */     assertParameterNotNull(paramCreateBucketRequest.getBucket(), "The bucket parameter must be specified when creating a bucket");
/*  316 */     log.debug("create bucket, bucket_name [" + paramCreateBucketRequest.getBucket() + "]");
/*      */ 
/*  318 */     BCSHttpRequest localBCSHttpRequest = createHttpRequest(paramCreateBucketRequest);
/*      */ 
/*  320 */     if (null != paramCreateBucketRequest.getAcl()) {
/*  321 */       localBCSHttpRequest.addHeader("x-bs-acl", paramCreateBucketRequest.getAcl().toString());
/*      */     }
/*      */ 
/*  324 */     return this.bcsHttpClient.execute(localBCSHttpRequest, new VoidResponseHandler());
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<Empty> createBucket(String paramString)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  352 */     return createBucket(new CreateBucketRequest(paramString));
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<Empty> deleteBucket(DeleteBucketRequest paramDeleteBucketRequest)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  375 */     assertParameterNotNull(paramDeleteBucketRequest, "The request parameter can be null.");
/*  376 */     assertParameterNotNull(paramDeleteBucketRequest.getHttpMethod(), "The http method parameter in Request must be specified.");
/*  377 */     assertParameterNotNull(paramDeleteBucketRequest.getBucket(), "The bucket parameter must be specified when deleting a bucket.");
/*  378 */     log.debug("delete bucket begin, bucket[" + paramDeleteBucketRequest.getBucket() + "]");
/*      */ 
/*  380 */     BCSHttpRequest localBCSHttpRequest = createHttpRequest(paramDeleteBucketRequest);
/*      */ 
/*  382 */     return this.bcsHttpClient.execute(localBCSHttpRequest, new VoidResponseHandler());
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<Empty> deleteBucket(String paramString)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  405 */     return deleteBucket(new DeleteBucketRequest(paramString));
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<Empty> deleteObject(DeleteObjectRequest paramDeleteObjectRequest)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  428 */     assertParameterNotNull(paramDeleteObjectRequest, "The request parameter can be null.");
/*  429 */     assertParameterNotNull(paramDeleteObjectRequest.getHttpMethod(), "The http method parameter in Request must be specified.");
/*  430 */     assertParameterNotNull(paramDeleteObjectRequest.getBucket(), "The bucket parameter must be specified when deleting an object.");
/*  431 */     assertParameterNotNull(paramDeleteObjectRequest.getObject(), "The object parameter must be specified when deleting an object.");
/*  432 */     log.debug("delete object, bucket[" + paramDeleteObjectRequest.getBucket() + "], object[" + paramDeleteObjectRequest.getObject() + "]");
/*      */ 
/*  434 */     BCSHttpRequest localBCSHttpRequest = createHttpRequest(paramDeleteObjectRequest);
/*      */ 
/*  436 */     return this.bcsHttpClient.execute(localBCSHttpRequest, new VoidResponseHandler());
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<Empty> deleteObject(String paramString1, String paramString2)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  460 */     return deleteObject(new DeleteObjectRequest(paramString1, paramString2));
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<Policy> getBucketPolicy(GetBucketPolicyRequest paramGetBucketPolicyRequest)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  481 */     assertParameterNotNull(paramGetBucketPolicyRequest, "The request parameter can be null.");
/*  482 */     assertParameterNotNull(paramGetBucketPolicyRequest.getHttpMethod(), "The http method parameter in Request must be specified.");
/*  483 */     assertParameterNotNull(paramGetBucketPolicyRequest.getBucket(), "The bucket parameter must be specified when get policy of bucket.");
/*  484 */     log.debug("get bucket policy begin, bucket[" + paramGetBucketPolicyRequest.getBucket() + "]");
/*      */ 
/*  486 */     BCSHttpRequest localBCSHttpRequest = createHttpRequest(paramGetBucketPolicyRequest);
/*      */ 
/*  488 */     localBCSHttpRequest.addParameter("acl", String.valueOf(1));
/*      */ 
/*  490 */     return this.bcsHttpClient.execute(localBCSHttpRequest, new PolicyResponseHandler());
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<Policy> getBucketPolicy(String paramString)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  511 */     return getBucketPolicy(new GetBucketPolicyRequest(paramString));
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<DownloadObject> getObject(GetObjectRequest paramGetObjectRequest)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  533 */     assertParameterNotNull(paramGetObjectRequest, "The request parameter can be null.");
/*  534 */     assertParameterNotNull(paramGetObjectRequest.getHttpMethod(), "The http method parameter in Request must be specified.");
/*  535 */     assertParameterNotNull(paramGetObjectRequest.getBucket(), "The bucket parameter must be specified when getting an object.");
/*  536 */     assertParameterNotNull(paramGetObjectRequest.getObject(), "The object parameter must be specified when getting an object.");
/*  537 */     log.debug("get object begin, bucket[" + paramGetObjectRequest.getBucket() + "], object[" + paramGetObjectRequest.getObject() + "]");
/*      */ 
/*  540 */     BCSHttpRequest localBCSHttpRequest = createHttpRequest(paramGetObjectRequest);
/*      */ 
/*  542 */     if (null != paramGetObjectRequest.getRange()) {
/*  543 */       Pair localPair = paramGetObjectRequest.getRange();
/*  544 */       assertParameterNotNull(localPair.getFirst(), "The range first parameter must be specified when getting an object by range.");
/*  545 */       assertParameterNotNull(localPair.getSecond(), "The range second parameter must be specified when getting an object by range.");
/*  546 */       localBCSHttpRequest.addHeader("Range", "bytes=" + Long.toString(((Long)localPair.getFirst()).longValue()) + "-" + Long.toString(((Long)localPair.getSecond()).longValue()));
/*      */     }
/*      */ 
/*  549 */     return this.bcsHttpClient.execute(localBCSHttpRequest, new ObjectResponseHandler());
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<DownloadObject> getObject(GetObjectRequest paramGetObjectRequest, File paramFile)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  573 */     assertParameterNotNull(paramFile, "The destination file parameter must be specified when downloading an object directly to a file.");
/*  574 */     BaiduBCSResponse localBaiduBCSResponse = getObject(paramGetObjectRequest);
/*  575 */     DownloadObject localDownloadObject = (DownloadObject)localBaiduBCSResponse.getResult();
/*  576 */     if (null == localDownloadObject) {
/*  577 */       throw new BCSClientException("Get object response is empty.");
/*      */     }
/*  579 */     BufferedOutputStream localBufferedOutputStream = null;
/*      */     try {
/*  581 */       localBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(paramFile));
/*  582 */       byte[] arrayOfByte = new byte[10240];
/*      */       int i;
/*  584 */       while ((i = localDownloadObject.getContent().read(arrayOfByte)) > -1)
/*  585 */         localBufferedOutputStream.write(arrayOfByte, 0, i);
/*      */     }
/*      */     catch (IOException localIOException) {
/*  588 */       throw new BCSClientException("Unable to store object contents to disk: " + localIOException.getMessage(), localIOException);
/*      */     } finally {
/*      */       try {
/*  591 */         localBufferedOutputStream.close();
/*      */       } catch (Exception localException3) {
/*      */       }
/*      */       try {
/*  595 */         localDownloadObject.getContent().close();
/*      */       }
/*      */       catch (Exception localException4)
/*      */       {
/*      */       }
/*      */     }
/*  601 */     if (localDownloadObject.getObjectMetadata().getContentLength() != paramFile.length()) {
/*  602 */       BCSServiceException localBCSServiceException = new BCSServiceException("Maybe download incompletely. http Content-Length=" + localDownloadObject.getObjectMetadata().getContentLength() + " ,download size=" + paramFile.length());
/*      */ 
/*  604 */       localBCSServiceException.setBcsErrorCode(0);
/*  605 */       localBCSServiceException.setBcsErrorMessage("");
/*  606 */       localBCSServiceException.setHttpErrorCode(200);
/*  607 */       localBCSServiceException.setRequestId(localBaiduBCSResponse.getRequestId());
/*      */ 
/*  609 */       throw localBCSServiceException;
/*      */     }
/*  611 */     return localBaiduBCSResponse;
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<DownloadObject> getObject(String paramString1, String paramString2)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  634 */     return getObject(new GetObjectRequest(paramString1, paramString2));
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<ObjectMetadata> getObjectMetadata(GetObjectMetadataRequest paramGetObjectMetadataRequest)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  657 */     BCSHttpRequest localBCSHttpRequest = createHttpRequest(paramGetObjectMetadataRequest);
/*  658 */     return this.bcsHttpClient.execute(localBCSHttpRequest, new ObjectMetadataResponseHandler());
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<ObjectMetadata> getObjectMetadata(String paramString1, String paramString2)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  681 */     return getObjectMetadata(new GetObjectMetadataRequest(paramString1, paramString2));
/*      */   }
/*      */ 
/*      */   public boolean doesObjectExist(String paramString1, String paramString2)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*      */     try
/*      */     {
/*  705 */       getObjectMetadata(paramString1, paramString2);
/*      */     } catch (BCSServiceException localBCSServiceException) {
/*  707 */       if (404 == localBCSServiceException.getHttpErrorCode()) {
/*  708 */         return false;
/*      */       }
/*  710 */       throw localBCSServiceException;
/*      */     } catch (BCSClientException localBCSClientException) {
/*  712 */       throw localBCSClientException;
/*      */     }
/*  714 */     return true;
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<Policy> getObjectPolicy(GetObjectPolicyRequest paramGetObjectPolicyRequest)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  735 */     assertParameterNotNull(paramGetObjectPolicyRequest, "The request parameter can be null.");
/*  736 */     assertParameterNotNull(paramGetObjectPolicyRequest.getHttpMethod(), "The http method parameter in Request must be specified.");
/*  737 */     assertParameterNotNull(paramGetObjectPolicyRequest.getBucket(), "The bucket parameter must be specified when getting policy of an object.");
/*  738 */     assertParameterNotNull(paramGetObjectPolicyRequest.getObject(), "The object parameter must be specified when getting policy of an object.");
/*  739 */     log.debug("get object policy, bucket[" + paramGetObjectPolicyRequest.getBucket() + "]" + ", object[" + paramGetObjectPolicyRequest.getObject() + "]");
/*      */ 
/*  742 */     BCSHttpRequest localBCSHttpRequest = createHttpRequest(paramGetObjectPolicyRequest);
/*      */ 
/*  744 */     localBCSHttpRequest.addParameter("acl", String.valueOf(1));
/*      */ 
/*  746 */     return this.bcsHttpClient.execute(localBCSHttpRequest, new PolicyResponseHandler());
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<Policy> getObjectPolicy(String paramString1, String paramString2)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  768 */     return getObjectPolicy(new GetObjectPolicyRequest(paramString1, paramString2));
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<List<BucketSummary>> listBucket(ListBucketRequest paramListBucketRequest)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  790 */     assertParameterNotNull(paramListBucketRequest, "The request parameter can be null.");
/*  791 */     assertParameterNotNull(paramListBucketRequest.getHttpMethod(), "The http method parameter in Request must be specified.");
/*      */ 
/*  794 */     BCSHttpRequest localBCSHttpRequest = createHttpRequest(paramListBucketRequest);
/*      */ 
/*  796 */     return this.bcsHttpClient.execute(localBCSHttpRequest, new BucketListResponseHandler());
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<ObjectListing> listObject(ListObjectRequest paramListObjectRequest)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  818 */     assertParameterNotNull(paramListObjectRequest, "The request parameter can be null.");
/*  819 */     assertParameterNotNull(paramListObjectRequest.getHttpMethod(), "The http method parameter in Request must be specified.");
/*  820 */     assertParameterNotNull(paramListObjectRequest.getBucket(), "The bucket parameter must be specified when listing an bucket.");
/*  821 */     log.debug("list object begin, bucket[" + paramListObjectRequest.getBucket() + "]");
/*      */ 
/*  824 */     BCSHttpRequest localBCSHttpRequest = createHttpRequest(paramListObjectRequest);
/*      */ 
/*  826 */     if ((null != paramListObjectRequest.getPrefix()) && (0 != paramListObjectRequest.getPrefix().length())) {
/*  827 */       localBCSHttpRequest.addParameter("prefix", paramListObjectRequest.getPrefix());
/*      */     }
/*      */ 
/*  830 */     if (paramListObjectRequest.getStart() >= 0) {
/*  831 */       localBCSHttpRequest.addParameter("start", String.valueOf(paramListObjectRequest.getStart()));
/*      */     }
/*      */ 
/*  834 */     if (paramListObjectRequest.getLimit() >= 0) {
/*  835 */       localBCSHttpRequest.addParameter("limit", String.valueOf(paramListObjectRequest.getLimit()));
/*      */     }
/*      */ 
/*  838 */     if (paramListObjectRequest.getListModel() != 0) {
/*  839 */       localBCSHttpRequest.addParameter("dir", String.valueOf(paramListObjectRequest.getListModel()));
/*      */     }
/*      */ 
/*  842 */     return this.bcsHttpClient.execute(localBCSHttpRequest, new ObjectListResponseHandler());
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<Empty> putBucketPolicy(PutBucketPolicyRequest paramPutBucketPolicyRequest)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  864 */     assertParameterNotNull(paramPutBucketPolicyRequest, "The request parameter can be null.");
/*  865 */     assertParameterNotNull(paramPutBucketPolicyRequest.getHttpMethod(), "The http method parameter in Request must be specified.");
/*  866 */     assertParameterNotNull(paramPutBucketPolicyRequest.getBucket(), "The bucket parameter must be specified when setting policy or acl to a bucket.");
/*      */ 
/*  868 */     log.debug("put bucket policy begin, bucket[" + paramPutBucketPolicyRequest.getBucket() + "]");
/*  869 */     if ((null != paramPutBucketPolicyRequest.getPolicy()) && (null != paramPutBucketPolicyRequest.getAcl())) {
/*  870 */       throw new BCSClientException("Can set policy or acl to bucket at the same time.");
/*      */     }
/*      */ 
/*  874 */     BCSHttpRequest localBCSHttpRequest = createHttpRequest(paramPutBucketPolicyRequest);
/*      */ 
/*  876 */     localBCSHttpRequest.addParameter("acl", String.valueOf(1));
/*      */ 
/*  878 */     if (null != paramPutBucketPolicyRequest.getPolicy()) {
/*  879 */       String str = paramPutBucketPolicyRequest.getPolicy().toJson();
/*  880 */       byte[] arrayOfByte = ServiceUtils.toByteArray(str);
/*  881 */       localBCSHttpRequest.setContent(new ByteArrayInputStream(arrayOfByte));
/*  882 */       localBCSHttpRequest.addHeader("Content-Length", String.valueOf(arrayOfByte.length));
/*  883 */     } else if (null != paramPutBucketPolicyRequest.getAcl()) {
/*  884 */       localBCSHttpRequest.addHeader("x-bs-acl", paramPutBucketPolicyRequest.getAcl().toString());
/*      */     }
/*      */ 
/*  890 */     return this.bcsHttpClient.execute(localBCSHttpRequest, new VoidResponseHandler());
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<Empty> putBucketPolicy(String paramString, Policy paramPolicy)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  913 */     return putBucketPolicy(new PutBucketPolicyRequest(paramString, paramPolicy));
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<Empty> putBucketPolicy(String paramString, X_BS_ACL paramX_BS_ACL)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  936 */     return putBucketPolicy(new PutBucketPolicyRequest(paramString, paramX_BS_ACL));
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<ObjectMetadata> putObject(PutObjectRequest paramPutObjectRequest)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/*  958 */     assertParameterNotNull(paramPutObjectRequest, "The request parameter can be null.");
/*  959 */     assertParameterNotNull(paramPutObjectRequest.getHttpMethod(), "The http method parameter in Request must be specified.");
/*  960 */     assertParameterNotNull(paramPutObjectRequest.getBucket(), "The bucket parameter must be specified when uploading an object.");
/*  961 */     assertParameterNotNull(paramPutObjectRequest.getObject(), "The object parameter must be specified when uploading an object.");
/*  962 */     log.debug("put object begin,bucket[" + paramPutObjectRequest.getBucket() + "], object[" + paramPutObjectRequest.getObject() + "]");
/*      */ 
/*  965 */     BCSHttpRequest localBCSHttpRequest = createHttpRequest(paramPutObjectRequest);
/*      */ 
/*  968 */     ObjectMetadata localObjectMetadata = paramPutObjectRequest.getMetadata();
/*  969 */     if (localObjectMetadata == null) {
/*  970 */       paramPutObjectRequest.setMetadata(new ObjectMetadata());
/*  971 */       localObjectMetadata = paramPutObjectRequest.getMetadata();
/*      */     }
/*      */     Object localObject1;
/*  973 */     if (paramPutObjectRequest.getFile() != null) {
/*  974 */       File localObject2 = paramPutObjectRequest.getFile();
/*  975 */       localObjectMetadata.setContentLength(((File)localObject2).length());
/*  976 */       if (localObjectMetadata.getContentType() == null) {
/*  977 */         localObjectMetadata.setContentType(Mimetypes.getInstance().getMimetype((File)localObject2));
/*      */       }
/*  979 */       FileInputStream localFileInputStream = null;
/*      */       try {
/*  981 */         localFileInputStream = new FileInputStream((File)localObject2);
/*  982 */         byte[] arrayOfByte = ServiceUtils.computeMD5Hash(localFileInputStream);
/*  983 */         localObjectMetadata.setContentMD5(ServiceUtils.toHex(arrayOfByte));
/*      */       } catch (Exception localException2) {
/*  985 */         throw new BCSClientException("Unable to calculate MD5 hash: " + localException2.getMessage(), localException2);
/*      */       } finally {
/*      */         try {
/*  988 */           localFileInputStream.close();
/*      */         } catch (Exception localException4) {
/*      */         }
/*      */       }
/*      */       try {
/*  993 */         localObject1 = new RepeatableFileInputStream((File)localObject2);
/*      */       } catch (FileNotFoundException localFileNotFoundException) {
/*  995 */         throw new BCSClientException("Unable to find file to upload", localFileNotFoundException);
/*      */       }
/*      */     } else {
/*  998 */       localObject1 = paramPutObjectRequest.getObjectContent();
/*  999 */       if (null == localObjectMetadata) {
/* 1000 */         throw new BCSClientException("Put object by Inputstream. Must have Content-Length in objectMetadata.");
/*      */       }
/*      */     }
/* 1003 */     if (localObjectMetadata.getContentLength() < 0L) {
/* 1004 */       throw new BCSClientException("Content-Length could not be empty.");
/*      */     }
/*      */ 
/* 1007 */     Object localObject2 = null;
/* 1008 */     if (localObjectMetadata.getContentMD5() == null) {
/*      */       try {
/* 1010 */         localObject2 = new MD5DigestCalculatingInputStream((InputStream)localObject1);
/* 1011 */         localObject1 = localObject2;
/*      */       } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
/* 1013 */         log.warn("No MD5 digest algorithm available.  Unable to calculate checksum and verify data integrity.", localNoSuchAlgorithmException);
/*      */       }
/*      */     }
/*      */ 
/* 1017 */     localBCSHttpRequest.setContent((InputStream)localObject1);
/*      */ 
/* 1019 */     if (null != paramPutObjectRequest.getAcl()) {
/* 1020 */       localBCSHttpRequest.addHeader("x-bs-acl", paramPutObjectRequest.getAcl().toString());
/*      */     }
/*      */ 
/* 1023 */     if (localObjectMetadata.getContentType() == null) {
/* 1024 */       localObjectMetadata.setContentType("application/octet-stream");
/*      */     }
/*      */ 
/* 1027 */     populateRequestMetadata(localBCSHttpRequest, localObjectMetadata);
/*      */ 
/* 1030 */     BaiduBCSResponse localBaiduBCSResponse = this.bcsHttpClient.execute(localBCSHttpRequest, new ObjectMetadataResponseHandler());
/*      */     try
/*      */     {
/* 1033 */       ((InputStream)localObject1).close();
/*      */     } catch (Exception localException3) {
/* 1035 */       log.warn("Unable to cleanly close input stream: " + localException3.getMessage(), localException3);
/*      */     }
/*      */ 
/* 1039 */     String str = localObjectMetadata.getContentMD5();
/* 1040 */     if (null != localObject2) {
/* 1041 */       str = ServiceUtils.toHex(((MD5DigestCalculatingInputStream)localObject2).getMd5Digest());
/*      */     }
/* 1043 */     if (!str.equalsIgnoreCase(((ObjectMetadata)localBaiduBCSResponse.getResult()).getContentMD5())) {
/* 1044 */       throw new BCSClientException("Client calculated content md5 didn't match md5 calculated by Baidu BCS. ");
/*      */     }
/*      */ 
/* 1047 */     return localBaiduBCSResponse;
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<ObjectMetadata> putObject(String paramString1, String paramString2, File paramFile)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/* 1071 */     return putObject(new PutObjectRequest(paramString1, paramString2, paramFile));
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<ObjectMetadata> putObject(String paramString1, String paramString2, InputStream paramInputStream, ObjectMetadata paramObjectMetadata)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/* 1097 */     return putObject(new PutObjectRequest(paramString1, paramString2, paramInputStream, paramObjectMetadata));
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<Empty> putObjectPolicy(PutObjectPolicyRequest paramPutObjectPolicyRequest)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/* 1119 */     assertParameterNotNull(paramPutObjectPolicyRequest, "The request parameter can be null.");
/* 1120 */     assertParameterNotNull(paramPutObjectPolicyRequest.getHttpMethod(), "The http method parameter in Request must be specified.");
/* 1121 */     assertParameterNotNull(paramPutObjectPolicyRequest.getBucket(), "The bucket parameter must be specified when setting policy or acl to an object.");
/*      */ 
/* 1123 */     assertParameterNotNull(paramPutObjectPolicyRequest.getObject(), "The object parameter must be specified when setting policy or acl to an object.");
/*      */ 
/* 1125 */     log.debug("put object policy begin, bucket[" + paramPutObjectPolicyRequest.getBucket() + "]");
/* 1126 */     if ((null != paramPutObjectPolicyRequest.getPolicy()) && (null != paramPutObjectPolicyRequest.getAcl())) {
/* 1127 */       throw new BCSClientException("Can set policy or acl to object at the same time.");
/*      */     }
/*      */ 
/* 1130 */     BCSHttpRequest localBCSHttpRequest = createHttpRequest(paramPutObjectPolicyRequest);
/*      */ 
/* 1132 */     localBCSHttpRequest.addParameter("acl", String.valueOf(1));
/*      */ 
/* 1134 */     if (null != paramPutObjectPolicyRequest.getPolicy()) {
/* 1135 */       String str = paramPutObjectPolicyRequest.getPolicy().toJson();
/* 1136 */       byte[] arrayOfByte = ServiceUtils.toByteArray(str);
/* 1137 */       ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
/* 1138 */       localBCSHttpRequest.setContent(localByteArrayInputStream);
/* 1139 */       localBCSHttpRequest.addHeader("Content-Length", String.valueOf(arrayOfByte.length));
/* 1140 */     } else if (null != paramPutObjectPolicyRequest.getAcl()) {
/* 1141 */       localBCSHttpRequest.addHeader("x-bs-acl", paramPutObjectPolicyRequest.getAcl().toString());
/*      */     }
/*      */ 
/* 1146 */     return this.bcsHttpClient.execute(localBCSHttpRequest, new VoidResponseHandler());
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<Empty> putObjectPolicy(String paramString1, String paramString2, Policy paramPolicy)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/* 1170 */     return putObjectPolicy(new PutObjectPolicyRequest(paramString1, paramString2, paramPolicy));
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<Empty> putObjectPolicy(String paramString1, String paramString2, X_BS_ACL paramX_BS_ACL)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/* 1194 */     return putObjectPolicy(new PutObjectPolicyRequest(paramString1, paramString2, paramX_BS_ACL));
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<ObjectMetadata> putSuperfile(PutSuperfileRequest paramPutSuperfileRequest)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/* 1216 */     assertParameterNotNull(paramPutSuperfileRequest, "The request parameter can be null.");
/* 1217 */     assertParameterNotNull(paramPutSuperfileRequest.getHttpMethod(), "The http method parameter in Request must be specified.");
/* 1218 */     assertParameterNotNull(paramPutSuperfileRequest.getSubObjectList(), "The sub-object list parameter in Request must be specified.");
/* 1219 */     assertParameterNotNull(paramPutSuperfileRequest.getBucket(), "The bucket parameter must be specified when creating a superfile.");
/* 1220 */     assertParameterNotNull(paramPutSuperfileRequest.getObject(), "The object parameter must be specified when creating a superfile.");
/*      */ 
/* 1223 */     BCSHttpRequest localBCSHttpRequest = createHttpRequest(paramPutSuperfileRequest);
/*      */ 
/* 1225 */     HashMap localHashMap = new HashMap();
/* 1226 */     localHashMap.put("object_list", new LinkedHashMap());
/* 1227 */     for (int i = 0; i < paramPutSuperfileRequest.getSubObjectList().size(); i++) {
/* 1228 */       SuperfileSubObject localObject = (SuperfileSubObject)paramPutSuperfileRequest.getSubObjectList().get(i);
/* 1229 */       ((Map)localHashMap.get("object_list")).put("part_" + i, new HashMap());
/* 1230 */       ((Map)((Map)localHashMap.get("object_list")).get("part_" + i)).put("url", "bs://" + ((SuperfileSubObject)localObject).getBucket() + ((SuperfileSubObject)localObject).getObject());
/* 1231 */       ((Map)((Map)localHashMap.get("object_list")).get("part_" + i)).put("etag", ((SuperfileSubObject)localObject).getEtag());
/*      */     }
/* 1233 */     JSONSerializer localJSONSerializer = new JSONSerializer();
/* 1234 */     Object localObject = localJSONSerializer.deepSerialize(localHashMap);
/* 1235 */     byte[] arrayOfByte = ServiceUtils.toByteArray((String)localObject);
/* 1236 */     localBCSHttpRequest.setContent(new ByteArrayInputStream(arrayOfByte));
/* 1237 */     localBCSHttpRequest.addHeader("Content-Length", String.valueOf(arrayOfByte.length));
/*      */ 
/* 1239 */     localBCSHttpRequest.addParameter("superfile", String.valueOf(1));
/*      */ 
/* 1241 */     if (null != paramPutSuperfileRequest.getObjectMetadata()) {
/* 1242 */       populateRequestMetadata(localBCSHttpRequest, paramPutSuperfileRequest.getObjectMetadata());
/*      */     }
/* 1244 */     return this.bcsHttpClient.execute(localBCSHttpRequest, new ObjectMetadataResponseHandler());
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<ObjectMetadata> putSuperfile(String paramString1, String paramString2, List<SuperfileSubObject> paramList)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/* 1269 */     return putSuperfile(new PutSuperfileRequest(paramString1, paramString2, paramList));
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<ObjectMetadata> putSuperfile(String paramString1, String paramString2, ObjectMetadata paramObjectMetadata, List<SuperfileSubObject> paramList)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/* 1295 */     return putSuperfile(new PutSuperfileRequest(paramString1, paramString2, paramObjectMetadata, paramList));
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<Empty> setObjectMetadata(SetObjectMetadataRequest paramSetObjectMetadataRequest)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/* 1319 */     assertParameterNotNull(paramSetObjectMetadataRequest, "The request parameter can be null.");
/* 1320 */     assertParameterNotNull(paramSetObjectMetadataRequest.getHttpMethod(), "The http method parameter in Request must be specified.");
/* 1321 */     assertParameterNotNull(paramSetObjectMetadataRequest.getBucket(), "The bucket parameter must be specified when setting object meta.");
/* 1322 */     assertParameterNotNull(paramSetObjectMetadataRequest.getObject(), "The object parameter must be specified when setting object meta.");
/*      */ 
/* 1325 */     BCSHttpRequest localBCSHttpRequest = createHttpRequest(paramSetObjectMetadataRequest);
/*      */ 
/* 1327 */     if (null != paramSetObjectMetadataRequest.getMetadata()) {
/* 1328 */       paramSetObjectMetadataRequest.getMetadata().setContentMD5("");
/* 1329 */       paramSetObjectMetadataRequest.getMetadata().setContentLength(0L);
/*      */     }
/*      */ 
/* 1332 */     populateRequestMetadata(localBCSHttpRequest, paramSetObjectMetadataRequest.getMetadata());
/*      */ 
/* 1334 */     localBCSHttpRequest.addHeader("x-bs-copy-source", "bs://" + paramSetObjectMetadataRequest.getBucket() + paramSetObjectMetadataRequest.getObject());
/*      */ 
/* 1336 */     return this.bcsHttpClient.execute(localBCSHttpRequest, new VoidResponseHandler());
/*      */   }
/*      */ 
/*      */   public BaiduBCSResponse<Empty> setObjectMetadata(String paramString1, String paramString2, ObjectMetadata paramObjectMetadata)
/*      */     throws BCSClientException, BCSServiceException
/*      */   {
/* 1362 */     return setObjectMetadata(new SetObjectMetadataRequest(paramString1, paramString2, paramObjectMetadata));
/*      */   }
/*      */ 
/*      */   public String generateUrl(GenerateUrlRequest paramGenerateUrlRequest) {
/* 1366 */     assertParameterNotNull(paramGenerateUrlRequest.getHttpMethod(), "The http method parameter in Request must be specified.");
/* 1367 */     assertParameterNotNull(paramGenerateUrlRequest.getBucket(), "The bucket parameter must be specified.");
/* 1368 */     assertParameterNotNull(paramGenerateUrlRequest.getObject(), "The object parameter must be specified.");
/*      */ 
/* 1371 */     BCSHttpRequest localBCSHttpRequest = createHttpRequest(paramGenerateUrlRequest);
/* 1372 */     BCSSigner.sign(paramGenerateUrlRequest, localBCSHttpRequest, this.credentials, paramGenerateUrlRequest.getBcsSignCondition());
/*      */ 
/* 1374 */     return this.bcsHttpClient.getHttpRequestFactory().buildUri(this.bcsHttpClient.getConfig(), localBCSHttpRequest);
/*      */   }
/*      */ 
/*      */   private BCSHttpRequest createHttpRequest(BaiduBCSRequest paramBaiduBCSRequest)
/*      */   {
/* 1392 */     DefaultBCSHttpRequest localDefaultBCSHttpRequest = new DefaultBCSHttpRequest(paramBaiduBCSRequest);
/*      */ 
/* 1394 */     localDefaultBCSHttpRequest.setEndpoint(this.endpoint);
/*      */ 
/* 1396 */     localDefaultBCSHttpRequest.setResourcePath(buildResourcePath(paramBaiduBCSRequest.getBucket(), paramBaiduBCSRequest.getObject()));
/*      */ 
/* 1398 */     localDefaultBCSHttpRequest.setHttpMethod(paramBaiduBCSRequest.getHttpMethod());
/*      */ 
/* 1400 */     BCSSigner.sign(paramBaiduBCSRequest, localDefaultBCSHttpRequest, this.credentials);
/* 1401 */     return localDefaultBCSHttpRequest;
/*      */   }
/*      */ 
/*      */   private void populateRequestMetadata(BCSHttpRequest paramBCSHttpRequest, ObjectMetadata paramObjectMetadata)
/*      */   {
/* 1413 */     if (null == paramObjectMetadata) {
/* 1414 */       log.debug("populateRequestMetadata, metadata is null");
/* 1415 */       return;
/*      */     }
/* 1417 */     Map localMap = paramObjectMetadata.getRawMetadata();
Iterator localObject1;
/* 1418 */     if (localMap != null)
/* 1419 */       for (localObject1 = localMap.entrySet().iterator(); ((Iterator)localObject1).hasNext(); ) { Entry localObject2 = (Map.Entry)((Iterator)localObject1).next();
/* 1420 */         paramBCSHttpRequest.addHeader((String)((Map.Entry)localObject2).getKey(), ((Map.Entry)localObject2).getValue().toString());
/*      */       }
/*      */     Object localObject2;
/* 1423 */     Object localObject11 = paramObjectMetadata.getUserMetadata();
/* 1424 */     if (localObject11 != null)
/* 1425 */       for (localObject2 = ((Map)localObject11).entrySet().iterator(); ((Iterator)localObject2).hasNext(); ) { Map.Entry localEntry = (Map.Entry)((Iterator)localObject2).next();
/* 1426 */         paramBCSHttpRequest.addHeader("x-bs-meta-" + (String)localEntry.getKey(), (String)localEntry.getValue());
/*      */       }
/*      */   }
/*      */ 
/*      */   private void assertParameterNotNull(Object paramObject, String paramString)
/*      */   {
/* 1432 */     if (null == paramObject)
/* 1433 */       throw new IllegalArgumentException(paramString);
/*      */   }
/*      */ 
/*      */   private String buildResourcePath(String paramString1, String paramString2)
/*      */   {
/* 1438 */     if (!paramString2.startsWith("/")) {
/* 1439 */       throw new BCSClientException("BCS object must start with a slash.");
/*      */     }
/* 1441 */     StringBuilder localStringBuilder = new StringBuilder();
/* 1442 */     localStringBuilder.append(paramString1);
/* 1443 */     if (!paramString2.equals("/")) {
/* 1444 */       localStringBuilder.append(ServiceUtils.urlEncode(paramString2));
/*      */     }
/* 1446 */     return localStringBuilder.toString();
/*      */   }
/*      */ }

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.BaiduBCS
 * JD-Core Version:    0.6.2
 */