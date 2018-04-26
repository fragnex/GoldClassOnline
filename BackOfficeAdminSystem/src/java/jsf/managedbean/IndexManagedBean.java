package jsf.managedbean;

import ejb.session.stateless.MessageOfTheDayEntityControllerLocal;
import ejb.session.stateless.StaffSessionBeanLocal;
import entity.MessageOfTheDayEntity;
import entity.StaffEntity;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author shaunphua
 */
@Named(value = "indexManagedBean")
@RequestScoped
public class IndexManagedBean {

    @EJB
    private MessageOfTheDayEntityControllerLocal messageOfTheDayEntityController;

    @EJB
    private StaffSessionBeanLocal staffSessionBean;
    
    private String username;
    private String password;

    /**
     * Creates a new instance of IndexManagedBean
     */
    public IndexManagedBean() {
    }
    
    public void login(ActionEvent event) throws IOException
    {
        try
        {
            StaffEntity currentStaffEntity = staffSessionBean.staffLogin(getUsername(), getPassword());FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isLogin", true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentStaffEntity", currentStaffEntity);
            if (currentStaffEntity.getAccessRightEnum().equals("ADMINISTRATIVESTAFF")) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isAdminisrativeStaff", true);
            }
            if (currentStaffEntity.getAccessRightEnum().equals("OPERATIONSTAFF")) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isOperationStaff", true);
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");            
        }
        catch(InvalidLoginCredentialException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid login credential: " + ex.getMessage(), null));
        }
    }
    
    public void logout(ActionEvent event) throws IOException
    {
        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<MessageOfTheDayEntity> getMessageOfTheDayEntities()
    {
        return messageOfTheDayEntityController.retrieveAllMessagesOfTheDay();
    }
    
    
}
