package src;
import java.util.Scanner;

public class Diet {
    public static double calculateDietEmission(Scanner scanner) {
        System.out.print("Enter your diet type (vegan, vegetarian, omnivore): ");
        String dietType = scanner.next().toLowerCase();

        double emissionFactor;
        switch (dietType) {
            case "vegan":
                emissionFactor = 125.0;
                break;
            case "vegetarian":
                emissionFactor = 150.0;
                break;
            case "omnivore":
                emissionFactor = 208.0;
                break;
            default:
                emissionFactor = 0.0; // Unknown diet type
                break;
        }

        System.out.printf("Diet Emission: %.2f kg CO2/month\n", emissionFactor);
        return emissionFactor;
    }
}
