package pizza.crm.init;

import pizza.crm.model.security.Role;
import pizza.crm.model.security.User;
import pizza.crm.service.security.RoleService;
import pizza.crm.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
public class DbDataGenerator implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");

        roleService.save(adminRole);
        roleService.save(userRole);

        userService.save(new User("admin", true, Arrays.asList(adminRole, userRole)));
        userService.save(new User("user", true, Collections.singletonList(userRole)));

    }
}
