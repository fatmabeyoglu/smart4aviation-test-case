package com.smart4aviation.flighttestcase.dataModel;

public class ItemEntity {
    private long id;
    private long weight;
    private String weightUnit;
    private long pieces;

    public ItemEntity() {
    }

    public ItemEntity(long id, long weight, String weightUnit, long pieces) {
        this.id = id;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.pieces = pieces;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public long getPieces() {
        return pieces;
    }

    public void setPieces(long pieces) {
        this.pieces = pieces;
    }
}
