package personicle.datagen.nosqlcomp.userattribute;

import asterix.recordV2.wrapper.DateTime;
import asterix.recordV2.wrapper.Uuid;
import personicle.datagen.nosqlcomp.GeneralMeasurement;

import java.util.List;

public class UserAttribute extends GeneralMeasurement {
    private String attributeId;
    private String userId;
    private String events;
    private String measurements;
    private DateTime beginAt;
    private Double strength;
    private Double valence;
    private Double arousal;
    private Double confidence;
    private Double degree;

    public UserAttribute() {
        super();
    }

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
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

    public DateTime getBeginAt() {
        return beginAt;
    }

    public void setBeginAt(DateTime beginAt) {
        this.beginAt = beginAt;
    }
}