public class DoubleRoomPricing implements RoomPricing 
{
    @Override
    public Money getMonthlyFee() 
    {
        return new Money(15000.0);
    }

    @Override
    public String getRoomType() 
    {
        return "DOUBLE";
    }
}