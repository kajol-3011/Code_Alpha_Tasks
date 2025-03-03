import java.util.*;

class Stock {
    String name;
    double price;
    
    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Portfolio {
    private final Map<String, Integer> holdings = new HashMap<>();
    private double balance;
    
    Portfolio(double initialBalance) {
        this.balance = initialBalance;
    }
    
    void buyStock(String stock, int quantity, double price) {
        double cost = quantity * price;
        if (cost > balance) {
            System.out.println("Insufficient balance!");
            return;
        }
        holdings.put(stock, holdings.getOrDefault(stock, 0) + quantity);
        balance -= cost;
        System.out.println("Bought " + quantity + " shares of " + stock);
    }
    
    void sellStock(String stock, int quantity, double price) {
        if (!holdings.containsKey(stock) || holdings.get(stock) < quantity) {
            System.out.println("Not enough shares to sell!");
            return;
        }
        holdings.put(stock, holdings.get(stock) - quantity);
        if (holdings.get(stock) == 0) holdings.remove(stock);
        balance += quantity * price;
        System.out.println("Sold " + quantity + " shares of " + stock);
    }
    
    void displayPortfolio() {
        System.out.println("Portfolio:");
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " shares");
        }
        System.out.println("Balance: $" + balance);
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Portfolio portfolio = new Portfolio(10000); // Initial balance of $10,000
        
        Map<String, Stock> market = new HashMap<>();
        market.put("AAPL", new Stock("AAPL", 150));
        market.put("GOOGL", new Stock("GOOGL", 2800));
        market.put("TSLA", new Stock("TSLA", 700));
        
        while (true) {
            System.out.println("1. Buy Stock\n2. Sell Stock\n3. View Portfolio\n4. Exit");
            int choice = scanner.nextInt();
            if (choice == 4) break;
            
            switch (choice) {
                case 1:
                    System.out.println("Enter stock symbol and quantity:");
                    String buySymbol = scanner.next();
                    int buyQuantity = scanner.nextInt();
                    if (market.containsKey(buySymbol)) {
                        portfolio.buyStock(buySymbol, buyQuantity, market.get(buySymbol).price);
                    } else {
                        System.out.println("Stock not found!");
                    }
                    break;
                case 2:
                    System.out.println("Enter stock symbol and quantity:");
                    String sellSymbol = scanner.next();
                    int sellQuantity = scanner.nextInt();
                    if (market.containsKey(sellSymbol)) {
                        portfolio.sellStock(sellSymbol, sellQuantity, market.get(sellSymbol).price);
                    } else {
                        System.out.println("Stock not found!");
                    }
                    break;
                case 3:
                    portfolio.displayPortfolio();
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
        
        scanner.close();
    }
}
