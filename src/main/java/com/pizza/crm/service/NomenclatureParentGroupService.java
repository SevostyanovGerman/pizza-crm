package com.pizza.crm.service;

import com.pizza.crm.model.NomenclatureParentGroup;

import java.util.List;

public interface NomenclatureParentGroupService {

    void save(NomenclatureParentGroup nomenclatureParentGroup);

    void delete(Long id);

    NomenclatureParentGroup getNomenclatureParentGroup(Long id);

    NomenclatureParentGroup getNomenclatureParentGroupByName(String name);

    List<NomenclatureParentGroup> findAlNomenclatureParentGroups();
}
