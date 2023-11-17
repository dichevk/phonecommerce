package com.phonecommerce.phonestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.phonecommerce.phonestore.model.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long>{
}
