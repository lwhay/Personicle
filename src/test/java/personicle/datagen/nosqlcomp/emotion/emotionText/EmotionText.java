package personicle.datagen.nosqlcomp.emotion.emotionText;

import personicle.datagen.nosqlcomp.GeneralMeasurement;

public class EmotionText extends GeneralMeasurement {
    private String message;
    private String comments;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
