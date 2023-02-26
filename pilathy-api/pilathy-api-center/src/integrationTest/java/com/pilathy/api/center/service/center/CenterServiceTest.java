package com.pilathy.api.center.service.center;

import com.pilathy.api.center.PreparedCenterIntegrationTest;
import com.pilathy.api.center.service.center.dto.request.CreateCenterRequest;
import com.pilathy.api.center.service.center.dto.request.UpdateCenterRequest;
import com.pilathy.api.center.service.center.dto.response.CenterResponse;
import com.pilathy.api.center.service.center.support.CenterTestUtils;
import com.pilathy.common.exception.ErrorCode;
import com.pilathy.common.exception.model.NotFoundException;
import com.pilathy.domain.rds.domain.center.Center;
import com.pilathy.domain.rds.domain.center.CenterFixture;
import com.pilathy.domain.rds.domain.center.CenterRepository;
import com.pilathy.domain.service.service.center.CenterServiceHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.pilathy.common.lib.RandomGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CenterServiceTest extends PreparedCenterIntegrationTest {

    @Autowired
    CenterService centerService;

    @Autowired
    CenterRepository centerRepository;

    @DisplayName("Center 생성 테스트")
    @Nested
    class CreateCenterTest {

        @Test
        void 성공() {
            //given
            CreateCenterRequest request = CreateCenterRequest.builder()
                    .name("center" + generateString())
                    .zipcode(generateString(10000, 99999))
                    .defaultAddress("Seoul")
                    .detailAddress("Somewhere")
                    .phone(generatePhone())
                    .img("img" + generateString())
                    .description("description" + generateString())
                    .build();

            //when
            CenterResponse response = centerService.createCenter(request, admin.getId());

            //then
            Center findCenter = centerRepository.findCenterById(response.getCenterId());
            assertThat(findCenter).isNotNull();
            CenterTestUtils.assertCenterResponse(response, findCenter);
        }

        @Test
        void 실패_잘못된_adminId() {
            //given
            CreateCenterRequest request = CreateCenterRequest.builder()
                    .name("center" + generateString())
                    .zipcode(generateString(10000, 99999))
                    .defaultAddress("Seoul")
                    .detailAddress("Somewhere")
                    .phone(generatePhone())
                    .img("img" + generateString())
                    .description("description" + generateString())
                    .build();

            //when & then
            NotFoundException exception = assertThrows(NotFoundException.class, () -> centerService.createCenter(request, admin.getId() + generateLong()));
            assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.E404_NOT_EXISTS_ADMIN);
        }

    }

    @DisplayName("Center 업데이트 테스트")
    @Nested
    class UpdateCenterTest {

        @Test
        void 성공() {
            //given
            Center center = CenterFixture.create(admin);
            centerRepository.save(center);

            UpdateCenterRequest request = UpdateCenterRequest.builder()
                    .name("updatedName")
                    .zipcode("12345")
                    .defaultAddress("Gyeongi-do")
                    .detailAddress("CenterOne")
                    .img("updatedImg")
                    .phone("01012345678")
                    .description("updatedDescription")
                    .build();

            //when
            CenterResponse response = centerService.updateCenter(request, center.getId());

            //then
            Center updatedCenter = centerRepository.findCenterById(response.getCenterId());
            CenterTestUtils.assertCenterResponse(response, updatedCenter);
        }

        @Test
        void 실패_잘못된_centerId() {
            //given
            Center center = CenterFixture.create(admin);
            centerRepository.save(center);

            UpdateCenterRequest request = UpdateCenterRequest.builder()
                    .name("updatedName")
                    .zipcode("12345")
                    .defaultAddress("Gyeongi-do")
                    .detailAddress("CenterOne")
                    .img("updatedImg")
                    .phone("01012345678")
                    .description("updatedDescription")
                    .build();

            //when & then
            NotFoundException exception = assertThrows(NotFoundException.class, () -> centerService.updateCenter(request, center.getId() + generateLong()));
            assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.E404_NOT_EXISTS_CENTER);
        }

    }

    @DisplayName("Center 삭제 테스트")
    @Nested
    class DeleteCenterTest {

        @Test
        void 성공() {
            //given
            centerService.deleteCenter(center.getId());

            //when & then
            NotFoundException exception = assertThrows(NotFoundException.class, () -> CenterServiceHelper.findCenterById(centerRepository, center.getId()));
            assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.E404_NOT_EXISTS_CENTER);
        }

        @Test
        void 실패_잘못된_centerId() {
            //given
            Long centerId = center.getId() + generateLong();

            //when & then
            NotFoundException exception = assertThrows(NotFoundException.class, () -> centerService.deleteCenter(centerId));
            assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.E404_NOT_EXISTS_CENTER);
        }

    }

}