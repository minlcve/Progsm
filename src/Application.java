import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Interface for Payment
interface IPayment {
    void processPayment(double amount);
}

class Payment implements IPayment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of: $" + amount);
    }
}

// Interface for Delivery
interface IDelivery {
    void trackDelivery(String trackingId);
    void handleNonDelivery(String trackingId);
}

class Delivery implements IDelivery {
    @Override
    public void trackDelivery(String trackingId) {
        System.out.println("Tracking delivery with ID: " + trackingId);
    }

    @Override
    public void handleNonDelivery(String trackingId) {
        System.out.println("Handling non-delivery for ID: " + trackingId);
    }
}

class CompanyDetails {
    public String getCompanyInfo() {
        return "People's Service Inc. - Helping you with just a few clicks!";
    }
}

class Product {
    private String productName;

    public Product(String productName) {
        this.productName = productName;
    }

    public void buyProduct() {
        System.out.println("Buying product: " + productName);
    }

    public void returnProduct() {
        System.out.println("Returning product: " + productName);
    }

    public String getProductName() {
        return productName;
    }
}

class Hub {
    public double calculateHubRate(String destination) {
        return 10.0;
    }
}

class Review {
    private List<String> reviews;

    public Review() {
        reviews = new ArrayList<>();
    }

    public void submitReview(String review) {
        reviews.add(review);
        System.out.println("Review submitted: " + review);
    }

    public void viewReviews() {
        System.out.println("Displaying reviews:");
        for (String review : reviews) {
            System.out.println("- " + review);
        }
    }
}

public class Application {
    // List of available products. Added more than one option :)
    private static List<Product> products = new ArrayList<>();
    private static List<Product> purchasedProducts = new ArrayList<>(); // List to track purchased products

    static {
        products.add(new Product("Smartphone"));
        products.add(new Product("Laptop"));
        products.add(new Product("Headphones"));
        products.add(new Product("Watch"));
    }

    public static void listProducts() {
        System.out.println("Available Products:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).getProductName());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        IPayment payment = new Payment();
        IDelivery delivery = new Delivery();
        CompanyDetails companyDetails = new CompanyDetails();
        Hub hub = new Hub();
        Review review = new Review();
        //Loop
        while (true) {
            System.out.println("\n=== Online Service Management System ===");
            System.out.println("1. Buy Product");
            System.out.println("2. Return Product");
            System.out.println("3. Process Payment");
            System.out.println("4. Track Delivery");
            System.out.println("5. Handle Non-Delivery");
            System.out.println("6. View Company Details");
            System.out.println("7. Submit Review");
            System.out.println("8. View Reviews");
            System.out.println("9. Exit");
            System.out.print("Please choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listProducts();
                    System.out.print("Select a product to buy (number): ");
                    int productChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (productChoice > 0 && productChoice <= products.size()) {
                        Product purchasedProduct = products.get(productChoice - 1);
                        purchasedProduct.buyProduct();
                        purchasedProducts.add(purchasedProduct);
                    } else {
                        System.out.println("Invalid product selection.");
                    }
                    break;
                case 2:

                    if (purchasedProducts.isEmpty()) {
                        System.out.println("No products have been purchased yet.");
                    } else {
                        System.out.println("Purchased Products:");
                        for (int i = 0; i < purchasedProducts.size(); i++) {
                            System.out.println((i + 1) + ". " + purchasedProducts.get(i).getProductName());
                        }
                        System.out.print("Select a product to return (number): ");
                        int returnChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (returnChoice > 0 && returnChoice <= purchasedProducts.size()) {
                            Product productToReturn = purchasedProducts.get(returnChoice - 1);
                            productToReturn.returnProduct();
                            purchasedProducts.remove(returnChoice - 1);
                        } else {
                            System.out.println("Invalid product selection for return.");
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter amount to pay: ");
                    double amount = scanner.nextDouble();
                    payment.processPayment(amount);
                    break;
                case 4:
                    System.out.print("Enter tracking ID: ");
                    String trackingId = scanner.nextLine();
                    delivery.trackDelivery(trackingId);
                    break;
                case 5:
                    System.out.print("Enter tracking ID for non-delivery: ");
                    String nonDeliveryId = scanner.nextLine();
                    delivery.handleNonDelivery(nonDeliveryId);
                    break;
                case 6:
                    System.out.println(companyDetails.getCompanyInfo());
                    break;
                case 7:
                    System.out.print("Enter your review: ");
                    String userReview = scanner.nextLine();
                    review.submitReview(userReview);
                    break;
                case 8:
                    review.viewReviews();
                    break;
                case 9:
                    System.out.println("Exiting the application. Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
