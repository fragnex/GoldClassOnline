/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import sun.security.krb5.internal.Ticket;

/**
 *
 * @author shaunphua
 */
@Entity
public class ScreeningScheduleEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long screeningScheduleId;
    @Temporal(TemporalType.DATE)
    private Date startTime;
    @Temporal(TemporalType.DATE)
    private Date endTime;
    @ManyToOne
    private CalendarDayEntity calendarDayEntity;
    
    
    @OneToMany
    private List<TicketEntity> tickets;
    
    @ManyToOne
    @JoinColumn(nullable = false) //must have have a movie!
    private MovieEntity movie;
   
    public ScreeningScheduleEntity() {
       
    }
    
    public Long getScreeningScheduleId() {
        return screeningScheduleId;
    }
    
    
    public void setScreeningScheduleId(Long screeningScheduleId) {
        this.screeningScheduleId = screeningScheduleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (screeningScheduleId != null ? screeningScheduleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the screeningScheduleId fields are not set
        if (!(object instanceof ScreeningScheduleEntity)) {
            return false;
        }
        ScreeningScheduleEntity other = (ScreeningScheduleEntity) object;
        if ((this.screeningScheduleId == null && other.screeningScheduleId != null) || (this.screeningScheduleId != null && !this.screeningScheduleId.equals(other.screeningScheduleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ScreeningScheduleEntity[ id=" + screeningScheduleId + " ]";
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public CalendarDayEntity getCalendarDayEntity() {
        return calendarDayEntity;
    }

    public void setCalendarDayEntity(CalendarDayEntity calendarDayEntity) {
        this.calendarDayEntity = calendarDayEntity;
    }

  
    public List<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }
    
}
