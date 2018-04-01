package com.pizza.crm.model;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "Nomenclature")
public class Nomenclature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long articul;

    private Integer code;

    private Double price;

    private LocalTime cookingTimeNorm;

    private LocalTime cookingTimePeak;

    private String name;

    @Enumerated(EnumType.STRING)
    private NomenclatureType nomenclatureType;

    @Enumerated(EnumType.STRING)
    private AccountingCategory accountingCategory;

    @Enumerated(EnumType.STRING)
    private CookingPlace cookingPlace;

    @ManyToMany
    @JoinTable(name = "Nomenclature_NomenclatureParentGroup",
            joinColumns = @JoinColumn(name = "nomenclature"),
            inverseJoinColumns = @JoinColumn(name = "NomenclatureParentGroup"))
    private Set<NomenclatureParentGroup> nomenclatureParentGroupSet;

    public Nomenclature() {
    }

    public Nomenclature(Integer code, Double price, LocalTime cookingTimeNorm,
                        LocalTime cookingTimePeak, String name, NomenclatureType nomenclatureType,
                        AccountingCategory accountingCategory, CookingPlace cookingPlace) {
        this.code = code;
        this.price = price;
        this.cookingTimeNorm = cookingTimeNorm;
        this.cookingTimePeak = cookingTimePeak;
        this.name = name;
        this.nomenclatureType = nomenclatureType;
        this.accountingCategory = accountingCategory;
        this.cookingPlace = cookingPlace;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticul() {
        return articul;
    }

    public void setArticul(Long articul) {
        this.articul = articul;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalTime getCookingTimeNorm() {
        return cookingTimeNorm;
    }

    public void setCookingTimeNorm(LocalTime cookingTimeNorm) {
        this.cookingTimeNorm = cookingTimeNorm;
    }

    public LocalTime getCookingTimePeak() {
        return cookingTimePeak;
    }

    public void setCookingTimePeak(LocalTime cookingTimePeak) {
        this.cookingTimePeak = cookingTimePeak;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NomenclatureType getNomenclatureType() {
        return nomenclatureType;
    }

    public void setNomenclatureType(NomenclatureType nomenclatureType) {
        this.nomenclatureType = nomenclatureType;
    }

    public AccountingCategory getAccountingCategory() {
        return accountingCategory;
    }

    public void setAccountingCategory(AccountingCategory accountingCategory) {
        this.accountingCategory = accountingCategory;
    }

    public CookingPlace getCookingPlace() {
        return cookingPlace;
    }

    public void setCookingPlace(CookingPlace cookingPlace) {
        this.cookingPlace = cookingPlace;
    }

    public Set<NomenclatureParentGroup> getNomenclatureParentGroupSet() {
        return nomenclatureParentGroupSet;
    }

    public void setNomenclatureParentGroupSet(Set<NomenclatureParentGroup> nomenclatureParentGroupSet) {
        this.nomenclatureParentGroupSet = nomenclatureParentGroupSet;
    }
}
