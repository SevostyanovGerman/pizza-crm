package com.pizza.crm.model.discount;

import com.pizza.crm.model.PaymentMethod;
import com.pizza.crm.model.Validity;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "Discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    private String nameInCheck;

    private String type;

    @Min(0)
    private Double minSum;

    private boolean minSumRestriction;

    private boolean scheduleRestriction;

    private boolean manualSelectWithOthers;

    private boolean manualInput;

    private boolean manualDishSelect;

    private boolean automatic;

    private boolean combinable;

    private boolean enabled = true;

    private boolean applyForAllDiscountCategories = true;

    private boolean detailWhenPrinting;

    @Min(0)
    private int priority;

    @Min(0)
    private int value;

    private String comment;

    @Enumerated(EnumType.STRING)
    private DiscountApplicationMethod discountApplicationMethod;

    @Enumerated(EnumType.STRING)
    private DiscountMode discountMode = DiscountMode.DISCOUNT;

    @Enumerated(EnumType.STRING)
    private DiscountAssignMode discountAssignMode;

    @Enumerated(EnumType.STRING)
    private DiscountCalculationMode discountCalculationMode;

    @ManyToMany
    @JoinTable(name = "Discount_PaymentMethod",
            joinColumns = @JoinColumn(name = "Discount"),
            inverseJoinColumns = @JoinColumn(name = "PaymentMethod"))
    private List<PaymentMethod> paymentMethods;

    @ManyToMany
    @JoinTable(name = "Discount_Validity",
            joinColumns = @JoinColumn(name = "Discount"),
            inverseJoinColumns = @JoinColumn(name = "Validity"))
    private List<Validity> validities;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<DiscountCategory> discountCategories;

    public Discount() {
    }

    public Discount(@NotBlank String name, String nameInCheck, String type, @Min(0) Double minSum, boolean minSumRestriction, boolean ScheduleRestriction, Boolean manualSelectWithOthers,
                    Boolean manualInput, boolean manualDishSelect, Boolean automatic, Boolean combinable, Boolean enabled, Boolean applyForAllDiscountCategories,
                    @Min(0) Integer priority, @Min(0) Integer value, String comment, DiscountApplicationMethod discountApplicationMethod,
                    DiscountMode discountMode, DiscountAssignMode discountAssignMode, DiscountCalculationMode discountCalculationMode,
                    List<PaymentMethod> paymentMethods, List<Validity> validities, List<DiscountCategory> discountCategories,boolean detailWhenPrinting) {
        this.name = name;
        this.nameInCheck = nameInCheck;
        this.type = type;
        this.minSum = minSum;
        this.minSumRestriction = minSumRestriction;
        this.scheduleRestriction = ScheduleRestriction;
        this.manualSelectWithOthers = manualSelectWithOthers;
        this.manualInput = manualInput;
        this.manualDishSelect = manualDishSelect;
        this.automatic = automatic;
        this.combinable = combinable;
        this.enabled = enabled;
        this.applyForAllDiscountCategories = applyForAllDiscountCategories;
        this.priority = priority;
        this.value = value;
        this.comment = comment;
        this.discountApplicationMethod = discountApplicationMethod;
        this.discountMode = discountMode;
        this.discountAssignMode = discountAssignMode;
        this.discountCalculationMode = discountCalculationMode;
        this.paymentMethods = paymentMethods;
        this.validities = validities;
        this.discountCategories = discountCategories;
        this.detailWhenPrinting = detailWhenPrinting;
    }

    public Discount(@NotBlank String name) {
        this.name = name;
        this.nameInCheck = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameInCheck() {
        return nameInCheck;
    }

    public void setNameInCheck(String nameInCheck) {
        this.nameInCheck = nameInCheck;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMinSum() {
        return minSum;
    }

    public void setMinSum(Double minSum) {
        this.minSum = minSum;
    }

    public boolean getManualSelectWithOthers() {
        return manualSelectWithOthers;
    }

    public void setManualSelectWithOthers(boolean manualSelectWithOthers) {
        this.manualSelectWithOthers = manualSelectWithOthers;
    }

    public boolean getManualInput() {
        return manualInput;
    }

    public void setManualInput(boolean manualInput) {
        this.manualInput = manualInput;
    }

    public boolean getAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    public boolean getCombinable() {
        return combinable;
    }

    public void setCombinable(boolean combinable) {
        this.combinable = combinable;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getApplyForAllDiscountCategories() {
        return applyForAllDiscountCategories;
    }

    public void setApplyForAllDiscountCategories(boolean applyForAllDiscountCategories) {
        this.applyForAllDiscountCategories = applyForAllDiscountCategories;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public DiscountApplicationMethod getDiscountApplicationMethod() {
        return discountApplicationMethod;
    }

    public void setDiscountApplicationMethod(DiscountApplicationMethod discountApplicationMethod) {
        this.discountApplicationMethod = discountApplicationMethod;
    }

    public DiscountMode getDiscountMode() {
        return discountMode;
    }

    public void setDiscountMode(DiscountMode discountMode) {
        this.discountMode = discountMode;
    }

    public DiscountCalculationMode getDiscountCalculationMode() {
        return discountCalculationMode;
    }

    public void setDiscountCalculationMode(DiscountCalculationMode discountCalculationMode) {
        this.discountCalculationMode = discountCalculationMode;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public List<Validity> getValidities() {
        return validities;
    }

    public void setValidities(List<Validity> validities) {
        this.validities = validities;
    }

    public List<DiscountCategory> getDiscountCategories() {
        return discountCategories;
    }

    public void setDiscountCategories(List<DiscountCategory> discountCategories) {
        this.discountCategories = discountCategories;
    }

    public boolean isMinSumRestriction() {
        return minSumRestriction;
    }

    public void setMinSumRestriction(boolean minSumRestriction) {
        this.minSumRestriction = minSumRestriction;
    }

    public boolean isScheduleRestriction() {
        return scheduleRestriction;
    }

    public void setScheduleRestriction(boolean scheduleRestriction) {
        this.scheduleRestriction = scheduleRestriction;
    }

    public DiscountAssignMode getDiscountAssignMode() {
        return discountAssignMode;
    }

    public void setDiscountAssignMode(DiscountAssignMode discountAssignMode) {
        this.discountAssignMode = discountAssignMode;
    }

    public boolean isManualDishSelect() {
        return manualDishSelect;
    }

    public void setManualDishSelect(boolean manualDishSelect) {
        this.manualDishSelect = manualDishSelect;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isDetailWhenPrinting() {
        return detailWhenPrinting;
    }

    public void setDetailWhenPrinting(boolean detailWhenPrinting) {
        this.detailWhenPrinting = detailWhenPrinting;
    }
}
