package br.com.alura.ForumHub.entity;

import br.com.alura.ForumHub.dto.AtualizarUsuarioRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String senha;

    private String curso;

    private String idAlternativo;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Topico> topicos = new ArrayList<>();
    // mappedBy = "autor" - o campo que relaciona usuario e topico na entidade Topico

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }

    public void atualizarUsuario(AtualizarUsuarioRequestDTO dto) {
        if (dto.username() != null) {
            this.username = dto.username();
        }
        if (dto.senha() != null) {
            this.senha = dto.senha();
        }
    }

    public void setIdAlternativo() {
        UUID uuid = UUID.randomUUID();
        this.idAlternativo = uuid.toString();
    }

}