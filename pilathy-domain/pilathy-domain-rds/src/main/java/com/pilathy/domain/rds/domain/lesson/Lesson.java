package com.pilathy.domain.rds.domain.lesson;

import com.pilathy.domain.rds.domain.common.BaseEntity;
import com.pilathy.domain.rds.domain.instructor.Instructor;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Lesson extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(mappedBy = "lesson")
    private List<Enrollment> enrollments = new ArrayList<>();
}
