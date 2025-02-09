package ru.isands.test.estore.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ru.isands.test.estore.dao.entity.ElectroItem;
import ru.isands.test.estore.dao.entity.Employee;
import ru.isands.test.estore.dao.entity.Purchase;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "store_shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "shop_counter")
    @TableGenerator(name = "shop_counter", pkColumnName = "name", pkColumnValue = "ru.isands.test.entity.dao.ru.isands.test.estore.Shop", table = "counter", valueColumnName = "currentid", allocationSize = 1)
    @Column(name = "id_", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    private String address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "shopId")
    @JsonIgnore
    private List<Purchase> purchases;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "shopId")
    @JsonIgnore
    private List<Employee> employees;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "store_eshop",
            joinColumns = @JoinColumn(name = "shopId"),
            inverseJoinColumns = @JoinColumn(name = "electroItemId"))
    @JsonIgnore
    private List<ElectroItem> electroItems;
}
