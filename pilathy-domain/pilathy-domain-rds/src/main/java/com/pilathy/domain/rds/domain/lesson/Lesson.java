package com.pilathy.domain.rds.domain.lesson;

import com.pilathy.domain.rds.domain.common.BaseEntity;
import com.pilathy.domain.rds.domain.instructor.Instructor;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Lesson extends BaseEntity {

    @Column(nullable = false, length = 30)
    private String name;

    private int registerCount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LessonType lessonType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Builder(access = AccessLevel.PRIVATE)
    private Lesson(String name, int registerCount, LessonType lessonType, Instructor instructor) {
        this.name = name;
        this.registerCount = registerCount;
        this.lessonType = lessonType;
        this.instructor = instructor;
    }

    public static Lesson of(String name, LessonType lessonType, Instructor instructor) {
        return Lesson.builder()
                .name(name)
                .registerCount(0)
                .lessonType(lessonType)
                .instructor(instructor)
                .build();
    }

}
