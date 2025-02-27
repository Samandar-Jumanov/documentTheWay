package com.dtw.repo;


import com.dtw.entity.Purchase;
import com.dtw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface PurchaseRepo  extends JpaRepository<Purchase , Long > {
    Stream<Purchase> findByUser(User user);
}
