package personicle.datagen.nosqlcomp.userattribute;

import asterix.recordV2.wrapper.DateTime;
import personicle.datagen.nosqlcomp.GeneralMeasurement;

public class UserAttribute extends GeneralMeasurement {
    private String attribute;
    private String userId;
    private String events;
    private String measurements;
    private Double strength;
    private Double valence;
    private Double arousal;
    private Double confidence;
    private Double degree;

    public UserAttribute() {
        super();
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    public String getMeasurements() {
        return measurements;
    }

    public void setMeasurements(String measurements) {
        this.measurements = measurements;
    }

    public Double getStrength() {
        return strength;
    }

    public void setStrength(Double strength) {
        this.strength = strength;
    }

    public Double getValence() {
        return valence;
    }

    public void setValence(Double valence) {
        this.valence = valence;
    }

    public Double getArousal() {
        return arousal;
    }

    public void setArousal(Double arousal) {
        this.arousal = arousal;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public Double getDegree() {
        return degree;
    }

    public void setDegree(Double degree) {
        this.degree = degree;
    }
}