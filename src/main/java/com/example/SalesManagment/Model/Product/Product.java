package com.example.SalesManagment.Model.Product;

import com.example.SalesManagment.Model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Product")
public class    Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productID;
    private double price;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "productList", cascade = CascadeType.ALL)
    private Set<User> userList = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoriesID", referencedColumnName = "categoriesID")
    private Categories categories;

    public Product(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public Product() {

    }


    public void setUserList(Set<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + productID +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
