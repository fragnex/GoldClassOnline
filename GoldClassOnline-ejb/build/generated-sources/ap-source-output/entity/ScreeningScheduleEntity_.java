package entity;

import entity.CalendarDayEntity;
import entity.MovieEntity;
import entity.TicketEntity;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-26T23:52:48")
@StaticMetamodel(ScreeningScheduleEntity.class)
public class ScreeningScheduleEntity_ { 

    public static volatile SingularAttribute<ScreeningScheduleEntity, Long> screeningScheduleId;
    public static volatile ListAttribute<ScreeningScheduleEntity, TicketEntity> tickets;
    public static volatile SingularAttribute<ScreeningScheduleEntity, MovieEntity> movie;
    public static volatile SingularAttribute<ScreeningScheduleEntity, CalendarDayEntity> calendarDayEntity;
    public static volatile SingularAttribute<ScreeningScheduleEntity, Date> startTime;
    public static volatile SingularAttribute<ScreeningScheduleEntity, Date> endTime;

}