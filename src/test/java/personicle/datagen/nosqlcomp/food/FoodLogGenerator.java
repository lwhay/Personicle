package personicle.datagen.nosqlcomp.food;

import asterix.recordV2.wrapper.DateTime;
import asterix.recordV2.wrapper.Uuid;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import personicle.datagen.nosqlcomp.GeneralMeasurement;
import personicle.datagen.nosqlcomp.userattribute.UserAttributeGenerator;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

public class FoodLogGenerator {
    private static int measureCount = 1000;//0000;

    private static int deviceCount = 100;//0000;

    private static int informationCount = 10000;//00000;

    private static final int attributesPerMeasurement = 5;

    private static int gran = 10;

    private static final double minY = 22.24;

    private static final double maxY = 22.52;

    private static final double minX = 113.46;

    private static final double maxX = 114.37;

    private static Random rand = new Random();

    private static Random randomnum = new Random();

    private static Random randompos = new Random();

    private static Random randattri = new Random();

    private static LocalDateTime baseTime = LocalDateTime.of(2017, 01, 01, 0, 0, 0, 0);

    public static List<String> foods = new ArrayList<>();

    public static List<String> users = new ArrayList<>();

    public static List<String> userIds = new ArrayList<>();

    public static List<String> userNames = new ArrayList<>();

    public static List<String> userAtNames = new ArrayList<>();

    public static List<String> userAtIds = new ArrayList<>();

    public static List<String> userAttributes = new ArrayList<>();

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
            JSONObject pa = JSONObject.parseObject(line);
            userIds.add(pa.getString("userId"));
            userNames.add(pa.getString("userName"));
        }
        b.close();
    }

    private static void genAttributes() throws IOException {
        BufferedReader bq = new BufferedReader(new FileReader("./example/AttributeAloneLog.adm"));
        String line;
        while ((line = bq.readLine()) != null) {
            JSONObject pb = JSONObject.parseObject(line);
            userAttributes.add(pb.getString("attributeId"));
            userAtIds.add(pb.getString("userId"));
            userAtNames.add(pb.getString("userName"));
        }
        bq.close();
    }

    public static void main(String[] args) throws IOException {
        if (args.length > 1)
            measureCount = Integer.parseInt(args[1]);
        informationCount = measureCount / 10;
        Generator(measureCount);
    }

    public static void Generator(int mc) throws IOException {
        measureCount = mc;
        deviceCount = measureCount / gran;

        genFoodsAndUsers();
        genUsers();
        genAttributes();
        List<UUID> deviceSet = new ArrayList<>();
        for (int i = 0; i < deviceCount; i++) {
            deviceSet.add(UUID.randomUUID());
        }
        HashMap<String, List<String>> hashMap = UserAttributeGenerator.Generator(informationCount);
// GeneralMeasurement

        BufferedWriter bw1 = new BufferedWriter(new FileWriter("./example/BigFoodLog.adm"));
        BufferedWriter bw2 = new BufferedWriter(new FileWriter("./example/FoodLog_alone.adm"));
        BufferedWriter bw3 = new BufferedWriter(new FileWriter("./example/FoodLog_general.adm"));
        BufferedWriter bw4 = new BufferedWriter(new FileWriter("./example/FoodAttribute.adm"));
        JSONArray jsonArray = new JSONArray();
        for (UUID device : deviceSet) {
            int u = rand.nextInt(userIds.size());
            String userId = userIds.get(u);
            String userName = userNames.get(u);
            //String userId=userAtIds.get(rand.nextInt(userAtIds.size()));
            List<String> AttriSet = new ArrayList<>();
            AttriSet = hashMap.get(userId);
            double minx = minX + rand.nextDouble() * 0.5;
            double maxx = maxX + rand.nextDouble() * 0.25;
            double miny = minY + rand.nextDouble() * 0.5;
            double maxy = maxY + rand.nextDouble() * 0.25;
            double delx = (maxx - minx) / gran;
            double dely = (maxy - miny) / gran;
            int second = rand.nextInt(2 * 365 * 24 * 60 * 60);
            if (second % 2 == 0) {
                second++;
            }
            LocalDateTime begin = baseTime.plusSeconds(second);
            for (int i = 0; i < gran; i++) {
                FoodLog BigLog = new FoodLog();
                double x = minx + i * delx;
                double y = miny + i * dely;
                BigLog.setDeviceId(new Uuid(device));
                BigLog.setUserName(userName);
                BigLog.setFoodName(foods.get(rand.nextInt(foods.size())));
                BigLog.setTotal_calories(randompos.nextDouble() * 1000);
                BigLog.setWeight(randompos.nextDouble() * 300);
                begin = begin.plusSeconds(2);
                BigLog.setTimestamp(begin.toInstant(ZoneOffset.of("+8")).toEpochMilli());
                BigLog.setStartAt(new DateTime(begin));
                BigLog.setEndAt(new DateTime(begin.plusSeconds(10)));
                BigLog.setLongitude(x);
                BigLog.setLatitude(y);
                BigLog.setPreference_star(randomnum.nextInt(10));
                BigLog.setMeasureId(new Uuid(UUID.randomUUID()));
                BigLog.setCategory("foodlog");
                BigLog.setDescription(BigLog.getUserName() + " ate " + BigLog.getWeight() + "g " + BigLog.getFoodName());
                BigLog.setComments(BigLog.getDescription());
                List<String> attribute = new ArrayList<>();
                for (int j = 0; j < attributesPerMeasurement; j++) {
                    String a = AttriSet.get(rand.nextInt(AttriSet.size()));
                    // String a=hashMap.get(randattri.nextInt(hashMap.size()));
                    attribute.add(a);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("attributeId", a);
                    jsonObject.put("deviceId", BigLog.getDeviceId());
                    bw4.write(jsonObject + "\n");
                }
                BigLog.setFoodAttribute(attribute);
                //System.out.println(event.toJSONString());
                GeneralMeasurement gm = new GeneralMeasurement(BigLog);
                FoodLogAlone alone = new FoodLogAlone(BigLog);
                bw1.write(BigLog.toJSONString() + "\n");
                bw2.write(alone.toJSONString() + "\n");
                bw3.write(gm.toJSONString() + "\n");
            }
        }
        bw1.close();
        bw2.close();
        bw3.close();
        bw4.close();
    }
}
