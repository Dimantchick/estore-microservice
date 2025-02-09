package ru.isands.test.estore.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import ru.isands.test.estore.deserializer.MultiDateDeserializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "store_purchase")
public class Purchase implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Идентификатор покупки
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "purchase_counter")
	@TableGenerator(name = "purchase_counter", pkColumnName = "name", pkColumnValue = "ru.isands.test.entity.dao.ru.isands.test.estore.Purchase", table = "counter", valueColumnName = "currentid", allocationSize = 1)
	@Column(name = "id_", unique = true, nullable = false)
	Long id;
	
	/**
	 * Идентификатор товара
	 */
	@Column(name = "electroId", nullable = false)
	Long electroId;
	
	/**
	 * Идентификатор сотрудника
	 */
	@Column(name = "employeeId", nullable = false)
	Long employeeId;
	
	/**
	 * Идентификатор магазина
	 */
	@Column(name = "shopId", nullable = false)
	Long shopId;
	
	/**
	 * Дата совершения покупки
	 */
	@Column(name = "purchaseDate", nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@JsonDeserialize(using = MultiDateDeserializer.class)
	Date purchaseDate;
	
	/**
	 * Способ оплаты
	 */
	@Column(name = "typeId", nullable = false)
	int typeId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "electroId", insertable = false, updatable = false)
	@JsonIgnore
    private ElectroItem electroItem;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeId", insertable = false, updatable = false)
	@JsonIgnore
	private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "typeId", insertable = false, updatable = false)
	@JsonIgnore
	private PurchaseType purchaseType;

}