package com.pizza.crm.service;

import com.pizza.crm.model.Ingredient;
import com.pizza.crm.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public Collection<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    @Override
    public Optional<Ingredient> findById(Long id) {
        return ingredientRepository.findById(id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Collection<Ingredient> saveAll(Collection<Ingredient> ingredients) {
        return ingredientRepository.saveAll(ingredients);
    }

    @Override
    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }
}
