public class FakeEligibilityStore implements EligibilityRepository 
{
    @Override
    public void save(String rollNo, String status) 
    {
        System.out.println("Saved evaluation for roll=" + rollNo);
    }
}