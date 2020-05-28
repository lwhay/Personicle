package personicle.datagen.nosqlcomp.emotion.emotionECG;

import asterix.recordV2.wrapper.Uuid;
import personicle.datagen.nosqlcomp.GeneralMeasurement;

import java.util.List;

public class EmotionECG extends GeneralMeasurement {
    private Double[] payload;
    private String comments;

    public Double[] getPayload() {
        return payload;
    }

    public void setPayload(Double[] payload) {
        this.payload = payload;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
