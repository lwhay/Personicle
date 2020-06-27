package personicle.integration;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;

import java.io.*;

public class CAObjectVerification {
    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream(new File("E:/obj.json"));
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        JSONObject.parseObject(responseStrBuilder.toString());
    }
}
