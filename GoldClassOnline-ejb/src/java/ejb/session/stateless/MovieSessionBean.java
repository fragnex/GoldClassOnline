/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CinemaEntity;
import static entity.CinemaEntity_.cinemaId;
import entity.HallEntity;
import entity.MovieEntity;
import entity.ScreeningScheduleEntity;
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
import util.exception.CreateNewMovieException;
import util.exception.DeleteCinemaException;
import util.exception.DeleteMovieException;
import util.exception.MovieNotFoundException;

/**
 *
 * @author shaunphua
 */
@Stateless
public class MovieSessionBean implements MovieSessionBeanLocal {

    @PersistenceContext(unitName = "GoldClassOnline-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public MovieEntity createNewMovie(MovieEntity newMoiveEntity) throws CreateNewMovieException
    {
        try
        {
            em.persist(newMoiveEntity);
            em.flush();

            return newMoiveEntity;
        }
        catch(PersistenceException ex)
        {
            if(ex.getCause() != null && 
                    ex.getCause().getCause() != null &&
                    ex.getCause().getCause().getClass().getSimpleName().equals("MySQLIntegrityConstraintViolationException"))
            {
                throw new CreateNewMovieException("Movie with same name already exist");
            }
            else
            {
                throw new CreateNewMovieException("An unexpected error has occurred: " + ex.getMessage());
            }
        }
        catch(Exception ex)
        {
            throw new CreateNewMovieException("An unexpected error has occurred: " + ex.getMessage());
        }
    }
    
    @Override
    public MovieEntity retrieveMovieByMovieId(Long movieId) throws MovieNotFoundException
    {
        MovieEntity movie = em.find(MovieEntity.class, movieId);
        
        if(movie != null)
        {
            return movie;
        }
        else
        {
            throw new MovieNotFoundException("Movie ID " + movieId + " does not exist!");
        }
    }
    
    
    @Override
    public MovieEntity retrieveMovieByName(String movieName) throws MovieNotFoundException
    {
        Query query = em.createQuery("SELECT mo FROM MovieEntity mo WHERE mo.title = :inMovie");
        query.setParameter("inMovie", movieName);
        
        try
        {
            return (MovieEntity)query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new MovieNotFoundException("Movie with name: " + movieName + " does not exist!");
        }
    }
    
    @Override
    public List<MovieEntity> retrieveAllMovieEntities() {
       Query query = em.createQuery("SELECT mo FROM MovieEntity mo");
       return query.getResultList();
    }
    
    @Override
    public void updateMovie(MovieEntity movieEntity) throws MovieNotFoundException
    {
        if(movieEntity.getMovieId()!= null)
        {
            MovieEntity movieEntityToUpdate = retrieveMovieByMovieId(movieEntity.getMovieId());
            //if(cinemaEntityToUpdate.getCinemaName().equals(cinemaEntity.getCinemaName()))
            //{
                
                movieEntityToUpdate.setPrice(movieEntity.getPrice());
                movieEntityToUpdate.setRating(movieEntity.getRating());
                movieEntityToUpdate.setGenre(movieEntity.getGenre());
                movieEntityToUpdate.setRunningTime(movieEntity.getRunningTime());
                movieEntityToUpdate.setTitle(movieEntity.getTitle());
                movieEntityToUpdate.setCasts(movieEntity.getCasts());
                movieEntityToUpdate.setDirectors(movieEntity.getDirectors());
                movieEntityToUpdate.setSynopsis(movieEntity.getSynopsis());
                
            //}
        }
        else
        {
            throw new MovieNotFoundException("Movie ID not provided for cinema to be updated");
        }
    }
    
    @Override
    public void deleteMovie(Long movieId) throws MovieNotFoundException, DeleteMovieException
    {
        MovieEntity movieEntityToRemove = retrieveMovieByMovieId(movieId);
        
        List<ScreeningScheduleEntity> screeningSchedules = movieEntityToRemove.getScreeningSchedules();
        
        if(screeningSchedules.isEmpty())
        {
            System.out.println("Checker: Cinema Entity:" + movieEntityToRemove.getTitle()+ " will be deleted");
            em.remove(movieEntityToRemove);
        }
        else
        {
            throw new DeleteMovieException("There are screenign schedule(s) associated with the Movie");
        }
    }
}
