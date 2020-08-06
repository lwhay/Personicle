package asterix.recordV2.wrapper;

import com.alibaba.fastjson.JSON;

public class Interval extends JSON {
    DateTime startAt;
    DateTime endAt;

    public Interval(DateTime startAt, DateTime endAt) {
        this.startAt = startAt;
        this.endAt = endAt;
    }

    @Override
    public String toJSONString() {
        return "interval(" + startAt + "," + endAt + ")";
    }
}
