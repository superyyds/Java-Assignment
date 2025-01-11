package src;
import java.util.Scanner;

public class Diet {
    public static double calculateDietEmission(Scanner scanner) {
        System.out.print("Enter your diet type (vegan, vegetarian, omnivore): ");
        String dietType = scanner.next().toLowerCase();

        double emissionFactor = switch (dietType) {
            case "vegan" -> 125.0;
            case "vegetarian" -> 150.0;
            case "omnivore" -> 208.0;
            default -> 0.0; // Unknown diet type
        };

        System.out.printf("Diet Emission: %.2f kg CO₂/month\n", emissionFactor);
        return emissionFactor;
    }
}
