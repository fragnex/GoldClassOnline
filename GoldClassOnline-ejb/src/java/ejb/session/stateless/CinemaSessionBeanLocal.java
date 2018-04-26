/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CinemaEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.CinemaNotFoundException;
import util.exception.CreateNewCinemaException;
import util.exception.DeleteCinemaException;

/**
 *
 * @author shaunphua
 */
@Local
public interface CinemaSessionBeanLocal {

    
    public List<CinemaEntity> retrieveAllCinemaEntities();

    public CinemaEntity retrieveCinemaByCinemaId(Long cinemaId) throws CinemaNotFoundException;

    public CinemaEntity createNewCinema(CinemaEntity newCinemaEntity) throws CreateNewCinemaException;

    public CinemaEntity retrieveCinemaByName(String cinemaName) throws CinemaNotFoundException;

    public void updateCinema(CinemaEntity cinemaEntity) throws CinemaNotFoundException;

    public void deleteCinema(Long cinemaId) throws CinemaNotFoundException, DeleteCinemaException;

    
    
}
