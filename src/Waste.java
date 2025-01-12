package src;

public class Waste extends Activity {
    private double plasticWaste;
    private double paperWaste;
    private double glassWaste;
    private String plasticDisposalMethod;
    private String paperDisposalMethod;
    private String glassDisposalMethod;

    public Waste(double plasticWaste, String plasticDisposalMethod, 
                 double paperWaste, String paperDisposalMethod, 
                 double glassWaste, String glassDisposalMethod) {
        super("Waste", validateQuantity(plasticWaste + paperWaste + glassWaste));
        this.plasticWaste = validateQuantity(plasticWaste);
        this.paperWaste = validateQuantity(paperWaste);
        this.glassWaste = validateQuantity(glassWaste);
        this.plasticDisposalMethod = validateDisposalMethod(plasticDisposalMethod);
        this.paperDisposalMethod = validateDisposalMethod(paperDisposalMethod);
        this.glassDisposalMethod = validateDisposalMethod(glassDisposalMethod);
    }

    public double[] calculateWasteEmissions() {
        double positiveEmissions = 0.0;
        double negativeEmissions = 0.0;

        // Calculate plastic emissions
        double plasticEmissionFactor = getEmissionFactor(plasticDisposalMethod, "plastic");
        if (plasticEmissionFactor > 0) {
            positiveEmissions += plasticWaste * plasticEmissionFactor;
        } else {
            negativeEmissions += plasticWaste * plasticEmissionFactor;
        }

        // Calculate paper emissions
        double paperEmissionFactor = getEmissionFactor(paperDisposalMethod, "paper");
        if (paperEmissionFactor > 0) {
            positiveEmissions += paperWaste * paperEmissionFactor;
        } else {
            negativeEmissions += paperWaste * paperEmissionFactor;
        }

        // Calculate glass emissions
        double glassEmissionFactor = getEmissionFactor(glassDisposalMethod, "glass");
        if (glassEmissionFactor > 0) {
            positiveEmissions += glassWaste * glassEmissionFactor;
        } else {
            negativeEmissions += glassWaste * glassEmissionFactor;
        }

        return new double[]{positiveEmissions, negativeEmissions};
    }

    private double getEmissionFactor(String method, String type) {
        switch (method) {
            case "landfill":
                return type.equals("plastic") ? 0.9 :
                       type.equals("paper") ? 0.6 : 0.2;
            case "recycling":
                return type.equals("plastic") ? -0.5 :
                       type.equals("paper") ? -0.6 : -0.2;
            case "composting":
                return type.equals("plastic") ? 0.0 : 
                       type.equals("paper") ? -0.3 : 0.0;
            default:
                return 0.0;
        }
    }

    @Override
    public String getReductionSuggestion() {
        return "Suggestion: Recycle, compost organic waste, and reduce plastic, paper, and glass waste.";
    }

    private static double validateQuantity(double wasteGenerated) {
        if (wasteGenerated < 0) {
            throw new IllegalArgumentException("Waste generated must be a non-negative value.");
        }
        return wasteGenerated;
    }

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