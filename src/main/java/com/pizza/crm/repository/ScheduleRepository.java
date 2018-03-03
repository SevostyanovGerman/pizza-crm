package com.pizza.crm.repository;

import com.pizza.crm.model.Schedule;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

    Schedule getScheduleByName(String name);

    List<Schedule> findAll();

    @Transactional
    void deleteByName(String name);

}
