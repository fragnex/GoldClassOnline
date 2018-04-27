/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author shaunphua
 */
@Entity
public class HallEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hallId;
    private Integer hallRows;
    private Integer hallColumns;
    @Column(nullable = false, unique = true)
    private Integer hallNumber;
    @ManyToOne(fetch = FetchType.EAGER)
    private CinemaEntity cinema;
    @OneToMany(fetch = FetchType.EAGER)
    private List<CalendarDayEntity> calendarDayEntities;
    @OneToMany(fetch = FetchType.EAGER)
    private List<NonSeatEntity> nonSeatEntities;

    public HallEntity() {
        calendarDayEntities = new ArrayList<>();
        nonSeatEntities = new ArrayList<>();
       
    }

    public HallEntity(CinemaEntity cinema, Integer hallNumber, Integer hallRows, Integer hallColumns) {
        this();
        this.cinema = cinema;
        this.hallNumber = hallNumber;
        this.hallRows = hallRows;
        this.hallColumns = hallColumns;
    }
    
    
    
    
    

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hallId != null ? hallId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the hallId fields are not set
        if (!(object instanceof HallEntity)) {
            return false;
        }
        HallEntity other = (HallEntity) object;
        if ((this.hallId == null && other.hallId != null) || (this.hallId != null && !this.hallId.equals(other.hallId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HallEntity[ id=" + hallId + " ]";
    }

    


    public Integer getHallNumber() {
        
        return hallNumber;
    }

    public void setHallNumber(Integer hallNumber) {
        this.hallNumber = hallNumber;
    }

    public CinemaEntity getCinema() {
        return cinema;
    }

    public void setCinema(CinemaEntity cinema) {
        this.cinema = cinema;
    }

    

    public Integer getHallRows() {
        return hallRows;
    }

    public void setHallRows(Integer hallRows) {
        this.hallRows = hallRows;
    }

    public Integer getHallColumns() {
        return hallColumns;
    }

    public void setHallColumns(Integer hallColumns) {
        this.hallColumns = hallColumns;
    }

    public List<NonSeatEntity> getNonSeatEntities() {
        return nonSeatEntities;
    }

    public void setNonSeatEntities(List<NonSeatEntity> nonSeatEntities) {
        this.nonSeatEntities = nonSeatEntities;
    }

    public List<CalendarDayEntity> getCalendarDayEntities() {
        return calendarDayEntities;
    }

    public void setCalendarDayEntities(List<CalendarDayEntity> calendarDayEntities) {
        this.calendarDayEntities = calendarDayEntities;
    }
    
}
