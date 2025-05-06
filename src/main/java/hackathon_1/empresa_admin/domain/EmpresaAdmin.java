package hackathon_1.empresa_admin.domain;


import hackathon_1.empresa.domain.Empresa;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

@Entity
@Table(name = "company_admins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaAdmin extends User{
    @OneToOne
    @JoinColumn(name = "admin_company_id",unique = true)
    private Empresa empresa;

    public EmpresaAdmin(User user, Empresa empresa){
        super(user.getId(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getPassword(), user.getCompany(),
                user.getLimits(), user.getRequests(), user.getRoles());
        this.empresa = empresa;
        this.getRoles().add("ROLE_COMPANY_ADMIN")
    }
}
