package changeM;

import java.util.Scanner;

public class Clientclass {
    private static ChangeM[] customers = new ChangeM[100];
    private static int count = 0;
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        studentInfo();
        System.out.println("Please enter at least 10 records to test the program.");
        
        // Uncomment the line below for tutor testing
        // count = populateSampleData(customers);
        
        inputLoop();
        menuLoop();
    }
    
    private static void inputLoop() {
        while (true) {
            System.out.println("Please enter the name of the person:");
            String name = input.nextLine().trim();
            
            // Check for duplicate names
            if (isDuplicateName(name)) {
                System.out.println("Name already exists. Please enter a different name.");
                continue;
            }
            
            // Get coin amount with validation
            int amount = -1;
            while (amount < 0) {
                System.out.println("Please enter the coin value for the person:");
                if (input.hasNextInt()) {
                    amount = input.nextInt();
                    if (amount < 0) {
                        System.out.println("Invalid amount. Must be a positive number.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    input.next(); // clear invalid input
                }
            }
            input.nextLine(); // clear the buffer
            
            // Create new customer and add to array
            customers[count] = new ChangeM(name, amount);
            count++;
            
            // Ask if user wants to continue
            System.out.println("Do you have more person to enter? (Yes/No)");
            String choice = input.nextLine().trim();
            
            if (choice.equalsIgnoreCase("no")) {
                break;
            } else if (!choice.equalsIgnoreCase("yes")) {
                System.out.println("Invalid input. Assuming 'No'. Proceeding to menu.");
                break;
            }
        }
    }
    
    private static void menuLoop() {
        int option;
        do {
            displayMenu();
            System.out.print("Enter your option (1-6): ");
            
            if (input.hasNextInt()) {
                option = input.nextInt();
                input.nextLine(); // clear buffer
                
                switch (option) {
                    case 1:
                        searchNameAndDisplayChange();
                        break;
                    case 2:
                        findSmallestAmount();
                        break;
                    case 3:
                        findLargestAmount();
                        break;
                    case 4:
                        displayTotalCoinsPerDenomination();
                        break;
                    case 5:
                        displayTotalAmount();
                        break;
                    case 6:
                        System.out.println("Thank you for using the system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please enter a number between 1-6.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine(); // clear invalid input
                option = 0;
            }
        } while (option != 6);
    }
    
    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Enter a name and display change to be given for each denomination");
        System.out.println("2. Find the name with the smallest amount and display change to be given for each denomination");
        System.out.println("3. Find the name with the largest amount and display change to be given for each denomination");
        System.out.println("4. Calculate and display the total number of coins for each denomination");
        System.out.println("5. Calculate and display the total amount");
        System.out.println("6. Exit\n");
    }
    
    private static void searchNameAndDisplayChange() {
        System.out.println("Enter the name to search:");
        String name = input.nextLine().trim();
        
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (customers[i].getName().equalsIgnoreCase(name)) {
                customers[i].displayChange();
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("Name: " + name);
            System.out.println("Not found\n");
        }
    }
    
    private static void findSmallestAmount() {
        if (count == 0) {
            System.out.println("No customers found.");
            return;
        }
        
        ChangeM min = customers[0];
        for (int i = 1; i < count; i++) {
            if (customers[i].getAmount() < min.getAmount()) {
                min = customers[i];
            }
        }
        
        System.out.println("Customer with smallest amount:");
        min.displayChange();
    }
    
    private static void findLargestAmount() {
        if (count == 0) {
            System.out.println("No customers found.");
            return;
        }
        
        ChangeM max = customers[0];
        for (int i = 1; i < count; i++) {
            if (customers[i].getAmount() > max.getAmount()) {
                max = customers[i];
            }
        }
        
        System.out.println("Customer with largest amount:");
        max.displayChange();
    }
    
    private static void displayTotalCoinsPerDenomination() {
        int totalOneDollar = 0;
        int totalFiftyCents = 0;
        int totalTwentyFiveCents = 0;
        int totalTenCents = 0;
        int totalFiveCents = 0;
        
        for (int i = 0; i < count; i++) {
            totalOneDollar += customers[i].getOneDollar();
            totalFiftyCents += customers[i].getFiftyCents();
            totalTwentyFiveCents += customers[i].getTwentyFiveCents();
            totalTenCents += customers[i].getTenCents();
            totalFiveCents += customers[i].getFiveCents();
        }
        
        System.out.println("Total number of coins per denomination:");
        System.out.println("1 dollar coins: " + totalOneDollar);
        System.out.println("50 cent coins: " + totalFiftyCents);
        System.out.println("25 cent coins: " + totalTwentyFiveCents);
        System.out.println("10 cent coins: " + totalTenCents);
        System.out.println("5 cent coins: " + totalFiveCents);
        System.out.println();
    }
    
    private static void displayTotalAmount() {
        int totalAmount = 0;
        for (int i = 0; i < count; i++) {
            totalAmount += customers[i].getAmount();
        }
        
        System.out.println("Total amount across all customers: " + totalAmount + " cents");
        System.out.println("Total amount in dollars: $" + (totalAmount / 100.0));
        System.out.println();
    }
    
    private static boolean isDuplicateName(String name) {
        for (int i = 0; i < count; i++) {
            if (customers[i].getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
    
    // Method for tutor testing with sample data
    private static int populateSampleData(ChangeM[] arr) {
        String[] names = {"Jane", "John", "Lin", "Jordan", "Alice", "Bob", "Charlie", "Diana", "Eve", "Frank"};
        int[] amounts = {30, 50, 94, 35, 125, 99, 0, 5, 260, 47};
        
        int dataCount = Math.min(arr.length, names.length);
        for (int i = 0; i < dataCount; i++) {
            arr[i] = new ChangeM(names[i], amounts[i]);
        }
        return dataCount;
    }
    
    public static void studentInfo() {
        System.out.println("Name: Rimsha Raheem");
        System.out.println("Student number: 35170856");
        System.out.println("Mode of enrollment: internal");
        System.out.println("Tutor name: Rajashree Rajamohnan");
        System.out.println("Tutorial attendance day and time: Thursday 8AM");
        System.out.println();
    }
}