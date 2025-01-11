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
        // double dietEmission = Diet.calculateDietEmission(scanner);
        // Create a DietManager instance
        DietManager manager = new DietManager();

        // Prompt user for diet type
        System.out.print("Enter your diet type (vegan, vegetarian, omnivore): ");
        String dietType = scanner.next();
        manager.setDietType(dietType);

        // Initialize and display food list based on diet type
        manager.initializeFoodList();

        // Calculate total carbon footprint
        double dietEmission = manager.calculateFoodEmission(scanner);

        // Waste Module
        System.out.println("\n--- Waste Emissions ---");
        double wasteEmission = Waste.calculateWasteEmission(scanner);

        // Total Emission
        double totalEmission = transportEmission + energyEmission + dietEmission + wasteEmission;
        System.out.printf("\nYour Total Monthly Carbon Footprint: %.2f kg CO2\n", totalEmission);

        scanner.close();
    }
}
