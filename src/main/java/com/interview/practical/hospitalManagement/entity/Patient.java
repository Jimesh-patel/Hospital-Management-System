package com.interview.practical.hospitalManagement.entity;

import com.interview.practical.hospitalManagement.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@ToString
@Getter
@Setter
@Table(
        name = "patient",
        uniqueConstraints = {
//                @UniqueConstraint(name = "unique_patient_email", columnNames = {"email"}),
                @UniqueConstraint(name = "unique_patient_name_birthdate", columnNames = {"name", "birthDate"})
        },
        indexes = {
                @Index(name = "idx_patient_birth_date", columnList = "birthDate")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    private User user;

    @Column(nullable = false, length = 40)
    private String name;

    //    @ToString.Exclude
    private LocalDate birthDate;

    @Column(unique = true, nullable = false)
    private String email;

    private String gender;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;

    @OneToOne(cascade = { CascadeType.ALL }, orphanRemoval = true )
    @JoinColumn(name = "insurance_id")  // Owning Side
    @ToString.Exclude
    private Insurance insurance;

    @OneToMany(mappedBy = "patient", cascade = {CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.EAGER) // Inverse side
//    @ToString.Exclude
    private List<Appointment> appointments;
}




/*
1.  Use of @Builder

    User user = User.builder()
            .id(1L)
            .name("Jimesh")
            .email("test@gmail.com")
            .build();

    User user = new User(1L, "Jimesh", "test@gmail.com");

2.  FetchType.EAGER  ---> with Patient fetch all his appointments immediately.
    FetchType.LAZY   ---> with Patient, his appointments are not required immediately.

3.  orphaRemoval = true
    patient.getAppointment.remove(appointment)
    child without parent -> orphan (delete auto)
    orphaRemoval   --> delete that not reference by its parent even if parent is in DB
    Cascade.REMOVE --> delete child when parent delete

4.  CascadeType.PERSIST  ---> Create
    CascadeType.MERGE    ---> Update
    CascadeType.REMOVE   ---> Delete
    CascadeType.ALL      ---> Create + update + delete
 */
