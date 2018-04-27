package entity;

import entity.HallEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-26T23:52:48")
@StaticMetamodel(CinemaEntity.class)
public class CinemaEntity_ { 

    public static volatile SingularAttribute<CinemaEntity, Long> cinemaId;
    public static volatile SingularAttribute<CinemaEntity, String> cinemaName;
    public static volatile ListAttribute<CinemaEntity, HallEntity> halls;

}