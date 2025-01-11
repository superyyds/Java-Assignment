package src;

public class Diet extends Activity {
    private String dietType;

    public Diet(String dietType, double foodConsumed) {
        super("Diet", foodConsumed);
        this.dietType = dietType;
    }

    @Override
    public double calculateEmissions() {
        double emissionFactor = 0.0;
        switch (dietType.toLowerCase()) {
            case "vegan":
                emissionFactor = 2.0; // kg CO2 per kg of food
                break;
            case "vegetarian":
                emissionFactor = 2.5;
                break;
            case "omnivore":
                emissionFactor = 5.0;
                break;
        }
        return emissionFactor * quantity;
    }

    @Override
    public String getReductionSuggestion() {
        return "Suggestion: Reduce meat consumption and prefer locally sourced food.";
    }
}
