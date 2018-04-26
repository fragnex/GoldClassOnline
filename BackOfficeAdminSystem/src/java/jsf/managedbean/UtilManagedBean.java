package jsf.managedbean;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author shaunphua
 */
@Named(value = "utilManagedBean")
@RequestScoped
public class UtilManagedBean {

    /**
     * Creates a new instance of UtilManagedBean
     */
    public UtilManagedBean() {
    }
     public String formatMotdHeader(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
        return simpleDateFormat.format(date);
    }
}
