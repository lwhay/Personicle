package personicle.datagen.nosqlcomp.bracelet.braceletECGList;

import asterix.recordV2.wrapper.Uuid;
import personicle.datagen.nosqlcomp.GeneralMeasurement;
import personicle.datagen.nosqlcomp.sensoring.Spatial3DPoint;

import java.util.UUID;

public class BraceletECGList extends GeneralMeasurement {
    private String id;
    private int netId;
    private String mac;
    private int ecg;
    private int isTouched;
    private int hr;
    private int mood;
    private int resp;
    private int stress;
    private int hrv;
    private int heartage;
    private int lfhf;
    private int count;
    private String ecgDetailUrl;
    private String dateString;
    private String status;
    private Long createTime;
    private Long updateTime;

    public String getId() {
        return id;
    }

    public void setId(Uuid id) {
        this.id = id.getUuid().toString().replace("-", "");
        this.id = this.id.trim();
    }

    public int getNetId() {
        return netId;
    }

    public void setNetId(int netId) {
        this.netId = netId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getEcg() {
        return ecg;
    }

    public void setEcg(int ecg) {
        this.ecg = ecg;
    }

    public int getIsTouched() {
        return isTouched;
    }

    public void setIsTouched(int isTouched) {
        this.isTouched = isTouched;
    }

    public int getHr() {
        return hr;
    }

    public void setHr(int hr) {
        this.hr = hr;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public int getResp() {
        return resp;
    }

    public void setResp(int resp) {
        this.resp = resp;
    }

    public int getStress() {
        return stress;
    }

    public void setStress(int stress) {
        this.stress = stress;
    }

    public int getHrv() {
        return hrv;
    }

    public void setHrv(int hrv) {
        this.hrv = hrv;
    }

    public int getHeartage() {
        return heartage;
    }

    public void setHeartage(int heartage) {
        this.heartage = heartage;
    }

    public int getLfhf() {
        return lfhf;
    }

    public void setLfhf(int lfhf) {
        this.lfhf = lfhf;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getEcgDetailUrl() {
        return ecgDetailUrl;
    }

    public void setEcgDetailUrl(String ecgDetailUrl) {
        this.ecgDetailUrl = ecgDetailUrl;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

}
