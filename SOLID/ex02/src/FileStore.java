import java.util.*;

public class FileStore implements InvoiceRepository 
{
    private final Map<String, String> files = new HashMap<>();

    @Override
    public void save(Invoice invoice, String content) 
    {
        files.put(invoice.invoiceId, content);
    }

    public int countLines(String invoiceId) 
    {
        String content = files.getOrDefault(invoiceId, "");
        if (content.isEmpty()) return 0;
        return content.split("\n").length;
    }

    public String getContent(String invoiceId) 
    {
        return files.getOrDefault(invoiceId, "");
    }
}