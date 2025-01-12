package src;

public class Waste extends Activity {
    private String disposalMethod;

    public Waste(double wasteGenerated, String disposalMethod) {
        super("Waste", validateQuantity(wasteGenerated));
        this.disposalMethod = validateDisposalMethod(disposalMethod);
    }

    @Override
    public double calculateEmissions() {
        double emissionFactor = 0.0;
        switch (disposalMethod.toLowerCase()) {
            case "landfill":
                emissionFactor = 0.9; // kg CO2 per kg of waste
                break;
            case "recycling":
                emissionFactor = -0.5; // Negative value for reduction
                break;
            case "composting":
                emissionFactor = -0.2;
                break;
        }
        return emissionFactor * quantity;
    }

    @Override
    public String getReductionSuggestion() {
        return "Suggestion: Recycle, compost organic waste, and reduce overall waste generation.";
    }

    // Helper to validate the waste quantity
    private static double validateQuantity(double wasteGenerated) {
        if (wasteGenerated <= 0) {
            throw new IllegalArgumentException("Waste generated must be a positive value.");
        }
        return wasteGenerated;
    }

    // Helper to validate the disposal method
    private static String validateDisposalMethod(String disposalMethod) {
        if (disposalMethod == null || disposalMethod.isBlank()) {
            throw new IllegalArgumentException("Disposal method cannot be null or empty.");
        }
        switch (disposalMethod.toLowerCase()) {
            case "landfill":
            case "recycling":
            case "composting":
                return disposalMethod.toLowerCase();
            default:
                throw new IllegalArgumentException("Invalid disposal method. Use 'landfill', 'recycling', or 'composting'.");
        }
    }
}
