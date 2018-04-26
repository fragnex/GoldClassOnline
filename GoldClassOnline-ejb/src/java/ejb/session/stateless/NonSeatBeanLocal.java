/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.NonSeatEntity;
import javax.ejb.Local;

/**
 *
 * @author shaunphua
 */
@Local
public interface NonSeatBeanLocal {

    public NonSeatEntity createNewNonSeatEntity(NonSeatEntity nse);
    
}
