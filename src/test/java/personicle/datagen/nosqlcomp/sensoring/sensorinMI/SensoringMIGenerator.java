package personicle.datagen.nosqlcomp.sensoring.sensorinMI;

import asterix.recordV2.wrapper.DateTime;
import asterix.recordV2.wrapper.Uuid;
import personicle.datagen.nosqlcomp.GeneralMeasurement;
import personicle.datagen.nosqlcomp.sensoring.sensoringGPS.SensoringGPS;
import personicle.datagen.nosqlcomp.sensoring.sensoringGPS.SensoringGPSAlone;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class SensoringMIGenerator {
    private static int measureCount = 1000;//0000;

    private static int deviceCount = 100;//0000;

    private static int informationCount = 100000;//00000;

    private static final int attributesPerMeasurement = 5;

    private static int gran = 10;

    private static final double minY = 30.0;

    private static final double maxY = 31.0;

    private static final double minX = 117;

    private static final double maxX = 118;

    private static Random rand = new Random();

    private static Random randomnum = new Random();

    private static Random randompos = new Random();

    private static LocalDateTime baseTime = LocalDateTime.of(2017, 01, 01, 0, 0, 0, 0);

    public static List<String> foods = new ArrayList<>();

    public static List<String> users = new ArrayList<>();

    private static void genUsers() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./resources/food_samples/raw.dat"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(" ");
            users.add(fields[1]);
        }
        br.close();
    }

    public static void main(String[] args) throws IOException {
        Generator(1000, null, null);
    }

    public static void Generator(int mc, List<UUID> AttriSet, List<String> userList) throws IOException {
        measureCount = mc * 4;
        deviceCount = measureCount / gran;

        if (AttriSet == null) {
            genUsers();
            AttriSet = new ArrayList<>();
            for (int i = 0; i < informationCount; i++) {
                AttriSet.add(UUID.randomUUID());
            }
        }
        if (userList == null) {
            genUsers();
        } else {
            users = userList;
        }
        List<UUID> deviceSet = new ArrayList<>();
        for (int i = 0; i < deviceCount; i++) {
            deviceSet.add(UUID.randomUUID());
        }
        List<String> file_types = new ArrayList<>();
        file_types.add("video");
        file_types.add("soundtrack");
        file_types.add("picture");
        file_types.add("text");

        BufferedWriter bw1 = new BufferedWriter(new FileWriter("./example/BigSensoringMI.adm"));
        BufferedWriter bw2 = new BufferedWriter(new FileWriter("./example/SensoringMI_alone.adm"));
        BufferedWriter bw3 = new BufferedWriter(new FileWriter("./example/SensoringMI_general.adm"));
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
                SensoringMI BigLog = new SensoringMI();
                double x = minx + i * delx * 2;
                double y = miny + i * dely * 2;
                // general
                BigLog.setDeviceId(new Uuid(device));
                BigLog.setUserName(userName);
                begin = begin.plusSeconds(2);
                BigLog.setTimestamp(begin.toInstant(ZoneOffset.of("+8")).toEpochMilli());
                BigLog.setStartAt(new DateTime(begin));
                BigLog.setEndAt(new DateTime(begin.plusSeconds(10)));
                BigLog.setMeasure(new Uuid(UUID.randomUUID()));
                BigLog.setCategory("sensoringMI");
                BigLog.setDescription(
                        "userName:" + BigLog.getUserName() + "deviceId: " + BigLog.getDeviceId() + ",measureId: "
                                + BigLog.getMeasure());
                List<Uuid> attribute = new ArrayList<>();
                for (int j = 0; j < attributesPerMeasurement; j++) {
                    attribute.add(new Uuid(AttriSet.get(rand.nextInt(AttriSet.size()))));
                }
                BigLog.setAttribute(attribute);

                // unique
                BigLog.setComments("deviceId: " + BigLog.getDeviceId() + ",timeStamp: " + BigLog.getTimestamp());
                BigLog.setHeartrate(rand.nextInt(100));
                BigLog.setStepcount(rand.nextInt(10000));
                //System.out.println(event.toJSONString());
                GeneralMeasurement gm = new GeneralMeasurement(BigLog);
                SensoringMIAlone alone = new SensoringMIAlone(BigLog);
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
