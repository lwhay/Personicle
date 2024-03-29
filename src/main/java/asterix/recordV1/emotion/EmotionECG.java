package asterix.recordV1.emotion;

import asterix.recordV1.ObservationBasis;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class EmotionECG extends ObservationBasis {
    private List<Double> payload; //payload: [double],

    public List<Double> getPayload() {
        return payload;
    }

    public void setPayload(List<Double> payload) {
        this.payload = payload;
    }

    public String toJSONString() {
        return JSONObject.toJSONString(this);
    }
}
