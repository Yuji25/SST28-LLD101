public class InvoiceFormatter 
{
    
    public String format(Invoice invoice) 
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Invoice# ").append(invoice.invoiceId).append("\n");
        
        for (OrderLineWithPrice line : invoice.lines) {
            sb.append(String.format("- %s x%d = %.2f\n", 
                line.itemName, line.qty, line.lineTotal));
        }
        
        sb.append(String.format("Subtotal: %.2f\n", invoice.subtotal));
        sb.append(String.format("%s: %.2f\n", invoice.taxDescription, invoice.tax));
        sb.append(String.format("Discount: -%.2f\n", invoice.discount));
        sb.append(String.format("TOTAL: %.2f\n", invoice.total));
        
        return sb.toString();
    }
}