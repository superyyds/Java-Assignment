package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Carbon Footprint Calculator\n");

        System.out.print("Enter transport mode (car, bus, bike, train): ");
        String transportMode = scanner.nextLine();
        System.out.print("Enter distance traveled (in km): ");
        double distance = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter energy consumed (in kWh): ");
        double energyConsumed = scanner.nextDouble();
        System.out.print("Enter energy emission factor (kg CO2 per kWh): ");
        double energyEmissionFactor = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter diet type (vegan, vegetarian, omnivore): ");
        String dietType = scanner.nextLine();
        System.out.print("Enter amount of food consumed (in kg): ");
        double foodConsumed = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter waste generated (in kg): ");
        double wasteGenerated = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter waste disposal method (landfill, recycling, composting): ");
        String disposalMethod = scanner.nextLine();

        Activity[] activities = {
            new Transport(transportMode, distance),
            new Energy(energyConsumed, energyEmissionFactor),
            new Diet(dietType, foodConsumed),
            new Waste(wasteGenerated, disposalMethod)
        };

        double totalEmissions = 0.0;
        for (Activity activity : activities) {
            double emissions = activity.calculateEmissions();
            totalEmissions += emissions;
            System.out.println("\nActivity: " + activity.activityType);
            System.out.println("Emissions: " + emissions + " kg CO2");
            System.out.println(activity.getReductionSuggestion());
        }

        System.out.println("\nTotal Carbon Footprint: " + totalEmissions + " kg CO2");

        scanner.close();
    }
}