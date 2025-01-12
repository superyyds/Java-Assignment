package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Carbon Footprint Calculator!\n");

        // Initialize the total emissions variable
        double totalEmissions = 0.0;

        boolean continueCalculating = true;

        while (continueCalculating) {
            // Display main menu to user
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
                    totalEmissions += transportEmissions; // Add emissions to total
                    System.out.println("Transport Emissions: " + transportEmissions + " kg CO2");
                    System.out.println(transport.getReductionSuggestion());
                    break;

                case 2:
                    // Energy Module
                    System.out.println("\n--- Energy Emissions ---");
                    double energyConsumed = getEnergyConsumed(scanner);
                    double energyEmissionFactor = getEnergyEmissionFactor(scanner);
                    Energy energy = new Energy(energyConsumed, energyEmissionFactor, 0); // Assuming no renewable energy
                    double energyEmissions = energy.calculateEmissions();
                    totalEmissions += energyEmissions; // Add emissions to total
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
                    totalEmissions += dietEmissions; // Add emissions to total
                    System.out.println("Diet Emissions: " + dietEmissions + " kg CO2");
                    System.out.println(diet.getReductionSuggestion());
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

                    // Add emissions to total
                    totalEmissions += positiveEmissions + negativeEmissions;

                    // Display results
                    System.out.println("Positive Waste Emissions: " + positiveEmissions + " kg CO2");
                    System.out.println("Negative Waste Emissions: " + negativeEmissions + " kg CO2");
                    System.out.println("Net Waste Emissions: " + (positiveEmissions + negativeEmissions) + " kg CO2");
                    System.out.println(waste.getReductionSuggestion());
                    break;



                case 5:
                    // Exit and display the Total Carbon Footprint
                    System.out.println("\nTotal Carbon Footprint: " + totalEmissions + " kg CO2");
                    System.out.println("Exiting the program. Goodbye!");
                    continueCalculating = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option (1-5).");
            }

            // Ask if the user wants to continue or exit after completing a section
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

    // Method to handle valid input for menu choices
    private static int getValidChoice(Scanner scanner) {
        int choice = 0;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 5) {
                    System.out.println("Invalid choice. Please select a valid option (1-5).");
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
                System.out.println("Invalid input. Please enter a valid number.");
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
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return energyConsumed;
    }

    private static double getEnergyEmissionFactor(Scanner scanner) {
        double emissionFactor;
        while (true) {
            System.out.print("Enter energy emission factor (kg CO2 per kWh): ");
            try {
                emissionFactor = Double.parseDouble(scanner.nextLine());
                if (emissionFactor < 0) {
                    System.out.println("Emission factor cannot be negative. Please try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return emissionFactor;
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
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return foodConsumed;
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
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return value;
    }

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
