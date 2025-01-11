package src;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Carbon Footprint Calculator!");

        // Transport Module
        System.out.println("\n--- Transport Emissions ---");
        double transportEmission = Transport.calculateTransportEmission(scanner);

        // Energy Module
        System.out.println("\n--- Energy Emissions ---");
        double energyEmission = Energy.calculateEnergyEmission(scanner);

        // Diet Module
        System.out.println("\n--- Diet Emissions ---");
        double dietEmission = Diet.calculateDietEmission(scanner);

        // Waste Module
        System.out.println("\n--- Waste Emissions ---");
        double wasteEmission = Waste.calculateWasteEmission(scanner);

        // Total Emission
        double totalEmission = transportEmission + energyEmission + dietEmission + wasteEmission;
        System.out.printf("\nYour Total Monthly Carbon Footprint: %.2f kg CO₂\n", totalEmission);

        scanner.close();
    }
}
