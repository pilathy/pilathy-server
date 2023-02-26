package com.pilathy.api.center.controller.center.support;

import com.pilathy.api.center.service.center.dto.response.CenterResponse;
import com.pilathy.common.TestUtils;
import com.pilathy.domain.rds.domain.center.Center;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static org.assertj.core.api.Assertions.assertThat;

@TestUtils
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CenterTestUtils {

    public static void assertCenterResponse(CenterResponse response, Center center) {
        assertThat(center).isNotNull();
        assertCenterResponse(
                response,
                center.getName(),
                center.getAddress().getZipcode(),
                center.getAddress().getDefaultAddress(),
                center.getAddress().getDetailAddress(),
                center.getPhone(),
                center.getImg(),
                center.getDescription()
        );
        assertThat(response.getCenterId()).isEqualTo(center.getId());
        assertThat(response.getAdminId()).isEqualTo(center.getAdmin().getId());
    }

    public static void assertCenterResponse(CenterResponse response, String name, String zipcode, String defaultAddress, String detailAddress, String phone, String img, String description) {
            assertThat(response.getName()).isEqualTo(name);
            assertThat(response.getZipcode()).isEqualTo(zipcode);
            assertThat(response.getDefaultAddress()).isEqualTo(defaultAddress);
            assertThat(response.getDetailAddress()).isEqualTo(detailAddress);
            assertThat(response.getPhone()).isEqualTo(phone);
            assertThat(response.getImg()).isEqualTo(img);
            assertThat(response.getDescription()).isEqualTo(description);
    }

    public static void assertCenterDeleteResponse(Center center) {
        assertThat(center).isNull();
    }
}
