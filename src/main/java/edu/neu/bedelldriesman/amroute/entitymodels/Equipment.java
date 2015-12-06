package edu.neu.bedelldriesman.amroute.entitymodels;

import java.util.Objects;

/**
 * Created by Joshua Driesman on 11/16/2015.
 *
 * Copyright 2015 Joshua Driesman, All rights reserved.
 */
public class Equipment {
    private int equipmentId;
    private String series;
    private String configuration;

    public Equipment(int equipmentId, String series, String configuration) {
        Objects.requireNonNull(series);
        Objects.requireNonNull(configuration);

        this.equipmentId = equipmentId;
        this.series = series;
        this.configuration = configuration;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        Objects.requireNonNull(series);

        this.series = series;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        Objects.requireNonNull(configuration);

        this.configuration = configuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Equipment equipment = (Equipment) o;

        if (equipmentId != equipment.equipmentId) return false;
        if (!series.equals(equipment.series)) return false;
        return configuration.equals(equipment.configuration);

    }

    @Override
    public int hashCode() {
        int result = equipmentId;
        result = 31 * result + series.hashCode();
        result = 31 * result + configuration.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentId=" + equipmentId +
                ", series='" + series + '\'' +
                ", configuration='" + configuration + '\'' +
                '}';
    }
}
