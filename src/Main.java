package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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
                    String dietType = getDietType(scanner);
                    double foodConsumed = getFoodConsumed(scanner);
                    Diet diet = new Diet(dietType, foodConsumed);
                    double dietEmissions = diet.calculateEmissions();
                    totalEmissions += dietEmissions;
                    System.out.println("Diet Emissions: " + dietEmissions + " kg CO2");
                    System.out.println(diet.getReductionSuggestion());
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
                if (!continueChoice.equals("y")) {
                    continueCalculating = false;
                    System.out.println("\nTotal Carbon Footprint: " + totalEmissions + " kg CO2");
                    System.out.println("Exiting the program. Goodbye!");
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

    private static String getTransportMode(Scanner scanner) {
        System.out.print("Enter transport mode (car, bus, bike, train): ");
        return scanner.nextLine().toLowerCase();
    }

    private static double getDistance(Scanner scanner) {
        double distance;
        while (true) {
            System.out.print("Enter distance traveled (in km): ");
            try {
                distance = Double.parseDouble(scanner.nextLine());
                if (distance < 0) {
                    System.out.println("Distance cannot be negative. Please try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number:");
            }
        }
        return distance;
    }

    private static double getEnergyConsumed(Scanner scanner) {
        double energyConsumed;
        while (true) {
            System.out.print("Enter energy consumed (in kWh): ");
            try {
                energyConsumed = Double.parseDouble(scanner.nextLine());
                if (energyConsumed < 0) {
                    System.out.println("Energy consumption cannot be negative. Please try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number:");
            }
        }
        return energyConsumed;
    }

    private static String getDietType(Scanner scanner) {
        System.out.print("Enter diet type (vegan, vegetarian, omnivore): ");
        return scanner.nextLine().toLowerCase();
    }

    private static double getFoodConsumed(Scanner scanner) {
        double foodConsumed;
        while (true) {
            System.out.print("Enter amount of food consumed (in kg): ");
            try {
                foodConsumed = Double.parseDouble(scanner.nextLine());
                if (foodConsumed < 0) {
                    System.out.println("Food consumption cannot be negative. Please try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number:");
            }
        }
        return foodConsumed;
    }

    private static double getWasteGenerated(Scanner scanner) {
        double wasteGenerated;
        while (true) {
            System.out.print("Enter waste generated (in kg): ");
            try {
                wasteGenerated = Double.parseDouble(scanner.nextLine());
                if (wasteGenerated < 0) {
                    System.out.println("Waste generated cannot be negative. Please try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number:");
            }
        }
        return wasteGenerated;
    }

    private static String getDisposalMethod(Scanner scanner) {
        System.out.print("Enter waste disposal method (landfill, recycling, composting): ");
        return scanner.nextLine().toLowerCase();
    }
}
