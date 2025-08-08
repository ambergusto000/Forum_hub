package  br.com.alura.ForumHub.infra;

import br.com.alura.ForumHub.entity.Usuario;
import br.com.alura.ForumHub.repository.UsuarioRepository;
import br.com.alura.ForumHub.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);

        if(tokenJWT != null){
            var subject = tokenService.getSubject(tokenJWT);
            System.out.println("Filtro JWT executado — subject: " + subject);

            var optionalUsuario = usuarioRepository.findByUsername(subject);
            System.out.println("usuario encontrado ? " + (optionalUsuario != null));

            Usuario user = optionalUsuario.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            if (optionalUsuario.isPresent()) {
                Usuario usuario = optionalUsuario.get();

                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                System.out.println("Usuário não encontrado no banco: " + subject);
            }
        }

        filterChain.doFilter(request, response);

    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;

    }
}