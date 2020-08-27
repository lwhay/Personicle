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
import java.util.*;

public class Total_Generator {
    static int mc = 30000;
    static List<UUID> al = new ArrayList<>();
    static List<String> ul = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            mc = Integer.parseInt(args[0]);
        }
        Map<String, List<String>> userAttributeMap = UserAttributeGenerator.Generator(mc);
        for (Map.Entry<String, List<String>> entry : userAttributeMap.entrySet()) {
            List<String> attributes = entry.getValue();
            ul.add(entry.getKey());
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
            @Override public void run() {

                try {
                    BraceletSleepGenerator.Generator(mc, al, ul, userAttributeMap);
                    BraceletECGListGenerator.Generator(mc, al, ul, userAttributeMap);
                    BraceletHbListGenerator.Generator(mc, al, ul, userAttributeMap);
                    System.out.println("完成1/5: Braclet-A");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override public void run() {
                try {
                    BraceletHeartRateBloodPressureGenerator.Generator(mc, al, ul, userAttributeMap);
                    BraceletRawListGenerator.Generator(mc, al, ul, userAttributeMap);
                    BraceletStepGenerator.Generator(mc, al, ul, userAttributeMap);
                    System.out.println("完成1/5: Bracelet-B");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override public void run() {
                try {
                    SensoringCPGenerator.Generator(mc, al, ul, userAttributeMap);
                    SensoringGPSGenerator.Generator(mc, al, ul, userAttributeMap);
                    SensoringUSGenerator.Generator(mc, al, ul, userAttributeMap);
                    System.out.println("完成1/5: Sensoring");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override public void run() {
                try {
                    SensoringMIGenerator.Generator(mc, al, ul, userAttributeMap);
                    EmotionECGGenerator.Generator(mc, al, ul, userAttributeMap);
                    EmotionTextGenerator.Generator(mc, al, ul, userAttributeMap);
                    System.out.println("完成1/5: Emotion");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        CommonFileMeasurementGenerator.Generator(mc, al, ul, userAttributeMap);
        System.out.println("完成1/10: Files");
        FoodLogGenerator.Generator(mc, userAttributeMap);
        System.out.println("完成1/10: Food");
    }
}
