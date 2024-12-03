package com.join.core.address.repository;

import com.join.core.address.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByProvinceAndCity(String province, String city);

}
