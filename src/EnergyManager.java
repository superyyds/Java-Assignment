package src;

import java.util.Scanner;

public class EnergyManager {

    // Display choices and prompt for energy-related inputs
    public double manageEnergyCalculations(Scanner scanner) {
        // Display energy source choices
        Energy.displayEmissionChoices();

        System.out.print("Select your electricity source (1-6): ");
        int energyChoice = getValidChoice(scanner);

        // Get emission factor from the selected choice
        double energyEmissionFactor = Energy.getEmissionFactorFromChoice(energyChoice);
        if (energyEmissionFactor == 0) {
            System.out.println("Invalid choice. Please restart the energy calculation module.");
            return 0;  // Return 0 emissions if invalid choice
        }

        // Get the amount of energy consumed
        double energyConsumed = getPositiveDouble(scanner, "Enter the energy consumed (in kWh): ");

        // Get the renewable energy percentage
        double renewablePercentage = getPercentage(scanner, "Enter renewable energy percentage (0 to 100): ");

        // Create an Energy object with the provided inputs
        Energy energy = new Energy(energyConsumed, energyEmissionFactor, renewablePercentage);

        // Calculate emissions and display the result
        double energyEmissions = energy.calculateEmissions();
        System.out.println("Energy Emissions: " + String.format("%.2f", energyEmissions) + " kg CO2");
        System.out.println(energy.getReductionSuggestion());

        return energyEmissions;  // Return calculated emissions
    }

    // Helper method to get a valid choice for energy source
    private int getValidChoice(Scanner scanner) {
        int choice = 0;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 6) {
                    break;
                } else {
                    System.out.print("Invalid choice. Please select a valid option (1-6): ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number between 1 and 6: ");
            }
        }
        return choice;
    }

    // Helper method to get a positive double value
    private double getPositiveDouble(Scanner scanner, String prompt) {
        double value;
        System.out.print(prompt);
        while (true) {
            try {
                value = Double.parseDouble(scanner.nextLine());
                if (value > 0) {
                    break;
                } else {
                    System.out.print("Value must be positive. " + prompt);
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
        return value;
    }

    // Helper method to get a percentage value between 0 and 100
    private double getPercentage(Scanner scanner, String prompt) {
        double percentage;
        System.out.print(prompt);
        while (true) {
            try {
                percentage = Double.parseDouble(scanner.nextLine());
                if (percentage >= 0 && percentage <= 100) {
                    break;
                } else {
                    System.out.print("Percentage must be between 0 and 100. " + prompt);
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid percentage: ");
            }
        }
        return percentage;
    }
}
