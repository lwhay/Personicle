package asterix.record.observations.attributes.users.motion;

import asterix.record.observations.attributes.users.UserAttribute;
import com.alibaba.fastjson.JSONObject;

public class MotionUserAttribute extends UserAttribute {
    private double degree; //strengthen: double

    public double getDegree() {
        return degree;
    }

    public void setDegree(double degree) {
        this.degree = degree;
    }

    public String toJSONString() {
        return JSONObject.toJSONString(this);
    }
}
