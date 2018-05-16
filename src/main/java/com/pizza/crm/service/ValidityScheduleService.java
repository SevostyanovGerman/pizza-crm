package com.pizza.crm.service;

import com.pizza.crm.model.ValiditySchedule;

import java.util.List;

public interface ValidityScheduleService {

    void save(ValiditySchedule validitySchedule);

    void delete(Long id);

    ValiditySchedule getSchedule(Long id);

  //  ValiditySchedule getScheduleByName(String name);

    List<ValiditySchedule> findAllSchedules();

   // void deleteByName(String name);
}
