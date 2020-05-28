package personicle.datagen.nosqlcomp.sensoring.sensoringUS;

import personicle.datagen.nosqlcomp.GeneralMeasurement;

public class SensoringUS extends GeneralMeasurement {
    private String softinfo;
    private String comments;

    public String getSoftinfo() {
        return softinfo;
    }

    public void setSoftinfo(String softinfo) {
        this.softinfo = softinfo;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
