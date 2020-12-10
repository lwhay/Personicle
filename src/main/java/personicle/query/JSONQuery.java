package personicle.query;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONQuery {
    private static String default_path = "./resources/food_samples/foodlogquery.json";

    public static List<JSONObject> getFields(String path) {
        List<JSONObject> queries = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                String[] kv = line.split(":");
                JSONObject obj = new JSONObject();
                obj.put(kv[0].trim().replace("\"", ""), kv[1].trim().replace("\"", ""));
                queries.add(obj);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queries;
    }

    public static List<JSONObject> getQuery() {
        List<JSONObject> queries = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(default_path));
            String line;
            while ((line = br.readLine()) != null) {
                queries.add(JSONObject.parseObject(line));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queries;
    }

    public static List<JSONObject> getQuery(String path) {
        default_path = path;
        return getQuery();
    }
}
