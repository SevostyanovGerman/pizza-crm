package com.pizza.crm.model;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "validity_schedule")
public class ValiditySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalTime beginTime;

    private LocalTime endTime;

    @ElementCollection(targetClass = DayOfWeek.class)
    @CollectionTable(name = "validity_schedule_DayOfWeek",
            joinColumns = @JoinColumn(name = "validity_schedule_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "DayOfWeek_id")
    private List<DayOfWeek> dayOfWeekList;

    public ValiditySchedule(LocalTime beginTime, LocalTime endTime) {
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public ValiditySchedule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public List<DayOfWeek> getDayOfWeekList() {
        return dayOfWeekList;
    }

    public void setDayOfWeekList(List<DayOfWeek> dayOfWeekList) {
        this.dayOfWeekList = dayOfWeekList;
    }
}
