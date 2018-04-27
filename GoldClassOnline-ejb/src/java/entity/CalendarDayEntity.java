/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author shaunphua
 */
@Entity
public class CalendarDayEntity implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date calendarDay;
    @ManyToOne(fetch = FetchType.EAGER)
    private HallEntity hall;
    @OneToMany(fetch = FetchType.EAGER)
    private List<ScreeningScheduleEntity> screeningScheduleEntities;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalendarDayEntity)) {
            return false;
        }
        CalendarDayEntity other = (CalendarDayEntity) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CalendarDayEntity[ id=" + getId() + " ]";
    }

    public HallEntity getHall() {
        return hall;
    }

    public void setHall(HallEntity hall) {
        this.hall = hall;
    }

    public List<ScreeningScheduleEntity> getScreeningScheduleEntities() {
        return screeningScheduleEntities;
    }

    public void setScreeningScheduleEntities(List<ScreeningScheduleEntity> screeningScheduleEntities) {
        this.screeningScheduleEntities = screeningScheduleEntities;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    public Date getCalendarDay() {
        return calendarDay;
    }

    public void setCalendarDay(Date calendarDay) {
        this.calendarDay = calendarDay;
    }
    
}
