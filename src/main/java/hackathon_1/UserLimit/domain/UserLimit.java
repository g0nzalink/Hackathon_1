package hackathon_1.UserLimit.domain;

import hackathon_1.User.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_limits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String modelType;

    @Column(nullable = false)
    private String modelId;

    @Column(nullable = false)
    private int maxRequestsPerTime;

    @Column(nullable = false)
    private int timeWindowInMinutes;

    @Column(nullable = false)
    private long maxTokensPerTime;

    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
