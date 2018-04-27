package ejb.session.singleton;

import ejb.session.stateless.CinemaSessionBeanLocal;
import ejb.session.stateless.HallSessionBeanLocal;
import ejb.session.stateless.MessageOfTheDayEntityControllerLocal;
import ejb.session.stateless.MovieSessionBeanLocal;
import ejb.session.stateless.NonSeatBeanLocal;
import ejb.session.stateless.StaffSessionBeanLocal;
import entity.CinemaEntity;
import entity.HallEntity;
import entity.MessageOfTheDayEntity;
import entity.MovieEntity;
import entity.NonSeatEntity;
import entity.StaffEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.Enumeration.AccessRightEnum;
import util.exception.CinemaNotFoundException;
import util.exception.CreateNewCinemaException;
import util.exception.DeleteCinemaException;
import util.exception.StaffNotFoundException;
/* Shaun is author */
@Singleton
@LocalBean
@Startup
public class DatInitSessionBean {

    @EJB
    private NonSeatBeanLocal nonSeatBean;

    @EJB
    private HallSessionBeanLocal hallSessionBean;

    @EJB
    private MovieSessionBeanLocal movieSessionBean;

    @EJB
    private CinemaSessionBeanLocal cinemaSessionBean;
    @EJB
    private MessageOfTheDayEntityControllerLocal messageOfTheDayEntityController;
    @EJB
    private StaffSessionBeanLocal staffSessionBean;
    
    
    @PersistenceContext(unitName = "GoldClassOnline-ejbPU")
    private EntityManager em;
    
    @PostConstruct
    public void postConstruct()
    {
        try
        {
            staffSessionBean.retrieveStaffByUsername("admin");
            CinemaEntity ce = cinemaSessionBean.createNewCinema(new CinemaEntity("firstCinema"));
            cinemaSessionBean.deleteCinema(ce.getCinemaId());
            System.out.println("Success!");
        }
        catch(StaffNotFoundException ex)
        {
            initializeData();
        } catch (CreateNewCinemaException | CinemaNotFoundException | DeleteCinemaException ex) {
            Logger.getLogger(DatInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void initializeData() 
    {
        try
        {
        movieSessionBean.createNewMovie(new MovieEntity(new Integer(15), "R21", "Comedy", new Integer(60), "Bruno", "a", "a", "a", "a"));
        movieSessionBean.createNewMovie(new MovieEntity(new Integer(10), "NC16", "Action", new Integer(120), "Avengers", "b", "b", "b", "b"));
        staffSessionBean.createNewAdministrativeStaffEntity(new StaffEntity("ADMINISTRATIVE", "STAFF", "ADMINISTRATIVESTAFF", "admin", "password"));
        staffSessionBean.createNewAdministrativeStaffEntity(new StaffEntity("OPERATION", "STAFF", "OPERATIONSTAFF", "admin1", "password"));
        cinemaSessionBean.createNewCinema(new CinemaEntity("Golden Village"));
        cinemaSessionBean.createNewCinema(new CinemaEntity("Eng Wah"));
        NonSeatEntity nse = new NonSeatEntity("WHEELCHAIR", "A", "1", null);
        em.persist(nse);
        List<NonSeatEntity> nseList = new ArrayList<>();
        nseList.add(nse);
        hallSessionBean.createNewHall(new HallEntity(cinemaSessionBean.retrieveCinemaByName("Eng Wah"), 1, 10, 10), new Long(2), nseList);
        nse.setHall(hallSessionBean.retrieveHallByhallId(new Long(2)));
       
        
        
        messageOfTheDayEntityController.createMessageOfTheDayEntity(new MessageOfTheDayEntity("Lock Cashier!", "Please remember to lock the cashier!", new Date(118, 1, 19)));
        messageOfTheDayEntityController.createMessageOfTheDayEntity(new MessageOfTheDayEntity("Marvel Avengers", "Remember to promote our new movie: The Avengers", new Date(118, 1, 20)));
        messageOfTheDayEntityController.createMessageOfTheDayEntity(new MessageOfTheDayEntity("Excellent Customer Service", "Keep up the excellent customer service!", new Date(118, 1, 21)));
        
        }
        catch(Exception ex)
        {
            System.err.println("********** DataInitializationSessionBean.initializeData(): An error has occurred while loading initial test data: " + ex.getMessage());
        }
    }
    
    
}
