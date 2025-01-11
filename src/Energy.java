package src;
import java.util.Scanner;

public class Energy {
    public static double calculateEnergyEmission(Scanner scanner) {
        System.out.print("Enter monthly electricity usage (in kWh): ");
        double electricityUsage = scanner.nextDouble();
        System.out.print("Enter percentage of renewable energy (0-100): ");
        double renewablePercentage = scanner.nextDouble();

        double emissionFactor = 0.85; // Default for coal-based grid
        double nonRenewableFactor = 1 - (renewablePercentage / 100.0);

        double monthlyEmission = electricityUsage * emissionFactor * nonRenewableFactor;
        System.out.printf("Energy Emission: %.2f kg CO₂/month\n", monthlyEmission);
        return monthlyEmission;
    }
}
