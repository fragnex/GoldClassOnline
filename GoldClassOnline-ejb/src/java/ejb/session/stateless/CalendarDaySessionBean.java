/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CalendarDayEntity;
import entity.HallEntity;
import entity.MovieEntity;
import entity.ScreeningScheduleEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.exception.CalendarDayNotFoundException;
import util.exception.CreateNewCalendarDayException;
import util.exception.CreateNewMovieException;
import util.exception.MovieNotFoundException;

/**
 *
 * @author shaunphua
 */
@Stateless
public class CalendarDaySessionBean implements CalendarDaySessionBeanLocal {

    @EJB
    private HallSessionBeanLocal hallSessionBean;
    
    @PersistenceContext(unitName = "GoldClassOnline-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    @Override
    public CalendarDayEntity retrieveCalendarDayByCalendarDayId(Long calendarDayId) throws CalendarDayNotFoundException
    {
        CalendarDayEntity calendarDay = em.find(CalendarDayEntity.class, calendarDayId);
        
        if(calendarDay != null)
        {
            return calendarDay;
        }
        else
        {
            throw new CalendarDayNotFoundException("Calendar Day ID " + calendarDayId + " does not exist!");
        }
    }
    @Override
    public CalendarDayEntity createNewCalendarDay(CalendarDayEntity newCalendarDayEntity, Long hallId) throws CreateNewCalendarDayException
    {
        try
        {
            
            HallEntity hall = hallSessionBean.retrieveHallByhallId(hallId);
            em.persist(newCalendarDayEntity);
            em.flush();
            newCalendarDayEntity.setHall(hall); //1 way to hall
            hall.getCalendarDayEntities().add(newCalendarDayEntity); //2 way to calendar day
            em.flush();
            return newCalendarDayEntity;
        }
        
        catch(Exception ex)
        {
            throw new CreateNewCalendarDayException("An unexpected error has occurred: " + ex.getMessage());
        }
    }
 
    @Override
    public List<CalendarDayEntity> retrieveCalendarDaysByHallId(Long hallId) {
        Query query = em.createQuery("SELECT ce FROM CalendarDayEntity ce WHERE ce.hall.hallId = :inCalendarDayEntity");
        query.setParameter("inCalendarDayEntity", hallId);
        return query.getResultList();
    }
    
    @Override
    public List<ScreeningScheduleEntity> retrieveScreeningScheduleUsingCalendarDayId(Long calendarDayId) {
        Query query = em.createQuery("SELECT sse FROM ScreeningScheduleEntity sse WHERE sse.calendarDayEntity.id = :inCDI");
        query.setParameter("inCDI", calendarDayId);
        return query.getResultList();
    }
    
    
}
