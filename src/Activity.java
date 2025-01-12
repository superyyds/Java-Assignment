package src;

public class Activity {
    protected String activityType;
    protected double quantity;

    public Activity(String activityType, double quantity) {
        this.activityType = validateActivityType(activityType);
        this.quantity = validateQuantity(quantity);
    }

    public double calculateEmissions() {
        return 0.0; // To be overridden by subclasses
    }

    public String getReductionSuggestion() {
        return "General suggestion: Reduce usage or optimize activities.";
    }

    // Helper method to validate activity type
    private String validateActivityType(String activityType) {
        if (activityType == null || activityType.trim().isEmpty()) {
            throw new IllegalArgumentException("Activity type cannot be null or empty.");
        }
        return activityType.trim();
    }

    // Helper method to validate quantity
    private double validateQuantity(double quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive value.");
        }
        return quantity;
    }

    // Getters for subclass or external use
    public String getActivityType() {
        return activityType;
    }

    public double getQuantity() {
        return quantity;
    }
}