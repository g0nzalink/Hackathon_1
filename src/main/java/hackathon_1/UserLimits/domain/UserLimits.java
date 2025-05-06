package hackathon_1.UserLimits.domain;

import hackathon_1.Usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_limits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLimits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Usuario user;

    @Column(nullable = false)
    private String modelType;

    @Column(nullable = false)
    private String modelName;

    @Column(nullable = false)
    private Integer maxRequestsPerWindow;

    @Column(nullable = false)
    private Integer maxTokensPerWindow;

    @Column(nullable = false)
    private Integer windowMinutes;

    @Column(nullable = false)
    private boolean active;
}
