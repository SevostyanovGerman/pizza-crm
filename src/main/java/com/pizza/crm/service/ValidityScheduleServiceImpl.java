package com.pizza.crm.service;

import com.pizza.crm.exceptions.NotFoundException;
import com.pizza.crm.model.ValiditySchedule;
import com.pizza.crm.repository.ValidityScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidityScheduleServiceImpl implements ValidityScheduleService {

    private final ValidityScheduleRepository validityScheduleRepository;

    @Autowired
    public ValidityScheduleServiceImpl(ValidityScheduleRepository validityScheduleRepository) {
        this.validityScheduleRepository = validityScheduleRepository;
    }

    @Override
    public void save(ValiditySchedule validitySchedule) {
        validityScheduleRepository.save(validitySchedule);
    }

    @Override
    public void delete(Long id) {
        if (validityScheduleRepository.existsById(id)) {
            validityScheduleRepository.deleteById(id);
        }
    }

    @Override
    public ValiditySchedule getSchedule(Long id) {
        return validityScheduleRepository.findById(id).orElseThrow(() -> new NotFoundException(""));
    }

    @Override
    public List<ValiditySchedule> findAllSchedules() {
        return validityScheduleRepository.findAll();
    }
}
