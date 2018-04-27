package entity;

import entity.ScreeningScheduleEntity;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-27T22:16:43")
@StaticMetamodel(MovieEntity.class)
public class MovieEntity_ { 

    public static volatile SingularAttribute<MovieEntity, List> images;
    public static volatile SingularAttribute<MovieEntity, String> languages;
    public static volatile SingularAttribute<MovieEntity, String> casts;
    public static volatile SingularAttribute<MovieEntity, Integer> price;
    public static volatile SingularAttribute<MovieEntity, String> directors;
    public static volatile SingularAttribute<MovieEntity, String> rating;
    public static volatile SingularAttribute<MovieEntity, String> genre;
    public static volatile SingularAttribute<MovieEntity, Long> movieId;
    public static volatile SingularAttribute<MovieEntity, Integer> runningTime;
    public static volatile SingularAttribute<MovieEntity, String> synopsis;
    public static volatile SingularAttribute<MovieEntity, String> title;
    public static volatile ListAttribute<MovieEntity, ScreeningScheduleEntity> screeningSchedules;

}