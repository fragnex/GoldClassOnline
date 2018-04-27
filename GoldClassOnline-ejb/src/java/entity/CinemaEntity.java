
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class CinemaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cinemaId;
    @OneToMany(fetch = FetchType.EAGER)
    private List<HallEntity> halls;
    
    @Column(length = 32, nullable = false, unique = true)
    private String cinemaName;

    public CinemaEntity() {
        this.halls = new ArrayList<>();
    }
    
    public CinemaEntity(String cinemaName) {
        this();
        this.cinemaName = cinemaName;
    }
    
    

    public Long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cinemaId != null ? cinemaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the cinemaId fields are not set
        if (!(object instanceof CinemaEntity)) {
            return false;
        }
        CinemaEntity other = (CinemaEntity) object;
        if ((this.cinemaId == null && other.cinemaId != null) || (this.cinemaId != null && !this.cinemaId.equals(other.cinemaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CinemaEntity[ id=" + cinemaId + " ]";
    }

    public List<HallEntity> getHalls() {
        return halls;
    }

    public void setHalls(List<HallEntity> halls) {
        this.halls = halls;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }
    
}
