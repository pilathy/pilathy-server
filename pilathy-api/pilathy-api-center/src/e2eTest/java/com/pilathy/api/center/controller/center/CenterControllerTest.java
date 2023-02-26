package com.pilathy.api.center.controller.center;

import com.pilathy.api.center.PreparedCenterControllerTest;
import com.pilathy.api.center.controller.center.support.CenterApiTestClient;
import com.pilathy.api.center.controller.center.support.CenterTestUtils;
import com.pilathy.api.center.service.center.dto.request.CreateCenterRequest;
import com.pilathy.api.center.service.center.dto.request.UpdateCenterRequest;
import com.pilathy.api.center.service.center.dto.response.CenterResponse;
import com.pilathy.common.exception.ErrorCode;
import com.pilathy.common.exception.model.NotFoundException;
import com.pilathy.common.model.dto.response.ApiResponse;
import com.pilathy.domain.rds.domain.center.Center;
import com.pilathy.domain.rds.domain.center.CenterRepository;
import com.pilathy.domain.service.service.center.CenterServiceHelper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static com.pilathy.common.lib.RandomGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CenterControllerTest extends PreparedCenterControllerTest {

    private CenterApiTestClient centerApiTestClient;

    @Autowired
    CenterRepository centerRepository;

    @BeforeEach
    void setUp() {
        centerApiTestClient = new CenterApiTestClient(mockMvc, objectMapper);
    }

    @DisplayName("POST /v1/admin/{adminId}/center : Center 생성 테스트")
    @Nested
    class CreateCenterApiTest {

        @Test
        void 성공() throws Exception {
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
            ApiResponse<CenterResponse> response = centerApiTestClient.createCenter(request, admin.getId(), HttpStatus.OK.value());

            //then
            Center findCenter = assertDoesNotThrow(() -> CenterServiceHelper.findCenterById(centerRepository, response.getData().getCenterId()));
            CenterTestUtils.assertCenterResponse(response.getData(), findCenter);
        }

        @Test
        void 실패_잘못된_adminId() throws Exception {
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
            ApiResponse<CenterResponse> response = centerApiTestClient.createCenter(request, admin.getId() + generateLong(), HttpStatus.NOT_FOUND.value());

            //then
            assertThat(response.getResultCode()).isEqualTo(ErrorCode.E404_NOT_EXISTS_ADMIN.getCode());
            assertThat(response.getMessage()).isEqualTo(ErrorCode.E404_NOT_EXISTS_ADMIN.getMessage());
        }

    }

    @DisplayName("PUT /v1/center : Center 업데이트 테스트")
    @Nested
    class UpdateCenterApiTest {

        @Test
        void 성공() throws Exception {
            //given
            UpdateCenterRequest request = UpdateCenterRequest.builder()
                    .name("center" + generateString())
                    .zipcode(generateString(10000, 99999))
                    .defaultAddress("Seoul")
                    .detailAddress("Somewhere")
                    .phone(generatePhone())
                    .img("img" + generateString())
                    .description("description" + generateString())
                    .build();

            //when
            ApiResponse<CenterResponse> response = centerApiTestClient.updateCenter(request, center.getId(), HttpStatus.OK.value());

            //then
            Center findCenter = assertDoesNotThrow(() -> CenterServiceHelper.findCenterById(centerRepository, response.getData().getCenterId()));
            CenterTestUtils.assertCenterResponse(response.getData(), findCenter);
        }

        @Test
        void 실패_잘못된_centerId() throws Exception {
            //given
            UpdateCenterRequest request = UpdateCenterRequest.builder()
                    .name("center" + generateString())
                    .zipcode(generateString(10000, 99999))
                    .defaultAddress("Seoul")
                    .detailAddress("Somewhere")
                    .phone(generatePhone())
                    .img("img" + generateString())
                    .description("description" + generateString())
                    .build();

            //when
            ApiResponse<CenterResponse> response = centerApiTestClient.updateCenter(request, center.getId() + generateLong(), HttpStatus.NOT_FOUND.value());

            //then
            assertThat(response.getResultCode()).isEqualTo(ErrorCode.E404_NOT_EXISTS_CENTER.getCode());
            assertThat(response.getMessage()).isEqualTo(ErrorCode.E404_NOT_EXISTS_CENTER.getMessage());
        }

    }

    @DisplayName("DELETE /v1/center/{centerId} : Center 삭제 테스트")
    @Nested
    class DeleteCenterApiTest {

        @Test
        void 성공() throws Exception {
            //given
            Long centerId = center.getId();

            //when
            ApiResponse<String> response = centerApiTestClient.deleteCenter(centerId, HttpStatus.OK.value());

            //then
            assertThat(response.getResultCode()).isEqualTo("success");
            assertThat(response.getMessage()).isEqualTo("성공입니다");

            NotFoundException exception = assertThrows(NotFoundException.class, () -> CenterServiceHelper.findCenterById(centerRepository, centerId));
            assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.E404_NOT_EXISTS_CENTER);
        }

        @Test
        void 실패_잘못된_centerId() throws Exception {
            //given
            Long centerId = center.getId() + generateLong();

            //when
            ApiResponse<String> response = centerApiTestClient.deleteCenter(centerId, HttpStatus.NOT_FOUND.value());

            //then
            assertThat(response.getResultCode()).isEqualTo(ErrorCode.E404_NOT_EXISTS_CENTER.getCode());
            assertThat(response.getMessage()).isEqualTo(ErrorCode.E404_NOT_EXISTS_CENTER.getMessage());
        }

    }


}
