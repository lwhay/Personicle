package asterix.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import personicle.query.JSONQuery;

import java.util.List;

public class BigFoodAsterixTest {
    public static void main(String[] args) throws Exception {
        List<JSONObject> queries = JSONQuery.getQuery();
        AsterixConf conf = new AsterixConf("http://172.16.2.225:19002");
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
    }
}
