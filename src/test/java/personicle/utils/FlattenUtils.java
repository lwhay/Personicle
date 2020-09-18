package personicle.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FlattenUtils {
    public static void main(String[] args) throws Exception {
        String geoInfo = new String(Files.readAllBytes(Paths.get(args[0])));
        List<JSONObject> rs = JSONObject.parseArray(geoInfo, JSONObject.class);
        BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
        for (JSONObject e : rs) {
            System.out.println(e.toJSONString());
            bw.write(e + "\n");
        }
        bw.close();
    }
}
