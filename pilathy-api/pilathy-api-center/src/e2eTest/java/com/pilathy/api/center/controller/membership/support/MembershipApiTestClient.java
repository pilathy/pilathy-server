package com.pilathy.api.center.controller.membership.support;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pilathy.api.center.MockMvcHelper;
import com.pilathy.api.center.service.membership.dto.request.MembershipRegisterRequest;
import com.pilathy.api.center.service.membership.dto.request.MembershipUpdateRequest;
import com.pilathy.api.center.service.membership.dto.response.MembershipResponse;
import com.pilathy.common.model.dto.response.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MembershipApiTestClient extends MockMvcHelper {

    public MembershipApiTestClient(MockMvc mockMvc, ObjectMapper objectMapper) {
        super(mockMvc, objectMapper);
    }

    public ApiResponse<MembershipResponse> registerMembership(MembershipRegisterRequest request, Long userId, Long centerId, int expectedStatus) throws Exception {
        MockHttpServletRequestBuilder builder = post("/v1/membership")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .param("userId", String.valueOf(userId))
                .param("centerId", String.valueOf(centerId));

        return objectMapper.readValue(
                mockMvc.perform(builder)
                        .andExpect(status().is(expectedStatus))
                        .andReturn()
                        .getResponse()
                        .getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {
                }
        );
    }

    public ApiResponse<MembershipResponse> updateMembership(MembershipUpdateRequest request, Long membershipId, int expectedStatus) throws Exception {
        MockHttpServletRequestBuilder builder = put("/v1/membership/" + membershipId)
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

    public ApiResponse<String> deleteMembership(Long membershipId, int expectedStatus) throws Exception {
        MockHttpServletRequestBuilder builder = delete("/v1/membership/" + membershipId);

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
