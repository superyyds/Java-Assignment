package src;
import java.util.Scanner;

public class Waste {
    public static double calculateWasteEmission(Scanner scanner) {
        System.out.print("Enter number of bags of waste generated per week: ");
        int bagsPerWeek = scanner.nextInt();
        double emissionPerBag = 5.0; // Average emission per bag (kg CO₂)

        double monthlyEmission = bagsPerWeek * emissionPerBag * 4; // 4 weeks in a month
        System.out.printf("Waste Emission: %.2f kg CO₂/month\n", monthlyEmission);
        return monthlyEmission;
    }
}
