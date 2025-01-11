package src;
import java.util.Scanner;

public class Transport {
    public static double calculateTransportEmission(Scanner scanner) {
        System.out.print("Enter daily distance traveled (in km): ");
        double distance = scanner.nextDouble();
        System.out.print("Enter mode of transport (car, bus, train, plane): ");
        String mode = scanner.next().toLowerCase();

        double emissionFactor = switch (mode) {
            case "car" -> 0.21;
            case "bus" -> 0.10;
            case "train" -> 0.05;
            case "plane" -> 0.25;
            default -> 0.0; // Bike or walking
        };

        System.out.print("Enter number of days you travel per month: ");
        int days = scanner.nextInt();

        double monthlyEmission = distance * emissionFactor * days;
        System.out.printf("Transport Emission: %.2f kg CO₂/month\n", monthlyEmission);
        return monthlyEmission;
    }
}
