package com.pizza.crm.repository;

import com.pizza.crm.model.SchemeModifiers;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface SchemeModifiersRepository extends JpaRepository<SchemeModifiers, Long> {

    void deleteByName(String name);

    SchemeModifiers getByName(String name);

    List<SchemeModifiers> findAll();

}
