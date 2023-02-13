package com.pilathy.domain.service.common.dto.response;

import com.pilathy.domain.rds.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AuditingTimeResponse {

    protected LocalDateTime createdAt;

    protected LocalDateTime updatedAt;

    protected void setAuditingTimeByEntity(BaseEntity baseEntity) {
        this.createdAt = baseEntity.getCreatedAt();
        this.updatedAt = baseEntity.getUpdatedAt();
    }

    protected void setAuditingTime(LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}