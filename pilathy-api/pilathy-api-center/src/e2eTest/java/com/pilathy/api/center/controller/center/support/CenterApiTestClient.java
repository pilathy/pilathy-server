package com.pilathy.api.center.controller.center.support;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pilathy.api.center.MockMvcHelper;
import com.pilathy.api.center.service.center.dto.request.CreateCenterRequest;
import com.pilathy.api.center.service.center.dto.request.UpdateCenterRequest;
import com.pilathy.api.center.service.center.dto.response.CenterResponse;
import com.pilathy.common.model.dto.response.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CenterApiTestClient extends MockMvcHelper {

    public CenterApiTestClient(MockMvc mockMvc, ObjectMapper objectMapper) {
        super(mockMvc, objectMapper);
    }

    public ApiResponse<CenterResponse> createCenter(CreateCenterRequest request, Long adminId, int expectedStatus) throws Exception {
        MockHttpServletRequestBuilder builder = post("/v1/admin/" + adminId + "/center")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));

        return objectMapper.readValue(
                mockMvc.perform(builder)
                        .andExpect(status().is(expectedStatus))
                        .andReturn()
                        .getResponse()
                        .getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {
                }
        );
    }

    public ApiResponse<CenterResponse> updateCenter(UpdateCenterRequest request, Long centerId, int expectedStatus) throws Exception {
        MockHttpServletRequestBuilder builder = put("/v1/center/" + centerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));

        return objectMapper.readValue(
                mockMvc.perform(builder)
                        .andExpect(status().is(expectedStatus))
                        .andReturn()
                        .getResponse()
                        .getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {
                }
        );
    }

    public ApiResponse<String> deleteCenter(Long centerId, int expectedStatus) throws Exception {
        MockHttpServletRequestBuilder builder = delete("/v1/center/" + centerId);

        return objectMapper.readValue(
                mockMvc.perform(builder)
                        .andExpect(status().is(expectedStatus))
                        .andReturn()
                        .getResponse()
                        .getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {
                }
        );
    }

}
