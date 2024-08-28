package com.join.core.address.service;

import com.join.core.address.domain.Address;

public interface AddressReader {
    Address getAddressByLocation(String province, String city);

}
