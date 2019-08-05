package asterix.record;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;

public class Users {
    private UUID userId;
    private String userName;
    private List<Address> addresses;
    private List<PhoneDetail> phones;
    private List<EmailDetail> emails;
    private Map<String, String> character;

    public Users() {
    }

    public Users(UUID uuid, String userName) {
        this.userId = uuid;
        this.userName = userName;
    }

    public Users(String userName) {
        this.userId = UUID.randomUUID();
        this.userName = userName;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Address> getAddresses() {
        return this.addresses;
    }

    public List<PhoneDetail> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDetail> phones) {
        this.phones = phones;
    }

    public List<EmailDetail> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailDetail> emails) {
        this.emails = emails;
    }

    public Map<String, String> getCharacter() {
        return character;
    }

    public void setCharacter(Map<String, String> character) {
        this.character = character;
    }

    public String toJSONString() {
        if (userId == null)
            userId = UUID.randomUUID();
        return JSONObject.toJSONString(this);
    }
}
