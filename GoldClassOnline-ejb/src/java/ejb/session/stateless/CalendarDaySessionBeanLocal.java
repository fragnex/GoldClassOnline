/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CalendarDayEntity;
import entity.ScreeningScheduleEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.CalendarDayNotFoundException;
import util.exception.CreateNewCalendarDayException;

/**
 *
 * @author shaunphua
 */
@Local
public interface CalendarDaySessionBeanLocal {

    public CalendarDayEntity createNewCalendarDay(CalendarDayEntity newCalendarDayEntity, Long hallId) throws CreateNewCalendarDayException;

    public List<CalendarDayEntity> retrieveCalendarDaysByHallId(Long hallId);

    public List<ScreeningScheduleEntity> retrieveScreeningScheduleUsingCalendarDayId(Long calendarDayId);

    public CalendarDayEntity retrieveCalendarDayByCalendarDayId(Long calendarDayId) throws CalendarDayNotFoundException;
    
}
