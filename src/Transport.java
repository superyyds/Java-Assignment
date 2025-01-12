package src;

public class Transport extends Activity {
    private String transportMode;
    private double emissionFactor;

    public Transport(String transportMode, double distance, double emissionFactor) {
        super("Transport", distance);
        this.transportMode = transportMode;
        this.emissionFactor = emissionFactor;
    }

    @Override
    public double calculateEmissions() {
        return emissionFactor * quantity;
    }

    @Override
    public String getReductionSuggestion() {
        switch (transportMode.toLowerCase()) {
            case "car":
                return "Suggestion: Consider using public transport, carpooling, or driving less frequently to reduce emissions.";
            case "bus":
                return "Suggestion: Consider using an electric bus or opting for less frequent travel if possible.";
            case "bike":
                return "Great choice! Keep cycling to reduce emissions and maintain a healthy lifestyle.";
            case "train":
                return "Suggestion: Consider choosing direct routes to reduce energy consumption and carbon footprint.";
            default:
                return "Suggestion: Consider using a more sustainable transport option to reduce your carbon footprint.";
        }
    }
}