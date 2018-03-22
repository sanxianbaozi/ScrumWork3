package homework;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class httphelp {
	public static String jsessionid;
	public static HttpResponse doPost(String url,Map<String,String> params,String cookie){
		String result=null;
		BasicCookieStore cookieStore = new BasicCookieStore();
		HttpResponse httpResponse=null;
	  try{
	//创建httpclient
	 CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
	//创建post对象
	 HttpPost post = new HttpPost(url);
	// 3. 设置POST请求传递参数
	 HttpEntity requestHttpEntity = null;
		 if (null != params) {  
             List<NameValuePair> pairList = new ArrayList<NameValuePair>(  params.size());  
             for (Map.Entry<String, String> entry : params.entrySet()) {  
                 NameValuePair pair = new BasicNameValuePair(entry.getKey(),entry.getValue());  
                 pairList.add(pair);  
             }  
             requestHttpEntity = new UrlEncodedFormEntity(pairList, "UTF-8"); 
          } 
		
	post.setEntity(requestHttpEntity);//带上参数 
	if(null!=cookie)
		post.setHeader("Cookie", cookie);//带上cookie
	httpResponse = httpClient.execute(post);//响应结果 
	/*
	//当提交无论怎么样都是200，成功转到下一页，否则转到登录界面，判断是不必要的但是还是放着
    if (httpResponse.getStatusLine().getStatusCode() == 200) {//如果是200  表示成功  
			result = EntityUtils.toString(httpResponse.getEntity());//把结果取出来  是一个STRING类型的 
			if(httpResponse.getFirstHeader("Transfer-Encoding")!=null)
				return "LOGIN SUCCESS";
			
		}*/
   
    }catch(Exception e){
			e.printStackTrace();
		}
	return httpResponse;
		
  }
	
	public static String doGet(String url){
		// 1. 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2. 创建HttpGet对象
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            // 3. 执行GET请求
            response = httpClient.execute(httpGet);
            System.out.println(response.getStatusLine());
            
            // 4. 获取响应实体
            HttpEntity entity = response.getEntity();
            // 5. 处理响应实体
            if (entity != null) {
                System.out.println("长度：" + entity.getContentLength());
                System.out.println("内容：" + EntityUtils.toString(entity));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 6. 释放资源
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return null;
	}
	public static String get(){
		try{
		URL url=new URL("http://jwcxxcx.ccsu.cn/jwxt/");
		HttpURLConnection hc=(HttpURLConnection)url.openConnection();
		//hc.connect();
		Map<String,List<String>> map=hc.getHeaderFields();
		for(Entry<String,List<String>> entry:map.entrySet())
		{
		   System.out.println("检验数据："+entry.getKey()+":"+entry.getValue());
		}
		System.out.println("Get 返回 "+map.get("Set-Cookie").get(0).split(";")[0]);
		return map.get("Set-Cookie").get(0).split(";")[0];
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void log(String message)
	{
		System.out.println(message);
	}
	public static String doGet(String url,String cookie){
		String result="test";
		// 1. 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2. 创建HttpGet对象
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;       
        try {
            // 3. 执行GET请求
        	httpGet.setHeader("cookie", cookie);
            response = httpClient.execute(httpGet);
            System.out.println(response.getStatusLine());
            
            // 4. 获取响应实体
            HttpEntity entity = response.getEntity();
            // 5. 处理响应实体
            if (entity != null) {
                System.out.println("长度：" + entity.getContentLength());
                result = EntityUtils.toString(entity);
                System.out.println("内容：" +result);;
                return result;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    	return result;
	}

}
