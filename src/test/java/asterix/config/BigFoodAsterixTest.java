package asterix.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import personicle.query.JSONQuery;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class BigFoodAsterixTest {
    private static void rrQuery() {
        try {
            List<JSONObject> queries = JSONQuery.getQuery();
            AsterixConf conf = new AsterixConf("http://172.16.2.209:19002");
            AsterixConn conn = new AsterixConn();
            conf.setDataverse("PersonicleServer");
            int count = 0;
            long begin = System.currentTimeMillis();
            for (JSONObject query : queries) {
                String q = "SELECT * FROM FoodLog WHERE `userName` = '" + query.get("userName") + "';";
                conf.setBody(q);
                String ret = conn.handleRequest(conf, AsterixConf.OpType.QUERY);
                count += ((JSONArray) (JSONObject.parseObject(ret).get("results"))).size();
            }
            System.out.println(count + " " + queries.size() + " " + (System.currentTimeMillis() - begin));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void batchQueray(int batch_size, String path) {
        try {
            List<JSONObject> queries = JSONQuery.getFields(path); // JSONQuery.getQuery(path);
            AsterixConf conf = new AsterixConf("http://172.16.2.209:19002");
            AsterixConn conn = new AsterixConn();
            conf.setDataverse("PersonicleServer");
            int count = 0, tick = 0;
            String q = "SELECT * FROM FoodLog WHERE `userName` in [";
            long begin = System.currentTimeMillis();
            for (JSONObject query : queries) {
                q += "'" + query.get("userName") + "'";
                if (++tick % batch_size == 0) {
                    q += "];";
                    // System.out.println(q + "\t" + query.toJSONString());
                    conf.setBody(q);
                    String ret = conn.handleRequest(conf, AsterixConf.OpType.QUERY);
                    count += ((JSONArray) (JSONObject.parseObject(ret).get("results"))).size();
                    // System.out.println(((JSONArray) (JSONObject.parseObject(ret).get("results"))).toJSONString());
                    q = "SELECT * FROM FoodLog WHERE `userName` in [";
                } else {
                    q += ",";
                }
            }
            conn.release();
            System.out.println(count + " " + queries.size() + " " + (System.currentTimeMillis() - begin));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void simpleKeyQueray(int batch_size, String path) {
        try {
            AsterixConf conf = new AsterixConf("http://172.16.2.209:19002");
            AsterixConn conn = new AsterixConn();
            conf.setDataverse("PersonicleServer");
            int count = 0, tick = 0;
            String q = "SELECT * FROM FoodLog WHERE `logId` = ";
            long begin = System.currentTimeMillis();
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(":");
                if (fields.length < 2)
                    continue;
                q += "uuid(" + fields[1].trim() + ")";
                q += ";";
                conf.setBody(q);
                String ret = conn.handleRequest(conf, AsterixConf.OpType.QUERY);
                count += ((JSONArray) (JSONObject.parseObject(ret).get("results"))).size();
                q = "SELECT * FROM FoodLog WHERE `logId` = ";
                tick++;
            }
            System.out.println(count + " " + tick + " " + (System.currentTimeMillis() - begin));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        // rrQuery();
        if (true) {
            String path = "./resources/food_samples/foodlogquery.json";
            if (args.length > 1)
                path = args[1];
            batchQueray(Integer.parseInt(args[0]), path);
            // simpleKeyQueray(Integer.parseInt(args[0]), args[1]);
        }
    }
}
