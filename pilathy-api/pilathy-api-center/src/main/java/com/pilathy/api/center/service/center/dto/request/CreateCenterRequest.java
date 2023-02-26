package com.pilathy.api.center.service.center.dto.request;

import com.pilathy.domain.rds.domain.account.admin.Admin;
import com.pilathy.domain.rds.domain.center.Center;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCenterRequest {

    private String name;

    private String zipcode;

    private String defaultAddress;

    private String detailAddress;

    private String phone;

    private String img;

    private String description;

    @Builder
    private CreateCenterRequest(String name, String zipcode, String defaultAddress, String detailAddress, String phone, String img, String description) {
        this.name = name;
        this.zipcode = zipcode;
        this.defaultAddress = defaultAddress;
        this.detailAddress = detailAddress;
        this.phone = phone;
        this.img = img;
        this.description = description;
    }

    public Center toEntity(Admin admin) {
        return Center.of(admin, name, zipcode, defaultAddress, detailAddress, phone, img, defaultAddress);
    }
}
