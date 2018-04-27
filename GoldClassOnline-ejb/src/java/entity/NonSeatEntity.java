/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author shaunphua
 */
@Entity
public class NonSeatEntity implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nonSeatEntityId;
    private String nonSeatEntityType; //either "WHEELCHAIR / NONAVAILABLE
    private String rowOfNSE;
    private String colOfNSE;
    @ManyToOne(fetch = FetchType.EAGER)
    private HallEntity hall;
    
    public Long getNonSeatEntityId() {
        return nonSeatEntityId;
    }

    public void setNonSeatEntityId(Long nonSeatEntityId) {
        this.nonSeatEntityId = nonSeatEntityId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getNonSeatEntityId() != null ? getNonSeatEntityId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the nonSeatEntityId fields are not set
        if (!(object instanceof NonSeatEntity)) {
            return false;
        }
        NonSeatEntity other = (NonSeatEntity) object;
        if ((this.getNonSeatEntityId() == null && other.getNonSeatEntityId() != null) || (this.getNonSeatEntityId() != null && !this.nonSeatEntityId.equals(other.nonSeatEntityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.NonSeatEntity[ id=" + getNonSeatEntityId() + " ]";
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    public String getNonSeatEntityType() {
        return nonSeatEntityType;
    }

    public void setNonSeatEntityType(String nonSeatEntityType) {
        System.out.println("SET TYPE OF NSE");
        this.nonSeatEntityType = nonSeatEntityType;
    }

    public String getRowOfNSE() {
        return rowOfNSE;
    }

    public void setRowOfNSE(String rowOfNSE) {
        this.rowOfNSE = rowOfNSE;
        System.out.println("SET ROW OF NSE");
    }

    public String getColOfNSE() {
        return colOfNSE;
    }

    public void setColOfNSE(String colOfNSE) {
        this.colOfNSE = colOfNSE;
    }

    public HallEntity getHall() {
        return hall;
    }

    public void setHall(HallEntity hall) {
        this.hall = hall;
    }
    
}
