public class OrderLineWithPrice 
{
    public final String itemName;
    public final int qty;
    public final double unitPrice;
    public final double lineTotal;

    public OrderLineWithPrice(String itemName, int qty, double unitPrice) 
    {
        this.itemName = itemName;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.lineTotal = qty * unitPrice;
    }
}