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
    private NonSeatEntity newNonSeatEntityToBeAdded;
    private List<NonSeatEntity> newNonSeatEntities;
    
    public HallManagementManagedBean() {
        hallEntities = new ArrayList<>();
        filteredHallEntities = new ArrayList<>();
        selectedHallEntityToView = new HallEntity();
        selectedHallEntityToUpdate = new HallEntity();
        newHallEntity = new HallEntity();
        newNonSeatEntities = new ArrayList<>();
        newNonSeatEntityToBeAdded = new NonSeatEntity();
    }
    
    @PostConstruct
    public void postConstruct()
    {
        
    }
    
    public void addNonSeatEntititesToAHallSoonToBeCreated(ActionEvent action)
    {
        System.out.println("***Non seat entity is added to arrayList!");
        newNonSeatEntities.add(newNonSeatEntityToBeAdded);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Non Seat Of Type " + newNonSeatEntityToBeAdded.getNonSeatEntityType() + " successfully added", null));
        newNonSeatEntityToBeAdded = new NonSeatEntity();
    }
    
    public void createNewHall(ActionEvent event) {
        try {
            HallEntity he = hallSessionBean.createNewHall(newHallEntity, selectedCinemaEntityToView.getCinemaId(), newNonSeatEntities);
            hallEntities.add(newHallEntity);
            filteredHallEntities.add(newHallEntity);
            newHallEntity = new HallEntity();
            newNonSeatEntities = new ArrayList<>();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "New Hall Created Sucesssfully (Hall Id): " + he.getHallId() + ")", null));
        } catch (CreateNewHallException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating the new Hall: " + ex.getMessage(), null));
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
        hallEntities = getHallSessionBean().retrieveHallEntitiesByCinemaId(selectedCinemaEntityToView.getCinemaId());
        for(HallEntity he: hallEntities) {
            filteredHallEntities.add(he);
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
    
    
    
    
}
