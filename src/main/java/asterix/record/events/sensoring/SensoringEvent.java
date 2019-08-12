package asterix.record.events.sensoring;

import asterix.record.events.Event;
import com.alibaba.fastjson.JSONObject;

public class SensoringEvent extends Event {
    private String category; //category: string,
    private String activity; //activity: string,
    private String description; //description: string

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toJSONString() {
        return JSONObject.toJSONString(this);
    }
}
