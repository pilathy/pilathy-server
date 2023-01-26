package com.pilathy.domain.rds.domain.notice;

import com.pilathy.domain.rds.domain.center.Center;
import com.pilathy.domain.rds.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Notice extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private Center center;

    @Column(nullable = false, length = 50)
    private String title;

    @Lob
    @Column(nullable = false)
    private String description;

    @Builder(access = AccessLevel.PRIVATE)
    private Notice(Center center, String title, String description) {
        this.center = center;
        this.title = title;
        this.description = description;
    }

    public static Notice of(Center center, String title, String description) {
        return Notice.builder()
                .center(center)
                .title(title)
                .description(description)
                .build();
    }

}
