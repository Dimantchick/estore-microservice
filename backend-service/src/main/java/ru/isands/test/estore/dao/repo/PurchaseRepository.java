package ru.isands.test.estore.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.isands.test.estore.dao.entity.Purchase;

import java.util.Date;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query("select p from Purchase p where p.shopId = :shopId")
    public List<Purchase> findByShop(@Param("shopId") Long shopId);

    List<Purchase> findByEmployeeIdAndPurchaseDateAfter(Long employee, Date purchaseDate);

}
