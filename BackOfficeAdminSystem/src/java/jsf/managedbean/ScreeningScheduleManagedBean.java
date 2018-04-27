/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.HallSessionBeanLocal;
import ejb.session.stateless.ScreeningScheduleSessionBeanLocal;
import entity.HallEntity;
import entity.ScreeningScheduleEntity;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import util.exception.HallNotFoundException;

/**
 *
 * @author shaunphua
 */
@Named(value = "screeningScheduleManagedBean")
@SessionScoped
public class ScreeningScheduleManagedBean implements Serializable {

    @EJB
    private ScreeningScheduleSessionBeanLocal screeningScheduleSessionBean;

    @EJB
    private HallSessionBeanLocal hallSessionBean;
    
    
    private HallEntity selectedHallToViewForOperationStaff;
    private List<ScreeningScheduleEntity> screeningSchedules;
    private List<ScreeningScheduleEntity> filteredScreeningSchedules;
    
    /**
     * Creates a new instance of ScreeningScheduleManagedBean
     */
    public ScreeningScheduleManagedBean() {
        screeningSchedules = new ArrayList<>();
    }

    public HallEntity getSelectedHallToViewForOperationStaff() {
        return selectedHallToViewForOperationStaff;
    }

    public void setSelectedHallToViewForOperationStaff(HallEntity selectedHallToViewForOperationStaff) throws HallNotFoundException {
        this.selectedHallToViewForOperationStaff = selectedHallToViewForOperationStaff;
        List<ScreeningScheduleEntity> sse = getScreeningScheduleSessionBean().retrieveScreeningSchedulesUsingHallEntity(this.selectedHallToViewForOperationStaff.getHallId());
        for (ScreeningScheduleEntity se: sse) {
            Date today = new Date();
            if ((se.getCalendarDayEntity().getCalendarDay()).compareTo(today) > 0) {
                getScreeningSchedules().add(se);
                filteredScreeningSchedules.add(se);
            }
        }
    }
    
    public void viewDailyScreeningSchedule(ActionEvent event) throws IOException
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewDailyScreeningSchedule.xhtml");
    }

    public HallSessionBeanLocal getHallSessionBean() {
        return hallSessionBean;
    }

    public void setHallSessionBean(HallSessionBeanLocal hallSessionBean) {
        this.hallSessionBean = hallSessionBean;
    }

    public List<ScreeningScheduleEntity> getScreeningSchedules() {
        return screeningSchedules;
    }

    public void setScreeningSchedules(List<ScreeningScheduleEntity> screeningSchedules) {
        this.screeningSchedules = screeningSchedules;
    }

    public ScreeningScheduleSessionBeanLocal getScreeningScheduleSessionBean() {
        return screeningScheduleSessionBean;
    }

    public void setScreeningScheduleSessionBean(ScreeningScheduleSessionBeanLocal screeningScheduleSessionBean) {
        this.screeningScheduleSessionBean = screeningScheduleSessionBean;
    }

    public List<ScreeningScheduleEntity> getFilteredScreeningSchedules() {
        return filteredScreeningSchedules;
    }

    public void setFilteredScreeningSchedules(List<ScreeningScheduleEntity> filteredScreeningSchedules) {
        this.filteredScreeningSchedules = filteredScreeningSchedules;
    }
    
}
