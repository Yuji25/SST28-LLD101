public interface TaxRules 
{
    double calculateTax(double subtotal);
    String getTaxDescription(); 
}