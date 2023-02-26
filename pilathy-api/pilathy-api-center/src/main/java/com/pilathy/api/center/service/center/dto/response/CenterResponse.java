package com.pilathy.api.center.service.center.dto.response;

import com.pilathy.domain.rds.domain.center.Center;
import com.pilathy.domain.service.common.dto.response.AuditingTimeResponse;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CenterResponse extends AuditingTimeResponse {

    private Long centerId;

    private Long adminId;

    private String name;

    private String zipcode;

    private String defaultAddress;

    private String detailAddress;

    private String phone;

    private String img;

    private String description;

    @Builder
    private CenterResponse(Long centerId, Long adminId, String name, String zipcode, String defaultAddress, String detailAddress, String phone, String img, String description) {
        this.centerId = centerId;
        this.adminId = adminId;
        this.name = name;
        this.zipcode = zipcode;
        this.defaultAddress = defaultAddress;
        this.detailAddress = detailAddress;
        this.phone = phone;
        this.img = img;
        this.description = description;
    }

    public static CenterResponse of(Center center) {
        CenterResponse response = CenterResponse.builder()
                .centerId(center.getId())
                .adminId(center.getAdmin().getId())
                .name(center.getName())
                .zipcode(center.getAddress().getZipcode())
                .defaultAddress(center.getAddress().getDefaultAddress())
                .detailAddress(center.getAddress().getDetailAddress())
                .phone(center.getPhone())
                .img(center.getImg())
                .description(center.getDescription())
                .build();
        response.setAuditingTimeByEntity(center);
        return response;
    }

}
