package ru.isands.test.estore.dao.entity;

import lombok.Getter;
import lombok.Setter;
import ru.isands.test.estore.dao.entity.ElectroShopPK;

import javax.persistence.*;

@Getter
@Setter
@Entity
@IdClass(ElectroShopPK.class)
@Table(name = "store_eshop")
public class ElectroShop {
	
	/**
	 * Идентификатор магазина
	 */
	@Id
	@Column(name = "shopId", nullable = false)
	Long shopId;
	
	/**
	 * Идентификатор электротовара
	 */
	@Id
	@Column(name = "electroItemId", nullable = false)
	Long electroItemId;
	
	/**
	 * Оставшееся количество
	 */
	@Column(name = "count", nullable = false)
	int count;

}
