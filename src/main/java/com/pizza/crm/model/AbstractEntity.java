package com.pizza.crm.model;

import javax.persistence.*;

/*@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)*/
@MappedSuperclass
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
}
