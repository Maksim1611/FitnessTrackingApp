package app.user.model;

import app.workout.model.Workout;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Size(min = 3)
    @Column(unique = true, nullable = false)
    private String name;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column
    @Size(min = 8)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String imageUrl;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    @Column(nullable = false)
    private LocalDateTime updatedOn;

    @OneToMany(mappedBy = "user")
    private List<Workout> workouts;

    @Column
    private int followers;

    @Column
    private int following;
}
