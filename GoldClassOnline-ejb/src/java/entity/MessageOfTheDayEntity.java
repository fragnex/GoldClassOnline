/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author shaunphua
 */
@Entity
public class MessageOfTheDayEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageOfTheDayId;
    @Column(length = 128, nullable = false)
    private String title;
    @Column(length = 2048, nullable = false)
    private String message;
    @Temporal(TemporalType.DATE)
    private Date messageDate;

    
    
    public MessageOfTheDayEntity()
    {
    }

    
    
    public MessageOfTheDayEntity(String title, String message, Date messageDate)
    {
        this.title = title;
        this.message = message;
        this.messageDate = messageDate;
    }

    public Long getMessageOfTheDayId() {
        return messageOfTheDayId;
    }

    public void setMessageOfTheDayId(Long messageOfTheDayId) {
        this.messageOfTheDayId = messageOfTheDayId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageOfTheDayId != null ? messageOfTheDayId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the messageOfTheDayId fields are not set
        if (!(object instanceof MessageOfTheDayEntity)) {
            return false;
        }
        MessageOfTheDayEntity other = (MessageOfTheDayEntity) object;
        if ((this.messageOfTheDayId == null && other.messageOfTheDayId != null) || (this.messageOfTheDayId != null && !this.messageOfTheDayId.equals(other.messageOfTheDayId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MessageOfTheDayEntity[ id=" + messageOfTheDayId + " ]";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }
    
}
