package personicle.datagen.nosqlcomp;

import asterix.recordV2.wrapper.DateTime;
import asterix.recordV2.wrapper.Uuid;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CommonFileMeasureGenerator {
    private static int measureCount = 1000;//0000;

    private static int deviceCount = 100;//0000;

    private static int informationCount = 100;//00000;

    private static final int attributePerEvent = 5;

    private static int gran = measureCount / deviceCount;

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
        if (args.length >= 3) {
            measureCount = Integer.parseInt(args[0]);
            deviceCount = Integer.parseInt(args[1]);
            informationCount = Integer.parseInt(args[2]);
            gran = measureCount / deviceCount;
        }
        genUsers();
        List<UUID> deviceSet = new ArrayList<>();
        for (int i = 0; i < deviceCount; i++) {
            deviceSet.add(UUID.randomUUID());
        }
        List<String> file_types = new ArrayList<>();
        file_types.add("video");
        file_types.add("soundtrack");
        file_types.add("picture");
        file_types.add("paper");
        List<UUID> AttriSet = new ArrayList<>();
        for (int i = 0; i < informationCount; i++) {
            AttriSet.add(UUID.randomUUID());
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("./commonFileLog.adm"));
        for (UUID device : deviceSet) {
            String userName = users.get(rand.nextInt(users.size()));
            int second = rand.nextInt(2 * 365 * 24 * 60 * 60);
            if (second % 2 == 0) {
                second++;
            }
            LocalDateTime begin = baseTime.plusSeconds(second);

            for (int i = 0; i < gran; i++) {
                CommonFileMeasure commonFileMeasure = new CommonFileMeasure();
                commonFileMeasure.setDeviceId(new Uuid(device));
                commonFileMeasure.setUserName(userName);
                begin = begin.plusSeconds(2);
                //                System.out.println(begin.toInstant(ZoneOffset.of("+8")).toEpochMilli());
                commonFileMeasure.setTimestamp(begin.toInstant(ZoneOffset.of("+8")).toEpochMilli());
                commonFileMeasure.setStartAt(new DateTime(begin));
                commonFileMeasure.setEndAt(new DateTime(begin.plusSeconds(10)));
                commonFileMeasure.setMeasureId(new Uuid(UUID.randomUUID()));
                commonFileMeasure.setCategory("unknown");
                commonFileMeasure.setFile_category(file_types.get(rand.nextInt(file_types.size())));
                commonFileMeasure.setDescription(
                        commonFileMeasure.getUserName() + " has " + commonFileMeasure.getFile_category() + " file ");
                commonFileMeasure.setFilepath("---");
                commonFileMeasure.setAttribute(new ArrayList<>());
                for (int j = 0; j < attributePerEvent; j++) {
                    commonFileMeasure.getAttribute().add(new Uuid(AttriSet.get(rand.nextInt(AttriSet.size()))));
                }
                //System.out.println(event.toJSONString());
                bw.write(commonFileMeasure.toJSONString() + "\n");
            }
        }
        bw.close();
    }
}
