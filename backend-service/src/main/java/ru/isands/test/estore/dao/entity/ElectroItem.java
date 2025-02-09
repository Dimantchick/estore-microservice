package ru.isands.test.estore.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import ru.isands.test.estore.deserializer.NumericBooleanDeserializer;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "store_eitem")
public class ElectroItem {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "eitem_counter")
    @TableGenerator(name = "eitem_counter", pkColumnName = "name", pkColumnValue = "ru.isands.test.entity.dao.ru.isands.test.estore.ElectroItem", table = "counter", valueColumnName = "currentid", allocationSize = 1)
    @Column(name = "id_", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;


    @Column(name = "etypeId", nullable = false)
    private Long etypeId;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "archive", nullable = false)
    @JsonDeserialize(using = NumericBooleanDeserializer.class)
    private boolean archive;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "electroItem", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Purchase> purchases;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etypeId", insertable = false, updatable = false)
    @JsonIgnore
    private ElectroType electroType;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "store_eshop",
            joinColumns = @JoinColumn(name = "electroItemId"),
            inverseJoinColumns = @JoinColumn(name = "shopId"))
    @JsonIgnore
    private List<Shop> shops;
}
