package org.lopertut.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Purchase implements Serializable {
    private UUID id;
    private User user;
    private Toy toy;
    private LocalDate purchaseDate;

    public Purchase() {
        this.id = UUID.randomUUID();
    }

    public Purchase(User user, Toy toy, LocalDate purchaseDate) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.toy = toy;
        this.purchaseDate = purchaseDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Toy getToy() {
        return toy;
    }

    public void setToy(Toy toy) {
        this.toy = toy;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(id, purchase.id) && Objects.equals(user, purchase.user) && Objects.equals(toy, purchase.toy) && Objects.equals(purchaseDate, purchase.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, toy, purchaseDate);
    }

    @Override
    public String toString() {
        return String.format("Покупка: %s, Товар: %s, Дата: %s",
                user.getFirstname() + " " + user.getLastname(),
                toy.getName(),
                purchaseDate);
    }
}
