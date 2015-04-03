package com.baidu.inf.iis.bcs.http;

import com.baidu.inf.iis.bcs.request.BaiduBCSRequest;
import java.io.InputStream;
import java.util.Map;

public abstract interface BCSHttpRequest
{
  public abstract void addHeader(String paramString1, String paramString2);

  public abstract void addParameter(String paramString1, String paramString2);

  public abstract InputStream getContent();

  public abstract String getEndpoint();

  public abstract Map<String, String> getHeaders();

  public abstract HttpMethodName getHttpMethod();

  public abstract BaiduBCSRequest getOriginalRequest();

  public abstract Map<String, String> getParameters();

  public abstract String getResourcePath();

  public abstract String getServiceName();

  public abstract void setContent(InputStream paramInputStream);

  public abstract void setEndpoint(String paramString);

  public abstract void setHttpMethod(HttpMethodName paramHttpMethodName);

  public abstract void setResourcePath(String paramString);

  public abstract BCSHttpRequest withParameter(String paramString1, String paramString2);
}

/* Location:           C:\Users\i058959\Downloads\homekits\Baidu-BCS-SDK-Java-1.4.5\bcs-sdk-java_1.4.5.jar
 * Qualified Name:     com.baidu.inf.iis.bcs.http.BCSHttpRequest
 * JD-Core Version:    0.6.2
 */