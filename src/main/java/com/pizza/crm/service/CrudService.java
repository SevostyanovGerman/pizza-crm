package com.pizza.crm.service;

import java.util.Collection;
import java.util.Optional;

public interface CrudService<T, ID> {

    Collection<T> getAll();

    Optional<T> findById(ID id);

    T save(T t);

    void deleteById(ID id);

}
