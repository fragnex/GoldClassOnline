package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-25T16:52:16")
@StaticMetamodel(StaffEntity.class)
public class StaffEntity_ { 

    public static volatile SingularAttribute<StaffEntity, String> firstName;
    public static volatile SingularAttribute<StaffEntity, String> lastName;
    public static volatile SingularAttribute<StaffEntity, String> accessRightEnum;
    public static volatile SingularAttribute<StaffEntity, String> password;
    public static volatile SingularAttribute<StaffEntity, String> salt;
    public static volatile SingularAttribute<StaffEntity, Long> staffId;
    public static volatile SingularAttribute<StaffEntity, String> username;

}