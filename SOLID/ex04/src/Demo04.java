import java.util.*;

public class Demo04 
{
    public static void main(String[] args) 
    {
        System.out.println("=== Hostel Fee Calculator ===");
        
        BookingRepository repo = new FakeBookingRepo();
        HostelFeeCalculator calc = new HostelFeeCalculator(repo);
        
        BookingRequest req = new BookingRequest(
            LegacyRoomTypes.DOUBLE, 
            List.of(AddOn.LAUNDRY, AddOn.MESS)
        );
        
        calc.process(req);
    }
}