package com.pilathy.api.center.service.center.dto.request;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateCenterRequest {

    private String name;

    private String zipcode;

    private String defaultAddress;

    private String detailAddress;

    private String phone;

    private String img;

    private String description;

    @Builder
    private UpdateCenterRequest(String name, String zipcode, String defaultAddress, String detailAddress, String phone, String img, String description) {
        this.name = name;
        this.zipcode = zipcode;
        this.defaultAddress = defaultAddress;
        this.detailAddress = detailAddress;
        this.phone = phone;
        this.img = img;
        this.description = description;
    }

}
