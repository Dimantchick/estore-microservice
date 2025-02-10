package ru.isands.test.estore.dao.entity;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import ru.isands.test.estore.deserializer.MultiDateDeserializer;
import ru.isands.test.estore.deserializer.NumericBooleanDeserializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "store_employee")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Идентификатор сотрудника
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "employee_counter")
	@TableGenerator(name = "employee_counter", pkColumnName = "name", pkColumnValue = "ru.isands.test.entity.dao.ru.isands.test.estore.Employee", table = "counter", valueColumnName = "currentid", allocationSize = 2)
	@Column(name = "id_", unique = true, nullable = false)
	Long id;
	
	/**
	 * Фамилия сотрудника
	 */
	@Column(name = "lastname", nullable = false, length = 100)
	String lastName;
	
	/**
	 * Имя сотрудника
	 */
	@Column(name = "firstname", nullable = false, length = 100)
	String firstName;
	
	/**
	 * Отчество сотрудника
	 */
	@Column(name = "patronymic", nullable = false, length = 100)
	String patronymic;
	
	/**
	 * Дата рождения сотрудника
	 */
	@Column(name = "birthDate", nullable = false)
	@JsonDeserialize(using = MultiDateDeserializer.class)
	Date birthDate;
	
	/**
	 * Ссылка на должность сотрудника
	 */
	@Column(name = "positionId", nullable = false)
	@JsonProperty("positionId")
	@JsonAlias("position")
	Long positionId;

	/**
	 * Ссылка на магазин
	 */
	@Column(name = "shopId", nullable = false)
	Long shopId;

	/**
	 * Пол сотрудника (true - мужской, false - женский)
	 */
	@Column(name = "gender", nullable = false)
	@JsonDeserialize(using = NumericBooleanDeserializer.class)
	boolean gender;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Purchase> purchases;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "positionId", insertable = false, updatable = false)
	@JsonIgnore
	private PositionType positionType;

	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "store_electroemployee",
			joinColumns = @JoinColumn(name = "employeeId"),
			inverseJoinColumns = @JoinColumn(name = "electroTypeId"))
	@JsonIgnore
	private List<ElectroType> electroTypes;
}