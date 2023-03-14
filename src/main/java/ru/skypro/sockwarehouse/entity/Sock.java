package ru.skypro.sockwarehouse.entity;

import jakarta.persistence.*;


import java.util.Objects;

/**
 * Sock - entity <br>
 * schema : <br>
 * id - id sock  <br>
 * color - color sock <br>
 * cottonPart - cotton part of sock <br>
 */

@Entity
@Table(name = "Socks")
public class Sock {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String color;
    private int cottonPart;

    public Sock() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sock sock = (Sock) o;
        return id == sock.id && cottonPart == sock.cottonPart && Objects.equals(color, sock.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, cottonPart);
    }

    @Override
    public String toString() {
        return "Sock{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", cottonPart=" + cottonPart +
                '}';
    }
}



