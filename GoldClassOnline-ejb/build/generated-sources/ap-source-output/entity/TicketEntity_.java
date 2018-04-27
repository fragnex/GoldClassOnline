package entity;

import entity.CustomerEntity;
import entity.ScreeningScheduleEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-27T22:16:43")
@StaticMetamodel(TicketEntity.class)
public class TicketEntity_ { 

    public static volatile SingularAttribute<TicketEntity, ScreeningScheduleEntity> screeningSchedule;
    public static volatile SingularAttribute<TicketEntity, String> ticketColumn;
    public static volatile SingularAttribute<TicketEntity, String> ticketRow;
    public static volatile SingularAttribute<TicketEntity, Long> ticketId;
    public static volatile SingularAttribute<TicketEntity, CustomerEntity> customer;

}