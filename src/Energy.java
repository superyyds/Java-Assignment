package src;

public class Energy extends Activity {
    private final double emissionFactor;  // Emission factor (kg CO2 per kWh)
    private final double renewablePercentage;  // Percentage of renewable energy in their source

    // Constructor
    public Energy(double energyConsumed, double emissionFactor, double renewablePercentage) {
        super("Energy", energyConsumed);
        if (emissionFactor < 0 || renewablePercentage < 0 || renewablePercentage > 100) {
            throw new IllegalArgumentException("Invalid values: emission factor and renewable percentage must be non-negative, and renewable percentage cannot exceed 100.");
        }
        this.emissionFactor = emissionFactor;
        this.renewablePercentage = renewablePercentage;
    }

    // Getter methods
    public double getEmissionFactor() {
        return emissionFactor;
    }

    public double getRenewablePercentage() {
        return renewablePercentage;
    }

    // Method to calculate emissions based on energy consumed and emission factor
    @Override
    public double calculateEmissions() {
        if (quantity < 0) {
            throw new IllegalArgumentException("Energy consumed must be non-negative.");
        }
        return quantity * emissionFactor * (1 - renewablePercentage / 100.0); // Adjust for renewable energy percentage
    }

    // Method to provide suggestions to reduce emissions
    @Override
    public String getReductionSuggestion() {
        StringBuilder suggestion = new StringBuilder("Suggestion: ");
        suggestion.append("Switch to higher renewable energy sources or suppliers. ");
        suggestion.append("Reduce energy consumption by using energy-efficient appliances and turning off unused devices.");
        return suggestion.toString();
    }

    // Simplified emission factors for various energy sources (in kg CO2 per kWh)
    public static double getEmissionFactorFromChoice(int choice) {
        switch (choice) {
            case 1: // Coal + Natural Gas (High Carbon)
                return 0.92;
            case 2: // Coal (Very High Carbon)
                return 1.0;
            case 3: // Mixed Energy (Renewable + Non-Renewable)
                return 0.5;
            case 4: // Natural Gas + Coal (Moderate Carbon)
                return 0.6;
            case 5: // Renewable Energy (Low Carbon)
                return 0.1;
            case 6: // Mixed Electricity (Moderate Carbon)
                return 0.5;
            default:
                return 0; // Default case (should not occur)
        }
    }

    // Method to display emission factor choices to the user
    public static void displayEmissionChoices() {
        System.out.println("Select your electricity source:");
        System.out.println("1: Coal + Natural Gas (High Carbon)");
        System.out.println("2: Coal (Very High Carbon)");
        System.out.println("3: Mixed Energy (Renewable + Non-Renewable)");
        System.out.println("4: Natural Gas + Coal (Moderate Carbon)");
        System.out.println("5: Renewable Energy (Low Carbon)");
        System.out.println("6: Mixed Electricity (Moderate Carbon)");
    }
}
