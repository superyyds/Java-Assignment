package src;

public class Energy extends Activity {
    private double emissionFactor;

    public Energy(double energyConsumed, double emissionFactor) {
        super("Energy", energyConsumed);
        this.emissionFactor = emissionFactor;
    }

    @Override
    public double calculateEmissions() {
        return emissionFactor * quantity; // kg CO2 = kWh * emission factor
    }

    @Override
    public String getReductionSuggestion() {
        return "Suggestion: Switch to renewable energy sources and reduce energy consumption.";
    }
}
