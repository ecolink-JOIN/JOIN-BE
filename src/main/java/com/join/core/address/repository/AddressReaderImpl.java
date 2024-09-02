package com.join.core.address.repository;

import com.join.core.address.domain.Address;
import com.join.core.address.service.AddressReader;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.InvalidSelectionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class AddressReaderImpl implements AddressReader {

    private final AddressRepository addressRepository;

    @Transactional(readOnly = true)
    @Override
    public Address getAddressByLocation(String province, String city) {
        return addressRepository.findByProvinceAndCity(province, city)
                .orElseThrow(() -> new InvalidSelectionException(ErrorCode.ADDRESS_SELECTION_REQUIRED));
    }

}
