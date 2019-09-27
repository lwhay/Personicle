package personicle.datagen;

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

import java.io.IOException;

public class Total_Generator {
    static int mc = 1000;

    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            mc = Integer.parseInt(args[0]);
        }
        System.out.println("每种分类" + mc + "条记录");
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    BraceletSleepGenerator.Generator(mc);
                    BraceletECGListGenerator.Generator(mc);
                    BraceletHbListGenerator.Generator(mc);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BraceletHeartRateBloodPressureGenerator.Generator(mc);
                    BraceletRawListGenerator.Generator(mc);
                    BraceletStepGenerator.Generator(mc);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SensoringCPGenerator.Generator(mc);
                    SensoringGPSGenerator.Generator(mc);
                    SensoringUSGenerator.Generator(mc);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SensoringMIGenerator.Generator(mc);
                    EmotionECGGenerator.Generator(mc);
                    EmotionTextGenerator.Generator(mc);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        CommonFileMeasurementGenerator.Generator(mc);
        FoodLogGenerator.Generator(mc);
    }
}
