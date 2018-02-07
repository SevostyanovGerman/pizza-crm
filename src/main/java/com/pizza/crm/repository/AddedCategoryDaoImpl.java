package com.pizza.crm.repository;

import com.pizza.crm.model.AddedCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class AddedCategoryDaoImpl implements AddedCategoryDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void save(AddedCategory category) {
        entityManager.persist(category);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(getCategory(id));
    }

    @Override
    public AddedCategory getCategory(Long id) {
        return entityManager.find(AddedCategory.class, id);
    }

    @Override
    public AddedCategory getCategoryByName(String name) {
        return (AddedCategory) entityManager.createQuery("from AddedCategory where name = :name").setParameter("name", name).getSingleResult();
    }

    @Override
    public List<AddedCategory> getAllCategories() {
        return entityManager.createQuery("from AddedCategory").getResultList();
    }
}
