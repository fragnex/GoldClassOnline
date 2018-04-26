package entity;

import entity.HallEntity;
import entity.ScreeningScheduleEntity;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-25T16:52:16")
@StaticMetamodel(CalendarDayEntity.class)
public class CalendarDayEntity_ { 

    public static volatile SingularAttribute<CalendarDayEntity, Date> calendarDay;
    public static volatile SingularAttribute<CalendarDayEntity, HallEntity> hall;
    public static volatile ListAttribute<CalendarDayEntity, ScreeningScheduleEntity> screeningScheduleEntities;
    public static volatile SingularAttribute<CalendarDayEntity, Long> id;

}