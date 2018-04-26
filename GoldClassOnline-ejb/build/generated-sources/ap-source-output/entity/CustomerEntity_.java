package entity;

import entity.TicketEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-25T16:52:16")
@StaticMetamodel(CustomerEntity.class)
public class CustomerEntity_ { 

    public static volatile ListAttribute<CustomerEntity, TicketEntity> tickets;
    public static volatile SingularAttribute<CustomerEntity, Long> customerId;

}