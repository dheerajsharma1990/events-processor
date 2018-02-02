package com.events.processor.persister.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Experiment implements Serializable {

    private int id;

    private String name;

    private Collection<Variant> variants = new ArrayList<>();

    public Experiment(int id) {
        this.id = id;
    }

    public Experiment(int id, String name, Collection<Variant> variants) {
        this.id = id;
        this.name = name;
        this.variants = variants;
    }

    public String getName() {
        return name;
    }

    public void addVariant(Variant variant) {
        this.variants.add(variant);
    }

    public int getId() {
        return id;
    }

    public Collection<Variant> getVariants() {
        return variants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Experiment that = (Experiment) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Experiment{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", variants=" + variants +
                '}';
    }
}
