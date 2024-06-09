package team.project.drivee.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "vehicles")
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('vehicles_id_seq'::regclass)")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "reg_no", nullable = false, length = 10)
    private String regNo;

    @Column(name = "brand", nullable = false, length = 20)
    private String brand;

    @Column(name = "color", nullable = false, length = 20)
    private String color;

    @Column(name = "length", nullable = false, precision = 5, scale = 2)
    private BigDecimal length;

    @Column(name = "width", nullable = false, precision = 5, scale = 2)
    private BigDecimal width;

    @Column(name = "height", nullable = false, precision = 5, scale = 2)
    private BigDecimal height;

    @Column(name = "max_weight", nullable = false, precision = 5, scale = 2)
    private BigDecimal maxWeight;

    @OneToOne(mappedBy = "vehicle")
    private User user = new User();

//    public Vehicle(String regNo, String brand, String color, BigDecimal length,
//                   BigDecimal width, BigDecimal height, BigDecimal maxWeight) {
//        this.regNo = regNo;
//        this.brand = brand;
//        this.color = color;
//        this.length = length;
//        this.width = width;
//        this.height = height;
//        this.maxWeight = maxWeight;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(BigDecimal maxWeight) {
        this.maxWeight = maxWeight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}