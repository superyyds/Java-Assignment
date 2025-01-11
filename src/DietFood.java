package src;

public class DietFood {
    private String name;
    private double carbonFootprint; // Carbon footprint per gram (kg CO₂)

    public DietFood(String name, double carbonFootprint) {
        this.name = name;
        this.carbonFootprint = carbonFootprint;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getCarbonFootprint() {
        return carbonFootprint;
    }
}
