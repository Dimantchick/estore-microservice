package ru.isands.test.estore.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "store_purchasetype")
public class PurchaseType {

    /**
     * Идентификатор типа покупки
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "purchasetype_counter")
    @TableGenerator(name = "purchasetype_counter", pkColumnName = "name", pkColumnValue = "ru.isands.test.entity.dao.ru.isands.test.estore.PurchaseType", table = "counter", valueColumnName = "currentid", allocationSize = 1)
    @Column(name = "id_", unique = true, nullable = false)
    private Long id;

    /**
     * Наименование типа покупки
     */
    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @OneToMany(mappedBy = "purchaseType", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Purchase> purchases;
}
