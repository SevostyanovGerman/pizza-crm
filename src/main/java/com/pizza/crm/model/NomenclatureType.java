package com.pizza.crm.model;

import java.util.Random;

public enum NomenclatureType {
    DISH, PRODUCT, BLANK, SERVICE, MODIFIER;


    //TODO перенести в сервис
    public static NomenclatureType getRandomNomenclatureType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

}