package hackathon_1.AiRequest.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import hackathon_1.Usuario.domain.Usuario;

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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario user;

    @Column(nullable = false)
    private String modelType;

    @Column(nullable = false)
    private String modelName;

    @Column(nullable = false, length = 4000)
    private String queryText;

    @Column(length = 8000)
    private String responseText;

    @Column
    private String mediaFileName;
    @Column(nullable = false)
    private Integer inputTokens;

    @Column(nullable = false)
    private Integer outputTokens;

    @Column(nullable = false)
    private LocalDateTime requestTime;

    @Column(nullable = false)
    private boolean successful;

    @Column
    private String errorMessage;
}