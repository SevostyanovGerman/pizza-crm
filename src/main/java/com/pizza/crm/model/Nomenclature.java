package com.pizza.crm.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Nomenclature")
public class Nomenclature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long vendorCode;

    private Integer code;

    private Double price;

    private LocalTime cookingTimeNorm;

    private LocalTime cookingTimePeak;

    private String name;

    private String backgroundColor;

    private String fontColor;

    @Enumerated(EnumType.STRING)
    private NomenclatureType nomenclatureType;

    @Enumerated(EnumType.STRING)
    private AccountingCategory accountingCategory;

    private String cookingPlace;

    @ManyToMany
    @JoinTable(name = "Nomenclature_NomenclatureParentGroup",
            joinColumns = @JoinColumn(name = "nomenclature"),
            inverseJoinColumns = @JoinColumn(name = "NomenclatureParentGroup"))
    private Set<NomenclatureParentGroup> nomenclatureParentGroupSet;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "nomenclatureId")
    private List<ModifierProperty> modifierPropertyList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "nomenclatureId")
    @Fetch(FetchMode.SELECT)
    private List<Packaging> packagingList;

    public Nomenclature() {
    }

    public Nomenclature(String name) {
        this.name = name;
    }

    public Nomenclature(Integer code, Double price, LocalTime cookingTimeNorm,
                        LocalTime cookingTimePeak, String name, NomenclatureType nomenclatureType,
                        AccountingCategory accountingCategory, String cookingPlace) {
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

    public Long getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(Long vendorCode) {
        this.vendorCode = vendorCode;
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

    public String getCookingPlace() {
        return cookingPlace;
    }

    public void setCookingPlace(String cookingPlace) {
        this.cookingPlace = cookingPlace;
    }

    public Set<NomenclatureParentGroup> getNomenclatureParentGroupSet() {
        return nomenclatureParentGroupSet;
    }

    public void setNomenclatureParentGroupSet(Set<NomenclatureParentGroup> nomenclatureParentGroupSet) {
        this.nomenclatureParentGroupSet = nomenclatureParentGroupSet;
    }

    public List<ModifierProperty> getModifierPropertyList() {
        return modifierPropertyList;
    }

    public void setModifierPropertyList(List<ModifierProperty> modifierPropertyList) {
        this.modifierPropertyList = modifierPropertyList;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public List<Packaging> getPackagingList() {
        return packagingList;
    }

    public void setPackagingList(List<Packaging> packagingList) {
        this.packagingList = packagingList;
    }
}
