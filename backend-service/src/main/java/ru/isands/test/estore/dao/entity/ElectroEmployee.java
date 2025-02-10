package ru.isands.test.estore.dao.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@IdClass(ElectroEmployeePK.class)
@Table(name = "store_electroemployee")
public class ElectroEmployee {

    /**
     * Идентификатор сотрудника
     */
    @Id
    @Column(name = "employeeId", nullable = false)
    Long employeeId;

    /**
     * Идентификатор типа электроники
     */
    @Id
    @Column(name = "electroTypeId", nullable = false)
    @JsonProperty("electroTypeId")
    @JsonAlias("etype")
    Long electroTypeId;

}
