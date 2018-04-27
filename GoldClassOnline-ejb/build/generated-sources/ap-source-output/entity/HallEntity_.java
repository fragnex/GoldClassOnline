package entity;

import entity.CalendarDayEntity;
import entity.CinemaEntity;
import entity.NonSeatEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-27T22:16:43")
@StaticMetamodel(HallEntity.class)
public class HallEntity_ { 

    public static volatile SingularAttribute<HallEntity, Integer> hallNumber;
    public static volatile SingularAttribute<HallEntity, Long> hallId;
    public static volatile ListAttribute<HallEntity, NonSeatEntity> nonSeatEntities;
    public static volatile SingularAttribute<HallEntity, Integer> hallRows;
    public static volatile ListAttribute<HallEntity, CalendarDayEntity> calendarDayEntities;
    public static volatile SingularAttribute<HallEntity, Integer> hallColumns;
    public static volatile SingularAttribute<HallEntity, CinemaEntity> cinema;

}