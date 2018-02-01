package com.events.processor.persister.domain;

import java.io.Serializable;

public class Variant implements Serializable {

    private int experimentId;

    private int variantNumber;

    private long visitors;

    public Variant(int experimentId, int variantNumber) {
        this.experimentId = experimentId;
        this.variantNumber = variantNumber;
    }

    public Variant(int experimentId, int variantNumber, long visitors) {
        this.experimentId = experimentId;
        this.variantNumber = variantNumber;
        this.visitors = visitors;
    }

    public long getVisitors() {
        return visitors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Variant variant = (Variant) o;

        if (experimentId != variant.experimentId) return false;
        return variantNumber == variant.variantNumber;

    }

    @Override
    public int hashCode() {
        int result = experimentId;
        result = 31 * result + variantNumber;
        return result;
    }

    @Override
    public String toString() {
        return "Variant{" +
                "experimentId='" + experimentId + '\'' +
                ", variantNumber=" + variantNumber +
                ", visitors=" + visitors +
                '}';
    }
}
