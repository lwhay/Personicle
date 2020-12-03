package asterix.config;

import asterix.record.users.PersonicleUser;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComparisonOnGM {

    private class QueryThread implements Runnable {
        private final int BATCH_SIZE;
        private final List<String> users;
        private final AsterixConf conf;
        private final AsterixConn conn;

        public QueryThread(int BATCH_SIZE, List<String> users) {
            this.BATCH_SIZE = BATCH_SIZE;
            this.users = users;
            this.conf = new AsterixConf("http://127.0.0.1:19002");
            this.conn = new AsterixConn();
        }

        @Override
        public void run() {
            Random rand = new Random(System.currentTimeMillis() + this.hashCode());
            conf.setDataverse("PersonicleServer");
            for (int i = 0; i < BATCH_SIZE; i++) {
                String indexnlQuery = "\nSELECT f.foodname as fn, count(*) as counter" + "\nFROM users u, FoodLog f"
                        + "\nWHERE u.userName = \"" + users.get(rand.nextInt(users.size())).trim() + "\""
                        + "\nAND u.userId /*+ indexnl */ = f.userId" + "\ngroup by f.foodname\n" + "order by counter desc;";
                conf.setBody(indexnlQuery);
                try {
                    conn.handleRequest(conf, AsterixConf.OpType.QUERY);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static int TOTAL_SIZE = 1000000;
    private static int PDEGREE = 2;
    private static int BATCH_SIZE = 10000;
    private static List<String> users = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("./pseudo_users.adm"));
        String line;
        while ((line = br.readLine()) != null) {
            PersonicleUser user = JSONObject.parseObject(line, PersonicleUser.class);
            if (users.size() == TOTAL_SIZE) {
                break;
            }
            users.add(user.getUserName());
        }
        br.close();
        int threadNum = PDEGREE;
        if (args.length >= 1) {
            threadNum = Integer.parseInt(args[0]);
        }
        System.out.println("Thread: " + threadNum + " total: " + TOTAL_SIZE + " batch: " + BATCH_SIZE);
        Thread[] threads = new Thread[threadNum];

        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread(new asterix.config.QueryThread(BATCH_SIZE, users));
            threads[i].start();
        }
    }
}
