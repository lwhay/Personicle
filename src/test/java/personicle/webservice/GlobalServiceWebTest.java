package personicle.webservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.asterix.common.config.GlobalConfig;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.Level;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

public class GlobalServiceWebTest {
    private static Logger LOGGER = Logger.getLogger(GlobalServiceWebTest.class);

    private static HttpClient client = new HttpClient();

    private static String[] handleError(HttpMethod method) throws Exception {
        String errorBody = method.getResponseBodyAsString();
        JSONObject result = JSON.parseObject(errorBody);

        String[] errors = {result.getJSONArray("error-code").getString(0), result.getString("summary"),
                result.getString("stacktrace")};
        return errors;
    }

    public static void main(String[] args) throws IOException {
        long begin = System.currentTimeMillis();
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet();
        for (int i = 0; i < 0; i++) {
            String url = "http://" + args[0] + ":10901/GlobalService/getGeoCodeDouble";
            LOGGER.debug(String.format("Request url:%s", url));
            String responseMsg = "";
            url += "/arg0/" + 9.9 * (i + 1) + "/arg1/" + 1.9 * (i + 1);
            try {
                httpget.setHeader("Content-Type", "application/xml; charset=utf-8");
                httpget.setURI(new URI(url));
                HttpResponse response = httpClient.execute(httpget);
                if (i % 100 == 0) System.out.println(response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                responseMsg = EntityUtils.toString(entity, "utf-8");
                if (i % 100 == 0) System.out.println(responseMsg);
            } catch (Exception e) {
                throw new IOException("webservice请求异常", e);
            }
        }
        httpClient.getConnectionManager().shutdown();
        httpget.releaseConnection();
        System.out.println(System.currentTimeMillis() - begin);
        begin = System.currentTimeMillis();

        httpClient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost();
        for (int i = 0; i < 0; i++) {
            String url = "http://localhost:10901/GlobalService/getUUID";
            LOGGER.debug(String.format("Request url:%s", url));
            String responseMsg = null;
            try {
                /*List<BasicNameValuePair> list = new LinkedList<>();
                BasicNameValuePair param1 = new BasicNameValuePair("name", "root");
                BasicNameValuePair param2 = new BasicNameValuePair("password", "123456");
                list.add(param1);
                list.add(param2);
                // 使用URL实体转换工具
                UrlEncodedFormEntity entityParam = new UrlEncodedFormEntity(list, "UTF-8");
                httppost.setEntity(entityParam);*/

                // HttpEntity re = new StringEntity("", "utf-8");
                // httppost.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
                // httppost.setHeader("Content-Type", "application/xml;charset=utf-8");
                // httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
                httppost.setHeader("Content-Type", "application/xml;charset=utf-8");
                // httppost.setEntity(re);
                httppost.setURI(new URI(url));
                HttpResponse response = httpClient.execute(httppost);
                System.out.println(response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                responseMsg = EntityUtils.toString(entity, "utf-8");
                System.out.println(responseMsg);
            } catch (Exception e) {
                throw new IOException("webservice请求异常", e);
            } finally {
                httpClient.getConnectionManager().shutdown();
            }
        }
        httpClient.getConnectionManager().shutdown();
        httppost.releaseConnection();
        System.out.println(System.currentTimeMillis() - begin);
        begin = System.currentTimeMillis();

        PostMethod post = new PostMethod();
        for (int i = 0; i < 1; i++) {
            String url = "http://localhost:10901/GlobalService/getUUID";
            post.setPath(url);
            post.setRequestHeader("Content-Type", "text/xml; charset=utf-8");
            // post.setRequestHeader("content-type", "application/x-www-form-urlencoded");
            // post.setRequestHeader("SOAPAction", "urn:mediate");
            /*post.setRequestBody(new NameValuePair[]{
                    new NameValuePair("record", "{id:123,userName:\"Michael Jordan\"}")
            });*/
            int ret = HttpStatus.SC_METHOD_FAILURE;
            String result;
            try {
                ret = client.executeMethod(post);
                System.out.println("ret: " + ret);
                InputStream is = post.getResponseBodyAsStream();
                if (is != null) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(is));
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                    }
                    result = sb.toString();
                    System.out.println(result);
                }
            } catch (IOException e) {
                LOGGER.error("Error while sending notification: " + e.getMessage());
                e.printStackTrace();
            } finally {
                LOGGER.info("ret: " + ret);
            }
        }
        httpClient.getConnectionManager().shutdown();
        post.releaseConnection();
        System.out.println(System.currentTimeMillis() - begin);
    }
}
