/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;


import ejb.session.stateless.HallSessionBeanLocal;
import entity.CinemaEntity;
import entity.HallEntity;
import entity.NonSeatEntity;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import util.exception.CreateNewHallException;
import util.exception.DeleteHallException;
import util.exception.HallNotFoundException;

/**
 *
 * @author shaunphua
 */
@Named(value = "hallManagementManagedBean")
@SessionScoped
public class HallManagementManagedBean implements Serializable {
    
    private CinemaManagementManagedBean cinemaManagementBean;
    @EJB
    private HallSessionBeanLocal hallSessionBean;
    
    /**
     * Creates a new instance of HallManagementManagedBean
     */
    private CinemaEntity selectedCinemaEntityToView;
    private List<HallEntity> hallEntities;
    private List<HallEntity> filteredHallEntities;
    private HallEntity newHallEntity;
    private HallEntity selectedHallEntityToView;
    private HallEntity selectedHallEntityToUpdate;
    private HallEntity selectedHallEntityToDelete;
    private NonSeatEntity newNonSeatEntityToBeAdded;
    private List<NonSeatEntity> newNonSeatEntities;
    private List<String> nonSeatType; // to be used in drop down menu
    private List<Integer> nonSeatColumnsForSelection;
    
    private List<HallEntity> allHallEntitiesForOperationStaff;
    private List<HallEntity> filteredAllHallEntitiesForOperationStaff;
    private HallEntity selectedHallToViewForOperationStaff;
    
    
    public HallManagementManagedBean() {
        hallEntities = new ArrayList<>();
        filteredHallEntities = new ArrayList<>();
        selectedHallEntityToView = new HallEntity();
        selectedHallEntityToUpdate = new HallEntity();
        newHallEntity = new HallEntity();
        newNonSeatEntities = new ArrayList<>();
        newNonSeatEntityToBeAdded = new NonSeatEntity();
        nonSeatType = new ArrayList<>();
        nonSeatType.add("WHEELCHAIR");
        nonSeatType.add("NONFORSALE");
        nonSeatColumnsForSelection = new ArrayList<>();
        allHallEntitiesForOperationStaff = new ArrayList<>();
        filteredAllHallEntitiesForOperationStaff = new ArrayList<>();
    }
    
    @PostConstruct
    public void postConstruct()
    {
        setAllHallEntitiesForOperationStaff(getHallSessionBean().retrieveAllHallEntities());
        for (HallEntity he: getAllHallEntitiesForOperationStaff()) {
            getFilteredAllHallEntitiesForOperationStaff().add(he);
        }
    }
    public void viewCinemaHallDetails(ActionEvent event) throws IOException
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewAllScreeningScheduleForAHall.xhtml");
    }
    
    public void addNonSeatEntititesToAHallSoonToBeCreated(ActionEvent action)
    {
        System.out.println("***Non seat entity is added to arrayList!");
        getNewNonSeatEntities().add(getNewNonSeatEntityToBeAdded());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Non Seat Of Type " + getNewNonSeatEntityToBeAdded().getNonSeatEntityType() + " successfully added", null));
        setNewNonSeatEntityToBeAdded(new NonSeatEntity());
    }
    
    public void createNewHall(ActionEvent event) {
        try {
            HallEntity he = getHallSessionBean().createNewHall(getNewHallEntity(), getSelectedCinemaEntityToView().getCinemaId(), getNewNonSeatEntities());
            getHallEntities().add(getNewHallEntity());
            getFilteredHallEntities().add(getNewHallEntity());
            setNewHallEntity(new HallEntity());
            setNewNonSeatEntities(new ArrayList<>());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "New Hall Created Sucesssfully (Hall Id): " + he.getHallId() + ")", null));
        } catch (CreateNewHallException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating the new Hall: " + ex.getMessage(), null));
        }
    }
    
     public void updateHall(ActionEvent event)
    {
            try {
                getHallSessionBean().updateHall(getSelectedHallEntityToUpdate(), getNewNonSeatEntities());
                setNewNonSeatEntities(new ArrayList<>());
                setSelectedHallEntityToUpdate(new HallEntity());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Hall updated successfully", null));
            } catch (HallNotFoundException ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while updating hall: " + ex.getMessage(), null));
            } catch (Exception ex) {
                   
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occured", null));
        }
        
    }
     
    public void deleteHall(ActionEvent event)
    {
        
            
        try {
            HallEntity hallEntityToBeDeleted = (HallEntity)event.getComponent().getAttributes().get("hallEntityToDelete");
            getHallSessionBean().deleteHall(hallEntityToBeDeleted.getHallId());
            getHallEntities().remove(getSelectedHallEntityToDelete());
            getFilteredHallEntities().remove(getSelectedHallEntityToDelete());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Hall deleted successfully", null));
        } catch (HallNotFoundException | DeleteHallException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while deleting hall: " + ex.getMessage(), null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error occured " + ex.getMessage(), null));
        }
        
        
    } 
    
    
    

    public List<HallEntity> getHallEntities() {
        return hallEntities;
    }

    public void setHallEntities(List<HallEntity> hallEntities) {
        this.hallEntities = hallEntities;
    }

    public List<HallEntity> getFilteredHallEntities() {
        return filteredHallEntities;
    }

    public void setFilteredHallEntities(List<HallEntity> filteredHallEntities) {
        this.filteredHallEntities = filteredHallEntities;
    }

    public HallEntity getNewHallEntity() {
        return newHallEntity;
    }

    public void setNewHallEntity(HallEntity newHallEntity) {
        this.newHallEntity = newHallEntity;
    }

    public HallEntity getSelectedHallEntityToView() {
        
        return selectedHallEntityToView;
    }

    public void setSelectedHallEntityToView(HallEntity selectedHallEntityToView) {
        System.out.println("##" + selectedHallEntityToView.getHallNumber());
        this.selectedHallEntityToView = selectedHallEntityToView;
        
    }

    public HallEntity getSelectedHallEntityToUpdate() {
        return selectedHallEntityToUpdate;
    }

    public void setSelectedHallEntityToUpdate(HallEntity selectedHallEntityToUpdate) {
        this.selectedHallEntityToUpdate = selectedHallEntityToUpdate;
    }

    public HallSessionBeanLocal getHallSessionBean() {
        return hallSessionBean;
    }

    public void setHallSessionBean(HallSessionBeanLocal hallSessionBean) {
        this.hallSessionBean = hallSessionBean;
    }

    public CinemaEntity getSelectedCinemaEntityToView() {
        
        return selectedCinemaEntityToView;
    }

    public void setSelectedCinemaEntityToView(CinemaEntity selectedCinemaEntityToView) {
        this.setHallEntities(getHallSessionBean().retrieveHallEntitiesByCinemaId(selectedCinemaEntityToView.getCinemaId()));
        for(HallEntity he: getHallEntities()) {
            this.getFilteredHallEntities().add(he);
        }
        
        this.selectedCinemaEntityToView = selectedCinemaEntityToView;
    }

    public NonSeatEntity getNewNonSeatEntityToBeAdded() {
        return newNonSeatEntityToBeAdded;
    }

    public void setNewNonSeatEntityToBeAdded(NonSeatEntity newNonSeatEntityToBeAdded) {
        this.newNonSeatEntityToBeAdded = newNonSeatEntityToBeAdded;
    }

    public List<NonSeatEntity> getNewNonSeatEntities() {
        return newNonSeatEntities;
    }

    public void setNewNonSeatEntities(List<NonSeatEntity> newNonSeatEntities) {
        this.newNonSeatEntities = newNonSeatEntities;
    }

    public CinemaManagementManagedBean getCinemaManagementBean() {
        return cinemaManagementBean;
    }

    public void setCinemaManagementBean(CinemaManagementManagedBean cinemaManagementBean) {
        this.cinemaManagementBean = cinemaManagementBean;
    }

    public List<String> getNonSeatType() {
        
        return nonSeatType;
    }

    public void setNonSeatType(List<String> nonSeatType) {
        this.nonSeatType = nonSeatType;
    }

    public List<Integer> getNonSeatColumnsForSelection() {
        return nonSeatColumnsForSelection;
    }

    public void setNonSeatColumnsForSelection(List<Integer> nonSeatColumnsForSelection) {
        this.nonSeatColumnsForSelection = nonSeatColumnsForSelection;
    }

    public HallEntity getSelectedHallEntityToDelete() {
        return selectedHallEntityToDelete;
    }

    public void setSelectedHallEntityToDelete(HallEntity selectedHallEntityToDelete) {
        this.selectedHallEntityToDelete = selectedHallEntityToDelete;
    }

    public List<HallEntity> getAllHallEntitiesForOperationStaff() {
        return allHallEntitiesForOperationStaff;
    }

    public void setAllHallEntitiesForOperationStaff(List<HallEntity> allHallEntitiesForOperationStaff) {
        this.allHallEntitiesForOperationStaff = allHallEntitiesForOperationStaff;
    }

    public List<HallEntity> getFilteredAllHallEntitiesForOperationStaff() {
        return filteredAllHallEntitiesForOperationStaff;
    }

    public void setFilteredAllHallEntitiesForOperationStaff(List<HallEntity> filteredAllHallEntitiesForOperationStaff) {
        this.filteredAllHallEntitiesForOperationStaff = filteredAllHallEntitiesForOperationStaff;
    }

    public HallEntity getSelectedHallToViewForOperationStaff() {
        return selectedHallToViewForOperationStaff;
    }

    public void setSelectedHallToViewForOperationStaff(HallEntity selectedHallToViewForOperationStaff) {
        this.selectedHallToViewForOperationStaff = selectedHallToViewForOperationStaff;
    }
    
    
    
    
}
