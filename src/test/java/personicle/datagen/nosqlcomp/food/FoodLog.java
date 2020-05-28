package personicle.datagen.nosqlcomp.food;

import asterix.recordV2.wrapper.Uuid;
import personicle.datagen.nosqlcomp.GeneralMeasurement;

import java.util.List;

public class FoodLog extends GeneralMeasurement {
    private String foodName;
    private Double weight;
    private Double total_calories;
    private List<Uuid> subEvents;   //subEvents: [uuid],    --sub events of event
//    private Point location;         //location: point?,     --location of event
    private Double latitude;
    private Double longitude;
    private Integer preference_star;
    private String comments;

    public FoodLog() {
        super();
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getTotal_calories() {
        return total_calories;
    }

    public void setTotal_calories(Double total_calories) {
        this.total_calories = total_calories;
    }

    public List<Uuid> getSubEvents() {
        return subEvents;
    }

    public void setSubEvents(List<Uuid> subEvents) {
        this.subEvents = subEvents;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getPreference_star() {
        return preference_star;
    }

    public void setPreference_star(Integer preference_star) {
        this.preference_star = preference_star;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

//    public String getActivity() {
//        return activity;
//    }
//
//    public void setActivity(String activity) {
//        this.activity = activity;
//    }

}
