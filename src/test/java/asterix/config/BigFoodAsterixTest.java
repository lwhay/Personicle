package asterix.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import personicle.query.JSONQuery;

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
            List<JSONObject> queries = JSONQuery.getQuery(path);
            AsterixConf conf = new AsterixConf("http://172.16.2.225:19002");
            AsterixConn conn = new AsterixConn();
            conf.setDataverse("PersonicleServer");
            int count = 0, tick = 0;
            String q = "SELECT * FROM FoodLog WHERE `userName` in [";
            long begin = System.currentTimeMillis();
            for (JSONObject query : queries) {
                q += "'" + query.get("userName") + "'";
                if (++tick % batch_size == 0) {
                    q += "];";
                    conf.setBody(q);
                    String ret = conn.handleRequest(conf, AsterixConf.OpType.QUERY);
                    count += ((JSONArray) (JSONObject.parseObject(ret).get("results"))).size();
                    q = "SELECT * FROM FoodLog WHERE `userName` in [";
                } else {
                    q += ",";
                }
            }
            System.out.println(count + " " + queries.size() + " " + (System.currentTimeMillis() - begin));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        rrQuery();
        if (false) {
            String path = "./resources/food_samples/foodlogquery.json";
            if (args.length > 1)
                path = args[1];
            batchQueray(Integer.parseInt(args[0]), path);
        }
    }
}
