package entity;

import entity.ScreeningScheduleEntity;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import util.Enumeration.GenreEnum;
import util.Enumeration.RatingEnum;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-25T16:52:16")
@StaticMetamodel(MovieEntity.class)
public class MovieEntity_ { 

    public static volatile SingularAttribute<MovieEntity, List> images;
    public static volatile SingularAttribute<MovieEntity, String> languages;
    public static volatile SingularAttribute<MovieEntity, String> casts;
    public static volatile SingularAttribute<MovieEntity, Integer> price;
    public static volatile SingularAttribute<MovieEntity, String> directors;
    public static volatile SingularAttribute<MovieEntity, RatingEnum> rating;
    public static volatile SingularAttribute<MovieEntity, GenreEnum> genre;
    public static volatile SingularAttribute<MovieEntity, Long> movieId;
    public static volatile SingularAttribute<MovieEntity, Integer> runningTime;
    public static volatile SingularAttribute<MovieEntity, String> synopsis;
    public static volatile SingularAttribute<MovieEntity, String> title;
    public static volatile ListAttribute<MovieEntity, ScreeningScheduleEntity> screeningSchedules;

}