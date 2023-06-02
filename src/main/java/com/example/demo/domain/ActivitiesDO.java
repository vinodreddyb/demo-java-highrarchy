package com.example.demo.domain;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("Activities1")

public class ActivitiesDO {

    @Id
    private String id;
    private String name;

    private double weightage;

    private double unitWeightage;

    private String unit;

    private int sorQuantity;

    private int sorCost;


    private Date startDate;


    private Date finishDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeightage() {
        return weightage;
    }

    public void setWeightage(double weightage) {
        this.weightage = weightage;
    }

    public double getUnitWeightage() {
        return unitWeightage;
    }

    public void setUnitWeightage(double unitWeightage) {
        this.unitWeightage = unitWeightage;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSorQuantity() {
        return sorQuantity;
    }

    public void setSorQuantity(int sorQuantity) {
        this.sorQuantity = sorQuantity;
    }

    public int getSorCost() {
        return sorCost;
    }

    public void setSorCost(int sorCost) {
        this.sorCost = sorCost;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
}
