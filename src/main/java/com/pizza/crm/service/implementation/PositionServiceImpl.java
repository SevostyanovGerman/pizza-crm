package com.pizza.crm.service.implementation;

import com.pizza.crm.model.Position;
import com.pizza.crm.repository.PositionRepository;
import com.pizza.crm.service.PositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositionServiceImpl.class);

    private final PositionRepository positionRepository;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public Collection<Position> getAll() {
        return positionRepository.findAll();
    }

    @Override
    public Optional<Position> findById(Long id) {
        return positionRepository.findById(id);
    }

    @Override
    public Position save(Position position) {
        LOGGER.debug("Saving {}", position);
        return positionRepository.save(position);
    }

    @Override
    public Collection<Position> saveAll(Collection<Position> positions) {
        LOGGER.debug("Saving all {}", positions);
        return positionRepository.saveAll(positions);
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.debug("Deleting by id {}", id);
        positionRepository.deleteById(id);
    }
}
