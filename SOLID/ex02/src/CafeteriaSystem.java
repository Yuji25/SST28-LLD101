import java.util.*;

public class CafeteriaSystem 
{
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final TaxRules taxRules;
    private final DiscountRules discountRules;
    private final InvoiceFormatter formatter;
    private final InvoiceRepository repository;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(TaxRules taxRules, DiscountRules discountRules,
                          InvoiceFormatter formatter, InvoiceRepository repository) 
    {
        this.taxRules = taxRules;
        this.discountRules = discountRules;
        this.formatter = formatter;
        this.repository = repository;
    }

    public void addToMenu(MenuItem item)
    {
        menu.put(item.id, item);
    }

    public void checkout(List<OrderLine> lines) 
    {
        String invoiceId = "INV-" + (++invoiceSeq);

        List<OrderLineWithPrice> enrichedLines = new ArrayList<>();
        double subtotal = 0.0;
        
        for (OrderLine line : lines) 
        {
            MenuItem item = menu.get(line.itemId);
            if (item != null) 
            {
                OrderLineWithPrice enriched = new OrderLineWithPrice(
                    item.name, line.qty, item.price
                );

                enrichedLines.add(enriched);
                subtotal += enriched.lineTotal;
            }
        }

        double tax = taxRules.calculateTax(subtotal);

        double discount = discountRules.calculateDiscount(subtotal, lines.size());

        double total = subtotal + tax - discount;

        Invoice invoice = new Invoice(
            invoiceId, enrichedLines, subtotal, tax, discount, total,
            taxRules.getTaxDescription()
        );

        String formattedInvoice = formatter.format(invoice);

        System.out.print(formattedInvoice);

        repository.save(invoice, formattedInvoice);

        FileStore fileStore = (FileStore) repository;
        int lineCount = fileStore.countLines(invoiceId);
        System.out.println("Saved invoice: " + invoiceId + " (lines=" + lineCount + ")");
    }
}