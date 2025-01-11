package src;

import java.util.*;

public class DietManager {
    private String dietType;
    private List<DietFood> foodList;

    // Set the diet type
    public void setDietType(String dietType) {
        this.dietType = dietType.toLowerCase();
    }

    // Initialize food list based on diet type
    public void initializeFoodList() {
        foodList = new ArrayList<>();

        // Common foods across all diets
        foodList.add(new DietFood("Tomatoes", 2.09));
        foodList.add(new DietFood("Rice", 4.45));
        foodList.add(new DietFood("Potatoes", 0.46));
        foodList.add(new DietFood("Tofu", 3.16));
        foodList.add(new DietFood("Nuts", 0.43));
        foodList.add(new DietFood("Citrus Fruit", 0.39));

        switch (dietType) {
            case "vegan":
                break;
            case "vegetarian":
                foodList.add(new DietFood("Eggs", 4.67));
                foodList.add(new DietFood("Cheese", 23.88));
                foodList.add(new DietFood("Milk", 3.15));
                break;
            case "omnivore":
                foodList.add(new DietFood("Eggs", 4.67));
                foodList.add(new DietFood("Cheese", 23.88));
                foodList.add(new DietFood("Milk", 3.15));
                foodList.add(new DietFood("Beef (beef herd)", 99.48));
                foodList.add(new DietFood("Poultry", 9.87));
                foodList.add(new DietFood("Pork", 12.31));
                
                break;
            default:
                System.out.println("Invalid diet type. Defaulting to common foods only.");
                break;
        }
    }

    // Display the list of foods
    public void displayFoodList() {
        System.out.println("\nFood Choices:");
        for (int i = 0; i < foodList.size(); i++) {
            DietFood food = foodList.get(i);
            System.out.printf("%d. %s (%.2f kg CO2 per gram)\n", i + 1, food.getName(), food.getCarbonFootprint());
        }
    }

    // Calculate the carbon footprint based on user's food selection
    public double calculateFoodEmission(Scanner scanner) {
        double totalEmission = 0.0;
        boolean continueSelection = true;

        while (continueSelection) {
            displayFoodList();
            System.out.print("Select a food by number: ");
            int choice = scanner.nextInt() - 1;

            if (choice >= 0 && choice < foodList.size()) {
                DietFood selectedFood = foodList.get(choice);
                System.out.print("Enter the amount (in grams): ");
                double quantity = scanner.nextDouble();
                double emission = selectedFood.getCarbonFootprint() * quantity;
                totalEmission += emission;

                System.out.printf("Added %.2f kg CO2 for %s.\n", emission, selectedFood.getName());
            } else {
                System.out.println("Invalid choice. Please try again.");
            }

            System.out.print("Would you like to select another food? (yes/no): ");
            continueSelection = scanner.next().equalsIgnoreCase("yes");
        }

        return totalEmission;
    }
}
