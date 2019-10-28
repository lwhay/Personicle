package asterix.recordV2.wrapper;

import com.alibaba.fastjson.JSON;

import java.util.UUID;

public class Uuid extends JSON {
    private UUID uuid;

    public Uuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override public String toJSONString() {
        return "uuid(\"" + uuid + "\")";
    }
}
