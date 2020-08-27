package personicle.datagen.nosqlcomp.bracelet.braceletECGList;

import asterix.recordV2.wrapper.DateTime;
import asterix.recordV2.wrapper.Uuid;
import personicle.datagen.nosqlcomp.GeneralMeasurement;
import personicle.datagen.nosqlcomp.sensoring.sensorinMI.SensoringMI;
import personicle.datagen.nosqlcomp.sensoring.sensorinMI.SensoringMIAlone;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

public class BraceletECGListGenerator {
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

    public static Map<String, List<String>> userAtts = null;

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
        Generator(1000, null, null, null);
    }

    public static void Generator(int mc, List<UUID> AttriSet, List<String> userList, Map<String, List<String>> userMap)
            throws IOException {
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
        if (userMap != null) {
            userAtts = userMap;
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

        BufferedWriter bw1 = new BufferedWriter(new FileWriter("./example/BigBraceletECGList.adm"));
        BufferedWriter bw2 = new BufferedWriter(new FileWriter("./example/BraceletECGList_alone.adm"));
        BufferedWriter bw3 = new BufferedWriter(new FileWriter("./example/BraceletECGList_general.adm"));
        for (UUID device : deviceSet) {
            String userName = users.get(rand.nextInt(users.size()));
            int second = rand.nextInt(2 * 365 * 24 * 60 * 60);
            if (second % 2 == 0) {
                second++;
            }
            LocalDateTime begin = baseTime.plusSeconds(second);
            for (int i = 0; i < gran; i++) {
                BraceletECGList BigLog = new BraceletECGList();
                // general
                BigLog.setDeviceId(new Uuid(device));
                BigLog.setUserName(userName);
                begin = begin.plusSeconds(2);
                BigLog.setTimestamp(begin.toInstant(ZoneOffset.of("+8")).toEpochMilli());
                BigLog.setStartAt(new DateTime(begin));
                BigLog.setEndAt(new DateTime(begin.plusSeconds(10)));
                BigLog.setMeasure(new Uuid(UUID.randomUUID()));
                BigLog.setId(new Uuid(UUID.randomUUID()));
                BigLog.setCategory("ecglist");
                BigLog.setDescription(
                        "userName:" + BigLog.getUserName() + "deviceId: " + BigLog.getDeviceId() + ",measureId: "
                                + BigLog.getMeasure());
                List<Uuid> attribute = new ArrayList<>();
                if (userAtts != null) {
                    List<String> attri = userAtts.get(userName);
                    Set<String> cont = new HashSet<>();
                    for (int j = 0; j < attributesPerMeasurement; j++) {
                        String att = attri.get(rand.nextInt(attri.size()));
                        if (!cont.contains(att)) {
                            String tmp = "";
                            tmp += att.substring(0, 8);
                            tmp += "-";
                            tmp += att.substring(8, 12);
                            tmp += "-";
                            tmp += att.substring(12, 16);
                            tmp += "-";
                            tmp += att.substring(16, 20);
                            tmp += "-";
                            tmp += att.substring(20, 32);
                            UUID uuid = UUID.fromString(tmp);
                            attribute.add(new Uuid(uuid));
                            cont.add(att);
                        }
                    }
                } else {
                    for (int j = 0; j < attributesPerMeasurement; j++) {
                        attribute.add(new Uuid(AttriSet.get(rand.nextInt(AttriSet.size()))));
                    }
                }
                BigLog.setAttribute(attribute);

                // unique

                //System.out.println(event.toJSONString());
                GeneralMeasurement gm = new GeneralMeasurement(BigLog);
                BraceletECGListAlone alone = new BraceletECGListAlone(BigLog);
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
