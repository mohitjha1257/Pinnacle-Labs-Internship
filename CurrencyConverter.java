import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Supported currencies and their rates relative to 1 USD
        String[] currencies = {"USD", "EUR", "GBP", "INR", "JPY", "CAD"};
        double[] ratesToUSD = {1.0,   0.92,  0.79,  83.25, 155.40, 1.37};

        boolean running = true;

        System.out.println("=== Flexible Java Currency Converter ===");

        while (running) {
            printCurrencyMenu(currencies);

            // 1. Select source currency ("From")
            System.out.print("\nSelect SOURCE currency (1-" + (currencies.length + 1) + "): ");
            int sourceChoice = getValidIntInput(scanner, 1, currencies.length + 1);

            // Exit option
            if (sourceChoice == currencies.length + 1) {
                System.out.println("Thank you for using the Currency Converter. Goodbye!");
                break;
            }

            // 2. Select target currency ("To")
            System.out.print("Select TARGET currency (1-" + currencies.length + "): ");
            int targetChoice = getValidIntInput(scanner, 1, currencies.length);

            // 3. Enter amount
            System.out.print("Enter amount to convert: ");
            double amount = getValidDoubleInput(scanner);

            // Perform conversion using USD as the base currency
            int sourceIdx = sourceChoice - 1;
            int targetIdx = targetChoice - 1;

            double amountInUSD = amount / ratesToUSD[sourceIdx];
            double convertedAmount = amountInUSD * ratesToUSD[targetIdx];

            // Display results
            System.out.println("\n----------------------------------------");
            System.out.printf("Result: %.2f %s = %.2f %s%n", 
                amount, currencies[sourceIdx], convertedAmount, currencies[targetIdx]);
            System.out.println("----------------------------------------");
        }

        scanner.close();
    }

    // Prints the list of available currencies
    private static void printCurrencyMenu(String[] currencies) {
        System.out.println("\nAvailable Currencies:");
        for (int i = 0; i < currencies.length; i++) {
            System.out.println((i + 1) + ". " + currencies[i]);
        }
        System.out.println((currencies.length + 1) + ". Exit");
    }

    // Validates integer inputs within a specific range
    private static int getValidIntInput(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int input = scanner.nextInt();
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.print("Invalid selection. Please choose between " + min + " and " + max + ": ");
            } catch (Exception e) {
                System.out.print("Invalid input. Enter a valid number: ");
                scanner.nextLine(); // Clear bad input
            }
        }
    }

    // Validates non-negative numerical amounts
    private static double getValidDoubleInput(Scanner scanner) {
        while (true) {
            try {
                double input = scanner.nextDouble();
                if (input >= 0) {
                    return input;
                }
                System.out.print("Amount cannot be negative. Try again: ");
            } catch (Exception e) {
                System.out.print("Invalid input. Enter a valid numerical amount: ");
                scanner.nextLine(); // Clear bad input
            }
        }
    }
}