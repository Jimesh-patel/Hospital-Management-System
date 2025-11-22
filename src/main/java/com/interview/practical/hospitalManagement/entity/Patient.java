package com.interview.practical.hospitalManagement.entity;

import com.interview.practical.hospitalManagement.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(
//        name="patient_tbl",
        indexes = {
                @Index(name = "idx_patient_birth_date", columnList = "birthDate, email")
        }
)
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @Column(name="patient_name", length=100)
    private String name;

    @ToString.Exclude
    private LocalDate birthDate;

//    @Column(name = "patient_email", unique = true)
    private String email;

    private String gender;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;

    @CurrentTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

}
