import java.util.*;

public class RoomPricingFactory 
{
    private static final Map<Integer, RoomPricing> pricingMap = new HashMap<>();

    static 
    {
        pricingMap.put(LegacyRoomTypes.SINGLE, new SingleRoomPricing());
        pricingMap.put(LegacyRoomTypes.DOUBLE, new DoubleRoomPricing());
        pricingMap.put(LegacyRoomTypes.TRIPLE, new TripleRoomPricing());
        pricingMap.put(LegacyRoomTypes.DELUXE, new DeluxeRoomPricing());
    }

    public static RoomPricing getForType(int roomType) 
    {
        return pricingMap.getOrDefault(roomType, new DeluxeRoomPricing());
    }
}