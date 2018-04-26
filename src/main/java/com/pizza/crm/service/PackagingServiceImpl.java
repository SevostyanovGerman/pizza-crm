package com.pizza.crm.service;

import com.pizza.crm.model.Packaging;
import com.pizza.crm.repository.PackagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackagingServiceImpl implements PackagingService {

    private final PackagingRepository packagingRepository;

    @Autowired
    public PackagingServiceImpl(PackagingRepository packagingRepository) {
        this.packagingRepository = packagingRepository;
    }

    @Override
    public void save(Packaging packaging) {
        packagingRepository.save(packaging);
    }

    @Override
    public void delete(Long id) {
        packagingRepository.deleteById(id);
    }

    @Override
    public Packaging getNomenclature(Long id) {
        return packagingRepository.getOne(id);
    }

    @Override
    public Packaging getNomenclatureByName(String name) {
        return packagingRepository.getByName(name);
    }

    @Override
    public List<Packaging> findAllPackagings() {
        return packagingRepository.findAll();
    }
}
