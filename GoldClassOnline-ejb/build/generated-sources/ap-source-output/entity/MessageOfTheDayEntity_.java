package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-25T16:52:16")
@StaticMetamodel(MessageOfTheDayEntity.class)
public class MessageOfTheDayEntity_ { 

    public static volatile SingularAttribute<MessageOfTheDayEntity, Date> messageDate;
    public static volatile SingularAttribute<MessageOfTheDayEntity, String> title;
    public static volatile SingularAttribute<MessageOfTheDayEntity, String> message;
    public static volatile SingularAttribute<MessageOfTheDayEntity, Long> messageOfTheDayId;

}