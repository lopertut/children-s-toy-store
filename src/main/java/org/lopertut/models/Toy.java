package org.lopertut.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Toy implements Serializable {
    private UUID id;
    private String name;
    private int price;
    private List<String> materials = new ArrayList<>();
    private int recommendedAge;

    public Toy() {
        this.id = UUID.randomUUID();
    }

    public Toy(String name, List<String> materials, int recommendedAge) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.materials = materials;
        this.recommendedAge = recommendedAge;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<String> getMaterials() {
        return materials;
    }

    public void setMaterials(List<String> materials) {
        this.materials = materials;
    }

    public int getRecommendedAge() {
        return recommendedAge;
    }

    public void setRecommendedAge(int recommendedAge) {
        this.recommendedAge = recommendedAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Toy toy = (Toy) o;
        return recommendedAge == toy.recommendedAge && id.equals(toy.id) && name.equals(toy.name) && Arrays.equals(materials.toArray(), toy.materials.toArray());
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + Arrays.hashCode(materials.toArray());
        result = 31 * result + recommendedAge;
        return result;
    }

    @Override
    public String toString() {
        return "Toy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", materials=" + Arrays.toString(materials.toArray()) +
                ", recommendedAge=" + recommendedAge +
                '}';
    }
}
