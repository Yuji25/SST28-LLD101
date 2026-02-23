public class WhatsAppSender extends NotificationSender 
{
    public WhatsAppSender(AuditLog audit) 
    {
        super(audit);
    }

    @Override
    protected void validate(Notification n) 
    {
        if (n.phone == null || n.phone.isBlank()) 
        {
            throw new IllegalArgumentException("Phone number is required");
        }
    }

    @Override
    protected void doSend(Notification n) 
    {
        String normalizedPhone = normalizePhone(n.phone);
        System.out.println("WA -> to=" + normalizedPhone + " body=" + n.body);
        audit.add("wa sent");
    }

    private String normalizePhone(String phone) 
    {
        if (phone.startsWith("+")) 
        {
            return phone;
        }

        String cleaned = phone.replaceAll("^0", "");
        return "+91" + cleaned;
    }
}