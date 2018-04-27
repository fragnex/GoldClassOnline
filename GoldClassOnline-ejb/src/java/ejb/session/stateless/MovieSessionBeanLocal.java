/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.MovieEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.CreateNewMovieException;
import util.exception.DeleteMovieException;
import util.exception.MovieNotFoundException;

/**
 *
 * @author shaunphua
 */
@Local
public interface MovieSessionBeanLocal {

    public MovieEntity createNewMovie(MovieEntity newMoiveEntity) throws CreateNewMovieException;

    public MovieEntity retrieveMovieByMovieId(Long movieId) throws MovieNotFoundException;

    public MovieEntity retrieveMovieByName(String movieName) throws MovieNotFoundException;

    public List<MovieEntity> retrieveAllMovieEntities();

    public void updateMovie(MovieEntity movieEntity) throws MovieNotFoundException;

    public void deleteMovie(Long movieId) throws MovieNotFoundException, DeleteMovieException;
    
}
