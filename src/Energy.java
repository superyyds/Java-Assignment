package src;

public class Energy extends Activity {
    private double emissionFactor;
    private double renewablePercentage;

    public Energy(double energyConsumed, double emissionFactor, double renewablePercentage) {
        super("Energy", energyConsumed);
        if (emissionFactor < 0 || renewablePercentage < 0 || renewablePercentage > 100) {
            throw new IllegalArgumentException("Invalid values: emission factor and renewable percentage must be non-negative, and renewable percentage cannot exceed 100.");
        }
        this.emissionFactor = emissionFactor;
        this.renewablePercentage = renewablePercentage;
    }

    @Override
    public double calculateEmissions() {
        if (quantity < 0) {
            throw new IllegalArgumentException("Energy consumed must be non-negative.");
        }
        return quantity * emissionFactor * (1 - renewablePercentage / 100.0); // Adjust for renewable energy percentage
    }

    @Override
    public String getReductionSuggestion() {
        StringBuilder suggestion = new StringBuilder("Suggestion: ");
        suggestion.append("Switch to higher renewable energy sources or suppliers. ");
        suggestion.append("Reduce energy consumption by using energy-efficient appliances and turning off unused devices.");
        return suggestion.toString();
    }

    public double getEmissionFactor() {
        return emissionFactor;
    }

    public void setEmissionFactor(double emissionFactor) {
        if (emissionFactor < 0) {
            throw new IllegalArgumentException("Emission factor must be non-negative.");
        }
        this.emissionFactor = emissionFactor;
    }

    public double getRenewablePercentage() {
        return renewablePercentage;
    }

    public void setRenewablePercentage(double renewablePercentage) {
        if (renewablePercentage < 0 || renewablePercentage > 100) {
            throw new IllegalArgumentException("Renewable percentage must be between 0 and 100.");
        }
        this.renewablePercentage = renewablePercentage;
    }
}
