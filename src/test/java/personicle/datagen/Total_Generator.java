package personicle.datagen;

import org.apache.avro.generic.GenericData;
import personicle.datagen.nosqlcomp.bracelet.braceletECGList.BraceletECGListGenerator;
import personicle.datagen.nosqlcomp.bracelet.braceletHbList.BraceletHbListGenerator;
import personicle.datagen.nosqlcomp.bracelet.braceletHeartRateBloodPressure.BraceletHeartRateBloodPressureGenerator;
import personicle.datagen.nosqlcomp.bracelet.braceletRawList.BraceletRawListGenerator;
import personicle.datagen.nosqlcomp.bracelet.braceletSleep.BraceletSleepGenerator;
import personicle.datagen.nosqlcomp.bracelet.braceletStep.BraceletStepGenerator;
import personicle.datagen.nosqlcomp.commonFileMeasurement.CommonFileMeasurementGenerator;
import personicle.datagen.nosqlcomp.emotion.emotionECG.EmotionECGGenerator;
import personicle.datagen.nosqlcomp.emotion.emotionText.EmotionTextGenerator;
import personicle.datagen.nosqlcomp.food.FoodLogGenerator;
import personicle.datagen.nosqlcomp.sensoring.sensorinMI.SensoringMIGenerator;
import personicle.datagen.nosqlcomp.sensoring.sensoringCP.SensoringCPGenerator;
import personicle.datagen.nosqlcomp.sensoring.sensoringGPS.SensoringGPSGenerator;
import personicle.datagen.nosqlcomp.sensoring.sensoringUS.SensoringUSGenerator;
import personicle.datagen.nosqlcomp.userattribute.UserAttributeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Total_Generator {
    static int mc = 100000;
    static List<UUID> al = new ArrayList<>();
    static List<String> ul = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            mc = Integer.parseInt(args[0]);
        }
        Map<String, List<String>> userAttributeMap = UserAttributeGenerator.Generator(mc);
        for (Map.Entry<String, List<String>> entry : userAttributeMap.entrySet()) {
            ul.add(entry.getKey());
            List<String> attributes = entry.getValue();
            for (String attribute : attributes) {
                String tmp = "";
                tmp += attribute.substring(0, 8);
                tmp += "-";
                tmp += attribute.substring(8, 12);
                tmp += "-";
                tmp += attribute.substring(12, 16);
                tmp += "-";
                tmp += attribute.substring(16, 20);
                tmp += "-";
                tmp += attribute.substring(20, 32);
                UUID uuid = UUID.fromString(tmp);
                al.add(uuid);
            }
        }
        System.out.println("每种分类" + mc + "条记录");
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    BraceletSleepGenerator.Generator(mc, al, ul);
                    BraceletECGListGenerator.Generator(mc, al, ul);
                    BraceletHbListGenerator.Generator(mc, al, ul);
                    System.out.println("完成1/5");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BraceletHeartRateBloodPressureGenerator.Generator(mc, al, ul);
                    BraceletRawListGenerator.Generator(mc, al, ul);
                    BraceletStepGenerator.Generator(mc, al, ul);
                    System.out.println("完成1/5");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SensoringCPGenerator.Generator(mc, al, ul);
                    SensoringGPSGenerator.Generator(mc, al, ul);
                    SensoringUSGenerator.Generator(mc, al, ul);
                    System.out.println("完成1/5");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SensoringMIGenerator.Generator(mc, al, ul);
                    EmotionECGGenerator.Generator(mc, al, ul);
                    EmotionTextGenerator.Generator(mc, al, ul);
                    System.out.println("完成1/5");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        CommonFileMeasurementGenerator.Generator(mc, al, ul);
        FoodLogGenerator.Generator(mc);
        System.out.println("完成1/5");
    }
}
