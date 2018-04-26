/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author shaunphua
 */
@Entity
public class TicketEntity implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;
    private String ticketRow;
    private String ticketColumn;
    @ManyToOne
    private ScreeningScheduleEntity screeningSchedule;
    @ManyToOne
    private CustomerEntity customer;

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getTicketId() != null ? getTicketId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the ticketId fields are not set
        if (!(object instanceof TicketEntity)) {
            return false;
        }
        TicketEntity other = (TicketEntity) object;
        if ((this.getTicketId() == null && other.getTicketId() != null) || (this.getTicketId() != null && !this.ticketId.equals(other.ticketId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TicketEntity[ id=" + getTicketId() + " ]";
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    
    public ScreeningScheduleEntity getScreeningSchedule() {
        return screeningSchedule;
    }

    public void setScreeningSchedule(ScreeningScheduleEntity screeningSchedule) {
        this.screeningSchedule = screeningSchedule;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public String getTicketRow() {
        return ticketRow;
    }

    public void setTicketRow(String ticketRow) {
        this.ticketRow = ticketRow;
    }

    public String getTicketColumn() {
        return ticketColumn;
    }

    public void setTicketColumn(String ticketColumn) {
        this.ticketColumn = ticketColumn;
    }
    
}
