package src;

public class Activity {
    protected String activityType;
    protected double quantity;

    public Activity(String activityType, double quantity) {
        this.activityType = activityType;
        this.quantity = quantity;
    }

    public double calculateEmissions() {
        return 0.0; // To be overridden by subclasses
    }

    public String getReductionSuggestion() {
        return "General suggestion: Reduce usage or optimize activities.";
    }
}
