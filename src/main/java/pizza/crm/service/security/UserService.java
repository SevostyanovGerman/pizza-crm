package pizza.crm.service.security;

import pizza.crm.model.security.User;
import pizza.crm.service.CrudService;

import java.util.Optional;

public interface UserService extends CrudService<User, Long> {

    Optional<User> findByPincode(String pincode);

}
