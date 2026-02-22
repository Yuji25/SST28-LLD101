import java.util.*;

public class Invoice 
{
    public final String invoiceId;
    public final List<OrderLineWithPrice> lines;
    public final double subtotal;
    public final double tax;
    public final double discount;
    public final double total;
    public final String taxDescription;

    public Invoice(String invoiceId, List<OrderLineWithPrice> lines, 
                   double subtotal, double tax, double discount, double total,
                   String taxDescription) 
    {
        this.invoiceId = invoiceId;
        this.lines = lines;
        this.subtotal = subtotal;
        this.tax = tax;
        this.discount = discount;
        this.total = total;
        this.taxDescription = taxDescription;
    }
}