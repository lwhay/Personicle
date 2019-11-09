package personicle.datagen.nosqlcomp.emotion.emotionECG;

import asterix.recordV2.wrapper.DateTime;
import asterix.recordV2.wrapper.Uuid;
import personicle.datagen.nosqlcomp.GeneralMeasurement;
import personicle.datagen.nosqlcomp.food.FoodLog;
import personicle.datagen.nosqlcomp.food.FoodLogAlone;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class EmotionECGGenerator {
    private static int measureCount = 1000;//0000;

    private static int deviceCount = 100;//0000;

    private static int informationCount = 100000;//00000;

    private static final int attributesPerMeasurement = 5;

    private static int gran = 10;

    private static final double minY = 22.24;

    private static final double maxY = 22.52;

    private static final double minX = 113.46;

    private static final double maxX = 114.37;

    private static Random rand = new Random();

    private static Random randomnum = new Random();

    private static Random randompos = new Random();

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
        Generator(1000);
    }

    public static void Generator(int mc) throws IOException {
        measureCount = mc;
        deviceCount = measureCount / gran;

        genFoodsAndUsers();
        List<UUID> deviceSet = new ArrayList<>();
        for (int i = 0; i < deviceCount; i++) {
            deviceSet.add(UUID.randomUUID());
        }
        List<UUID> AttriSet = new ArrayList<>();
        for (int i = 0; i < informationCount; i++) {
            AttriSet.add(UUID.randomUUID());
        }
// GeneralMeasurement

        BufferedWriter bw1 = new BufferedWriter(new FileWriter("./example/BigEmotionECG.adm"));
        BufferedWriter bw2 = new BufferedWriter(new FileWriter("./example/EmotionECG_alone.adm"));
        BufferedWriter bw3 = new BufferedWriter(new FileWriter("./example/EmotionECG_general.adm"));
        for (UUID device : deviceSet) {
            String userName = users.get(rand.nextInt(users.size()));
            int second = rand.nextInt(2 * 365 * 24 * 60 * 60);
            if (second % 2 == 0) {
                second++;
            }
            LocalDateTime begin = baseTime.plusSeconds(second);
            for (int i = 0; i < gran; i++) {
                EmotionECG BigLog = new EmotionECG();
                // general
                BigLog.setDeviceId(new Uuid(device));
                BigLog.setUserName(userName);
                begin = begin.plusSeconds(2);
                BigLog.setTimestamp(begin.toInstant(ZoneOffset.of("+8")).toEpochMilli());
                BigLog.setStartAt(new DateTime(begin));
                BigLog.setEndAt(new DateTime(begin.plusSeconds(10)));
                BigLog.setMeasureId(new Uuid(UUID.randomUUID()));
                BigLog.setCategory("emotionECG");
                BigLog.setDescription("userName:" + BigLog.getUserName() + "deviceId: " + BigLog.getDeviceId() + ",measureId: " + BigLog.getMeasureId());
                List<Uuid> attribute = new ArrayList<>();
                for (int j = 0; j < attributesPerMeasurement; j++) {
                    attribute.add(new Uuid(AttriSet.get(rand.nextInt(AttriSet.size()))));
                }
                BigLog.setAttribute(attribute);

                // unique
                BigLog.setComments("deviceId: " + BigLog.getDeviceId() + ",timeStamp: " + BigLog.getTimestamp());
                BigLog.setPayload(new Double[]{1.0, 2.0});

                //System.out.println(event.toJSONString());
                GeneralMeasurement gm = new GeneralMeasurement(BigLog);
                EmotionECGAlone alone = new EmotionECGAlone(BigLog);
                bw1.write(BigLog.toJSONString() + "\n");
                bw2.write(alone.toJSONString() + "\n");
                bw3.write(gm.toJSONString() + "\n");
            }
        }
        bw1.close();
        bw2.close();
        bw3.close();
    }
}
