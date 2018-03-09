package com.pizza.crm.service;

import com.pizza.crm.model.Schedule;

import java.util.List;

public interface ScheduleService {

    void save(Schedule schedule);

    void delete(Long id);

    Schedule getSchedule(Long id);

    Schedule getScheduleByName(String name);

    List<Schedule> findAllSchedules();

    void deleteByName(String name);
}
