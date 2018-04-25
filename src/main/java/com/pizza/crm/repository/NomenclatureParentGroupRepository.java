package com.pizza.crm.repository;

import com.pizza.crm.model.NomenclatureParentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface NomenclatureParentGroupRepository extends JpaRepository<NomenclatureParentGroup, Long> {

    NomenclatureParentGroup getByName(String name);

    List<NomenclatureParentGroup> findAll();

}
