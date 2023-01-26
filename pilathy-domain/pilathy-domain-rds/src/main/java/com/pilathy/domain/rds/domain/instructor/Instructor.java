package com.pilathy.domain.rds.domain.instructor;

import com.pilathy.domain.rds.domain.center.Center;
import com.pilathy.domain.rds.domain.common.BaseEntity;
import com.pilathy.domain.rds.domain.lesson.Lesson;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Instructor extends BaseEntity {

    @Column(nullable = false, length = 20)
    private String name;

    private String img;

    @Column(length = 15)
    private String phone;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private Center center;

    @OneToMany(mappedBy = "instructor")
    private List<Lesson> lessons = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private Instructor(String name, String img, String phone, String description, Center center) {
        this.name = name;
        this.img = img;
        this.phone = phone;
        this.description = description;
        this.center = center;
    }

    public static Instructor of (String name, String img, String phone, String description, Center center) {
        return Instructor.builder()
                .name(name)
                .img(img)
                .phone(phone)
                .description(description)
                .center(center)
                .build();
    }

}
