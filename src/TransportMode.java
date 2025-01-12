package src;

public class TransportMode {
    private String mode;
    private double emissionFactor;

    public TransportMode(String mode, double emissionFactor) {
        this.mode = mode;
        this.emissionFactor = emissionFactor;
    }

    public String getMode() {
        return mode;
    }

    public double getEmissionFactor() {
        return emissionFactor;
    }
}
