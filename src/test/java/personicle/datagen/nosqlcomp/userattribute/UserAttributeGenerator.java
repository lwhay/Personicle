package personicle.datagen.nosqlcomp.userattribute;

import asterix.recordV2.wrapper.DateTime;
import asterix.recordV2.wrapper.Uuid;
import com.alibaba.fastjson.JSONObject;
import personicle.datagen.nosqlcomp.GeneralMeasurement;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.HashMap;

public class UserAttributeGenerator {
    private static int total_attribute = 10000;
    private static Random rand = new Random();

    private static Random randomnum = new Random();

    private static Random randompos = new Random();

    private static LocalDateTime baseTime = LocalDateTime.of(2017, 01, 01, 0, 0, 0, 0);

    public static List<String> foods = new ArrayList<>();

    public static List<String> users = new ArrayList<>();

    public static List<String> userIds = new ArrayList<>();

    public static List<String> userNames = new ArrayList<>();

    private static void genFoodsAndUsers() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./resources/food_samples/raw.dat"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(" ");
            users.add(fields[1]);
            foods.add(fields[3]);
        }
        br.close();
    }

    private static void genUsers() throws IOException {
        BufferedReader b = new BufferedReader(new FileReader("./pseudo_users.adm"));
        String line;
        while ((line = b.readLine()) != null) {
            //JSONObject jo = JSONObject.fromObject(line);
            JSONObject pa = JSONObject.parseObject(line);
            userIds.add(pa.getString("userId"));
            userNames.add(pa.getString("userName"));
            //String[] fields = line.split(",");
            //userIds.add(fields[4]);
            //userNames.add(fields[5]);
        }
        b.close();
    }

    public static void main(String[] args) throws IOException {
        if (args.length > 0)
            total_attribute = Integer.parseInt(args[0]);
        // HashMap<Integer, String> h = Generator(100);
        // HashMap<String,List<String>> hashMap = new HashMap<>();
        HashMap<String, List<String>> hashMap = Generator(total_attribute);
        List<String> a = hashMap.get(userIds.get(rand.nextInt(userIds.size())));
        System.out.println(a.size());
        //genUsers();
    }

    public static HashMap<String, List<String>> Generator(int mc) throws IOException {
        HashMap<String, List<String>> hashMap = new HashMap<>();

        genFoodsAndUsers();
        genUsers();

        List<UUID> AttriSet = new ArrayList<>();
        for (int i = 0; i < mc; i++) {
            AttriSet.add(UUID.randomUUID());
        }
// GeneralMeasurement

        BufferedWriter bw1 = new BufferedWriter(new FileWriter("./example/AttributeLog.adm"));
        BufferedWriter bw2 = new BufferedWriter(new FileWriter("./example/AttributeAloneLog.adm"));
        String userName, userId;
        int j = 0, u;
        //u=rand.nextInt(userNames.size());
        for (UUID attribute : AttriSet) {
            u = rand.nextInt(userIds.size());
            userId = userIds.get(u);
            userName = userNames.get(u);
            int second = rand.nextInt(2 * 365 * 24 * 60 * 60);
            if (second % 2 == 0) {
                second++;
            }
            LocalDateTime begin = baseTime.plusSeconds(second);
            // for (int i = 0; i < gran; i++) {
            UserAttribute AtLog = new UserAttribute();
            AtLog.setAttributeId(attribute.toString().replace("-", "").trim());
            AtLog.setUserId(userId);
            AtLog.setUserName(userName);
            begin = begin.plusSeconds(2);
            AtLog.setTimestamp(begin.toInstant(ZoneOffset.of("+8")).toEpochMilli());
            AtLog.setBeginAt(new DateTime(begin));
            AtLog.setEndAt(new DateTime(begin.plusSeconds(10)));
            AtLog.setEvents("event");
            AtLog.setMeasurements("unknown");
            if (j % 4 == 0) {
                AtLog.setCategory("emotion");
            } else if (j % 4 == 1) {
                AtLog.setCategory("behavior");
            } else if (j % 4 == 2) {
                AtLog.setCategory("food");
            } else {
                AtLog.setCategory("others");
            }

            if (j % 2 == 0) {
                AtLog.setStrength(randompos.nextDouble() * 1000);
                AtLog.setArousal(randompos.nextDouble() * 500);
                AtLog.setValence(randompos.nextDouble() * 300);
            } else {
                AtLog.setConfidence(randomnum.nextDouble() * 500);
                AtLog.setDegree(randomnum.nextDouble() * 300);
            }
            j++;
            //System.out.println(event.toJSONString());
            // GeneralMeasurement gm = new GeneralMeasurement(BigLog);
            bw1.write(AtLog.toJSONString() + "\n");
            //hashMap.put(j, AtLog.getAttributeId());
            //hashMap.get()
            if (hashMap.get(AtLog.getUserId()) == null) {
                List<String> a = new ArrayList();
                a.add(AtLog.getAttributeId());
                hashMap.put(AtLog.getUserId(), a);
            } else {
                hashMap.get(AtLog.getUserId()).add(AtLog.getAttributeId());
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("attributeId", AtLog.getAttributeId());
            jsonObject.put("userName", AtLog.getUserName());
            jsonObject.put("userId", AtLog.getUserId());
            bw2.write(jsonObject + "\n");
            //     }
        }
        bw1.flush();
        bw1.close();
        bw2.flush();
        bw2.close();
        return hashMap;
    }
}
