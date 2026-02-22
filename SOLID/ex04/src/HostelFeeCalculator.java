import java.util.*;

public class HostelFeeCalculator 
{
    private final BookingRepository repository;
    private int bookingSeq = 7780;

    public HostelFeeCalculator(BookingRepository repository) 
    {
        this.repository = repository;
    }

    public void process(BookingRequest req) 
    {
        Money monthly = calculateMonthly(req);
        
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (++bookingSeq);

        repository.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) 
    {
        RoomPricing roomPricing = RoomPricingFactory.getForType(req.roomType);
        Money roomFee = roomPricing.getMonthlyFee();

        Money addOnFees = Money.ZERO;
        for (AddOn addOn : req.addOns) 
        {
            AddOnPricing pricing = AddOnPricingFactory.getForAddOn(addOn);
            addOnFees = addOnFees.plus(pricing.getMonthlyFee());
        }

        return roomFee.plus(addOnFees);
    }
}