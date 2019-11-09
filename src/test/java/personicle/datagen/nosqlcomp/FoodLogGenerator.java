package personicle.datagen.nosqlcomp;

import asterix.recordV1.food.Food;
import asterix.recordV2.events.PersonicleEvent;
import asterix.recordV2.wrapper.DateTime;
import asterix.recordV2.wrapper.Point;
import asterix.recordV2.wrapper.Uuid;
import personicle.datagen.nosqlcomp.userattribute.UserAttributeGenerator;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

public class FoodLogGenerator {
    private static int measureCount = 1000;//0000;

    private static int deviceCount = 100;//0000;

    private static final int subEventPer = 10;

    private static int informationCount = 100;//00000;

    private static final int attributePerEvent = 5;

    private static int gran = measureCount / deviceCount;

    private static final double minY = 30.0;

    private static final double maxY = 31.0;

    private static final double minX = 117;

    private static final double maxX = 118;

    private static Random rand = new Random();

    private static Random randomnum = new Random();

    private static Random randompos = new Random();

    private static Random randattri = new Random();

    private static LocalDateTime baseTime = LocalDateTime.of(2017, 01, 01, 0, 0, 0, 0);

    public static List<String> foods = new ArrayList<>();

    public static List<String> users = new ArrayList<>();

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

    public static void main(String[] args) throws IOException {
        if (args.length >= 3) {
            measureCount = Integer.parseInt(args[0]);
            deviceCount = Integer.parseInt(args[1]);
            informationCount = Integer.parseInt(args[2]);
            gran = measureCount / deviceCount;
        }
        genFoodsAndUsers();
        //HashMap<Integer, String> hashMap = UserAttributeGenerator.Generator(informationCount);
       // int hashSize = hashMap.size();
        List<UUID> deviceSet = new ArrayList<>();
        for (int i = 0; i < deviceCount; i++) {
            deviceSet.add(UUID.randomUUID());
        }
        List<UUID> AttriSet = new ArrayList<>();
        for (int i = 0; i < informationCount; i++) {
            AttriSet.add(UUID.randomUUID());
        }
        // GeneralMeasurement
        BufferedWriter bw = new BufferedWriter(new FileWriter("./BigFoodLog"));
        for (UUID device : deviceSet) {
            String userName = users.get(rand.nextInt(users.size()));
            double minx = minX + rand.nextDouble() * 0.5;
            double maxx = minx + rand.nextDouble() * 0.25;
            double miny = minY + rand.nextDouble() * 0.5;
            double maxy = miny + rand.nextDouble() * 0.25;
            double delx = (maxx - minx) / gran;
            double dely = (maxy - miny) / gran;
            int second = rand.nextInt(2 * 365 * 24 * 60 * 60);
            if (second % 2 == 0) {
                second++;
            }
            LocalDateTime begin = baseTime.plusSeconds(second);
            for (int i = 0; i < gran; i++) {
                FoodLog foodLog = new FoodLog();
                double x = minx + i * delx;
                double y = miny + i * dely;
                foodLog.setDeviceId(device.toString().replace("-", "").trim());
                foodLog.setUserName(userName);
                foodLog.setFoodName(foods.get(rand.nextInt(foods.size())));
                foodLog.setTotal_calories(randompos.nextDouble() * 1000);
                foodLog.setWeight(randompos.nextDouble() * 300);
                begin = begin.plusSeconds(2);
                foodLog.setTimestamp(begin.toInstant(ZoneOffset.of("+8")).toEpochMilli());
                foodLog.setStartAt(new DateTime(begin));
                foodLog.setEndAt(new DateTime(begin.plusSeconds(10)));
                foodLog.setLongitude(x);
                foodLog.setLatitude(y);
                foodLog.setPreference_star(randomnum.nextInt(10));
                foodLog.setMeasureId(new Uuid(UUID.randomUUID()).getUuid().toString().replace("-", "").trim());
                foodLog.setCategory("foodlog");
                foodLog.setDescription(
                        foodLog.getUserName() + " ate " + foodLog.getWeight() + "g " + foodLog.getFoodName());
                foodLog.setComments(
                        foodLog.getUserName() + " ate " + foodLog.getWeight() + "g " + foodLog.getFoodName());
                List<String> attribute = new ArrayList<>();
                for (int j = 0; j < attributePerEvent; j++) {
                    //attribute.add(new Uuid(AttriSet.get(rand.nextInt(AttriSet.size()))));
                   // attribute.add(hashMap.get(randattri.nextInt(hashMap.size())));
                }
                foodLog.setAttribute(attribute);
                //System.out.println(event.toJSONString());
                bw.write(foodLog.toJSONString() + "\n");
            }
        }
        bw.close();
    }
}
