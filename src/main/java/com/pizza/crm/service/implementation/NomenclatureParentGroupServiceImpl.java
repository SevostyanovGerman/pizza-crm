package com.pizza.crm.service.implementation;

import com.pizza.crm.model.NomenclatureParentGroup;
import com.pizza.crm.repository.NomenclatureParentGroupRepository;
import com.pizza.crm.service.NomenclatureParentGroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NomenclatureParentGroupServiceImpl implements NomenclatureParentGroupService {

    private final NomenclatureParentGroupRepository nomenclatureParentGroupRepository;

    public NomenclatureParentGroupServiceImpl(NomenclatureParentGroupRepository nomenclatureParentGroupRepository) {
        this.nomenclatureParentGroupRepository = nomenclatureParentGroupRepository;
    }

    @Override
    public void save(NomenclatureParentGroup nomenclatureParentGroup) {
        nomenclatureParentGroupRepository.save(nomenclatureParentGroup);
    }

    @Override
    public void delete(Long id) {
        nomenclatureParentGroupRepository.deleteById(id);
    }

    @Override
    public NomenclatureParentGroup getNomenclatureParentGroup(Long id) {
        return nomenclatureParentGroupRepository.getOne(id);
    }

    @Override
    public NomenclatureParentGroup getNomenclatureParentGroupByName(String name) {
        return nomenclatureParentGroupRepository.getByName(name);
    }

    @Override
    public List<NomenclatureParentGroup> findAlNomenclatureParentGroups() {
        return nomenclatureParentGroupRepository.findAll();
    }
}
