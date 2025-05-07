package hackathon_1.AIRequest.domain;

import hackathon_1.User.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ai_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AIRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String modelType;

    @Column(nullable = false)
    private String modelId;

    @Column(nullable = false)
    private String modelName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String queryText;

    @Column(columnDefinition = "TEXT")
    private String responseText;

    private String fileName; // Para solicitudes multimodales

    @Column(nullable = false)
    private long inputTokens;

    @Column(nullable = false)
    private long outputTokens;

    @Column(nullable = false)
    private long totalTokens;

    @Column(nullable = false)
    private boolean successful;

    private String errorMessage;

    @Column(nullable = false)
    private LocalDateTime requestTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    public void prePersist() {
        requestTime = LocalDateTime.now();
    }
}
