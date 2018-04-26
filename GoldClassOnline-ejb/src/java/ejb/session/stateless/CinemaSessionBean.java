/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CinemaEntity;
import entity.HallEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.exception.CinemaNotFoundException;
import util.exception.CreateNewCinemaException;
import util.exception.DeleteCinemaException;

/**
 *
 * @author shaunphua
 */
@Stateless
public class CinemaSessionBean implements CinemaSessionBeanLocal {

    @PersistenceContext(unitName = "GoldClassOnline-ejbPU")
    private EntityManager em;
    
    
    @Override
    public CinemaEntity createNewCinema(CinemaEntity newCinemaEntity) throws CreateNewCinemaException
    {
        try
        {
            em.persist(newCinemaEntity);
            em.flush();

            return newCinemaEntity;
        }
        catch(PersistenceException ex)
        {
            if(ex.getCause() != null && 
                    ex.getCause().getCause() != null &&
                    ex.getCause().getCause().getClass().getSimpleName().equals("MySQLIntegrityConstraintViolationException"))
            {
                throw new CreateNewCinemaException("Cinema with same name already exist");
            }
            else
            {
                throw new CreateNewCinemaException("An unexpected error has occurred: " + ex.getMessage());
            }
        }
        catch(Exception ex)
        {
            throw new CreateNewCinemaException("An unexpected error has occurred: " + ex.getMessage());
        }
    }
    
    
    @Override
    public CinemaEntity retrieveCinemaByCinemaId(Long cinemaId) throws CinemaNotFoundException
    {
        CinemaEntity cinema = em.find(CinemaEntity.class, cinemaId);
        
        if(cinema != null)
        {
            return cinema;
        }
        else
        {
            throw new CinemaNotFoundException("Cinema ID " + cinemaId + " does not exist!");
        }
    }
    
    
    @Override
    public CinemaEntity retrieveCinemaByName(String cinemaName) throws CinemaNotFoundException
    {
        Query query = em.createQuery("SELECT ce FROM CinemaEntity ce WHERE ce.cinemaName = :inCinemaCode");
        query.setParameter("inCinemaCode", cinemaName);
        
        try
        {
            return (CinemaEntity)query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new CinemaNotFoundException("Cinema with name: " + cinemaName + " does not exist!");
        }
    }
    
    @Override
    public List<CinemaEntity> retrieveAllCinemaEntities() {
       Query query = em.createQuery("SELECT ce FROM CinemaEntity ce");
       return query.getResultList();
    }
    
    @Override
    public void updateCinema(CinemaEntity cinemaEntity) throws CinemaNotFoundException
    {
        if(cinemaEntity.getCinemaId()!= null)
        {
            CinemaEntity cinemaEntityToUpdate = retrieveCinemaByCinemaId(cinemaEntity.getCinemaId());
            //if(cinemaEntityToUpdate.getCinemaName().equals(cinemaEntity.getCinemaName()))
            //{
                System.err.println("**** Swee Swee");
                cinemaEntityToUpdate.setCinemaName(cinemaEntity.getCinemaName());
                cinemaEntityToUpdate.setHalls(cinemaEntity.getHalls());
            //}
        }
        else
        {
            throw new CinemaNotFoundException("Cinema ID not provided for cinema to be updated");
        }
    }
    
    @Override
    public void deleteCinema(Long cinemaId) throws CinemaNotFoundException, DeleteCinemaException
    {
        CinemaEntity cinemaEntityToRemove = retrieveCinemaByCinemaId(cinemaId);
        
        List<HallEntity> hallEntities = cinemaEntityToRemove.getHalls();
        
        if(hallEntities.isEmpty())
        {
            System.out.println("Checker: Cinema Entity:" + cinemaEntityToRemove.getCinemaName() + " will be deleted");
            em.remove(cinemaEntityToRemove);
        }
        else
        {
            throw new DeleteCinemaException("There are hall(s) associated with the cinema");
        }
    }
    
    
}
