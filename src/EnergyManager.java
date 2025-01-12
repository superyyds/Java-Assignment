package src;

import java.util.Scanner;

public class EnergyManager {

    // Method to handle the selection of energy sources and emission calculations
    public void calculateEnergyEmissions(Scanner scanner) {
        System.out.println("\n--- Energy Emissions ---");
        
        // Display emission choices to the user
        Energy.displayEmissionChoices();
        
        // Get user's choice of energy source
        int energyChoice = getValidChoice(scanner);  
        double energyEmissionFactor = Energy.getEmissionFactorFromChoice(energyChoice);
        
        // Get the amount of energy consumed
        double energyConsumed = getEnergyConsumed(scanner);
        
        // Ask for renewable energy percentage
        double renewablePercentage = getRenewablePercentage(scanner);
        
        // Create Energy object and calculate emissions
        Energy energy = new Energy(energyConsumed, energyEmissionFactor, renewablePercentage);
        double energyEmissions = energy.calculateEmissions();
        
        System.out.println("Energy Emissions: " + energyEmissions + " kg CO2");
        System.out.println(energy.getReductionSuggestion());
    }

    // Method to get a valid integer choice from the user (1-6)
    private int getValidChoice(Scanner scanner) {
        int choice = 0;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 6) {
                    System.out.println("Invalid choice. Please select a number between 1 and 6.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return choice;
    }

    // Method to get energy consumption from the user
    private double getEnergyConsumed(Scanner scanner) {
        double energyConsumed = 0;
        System.out.print("Enter the energy consumed (in kWh): ");
        while (true) {
            try {
                energyConsumed = Double.parseDouble(scanner.nextLine());
                if (energyConsumed <= 0) {
                    System.out.println("Please enter a positive number for the energy consumed.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number:");
            }
        }
        return energyConsumed;
    }

    // Method to get the renewable energy percentage from the user
    private double getRenewablePercentage(Scanner scanner) {
        double renewablePercentage = 0;
        System.out.print("Enter renewable energy percentage (0 to 100): ");
        while (true) {
            try {
                renewablePercentage = Double.parseDouble(scanner.nextLine());
                if (renewablePercentage < 0 || renewablePercentage > 100) {
                    System.out.println("Please enter a valid percentage between 0 and 100.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid percentage:");
            }
        }
        return renewablePercentage;
    }
}
