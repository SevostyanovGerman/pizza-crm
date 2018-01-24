package pizza.crm.repository;

import pizza.crm.model.security.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {

    @Override
    List<Role> findAll();

}
