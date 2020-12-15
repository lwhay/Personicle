package personicle.webservice;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class WebServerTestDemo {

    // 日志记录器
    private static Logger logger = Logger.getLogger(WebServerTestDemo.class);

    /*
     * public static String postByMap(String url, Map<String, Object> pv) throws
     * SystemException{ logger.debug(String.format("Request url:%s", url));
     * String responseMsg = ""; PostMethod postMethod = null; try { HttpClient
     * httpClient = new HttpClient();
     * httpClient.getParams().setContentCharset("utf-8"); postMethod = new
     * PostMethod(url);
     *
     * Set<String> set = pv.keySet(); Iterator<String> it = set.iterator();
     * while (it.hasNext()) { String key = it.next(); Object value =
     * pv.get(key); if(null != value) postMethod.addParameter(key,
     * value.toString()); else postMethod.addParameter(key, ""); }
     *
     * httpClient.executeMethod(postMethod);// 200 responseMsg =
     * postMethod.getResponseBodyAsString().trim(); } catch(Exception e){ throw
     * new SystemException("webservice请求异常",e ); } finally {
     * postMethod.releaseConnection(); }
     *
     * return responseMsg; }
     */

    /**
     * post调用
     *
     * @param url
     * @param xml
     * @return
     * @throws IOException
     */
    public static String postMethodInvoke(String url, String xml) throws IOException {
        logger.debug(String.format("Request url:%s", url));
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        String responseMsg = null;

        try {
            HttpEntity re = new StringEntity(xml, "utf-8");
            httppost.setHeader("Content-Type", "application/xml;charset=utf-8");
            httppost.setEntity(re);
            HttpResponse response = httpClient.execute(httppost);
            HttpEntity entity = response.getEntity();
            responseMsg = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            throw new IOException("webservice请求异常", e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return responseMsg;

    }

    /**
     * get调用
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String getMethodInvoke(String url) throws Exception {
        logger.debug(String.format("Request url:%s", url));
        String responseMsg = "";
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        try {
            httpget.setHeader("Content-Type", "application/xml; charset=utf-8");
            HttpResponse response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            responseMsg = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            throw new IOException("webservice请求异常", e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return responseMsg;
    }

    /**
     * put
     *
     * @param url
     * @param xml
     * @return
     * @throws IOException
     */
    public static String putMethodInvoke(String url, String xml) throws IOException {
        logger.debug(String.format("Request url:%s", url));
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPut httpput = new HttpPut(url);
        String state = null;
        try {
            HttpEntity re = new StringEntity(xml);
            httpput.setHeader("Content-Type", "charset=utf-8");
            httpput.setEntity(re);
            HttpResponse response = httpClient.execute(httpput);
            state = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw new IOException("webservice请求异常", e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return state;

    }

    /**
     * delete
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String deleteMethodInvoke(String url) throws Exception {
        logger.debug(String.format("Request url:%s", url));
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpDelete httpdelete = new HttpDelete(url);
        String state = null;
        try {
            httpdelete.setHeader("Content-Type", "charset=utf-8");
            HttpResponse response = httpClient.execute(httpdelete);
            state = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw new IOException("webservice请求异常", e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return state;

    }

    /**
     * post 文件下载
     *
     * @param url
     * @param file
     * @return
     * @throws IOException
     */
    public static String postUploadMethodInvoke(String url, File file) throws IOException {
        logger.debug(String.format("Request url:%s", url));
        DefaultHttpClient httpClient = new DefaultHttpClient();

        String state = null;
        FileInputStream fis = null;
        BufferedInputStream in = null;
        try {
            HttpPost httppost = new HttpPost(url);
            fis = new FileInputStream(file);
            in = new BufferedInputStream(fis);

            HttpEntity re = new InputStreamEntity(in, file.length(),
                    ContentType.MULTIPART_FORM_DATA);
            httppost.setHeader("Content-Type", "charset=utf-8");
            httppost.setHeader("enctype", "multipart/form-data'");
            httppost.setEntity(re);
            HttpResponse response = httpClient.execute(httppost);
            HttpEntity e = response.getEntity();
            state = EntityUtils.toString(e == null ? new StringEntity("") : e);
        } catch (Exception e) {
            throw new IOException("webservice请求异常", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (fis != null) {
                    fis.close();
                }
                httpClient.getConnectionManager().shutdown();
            } catch (IOException e) {
                throw new IOException("webservice请求异常", e);
            }
        }
        return state;
    }

}
