package edu.neu.bedelldriesman.amroute.entitybeans;

import java.util.Objects;

/**
 * Created by Joshua Driesman on 11/16/2015.
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
}
