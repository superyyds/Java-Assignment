package src;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DietManager manager = new DietManager();

        System.out.println("Welcome to the Carbon Footprint Calculator!\n");

        double totalEmissions = 0.0;
        boolean continueCalculating = true;

        while (continueCalculating) {
            System.out.println("--- Main Menu ---");
            System.out.println("1. Calculate Transport Emissions");
            System.out.println("2. Calculate Energy Emissions");
            System.out.println("3. Calculate Diet Emissions");
            System.out.println("4. Calculate Waste Emissions");
            System.out.println("5. Exit and Show Total Carbon Footprint");
            System.out.print("Please select an option (1-5): ");
            
            int choice = getValidChoice(scanner);

            switch (choice) {
                case 1:
                    // Transport Module
                    System.out.println("\n--- Transport Emissions ---");
                    String transportMode = getTransportMode(scanner);
                    double distance = getDistance(scanner);
                    Transport transport = new Transport(transportMode, distance);
                    double transportEmissions = transport.calculateEmissions();
                    totalEmissions += transportEmissions;
                    System.out.println("Transport Emissions: " + transportEmissions + " kg CO2");
                    System.out.println(transport.getReductionSuggestion());
                    break;

                case 2:
                    // Energy Module
                    System.out.println("\n--- Energy Emissions ---");
                    // Display emission choices first
                    Energy.displayEmissionChoices();  
                    System.out.print("Select the Electricity Source: ");
                    int energyChoice = getValidChoice(scanner);  // Get user choice for energy source
                    double energyEmissionFactor = Energy.getEmissionFactorFromChoice(energyChoice);
                    
                    // Get energy consumed next
                    double energyConsumed = getEnergyConsumed(scanner);
                    
                    // Then ask for renewable energy percentage
                    System.out.print("Enter renewable energy percentage (0 to 100): ");
                    double renewablePercentage = Double.parseDouble(scanner.nextLine()); // Custom renewable percentage
                    
                    // Create an Energy object with the inputs
                    Energy energy = new Energy(energyConsumed, energyEmissionFactor, renewablePercentage);
                    double energyEmissions = energy.calculateEmissions();
                    totalEmissions += energyEmissions;
                    System.out.println("Energy Emissions: " + energyEmissions + " kg CO2");
                    System.out.println(energy.getReductionSuggestion());
                    break;

                case 3:
                    // Diet Module
                    System.out.println("\n--- Diet Emissions ---");
                    String dietType = getDietType(scanner, manager); // Get diet type and update manager
                    manager.initializeFoodList(); // Initialize food list after diet type selection
                    totalEmissions += manager.calculateFoodEmission(scanner); // Calculate emissions for food
                    break;

                case 4:
                    // Waste Module
                    System.out.println("\n--- Waste Emissions ---");
                    double wasteGenerated = getWasteGenerated(scanner);
                    String disposalMethod = getDisposalMethod(scanner);
                    Waste waste = new Waste(wasteGenerated, disposalMethod);
                    double wasteEmissions = waste.calculateEmissions();
                    totalEmissions += wasteEmissions;
                    System.out.println("Waste Emissions: " + wasteEmissions + " kg CO2");
                    System.out.println(waste.getReductionSuggestion());
                    break;

                case 5:
                    // Exit and display the Total Carbon Footprint
                    System.out.println("\nTotal Carbon Footprint: " + totalEmissions + " kg CO2");
                    System.out.println("Exiting the program. Goodbye!");
                    continueCalculating = false;
                    break;

                default:
                    System.out.print("Invalid choice. Please select a valid option.");
            }

            if (choice != 5) {
                System.out.print("\nDo you want to calculate another section? (y/n): ");
                String continueChoice = scanner.nextLine().toLowerCase();
                if (continueChoice.equals("y")) {
                    // Continue the loop to show the main menu again
                    continueCalculating = true;
                } else if (continueChoice.equals("n")) {
                    // Exit after the user selects 'n'
                    System.out.println("\nTotal Carbon Footprint: " + totalEmissions + " kg CO2");
                    System.out.println("Exiting the program. Goodbye!");
                    continueCalculating = false;
                } else {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                    // Keep the loop running if the input is invalid
                    continueCalculating = true;
                }
            }
        }

        scanner.close();
    }

    private static int getValidChoice(Scanner scanner) {
        int choice = 0;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 5) {
                    System.out.print("Invalid choice. Please select a valid option.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
            }
        }
        return choice;
    }

    // Method to get the transport mode
    private static String getTransportMode(Scanner scanner) {
        System.out.print("Enter your transport mode (car, bus, train, etc.): ");
        return scanner.nextLine().toLowerCase();
    }

    // Method to get the distance for transport emissions
    private static double getDistance(Scanner scanner) {
        double distance = 0;
        System.out.print("Enter the distance traveled (in kilometers): ");
        while (true) {
            try {
                distance = Double.parseDouble(scanner.nextLine());
                if (distance <= 0) {
                    System.out.println("Please enter a positive number for the distance.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number:");
            }
        }
        return distance;
    }

    // Method to get the energy consumed for energy emissions
    private static double getEnergyConsumed(Scanner scanner) {
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
                System.out.print("Invalid input. Please enter a valid number:");
            }
        }
        return energyConsumed;
    }

    // Method to get the user's diet type
    private static String getDietType(Scanner scanner, DietManager manager) {
        String dietType = "";
        while (true) {
            System.out.print("Enter diet type (vegan, vegetarian, omnivore): ");
            dietType = scanner.nextLine().toLowerCase();
            if (dietType.equals("vegan") || dietType.equals("vegetarian") || dietType.equals("omnivore")) {
                manager.setDietType(dietType);
                break;
            } else {
                System.out.println("Invalid diet type. Please enter 'vegan', 'vegetarian', or 'omnivore'.");
            }
        }
        return dietType;
    }

    // Method to get the waste generated
    private static double getWasteGenerated(Scanner scanner) {
        double wasteGenerated = 0;
        System.out.print("Enter the amount of waste generated (in kg): ");
        while (true) {
            System.out.print("Enter waste generated (in kg): ");
            try {
                wasteGenerated = Double.parseDouble(scanner.nextLine());
                if (wasteGenerated <= 0) {
                    System.out.println("Please enter a positive number for the waste generated.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid amount of waste.");
            }
        }
        return wasteGenerated;
    }

    private static String getDisposalMethod(Scanner scanner) {
        System.out.print("Enter waste disposal method (landfill, recycling, composting): ");
        return scanner.nextLine().toLowerCase();
    }
}
