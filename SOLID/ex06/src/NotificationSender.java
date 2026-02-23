public abstract class NotificationSender 
{
    protected final AuditLog audit;

    protected NotificationSender(AuditLog audit) 
    {
        this.audit = audit;
    }

    public final void send(Notification n) 
    {
        if (n == null) 
        {
            throw new NullPointerException("Notification cannot be null");
        }
        if (n.body == null) 
        {
            throw new NullPointerException("Notification.body cannot be null");
        }

        validate(n);

        doSend(n);
    }

    protected void validate(Notification n) {}
    
    protected abstract void doSend(Notification n);
}