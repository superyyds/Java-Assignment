package src;

public class Transport extends Activity {
    private String transportMode;

    public Transport(String transportMode, double distance) {
        super("Transport", distance);
        this.transportMode = transportMode;
    }

    @Override
    public double calculateEmissions() {
        double emissionFactor = 0.0;
        switch (transportMode.toLowerCase()) {
            case "car":
                emissionFactor = 0.21; // kg CO2 per km
                break;
            case "bus":
                emissionFactor = 0.10;
                break;
            case "bike":
                emissionFactor = 0.0;
                break;
            case "train":
                emissionFactor = 0.05;
                break;
        }
        return emissionFactor * quantity;
    }

    @Override
    public String getReductionSuggestion() {
        return "Suggestion: Use public transport, carpool, or switch to biking/walking.";
    }
}
