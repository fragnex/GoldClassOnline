package jsf.managedbean;

import ejb.session.stateless.CinemaSessionBeanLocal;
import entity.CinemaEntity;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.exception.CinemaNotFoundException;
import util.exception.CreateNewCinemaException;
import util.exception.DeleteCinemaException;

/**
 *
 * @author shaunphua
 */
@Named(value = "cinemaManagementManagedBean")
@SessionScoped
public class CinemaManagementManagedBean implements Serializable {

    @EJB
    private CinemaSessionBeanLocal cinemaSessionBean;
    
    private List<CinemaEntity> cinemaEntities;
    private List<CinemaEntity> filteredCinemaEntities;
    private CinemaEntity newCinemaEntity;
    private CinemaEntity selectedCinemaEntityToView;
    private CinemaEntity selectedCinemaEntityToUpdate;
    /**
     * Creates a new instance of CinemaManagementManagedBean
     */
    public CinemaManagementManagedBean() {
        cinemaEntities = new ArrayList<>();
        filteredCinemaEntities = new ArrayList<>();
        
        newCinemaEntity = new CinemaEntity();
    }
    
   @PostConstruct
    public void postConstruct()
    {
        cinemaEntities = cinemaSessionBean.retrieveAllCinemaEntities();
        for(CinemaEntity ce: cinemaEntities) {
            filteredCinemaEntities.add(ce);
        }
    }
    
    public void viewCinemaDetails(ActionEvent event) throws IOException
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewCinema.xhtml");
    }
    
    public void viewProductDetails(ActionEvent event) throws IOException
    {
        Long cinemaIdToView = (Long)event.getComponent().getAttributes().get("cinemaId");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("productIdToView", cinemaIdToView);
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewCinemaDetails.xhtml");
    }
    
    public void createNewCinema(ActionEvent event)
    {
        try
        {
            CinemaEntity ce = cinemaSessionBean.createNewCinema(getNewCinemaEntity());
            cinemaEntities.add(ce);
            getFilteredCinemaEntities().add(ce);
            setNewCinemaEntity(new CinemaEntity());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New cinema created successfully (Cinema ID: " + ce.getCinemaId() + ")", null));
        }
        catch(CreateNewCinemaException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating the new product: " + ex.getMessage(), null));
        }
    }
    
    public void updateCinema(ActionEvent event)
    {
        try
        {
            cinemaSessionBean.updateCinema(getSelectedCinemaEntityToUpdate());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cinema updated successfully", null));
        }
        catch(CinemaNotFoundException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while updating product: " + ex.getMessage(), null));
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }
    
    public void deleteCinema(ActionEvent event)
    {
        try
        {
            
            CinemaEntity cinemaEntityToDelete = (CinemaEntity)event.getComponent().getAttributes().get("cinemaEntityToDelete");
            
            cinemaSessionBean.deleteCinema(cinemaEntityToDelete.getCinemaId());
            
            cinemaEntities.remove(cinemaEntityToDelete);
            getFilteredCinemaEntities().remove(cinemaEntityToDelete);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cinema deleted successfully", null));
        }
        catch(CinemaNotFoundException | DeleteCinemaException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while deleting cinema: " + ex.getMessage(), null));
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }

    public CinemaSessionBeanLocal getCinemaSessionBean() {
        return cinemaSessionBean;
    }

    public void setCinemaSessionBean(CinemaSessionBeanLocal cinemaSessionBean) {
        this.cinemaSessionBean = cinemaSessionBean;
    }

    public List<CinemaEntity> getCinemaEntities() {
        return cinemaEntities;
    }

    public void setCinemaEntities(List<CinemaEntity> cinemaEntities) {
        this.cinemaEntities = cinemaEntities;
    }

    public List<CinemaEntity> getFilteredCinemaEntities() {
        return filteredCinemaEntities;
    }

    public void setFilteredCinemaEntities(List<CinemaEntity> filteredCinemaEntities) {
        this.filteredCinemaEntities = filteredCinemaEntities;
    }

    public CinemaEntity getNewCinemaEntity() {
        return newCinemaEntity;
    }

    public void setNewCinemaEntity(CinemaEntity newCinemaEntity) {
        this.newCinemaEntity = newCinemaEntity;
    }

    public CinemaEntity getSelectedCinemaEntityToView() {
        if (selectedCinemaEntityToView == null) {
        System.err.println("BF 1");
        }
        return selectedCinemaEntityToView;
    }

    public void setSelectedCinemaEntityToView(CinemaEntity selectedCinemaEntityToView) {
        this.selectedCinemaEntityToView = selectedCinemaEntityToView;
        if (this.selectedCinemaEntityToView == null) {
            System.err.println("***Still null la siao liao");
        }
        System.err.println("**Id"+this.selectedCinemaEntityToView.getCinemaId() + "  name: " + this.selectedCinemaEntityToView.getCinemaName());
        System.out.println("Yeah Baby");
    }

    public CinemaEntity getSelectedCinemaEntityToUpdate() {
        return selectedCinemaEntityToUpdate;
    }

    public void setSelectedCinemaEntityToUpdate(CinemaEntity selectedCinemaEntityToUpdate) {
        this.selectedCinemaEntityToUpdate = selectedCinemaEntityToUpdate;
    }
    
    
    
}
