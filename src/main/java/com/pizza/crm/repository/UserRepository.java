package com.pizza.crm.repository;

import com.pizza.crm.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("FROM User p JOIN FETCH p.roles WHERE p.pincode = (:pincode)")
    User findByPincodeAndFetchRolesEagerly(@Param("pincode") String pincode);

}
