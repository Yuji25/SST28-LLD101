public class StaffTaxRules implements TaxRules 
{
    private static final double TAX_PERCENT = 2.0;

    @Override
    public double calculateTax(double subtotal) 
    {
        return subtotal * (TAX_PERCENT / 100.0);
    }

    @Override
    public String getTaxDescription() 
    {
        return "Tax(" + TAX_PERCENT + "%)";
    }
}