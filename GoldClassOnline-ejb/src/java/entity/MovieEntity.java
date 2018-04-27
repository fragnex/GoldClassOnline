/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import util.Enumeration.GenreEnum;
import util.Enumeration.RatingEnum;

/**
 *
 * @author shaunphua
 */
@Entity
public class MovieEntity implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    @Column(nullable = false)
    private Integer price; //$10 or $15
    @Column(nullable = false)
    private String rating;
    @Column(nullable = false)
    private String genre;
    @Column(nullable = false)
    private Integer runningTime;
    @Column(length = 32, nullable = false, unique = true)
    private String title;
    @Column(length = 32, nullable = false)
    private String casts;
    @Column(length = 32, nullable = false)
    private String directors;
    @Column(length = 32, nullable = false)
    private String languages;
    @Column(length = 32, nullable = false)
    private String synopsis;
    //LATER COME BACK!
    private List<String> images;
    
    @OneToMany(fetch = FetchType.EAGER)
    private List<ScreeningScheduleEntity> screeningSchedules;

    public MovieEntity() {
        images = new ArrayList<>();
        screeningSchedules = new ArrayList<>();
        price = new Integer(0);
        runningTime = new Integer(0);
    }

    public MovieEntity(Integer price, String rating, String genre, Integer runningTime, String title, String casts, String directors, String languages, String synopsis, List<String> images, List<ScreeningScheduleEntity> screeningSchedules) {
        this();
        this.price = price;
        this.rating = rating;
        this.genre = genre;
        this.runningTime = runningTime;
        this.title = title;
        this.casts = casts;
        this.directors = directors;
        this.languages = languages;
        this.synopsis = synopsis;
        this.images = images;
        this.screeningSchedules = screeningSchedules;
    }
    
    public MovieEntity(Integer price, String rating, String genre, Integer runningTime, String title, String casts, String directors, String languages, String synopsis) {
        this();
        this.price = price;
        this.rating = rating;
        this.genre = genre;
        this.runningTime = runningTime;
        this.title = title;
        this.casts = casts;
        this.directors = directors;
        this.languages = languages;
        this.synopsis = synopsis;
        
    }
    
    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movieId != null ? movieId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the movieId fields are not set
        if (!(object instanceof MovieEntity)) {
            return false;
        }
        MovieEntity other = (MovieEntity) object;
        if ((this.movieId == null && other.movieId != null) || (this.movieId != null && !this.movieId.equals(other.movieId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MovieEntity[ id=" + movieId + " ]";
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    

    public Integer getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Integer runningTime) {
        this.runningTime = runningTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCasts() {
        return casts;
    }

    public void setCasts(String casts) {
        this.casts = casts;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<ScreeningScheduleEntity> getScreeningSchedules() {
        return screeningSchedules;
    }

    public void setScreeningSchedules(List<ScreeningScheduleEntity> screeningSchedules) {
        this.screeningSchedules = screeningSchedules;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
}
