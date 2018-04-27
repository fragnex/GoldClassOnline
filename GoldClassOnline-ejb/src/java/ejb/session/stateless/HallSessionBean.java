package ejb.session.stateless;

import entity.CalendarDayEntity;
import entity.CinemaEntity;
import static entity.CinemaEntity_.cinemaId;
import entity.HallEntity;
import entity.NonSeatEntity;
import entity.ScreeningScheduleEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.exception.CinemaNotFoundException;
import util.exception.CreateNewCinemaException;
import util.exception.CreateNewHallException;
import util.exception.DeleteCinemaException;
import util.exception.DeleteHallException;
import util.exception.HallNotFoundException;

/**
 *
 * @author shaunphua
 */
@Stateless
public class HallSessionBean implements HallSessionBeanLocal {

    @EJB
    private CinemaSessionBeanLocal cinemaSessionBean;
    
    @PersistenceContext(unitName = "GoldClassOnline-ejbPU")
    private EntityManager em;
    
    
    
    @Override
    public HallEntity createNewHall(HallEntity newHallEntity, Long cinemaId, List<NonSeatEntity> nonSeatEntities) throws CreateNewHallException
    {
        try
        {
            for (NonSeatEntity nSE: nonSeatEntities) {
                em.persist(nSE);
            }
            newHallEntity.setCinema(cinemaSessionBean.retrieveCinemaByCinemaId(cinemaId));
            
            em.persist(newHallEntity);
            CinemaEntity cinema = cinemaSessionBean.retrieveCinemaByCinemaId(cinemaId);
            newHallEntity.setCinema(cinema); //1 way
            cinema.getHalls().add(newHallEntity); //2 way
            
            for (NonSeatEntity nSE: nonSeatEntities) {
                nSE.setHall(newHallEntity); //1 way
                newHallEntity.getNonSeatEntities().add(nSE); //2 way
            }
            
            em.flush();

            return newHallEntity;
        }
        catch(Exception ex)
        {
            throw new CreateNewHallException("An unexpected error has occurred: " + ex.getMessage());
        }
        
    }
    
    @Override //only updates seating plan!
    public void updateHall(HallEntity hallEntity, List<NonSeatEntity> nonSeatEntities) throws HallNotFoundException 
    {
        if(hallEntity.getHallId()!= null)
        {
            try{
            HallEntity hallEntityToUpdate = retrieveHallByhallId(hallEntity.getHallId());
            for (NonSeatEntity nSE: nonSeatEntities) {
                em.persist(nSE);
            }
            //if(cinemaEntityToUpdate.getCinemaName().equals(cinemaEntity.getCinemaName()))
            //{
            hallEntityToUpdate.setHallRows(hallEntity.getHallRows());
            hallEntityToUpdate.setHallColumns(hallEntity.getHallColumns());
            hallEntityToUpdate.getNonSeatEntities().clear();
            for (NonSeatEntity nse: nonSeatEntities) {
                nse.setHall(hallEntityToUpdate); //1 way
                hallEntityToUpdate.getNonSeatEntities().add(nse);// 2 way
            }  
            //}
        }
            catch(Exception e) {
                throw new HallNotFoundException("Hall Id Provided is invalid!");
            }
        }
       
        else
        {
            throw new HallNotFoundException("Hall ID not provided for cinema to be updated");
        }
    }
    //must check for calendar Day entities, if not cannot delete!
    @Override
    public void deleteHall(Long hallId) throws HallNotFoundException, DeleteHallException
    {
        
        HallEntity hallEntityToRemove = retrieveHallByhallId(hallId);
        List<CalendarDayEntity> calendarDays = hallEntityToRemove.getCalendarDayEntities();
       
        if(calendarDays.isEmpty())
        {
            CinemaEntity c = hallEntityToRemove.getCinema();
            c.getHalls().remove(hallEntityToRemove); //dissassociation
            hallEntityToRemove.setCinema(null); //disassocation
            List<NonSeatEntity> allNonSeats = hallEntityToRemove.getNonSeatEntities();
            for (NonSeatEntity nse: allNonSeats) {
                nse.setHall(null);
            }
            hallEntityToRemove.setNonSeatEntities(null);
            for (NonSeatEntity nse: allNonSeats) {
                em.remove(nse);
            }
            System.out.println("Checker: Hall Entity:" + hallEntityToRemove.getHallNumber() + " will be deleted");
            em.remove(hallEntityToRemove);
        }
        else
        {
            throw new DeleteHallException("There are Calendar Day(s) associated with the hall");
        }
    }
    
    @Override
    public HallEntity retrieveHallByhallId(Long hallId) throws HallNotFoundException
    {
        HallEntity hall = em.find(HallEntity.class, hallId);
        
        if(hall != null)
        {
            return hall;
        }
        else
        {
            throw new HallNotFoundException("Hall ID " + hallId + " does not exist!");
        }
    }
    
    
    /* might need to double check! */
    @Override
    public List<HallEntity> retrieveHallEntitiesByCinemaId(Long cinemaId) {
        Query query = em.createQuery("SELECT he FROM HallEntity he WHERE he.cinema.cinemaId = :ci");
        query.setParameter("ci", cinemaId);
        return query.getResultList();
    }
    
    @Override
    public List<HallEntity> retrieveAllHallEntities() {
       Query query = em.createQuery("SELECT he FROM HallEntity he");
       return query.getResultList();
    }
    /*To be edited!@@@@@@@@@@@
    public HallEntity createNewHallEntity(HallEntity hall, Long cinemaId) {
        try {
            CinemaEntity cinema = cinemaSessionBean.retrieveCinemaByCinemaId(cinemaId);
            em.persist(hall);
        } catch (CinemaNotFoundException ex) {
            Logger.getLogger(HallSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    */

}
