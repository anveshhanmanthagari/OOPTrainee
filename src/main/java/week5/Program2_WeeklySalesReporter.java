package week5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Weekly Sales Reporter
 * This program collects daily sales data for a store over a period of 10 days, calculates the
 * total and average sales, and generates a formatted sales report.
 * The program prompts the user to enter the store name and daily sales amounts, processes the
 * input data, and outputs the results in a clear format.
 *
 * @author Anvesh Hanmanthagari
 */
public class Program2_WeeklySalesReporter {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        printStmt("Enter Store Name:");
        // Reading the store name
        String storeName = sc.nextLine();
        // Prompting user for the number of sales days to process
        printStmt("Enter Number of Sales Days to process:");
        int numberOfDays = sc.nextInt();

        // Collecting daily sales data for the specified number of days with Stream API
        // and lambda expressions with for loop
        List<Double> sales = collectSalesData(numberOfDays, sc);
        printStmt("________________________________");

        double totalSales = calculateTotalSales(sales);
        double averageSales = totalSales / numberOfDays;
        printStmt("Store : " + storeName.toUpperCase());
        printStmt("\nDaily Sales: " + dailySalesReportMsgStructure(sales));
        printStmt("\nAverage Daily Sales: " + formatCurrency(averageSales));
        printStmt("\nTotal Sales: " + formatCurrency(totalSales));
        printStmt("________________________________");
        printStmt("calculated on : " +
                java.time.LocalDate.now()
                        .format(java.time.format.DateTimeFormatter.ofPattern("MM dd, yyyy")));
        printStmt("calculated at : " + java.time.LocalTime.now());
        printStmt("calculated by : Anvesh Hanmanthagari");
        printStmt("Thank you for using the Weekly Sales Reporter!");
    }

    /**
     * Utility method to print a statement to the console.
     *
     * @param printStmt
     */
    public static void printStmt(String printStmt) {
        System.out.println(printStmt);
    }

    /**
     * Formats a double value as a currency string with two decimal places and a dollar sign.
     *
     * @param amount
     * @return
     */
    public static String formatCurrency(double amount) {
        return String.format("$%.2f", amount);
    }

    /**
     * Formats the daily sales data into a comma-separated string for reporting.
     *
     * @param salesData
     * @return
     */
    public static String dailySalesReportMsgStructure(List<Double> salesData) {
        return salesData.stream()
                .map(Program2_WeeklySalesReporter::formatCurrency)
                .collect(Collectors.joining(", "));
    }

    /**
     * Calculates the total sales from a list of daily sales amounts.
     *
     * @param salesData
     * @return
     */
    public static double calculateTotalSales(List<Double> salesData) {
        double total = 0.0;

        for (double sale : salesData) {
            if (sale < 0) {
                printStmt("Warning: Negative sales value (" + formatCurrency(sale) + ") detected.");
            }
            total += sale;
        }

        return total;
    }

    /**
     * Collects daily sales data from the user for a specified number of days.
     *
     * @param numberOfDays
     * @param sc
     * @return
     */
    public static List<Double> collectSalesData(int numberOfDays, Scanner sc) {
        List<Double> sales = new ArrayList<>();
        IntStream.range(0, numberOfDays)
                .mapToObj(i -> {
                    printStmt("Enter sales for Day " + (i + 1) + ":");
                    return sc.nextDouble();
                })
                .forEach(sales::add);
        return sales;

    }
}
