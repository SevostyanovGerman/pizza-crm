package com.java_mentor.pizzacrm.service;

import java.util.Collection;

public interface CrudService<T, ID> {

    Collection<T> getAll();

    T findById(ID id);

    T save(T t);

    void deleteById(ID id);

}
