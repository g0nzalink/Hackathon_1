package hackathon_1.CompanyRestrictions.domain;

import hackathon_1.Company.domain.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company_restrictions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRestrictions{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String modelType;

    @Column(nullable = false)
    private String modelId;

    @Column(nullable = false)
    private String modelName;

    @Column(nullable = false)
    private int maxRequestsPerTime;

    @Column(nullable = false)
    private int timeWindowInMinutes;

    @Column(nullable = false)
    private long maxTokensPerTime;

    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
