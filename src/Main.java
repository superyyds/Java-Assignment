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
                    TransportManager transportManager = new TransportManager(); 
                    transportManager.displayTransportModes(); 
                    String transportMode = getTransportMode(scanner);
                    TransportMode selectedMode = transportManager.getTransportMode(transportMode);
                     if (selectedMode != null) {
                          double distance = getDistance(scanner); 
                          double emissionFactor = selectedMode.getEmissionFactor();
                          Transport transport = new Transport(selectedMode.getMode(), distance, emissionFactor);
                          double transportEmissions = transport.calculateEmissions();
                          totalEmissions += transportEmissions;
                          System.out.println("Transport Emissions: " + String.format("%.2f", transportEmissions) + " kg CO2");
                          System.out.println(transport.getReductionSuggestion());
                       } else { 
                              System.out.println("Invalid transport mode. Please try again.");
                             }
                    break;

                    case 2:
                    System.out.println("\n--- Energy Emissions ---");
                    EnergyManager energyManager = new EnergyManager();
                    double energyEmissions = energyManager.manageEnergyCalculations(scanner);  // Get emissions
                    totalEmissions += energyEmissions;  // Add to total carbon footprint
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

                    // Input for plastic waste
                    System.out.print("Enter plastic waste generated (in kg): ");
                    double plasticWaste = getPositiveDouble(scanner);
                    String plasticDisposal = getDisposalMethod(scanner);

                    // Input for paper waste
                    System.out.print("Enter paper waste generated (in kg): ");
                    double paperWaste = getPositiveDouble(scanner);
                    String paperDisposal = getDisposalMethod(scanner);

                    // Input for glass waste
                    System.out.print("Enter glass waste generated (in kg): ");
                    double glassWaste = getPositiveDouble(scanner);
                    String glassDisposal = getDisposalMethod(scanner);

                    // Create Waste object
                    Waste waste = new Waste(plasticWaste, plasticDisposal, paperWaste, paperDisposal, glassWaste, glassDisposal);
                    double[] wasteEmissions = waste.calculateWasteEmissions();
                    double positiveEmissions = wasteEmissions[0];
                    double negativeEmissions = wasteEmissions[1];

                   
                    totalEmissions += positiveEmissions + negativeEmissions;

                    // Display results
                    System.out.println("Positive Waste Emissions: " + positiveEmissions + " kg CO2");
                    System.out.println("Negative Waste Emissions: " + negativeEmissions + " kg CO2");
                    System.out.println("Net Waste Emissions: " + (positiveEmissions + negativeEmissions) + " kg CO2");
                    System.out.println(waste.getReductionSuggestion());
                    break;



                case 5:
                    // Exit and display the Total Carbon Footprint
                    System.out.println("\nTotal Carbon Footprint: " + String.format("%.2f", totalEmissions) + " kg CO2");
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

    private static double getPositiveDouble(Scanner scanner) {
        double value;
        while (true) {
            try {
                value = Double.parseDouble(scanner.nextLine());
                if (value < 0) {
                    System.out.println("Value cannot be negative. Please try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid amount of waste.");
            }
        }
        return value;
    }

    // Method to get the waste disposal method
    private static String getDisposalMethod(Scanner scanner) {
        while (true) {
            System.out.print("Enter waste disposal method (landfill, recycling, composting): ");
            String method = scanner.nextLine().toLowerCase();
            if (method.equals("landfill") || method.equals("recycling") || method.equals("composting")) {
                return method;
            }
            System.out.println("Invalid disposal method. Please choose from 'landfill', 'recycling', or 'composting'.");
        }
    }


}