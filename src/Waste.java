package src;

public class Waste extends Activity {
    private String disposalMethod;

    public Waste(double wasteGenerated, String disposalMethod) {
        super("Waste", wasteGenerated);
        this.disposalMethod = disposalMethod;
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
}

