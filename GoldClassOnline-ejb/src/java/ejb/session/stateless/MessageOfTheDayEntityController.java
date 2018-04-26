package ejb.session.stateless;

import entity.MessageOfTheDayEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author shaunphua
 */
@Stateless
public class MessageOfTheDayEntityController implements MessageOfTheDayEntityControllerLocal {

    @PersistenceContext(unitName = "GoldClassOnline-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    @Override
    public MessageOfTheDayEntity createMessageOfTheDayEntity(MessageOfTheDayEntity newMessageOfTheDayEntity)
    {
        em.persist(newMessageOfTheDayEntity);
        em.flush();
        
        return newMessageOfTheDayEntity;
    }
    
    @Override
    public List<MessageOfTheDayEntity> retrieveAllMessagesOfTheDay()
    {
        Query query = em.createQuery("SELECT motd FROM MessageOfTheDayEntity motd ORDER BY motd.messageDate DESC");
        
        return query.getResultList();
    }
}
