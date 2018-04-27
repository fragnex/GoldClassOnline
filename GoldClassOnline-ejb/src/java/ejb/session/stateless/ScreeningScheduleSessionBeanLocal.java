/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.ScreeningScheduleEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.CreateNewScreeningScheduleException;
import util.exception.DeleteScreeningScheduleException;
import util.exception.HallNotFoundException;
import util.exception.ScreeningScheduleNotFoundException;

/**
 *
 * @author shaunphua
 */
@Local
public interface ScreeningScheduleSessionBeanLocal {

    public List<ScreeningScheduleEntity> retrieveScreeningSchedulesUsingHallEntity(Long hallId) throws HallNotFoundException;

    public ScreeningScheduleEntity createScreeningSchedule(ScreeningScheduleEntity newScreeningSchedule, Long calendarDayId, Long movieId) throws CreateNewScreeningScheduleException;

    public ScreeningScheduleEntity retrieveScreeningScheduleByScreeningScheduleId(Long ssId) throws ScreeningScheduleNotFoundException;

    public void updateScreeningSchedule(ScreeningScheduleEntity sse) throws ScreeningScheduleNotFoundException;

    public void deleteScreeningSchedule(Long screeningScheduleId) throws ScreeningScheduleNotFoundException, DeleteScreeningScheduleException;
    
}
