package com.pizza.crm.repository;

import com.pizza.crm.model.ValiditySchedule;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ValidityScheduleRepository extends CrudRepository<ValiditySchedule, Long> {

    // ValiditySchedule getScheduleByName(String name);

    List<ValiditySchedule> findAll();


    // void deleteByName(String name);

}
