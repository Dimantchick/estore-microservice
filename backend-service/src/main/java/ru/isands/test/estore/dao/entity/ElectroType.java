package ru.isands.test.estore.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "store_etype")
public class ElectroType {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "etype_counter")
    @TableGenerator(name = "etype_counter", pkColumnName = "name", pkColumnValue = "ru.isands.test.entity.dao.ru.isands.test.estore.ElectroType", table = "counter", valueColumnName = "currentid", allocationSize = 1)
    @Column(name = "id_", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @OneToMany(mappedBy = "electroType", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ElectroItem> electroItems;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "store_electroemployee",
            joinColumns = @JoinColumn(name = "electroTypeId"),
            inverseJoinColumns = @JoinColumn(name = "employeeId"))
    @JsonIgnore
    private List<Employee> employees;
}
