import java.util.*;

public class AddOnPricingFactory 
{
    private static final Map<AddOn, AddOnPricing> pricingMap = new HashMap<>();

    static 
    {
        pricingMap.put(AddOn.MESS, new MessPricing());
        pricingMap.put(AddOn.LAUNDRY, new LaundryPricing());
        pricingMap.put(AddOn.GYM, new GymPricing());
    }

    public static AddOnPricing getForAddOn(AddOn addOn) 
    {
        return pricingMap.getOrDefault(addOn, () -> new Money(0.0));
    }
}