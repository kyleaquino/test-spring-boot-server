package com.mynt.api.model;

public class Parcel {
    public enum ParcelType {
        REJECT,
        HEAVY,
        SMALL,
        MEDIUM,
        LARGE
    }

    private double weight;
    private double height;
    private double width;
    private double length;

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public double getVolume() {
        return height * width * length;
    }

    public ParcelType getParcelType() {
        if (weight > 50) {
            return ParcelType.REJECT;
        } else if (weight > 10) {
            return ParcelType.HEAVY;
        } else if (getVolume() < 1500) {
            return ParcelType.SMALL;
        } else if (getVolume() < 2500) {
            return ParcelType.MEDIUM;
        } else {
            return ParcelType.LARGE;
        }
    }

    public double calculateDeliveryCost() {
        switch (this.getParcelType()) {
            case REJECT:
                return 0.0;
            case HEAVY:
                return 20 * weight;
            case SMALL:
                return 0.03 * getVolume();
            case MEDIUM:
                return 0.04 * getVolume();
            case LARGE:
                return 0.05 * getVolume();
            default:
                return 0.0;
        }
    }
}
