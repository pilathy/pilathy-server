package com.pilathy.domain.rds.domain.common;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class Address {

    @Column(nullable = false, length = 7)
    private String zipcode;

    @Column(nullable = false, length = 20)
    private String defaultAddress;

    @Column(nullable = false, length = 30)
    private String detailAddress;

    @Builder(access = AccessLevel.PRIVATE)
    private Address(String zipcode, String defaultAddress, String detailAddress) {
        this.zipcode = zipcode;
        this.defaultAddress = defaultAddress;
        this.detailAddress = detailAddress;
    }

    public static Address of(String zipcode, String defaultAddress, String detailAddress) {
        return Address.builder()
                .zipcode(zipcode)
                .defaultAddress(defaultAddress)
                .detailAddress(detailAddress)
                .build();
    }

}
