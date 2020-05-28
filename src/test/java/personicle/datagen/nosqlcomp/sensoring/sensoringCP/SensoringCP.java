package personicle.datagen.nosqlcomp.sensoring.sensoringCP;

import personicle.datagen.nosqlcomp.GeneralMeasurement;
import personicle.datagen.nosqlcomp.sensoring.Spatial3DPoint;

public class SensoringCP extends GeneralMeasurement {
    private Spatial3DPoint[] accelerometer;
    private Spatial3DPoint[] gyroscope;
    private String comments;

    public Spatial3DPoint[] getAccelerometer() {
        return accelerometer;
    }

    public void setAccelerometer(Spatial3DPoint[] accelerometer) {
        this.accelerometer = accelerometer;
    }

    public Spatial3DPoint[] getGyroscope() {
        return gyroscope;
    }

    public void setGyroscope(Spatial3DPoint[] gyroscope) {
        this.gyroscope = gyroscope;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
