package com.interview.practical.hospitalManagement.dto;

import com.interview.practical.hospitalManagement.entity.type.BloodGroupType;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PatientResponseDto {
    private Long id;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private BloodGroupType bloodGroup;
}
