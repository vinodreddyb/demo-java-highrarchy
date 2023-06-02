package com.example.demo.domain;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("Activities")

public class Activities {
    @CsvBindByPosition(position = 0)
    private String name;
    @CsvBindByPosition(position = 1)
    private double weightage;
    @CsvBindByPosition(position = 2)
    private double unitWeightage;
    @CsvBindByPosition(position = 3)
    private String unit;
    @CsvBindByPosition(position = 4)
    private String sorQuantity;
    @CsvBindByPosition(position = 5)
    private String sorCost;
    @CsvBindByPosition(position = 6)
    @CsvDate(value = "dd-MMM-yy")
    private Date startDate;
    @CsvDate(value = "dd-MMM-yy")
    @CsvBindByPosition(position = 7)
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

    public String getSorQuantity() {
        return sorQuantity;
    }

    public void setSorQuantity(String sorQuantity) {
        this.sorQuantity = sorQuantity;
    }

    public String getSorCost() {
        return sorCost;
    }

    public void setSorCost(String sorCost) {
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
