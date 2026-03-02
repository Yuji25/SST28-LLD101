import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        IncidentTicket assigned = service.assign(t, "agent@example.com");
        IncidentTicket escalated = service.escalateToCritical(assigned);

        System.out.println("\nOriginal after updates: " + t);
        System.out.println("Assigned copy:         " + assigned);
        System.out.println("Escalated copy:        " + escalated);

        List<String> tags = escalated.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE");
            System.out.println("\nBUG: external mutation worked!");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nImmutability holds: cannot modify tags from outside");
        }

        System.out.println("Escalated unchanged:   " + escalated);
    }
}
