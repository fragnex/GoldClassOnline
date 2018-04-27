/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.CalendarDaySessionBeanLocal;
import ejb.session.stateless.MovieSessionBeanLocal;
import ejb.session.stateless.ScreeningScheduleSessionBeanLocal;
import entity.CalendarDayEntity;
import entity.CinemaEntity;
import entity.HallEntity;
import entity.MovieEntity;
import entity.ScreeningScheduleEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.util.DateUtils;
import util.exception.CinemaNotFoundException;
import util.exception.CreateNewCalendarDayException;
import util.exception.CreateNewCinemaException;
import util.exception.CreateNewScreeningScheduleException;
import util.exception.DeleteCinemaException;
import util.exception.DeleteScreeningScheduleException;
import util.exception.MovieNotFoundException;
import util.exception.ScreeningScheduleNotFoundException;


/**
 *
 * @author shaunphua
 */
@Named(value = "calendarDayManagementManagedBean")
@SessionScoped
public class CalendarDayManagementManagedBean implements Serializable {

    @EJB
    private MovieSessionBeanLocal movieSessionBean;
   
    @EJB
    private ScreeningScheduleSessionBeanLocal screeningScheduleSessionBean;

    @EJB
    private CalendarDaySessionBeanLocal calendarDaySessionBean;
    
    
    private List<CalendarDayEntity> allCalendarDays;
    private List<CalendarDayEntity> filteredAllCalendarDays;
    //for last view 
    private CalendarDayEntity selectedCalendarDayEntityToBeViewed;
    private List<ScreeningScheduleEntity> screeningSchedulesOfselectedCalendarDayEntityToBeViewed;
    private List<ScreeningScheduleEntity> filteredScreeningScheduesofselectedCalendarDayEntityToBeViewed;
    private ScreeningScheduleEntity newScreeningScheduleEntityToBeCreated;
    private String movieTitle;
    private List<MovieEntity> allMovies;
    private List<String> movieTitles;
    private MovieEntity movie;
    
    private ScreeningScheduleEntity screeningScheduleToBeUpdated;
    
    private CalendarDayEntity newCalendarDayEntityToBeCreated;
    private HallEntity selectedHallEntityToBeViewed;
    /**
     * Creates a new instance of CalendarDayManagementManagedBean
     */
    public CalendarDayManagementManagedBean() {
        newCalendarDayEntityToBeCreated = new CalendarDayEntity();
        allCalendarDays = new ArrayList<>();
        filteredAllCalendarDays = new ArrayList<>();
        screeningSchedulesOfselectedCalendarDayEntityToBeViewed = new ArrayList<>();
        filteredScreeningScheduesofselectedCalendarDayEntityToBeViewed = new ArrayList<>();
        newScreeningScheduleEntityToBeCreated = new ScreeningScheduleEntity();
        allMovies = new ArrayList<>();
        
        
        movieTitles = new ArrayList<>();
        
    }
    
    @PostConstruct
    public void postConstruct()
    {
        setAllMovies(getMovieSessionBean().retrieveAllMovieEntities());
        for (MovieEntity me: getAllMovies()) {
            getMovieTitles().add(me.getTitle());
        }
    }
    
    public void updateScreeningSchedule(ActionEvent event)
    {
        try
        {
            screeningScheduleToBeUpdated.setEndTime(addMinutesToDate(screeningScheduleToBeUpdated.getMovie().getRunningTime(), screeningScheduleToBeUpdated.getStartTime()));
            screeningScheduleToBeUpdated.setMovie(movieSessionBean.retrieveMovieByName(movieTitle));
            screeningScheduleSessionBean.updateScreeningSchedule(screeningScheduleToBeUpdated);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Screening Schedule updated successfully", null));
        }
        catch(ScreeningScheduleNotFoundException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while updating screening schedule: " + ex.getMessage(), null));
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }
    
    public void deleteScreeningSchedule(ActionEvent event)
    {
        try
        {
            
            ScreeningScheduleEntity screeningScheduleEntityToDelete = (ScreeningScheduleEntity)event.getComponent().getAttributes().get("screeningScheduleEntityToDelete");
            
            screeningScheduleSessionBean.deleteScreeningSchedule(screeningScheduleEntityToDelete.getScreeningScheduleId());
            
            screeningSchedulesOfselectedCalendarDayEntityToBeViewed.remove(screeningScheduleEntityToDelete);
            filteredScreeningScheduesofselectedCalendarDayEntityToBeViewed.remove(screeningScheduleEntityToDelete);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Screening Schedule deleted successfully", null));
        }
        catch(ScreeningScheduleNotFoundException | DeleteScreeningScheduleException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while deleting screening schedule: " + ex.getMessage(), null));
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }
    public void createNewCalendarDay(ActionEvent event)
    {
        try
        {
            CalendarDayEntity cde = getCalendarDaySessionBean().createNewCalendarDay(getNewCalendarDayEntityToBeCreated(), getSelectedHallEntityToBeViewed().getHallId());
            getAllCalendarDays().add(cde);
            getFilteredAllCalendarDays().add(cde);
            setNewCalendarDayEntityToBeCreated(new CalendarDayEntity());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New calendar day created successfully (Calendar Day: " + cde.getId()+ ")", null));
        }
        catch(CreateNewCalendarDayException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating the new product: " + ex.getMessage(), null));
        }
    }
    
    private static Date addMinutesToDate(int minutes, Date beforeTime){
    final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs

    long curTimeInMs = beforeTime.getTime();
    Date afterAddingMins = new Date(curTimeInMs + (minutes * ONE_MINUTE_IN_MILLIS));
    return afterAddingMins;
    }
    
    public void createNewScreeningSchedule(ActionEvent event)
    {
        try
        {
            MovieEntity movie = getMovieSessionBean().retrieveMovieByName(getMovieTitle());
            getNewScreeningScheduleEntityToBeCreated().setEndTime(addMinutesToDate(movie.getRunningTime(), getNewScreeningScheduleEntityToBeCreated().getStartTime()));
            ScreeningScheduleEntity sse = getScreeningScheduleSessionBean().createScreeningSchedule(getNewScreeningScheduleEntityToBeCreated(), getSelectedCalendarDayEntityToBeViewed().getId(), movie.getMovieId()); 
            getScreeningSchedulesOfselectedCalendarDayEntityToBeViewed().add(sse);
            getFilteredScreeningScheduesofselectedCalendarDayEntityToBeViewed().add(sse);
            setNewScreeningScheduleEntityToBeCreated(new ScreeningScheduleEntity());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Screening Schedule created successfully (Calendar Day: " + sse.getScreeningScheduleId()+ ")", null));
        }
        catch(CreateNewScreeningScheduleException | MovieNotFoundException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating the new product: " + ex.getMessage(), null));
        }
    }

    public CalendarDayEntity getSelectedCalendarDayEntityToBeViewed() {
        return selectedCalendarDayEntityToBeViewed;
    }

    public void setSelectedCalendarDayEntityToBeViewed(CalendarDayEntity selectedCalendarDayEntityToBeViewed) {
        this.selectedCalendarDayEntityToBeViewed = selectedCalendarDayEntityToBeViewed;
        setScreeningSchedulesOfselectedCalendarDayEntityToBeViewed(getCalendarDaySessionBean().retrieveScreeningScheduleUsingCalendarDayId(selectedCalendarDayEntityToBeViewed.getId()));
        for (ScreeningScheduleEntity sse: getScreeningSchedulesOfselectedCalendarDayEntityToBeViewed()) {
            getFilteredScreeningScheduesofselectedCalendarDayEntityToBeViewed().add(sse);
        }
    }
    

    public CalendarDayEntity getNewCalendarDayEntityToBeCreated() {
        return newCalendarDayEntityToBeCreated;
    }

    public void setNewCalendarDayEntityToBeCreated(CalendarDayEntity newCalendarDayEntityToBeCreated) {
        this.newCalendarDayEntityToBeCreated = newCalendarDayEntityToBeCreated;
    }

    public CalendarDaySessionBeanLocal getCalendarDaySessionBean() {
        return calendarDaySessionBean;
    }

    public void setCalendarDaySessionBean(CalendarDaySessionBeanLocal calendarDaySessionBean) {
        this.calendarDaySessionBean = calendarDaySessionBean;
    }

    public HallEntity getSelectedHallEntityToBeViewed() {
        return selectedHallEntityToBeViewed;
    }

    public void setSelectedHallEntityToBeViewed(HallEntity selectedHallEntityToBeViewed) {
        this.selectedHallEntityToBeViewed = selectedHallEntityToBeViewed;
        setAllCalendarDays(getCalendarDaySessionBean().retrieveCalendarDaysByHallId(selectedHallEntityToBeViewed.getHallId()));
        for (CalendarDayEntity cde: getAllCalendarDays()) {
            System.out.println("***CDE ++++ " + cde.getCalendarDay());
            getFilteredAllCalendarDays().add(cde);
        }
    }

    public List<CalendarDayEntity> getAllCalendarDays() {
        return allCalendarDays;
    }

    public void setAllCalendarDays(List<CalendarDayEntity> allCalendarDays) {
        this.allCalendarDays = allCalendarDays;
    }

    public List<CalendarDayEntity> getFilteredAllCalendarDays() {
        return filteredAllCalendarDays;
    }

    public void setFilteredAllCalendarDays(List<CalendarDayEntity> filteredAllCalendarDays) {
        this.filteredAllCalendarDays = filteredAllCalendarDays;
    }

    public List<ScreeningScheduleEntity> getScreeningSchedulesOfselectedCalendarDayEntityToBeViewed() {
        return screeningSchedulesOfselectedCalendarDayEntityToBeViewed;
    }

    public void setScreeningSchedulesOfselectedCalendarDayEntityToBeViewed(List<ScreeningScheduleEntity> screeningSchedulesOfselectedCalendarDayEntityToBeViewed) {
        this.screeningSchedulesOfselectedCalendarDayEntityToBeViewed = screeningSchedulesOfselectedCalendarDayEntityToBeViewed;
    }

    public List<ScreeningScheduleEntity> getFilteredScreeningScheduesofselectedCalendarDayEntityToBeViewed() {
        return filteredScreeningScheduesofselectedCalendarDayEntityToBeViewed;
    }

    public void setFilteredScreeningScheduesofselectedCalendarDayEntityToBeViewed(List<ScreeningScheduleEntity> filteredScreeningScheduesofselectedCalendarDayEntityToBeViewed) {
        this.filteredScreeningScheduesofselectedCalendarDayEntityToBeViewed = filteredScreeningScheduesofselectedCalendarDayEntityToBeViewed;
    }

    public ScreeningScheduleEntity getNewScreeningScheduleEntityToBeCreated() {
        return newScreeningScheduleEntityToBeCreated;
    }

    public void setNewScreeningScheduleEntityToBeCreated(ScreeningScheduleEntity newScreeningScheduleEntityToBeCreated) {
        this.newScreeningScheduleEntityToBeCreated = newScreeningScheduleEntityToBeCreated;
    }

    public MovieSessionBeanLocal getMovieSessionBean() {
        return movieSessionBean;
    }

    public void setMovieSessionBean(MovieSessionBeanLocal movieSessionBean) {
        this.movieSessionBean = movieSessionBean;
    }

    public ScreeningScheduleSessionBeanLocal getScreeningScheduleSessionBean() {
        return screeningScheduleSessionBean;
    }

    public void setScreeningScheduleSessionBean(ScreeningScheduleSessionBeanLocal screeningScheduleSessionBean) {
        this.screeningScheduleSessionBean = screeningScheduleSessionBean;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public List<MovieEntity> getAllMovies() {
        return allMovies;
    }

    public void setAllMovies(List<MovieEntity> allMovies) {
        this.allMovies = allMovies;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public List<String> getMovieTitles() {
        return movieTitles;
    }

    public void setMovieTitles(List<String> movieTitles) {
        this.movieTitles = movieTitles;
    }

    public ScreeningScheduleEntity getScreeningScheduleToBeUpdated() {
        return screeningScheduleToBeUpdated;
    }

    public void setScreeningScheduleToBeUpdated(ScreeningScheduleEntity screeningScheduleToBeUpdated) {
        this.screeningScheduleToBeUpdated = screeningScheduleToBeUpdated;
    }

    

    
    
}
