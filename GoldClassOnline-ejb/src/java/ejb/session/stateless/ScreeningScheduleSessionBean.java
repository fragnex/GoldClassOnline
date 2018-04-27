/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CalendarDayEntity;
import entity.HallEntity;
import entity.MovieEntity;
import static entity.MovieEntity_.screeningSchedules;
import entity.ScreeningScheduleEntity;
import entity.TicketEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.exception.CalendarDayNotFoundException;
import util.exception.CreateNewMovieException;
import util.exception.CreateNewScreeningScheduleException;
import util.exception.DeleteMovieException;
import util.exception.DeleteScreeningScheduleException;
import util.exception.HallNotFoundException;
import util.exception.MovieNotFoundException;
import util.exception.ScreeningScheduleNotFoundException;

/**
 *
 * @author shaunphua
 */
@Stateless
public class ScreeningScheduleSessionBean implements ScreeningScheduleSessionBeanLocal {

    @EJB
    private CalendarDaySessionBeanLocal calendarDaySessionBean;

    @EJB
    private MovieSessionBeanLocal movieSessionBean;

    @EJB
    private HallSessionBeanLocal hallSessionBean;

    @PersistenceContext(unitName = "GoldClassOnline-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    @Override
    public ScreeningScheduleEntity createScreeningSchedule(ScreeningScheduleEntity newScreeningSchedule, Long calendarDayId, Long movieId) throws CreateNewScreeningScheduleException
    {
        try
        {
            System.err.println("### MOVIE ID IS " + movieId);
            CalendarDayEntity calendarDay = calendarDaySessionBean.retrieveCalendarDayByCalendarDayId(calendarDayId);
            MovieEntity movie = movieSessionBean.retrieveMovieByMovieId(movieId);
            newScreeningSchedule.setMovie(movie); //1 way
            em.persist(newScreeningSchedule);
            em.flush();
            
            calendarDay.getScreeningScheduleEntities().add(newScreeningSchedule); //1 way
            newScreeningSchedule.setCalendarDayEntity(calendarDay); //2 way
            
            newScreeningSchedule.setMovie(movie); //1 way
            movie.getScreeningSchedules().add(newScreeningSchedule); //2 way
            em.flush();
            

            return newScreeningSchedule;
        }
        catch(Exception ex)
        {
            throw new CreateNewScreeningScheduleException("An unexpected error has occurred: " + ex.getMessage());
        }
    } 
    @Override
    public List<ScreeningScheduleEntity> retrieveScreeningSchedulesUsingHallEntity(Long hallId) throws HallNotFoundException {
        HallEntity hall = hallSessionBean.retrieveHallByhallId(hallId);
        Query query = em.createQuery("SELECT se FROM ScreeningScheduleEntity se where se.calendarDayEntity.hall.hallId = :inScreeningSchedule");
        query.setParameter("inScreeningSchedule",hallId);
        return query.getResultList();
    }
    
    @Override
    public ScreeningScheduleEntity retrieveScreeningScheduleByScreeningScheduleId(Long ssId) throws ScreeningScheduleNotFoundException
    {
        ScreeningScheduleEntity sse = em.find(ScreeningScheduleEntity.class, ssId);
        
        if(sse != null)
        {
            return sse;
        }
        else
        {
            throw new ScreeningScheduleNotFoundException("Screening Schedule ID " + ssId + " does not exist!");
        }
    }
    
    @Override
    public void updateScreeningSchedule(ScreeningScheduleEntity sse) throws ScreeningScheduleNotFoundException
    {
        if(sse.getScreeningScheduleId()!= null)
        {
            ScreeningScheduleEntity sseToUpdate = retrieveScreeningScheduleByScreeningScheduleId(sse.getScreeningScheduleId());
            //if(cinemaEntityToUpdate.getCinemaName().equals(cinemaEntity.getCinemaName()))
            //{
                sseToUpdate.setStartTime(sse.getStartTime());
                sseToUpdate.setEndTime(sse.getEndTime());
                sseToUpdate.setMovie(sse.getMovie());            
            //}
        }
        else
        {
            throw new ScreeningScheduleNotFoundException("Screening Schedule ID not provided for screening schedule to be updated");
        }
    }
    
    @Override
    public void deleteScreeningSchedule(Long screeningScheduleId) throws ScreeningScheduleNotFoundException, DeleteScreeningScheduleException
    {
        ScreeningScheduleEntity ssToRemove = retrieveScreeningScheduleByScreeningScheduleId(screeningScheduleId);
        
        List<TicketEntity> tickets = ssToRemove.getTickets();
        
        if(tickets.isEmpty())
        {
            try {
                System.out.println("Checker: Screening Schedule Entity (ID):" + ssToRemove.getScreeningScheduleId()+ " will be deleted");
                MovieEntity movie = ssToRemove.getMovie();
                CalendarDayEntity calendarDay = calendarDaySessionBean.retrieveCalendarDayByCalendarDayId(ssToRemove.getCalendarDayEntity().getId());
                calendarDay.getScreeningScheduleEntities().remove(ssToRemove);
                ssToRemove.setCalendarDayEntity(null);
                ssToRemove.setMovie(null);
                movie.getScreeningSchedules().remove(ssToRemove);
                em.remove(ssToRemove);
            } catch (CalendarDayNotFoundException ex) {
                Logger.getLogger(ScreeningScheduleSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            throw new DeleteScreeningScheduleException("There are tickets(s) associated with the Screening Schedule");
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist1(Object object) {
        em.persist(object);
    }
}
