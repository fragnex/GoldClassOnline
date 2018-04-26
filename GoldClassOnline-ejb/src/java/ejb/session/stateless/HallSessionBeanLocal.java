/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.HallEntity;
import entity.NonSeatEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.CreateNewHallException;
import util.exception.DeleteHallException;
import util.exception.HallNotFoundException;

/**
 *
 * @author shaunphua
 */
@Local
public interface HallSessionBeanLocal {

    public List<HallEntity> retrieveHallEntitiesByCinemaId(Long cinemaId);

    

    public HallEntity retrieveHallByhallId(Long hallId) throws HallNotFoundException;

    public HallEntity createNewHall(HallEntity newHallEntity, Long cinemaId, List<NonSeatEntity> nonSeatEntities) throws CreateNewHallException;

    public void updateHall(HallEntity hallEntity, List<NonSeatEntity> nonSeatEntities) throws HallNotFoundException;

    public void deleteHall(Long hallId) throws HallNotFoundException, DeleteHallException;

    

    
    
}
