package personicle.datagen.nosqlcomp.sensoring.sensorinMI;

import personicle.datagen.nosqlcomp.GeneralMeasurement;

public class SensoringMI extends GeneralMeasurement {
    private int heartrate;
    private int stepcount;
    private String comments;

    public int getHeartrate() {
        return heartrate;
    }

    public void setHeartrate(int heartrate) {
        this.heartrate = heartrate;
    }

    public int getStepcount() {
        return stepcount;
    }

    public void setStepcount(int stepcount) {
        this.stepcount = stepcount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
