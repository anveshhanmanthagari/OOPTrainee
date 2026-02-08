package week5;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
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
        List<Double> sales = new ArrayList<>();
        // Prompting user for the number of sales days to process
        printStmt("Enter Number of Sales Days to process:");
        int numberOfDays = sc.nextInt();

        // Collecting daily sales data for the specified number of days with Stream API
        // and lambda expressions with for loop
        IntStream.range(0, numberOfDays)
                .mapToObj(i -> {
                    printStmt("Enter sales for Day " + (i + 1) + ":");
                    return sc.nextDouble();
                })
                .forEach(sales::add);
        List<Double> salesData = new ArrayList<>(sales);
        printStmt("________________________________");

        DoubleSummaryStatistics stats = sales.stream()
                .mapToDouble(Double::doubleValue)
                .summaryStatistics();
        double totalSales = stats.getSum();
        double averageSales = stats.getAverage();
        printStmt("Store : " + storeName.toUpperCase());
        printStmt("Daily Sales: " + dailySalesReportMsgStructure(salesData));
        printStmt("Average Daily Sales: " + formatCurrency(averageSales));
        printStmt("Total Sales: $" + formatCurrency(totalSales));
    }

    public static void printStmt(String printStmt) {
        System.out.println(printStmt);
    }
    public static String formatCurrency(double amount) {
        return String.format("$%.2f", amount);
    }
    public static String dailySalesReportMsgStructure(List<Double> salesData) {
        return salesData.stream()
                .map(Program2_WeeklySalesReporter::formatCurrency)
                .collect(Collectors.joining(", "));
    }
}
