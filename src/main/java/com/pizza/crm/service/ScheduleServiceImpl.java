package com.pizza.crm.service;

import com.pizza.crm.exceptions.NotFoundException;
import com.pizza.crm.model.Schedule;
import com.pizza.crm.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void save(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public void delete(Long id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
        }
    }

    @Override
    public Schedule getSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new NotFoundException(""));
    }

    @Override
    public Schedule getScheduleByName(String name) {
        return scheduleRepository.getScheduleByName(name);
    }

    @Override
    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public void deleteByName(String name) {
        scheduleRepository.deleteByName(name);
    }
}
