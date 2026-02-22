import java.util.*;

public class Demo02 
{
    public static void main(String[] args) 
    {
        System.out.println("=== Cafeteria Billing ===");

        TaxRules studentTax = new StudentTaxRules();
        DiscountRules studentDiscount = new StudentDiscountRules();
        InvoiceFormatter formatter = new InvoiceFormatter();
        FileStore fileStore = new FileStore();

        CafeteriaSystem system = new CafeteriaSystem(
            studentTax, studentDiscount, formatter, fileStore
        );

        system.addToMenu(new MenuItem("M1", "Veg Thali", 80.00));
        system.addToMenu(new MenuItem("C1", "Coffee", 30.00));
        system.addToMenu(new MenuItem("S1", "Sandwich", 60.00));

        List<OrderLine> studentOrder = List.of(
            new OrderLine("M1", 2),
            new OrderLine("C1", 1)
        );

        system.checkout(studentOrder);

        System.out.println("\n=== Staff Order ===");
        TaxRules staffTax = new StaffTaxRules();
        DiscountRules staffDiscount = new StaffDiscountRules();
        
        CafeteriaSystem staffSystem = new CafeteriaSystem(
            staffTax, staffDiscount, formatter, fileStore
        );
        
        staffSystem.addToMenu(new MenuItem("M1", "Veg Thali", 80.00));
        staffSystem.addToMenu(new MenuItem("C1", "Coffee", 30.00));
        staffSystem.addToMenu(new MenuItem("S1", "Sandwich", 60.00));
        
        List<OrderLine> staffOrder = List.of(
            new OrderLine("M1", 1),
            new OrderLine("C1", 1),
            new OrderLine("S1", 1)
        );
        
        staffSystem.checkout(staffOrder);
    }
}