public class NoDiscountRules implements DiscountRules 
{
    @Override
    public double calculateDiscount(double subtotal, int distinctLines)
    {
        return 0.0;
    }
}