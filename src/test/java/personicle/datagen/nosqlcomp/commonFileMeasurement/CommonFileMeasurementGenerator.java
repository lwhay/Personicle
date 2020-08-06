package personicle.datagen.nosqlcomp.commonFileMeasurement;

import asterix.recordV2.wrapper.DateTime;
import asterix.recordV2.wrapper.Uuid;
import personicle.datagen.nosqlcomp.GeneralMeasurement;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CommonFileMeasurementGenerator {
    private static int measureCount = 4000;//0000;

    private static int deviceCount = 400;//0000;

    private static int informationCount = 100;//00000;

    private static final int attributePerEvent = 5;

    private static int gran = 10;

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

        BufferedWriter bw1 = new BufferedWriter(new FileWriter("./example/BigCommonFileMeasurement.adm"));
        BufferedWriter bw2 = new BufferedWriter(new FileWriter("./example/CommonFileMeasurement_alone.adm"));
        BufferedWriter bw3 = new BufferedWriter(new FileWriter("./example/CommonFileMeasurement_general.adm"));
        for (UUID device : deviceSet) {
            String userName = users.get(rand.nextInt(users.size()));
            int second = rand.nextInt(2 * 365 * 24 * 60 * 60);
            if (second % 2 == 0) {
                second++;
            }
            LocalDateTime begin = baseTime.plusSeconds(second);

            for (int i = 0; i < gran; i++) {
                CommonFileMeasurement BigLog = new CommonFileMeasurement();
                BigLog.setDeviceId(new Uuid(device));
                BigLog.setUserName(userName);
                begin = begin.plusSeconds(2);
                // System.out.println(begin.toInstant(ZoneOffset.of("+8")).toEpochMilli());
                BigLog.setTimestamp(begin.toInstant(ZoneOffset.of("+8")).toEpochMilli());
                BigLog.setStartAt(new DateTime(begin));
                BigLog.setEndAt(new DateTime(begin.plusSeconds(10)));
                BigLog.setMeasure(new Uuid(UUID.randomUUID()));
                BigLog.setCategory("commonFileMeasurement");
                BigLog.setFile_category(file_types.get(rand.nextInt(file_types.size())));
                BigLog.setDescription(BigLog.getUserName() + " has " + BigLog.getFile_category() + " file ");
                BigLog.setComments(BigLog.getDescription());
                BigLog.setFilepath("---");
                List<Uuid> attribute = new ArrayList<>();
                for (int j = 0; j < attributePerEvent; j++) {
                    attribute.add(new Uuid(AttriSet.get(rand.nextInt(AttriSet.size()))));
                }
                BigLog.setAttribute(attribute);
                //System.out.println(event.toJSONString());
                GeneralMeasurement gm = new GeneralMeasurement(BigLog);
                CommonFileMeasurementAlone alone = new CommonFileMeasurementAlone(BigLog);
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
