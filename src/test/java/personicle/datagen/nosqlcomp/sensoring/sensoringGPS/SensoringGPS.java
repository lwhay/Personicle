package personicle.datagen.nosqlcomp.sensoring.sensoringGPS;

import personicle.datagen.nosqlcomp.GeneralMeasurement;

public class SensoringGPS extends GeneralMeasurement {
    private int weatherInfo;
    private Double[] latitude;
    private Double[] longitude;
    private String comments;

    public int getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(int weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public Double[] getLatitude() {
        return latitude;
    }

    public void setLatitude(Double[] latitude) {
        this.latitude = latitude;
    }

    public Double[] getLongitude() {
        return longitude;
    }

    public void setLongitude(Double[] longitude) {
        this.longitude = longitude;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
