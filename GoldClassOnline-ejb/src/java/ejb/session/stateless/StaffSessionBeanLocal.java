/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.StaffEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.InvalidLoginCredentialException;
import util.exception.StaffNotFoundException;

@Local
public interface StaffSessionBeanLocal {

    public List<StaffEntity> retrieveAllStaffEntities();

    public StaffEntity createNewAdministrativeStaffEntity(StaffEntity staffEntity);

    public StaffEntity retrieveStaffByStaffId(Long staffId) throws StaffNotFoundException;

    public StaffEntity retrieveStaffByUsername(String username) throws StaffNotFoundException;

    public StaffEntity staffLogin(String username, String password) throws InvalidLoginCredentialException;

   
    
}
