package br.com.alura.ForumHub.service;

import br.com.alura.ForumHub.dto.AtualizarUsuarioRequestDTO;
import br.com.alura.ForumHub.dto.UsuarioRequestDTO;
import br.com.alura.ForumHub.dto.UsuarioResponseDTO;
import br.com.alura.ForumHub.entity.Usuario;
import br.com.alura.ForumHub.infra.ValidacaoException;
import br.com.alura.ForumHub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO dto) {
        validarUsername(dto);
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.username());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setIdAlternativo();
        usuario.setCurso(dto.curso());
        var novoUsuario = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(novoUsuario);
    }

    public void deletar(Long id) {
        var usuario = procurarUsuarioId(id);
        usuarioRepository.delete(usuario);
    }

    public UsuarioResponseDTO atualizar(Long id, AtualizarUsuarioRequestDTO dto){
        var usuario = procurarUsuarioId(id);

        if (!usuario.getUsername().equals(dto.username()) &&
                usuarioRepository.existsByUsername(dto.username())) {
            throw new ValidacaoException("Já existe outro usuário com esse username.");
        }

        usuario.atualizarUsuario(dto);
        return new UsuarioResponseDTO(usuario);
    }

    public Page<UsuarioResponseDTO> listarUsuario(Pageable pageable) {
        return usuarioRepository.findAll(pageable)
                .map(UsuarioResponseDTO::new);
    }







    //Métodos auxiliares


    public Usuario obterUsuarioAutenticado(Usuario usuarioAutenticado) {
        return usuarioRepository.findById(usuarioAutenticado.getId())
                .orElseThrow(() -> new ValidacaoException("Usuário não encontrado"));
    }

    private Usuario procurarUsuarioId(Long id) {
        var usuarioEncontrado = usuarioRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Usuário não encontrado!"));
        return usuarioEncontrado;
    }

    private void validarUsername(UsuarioRequestDTO dto) {

        if (usuarioRepository.existsByUsername(dto.username())){
            throw new ValidacaoException("Já existe um cadastro com esse username.");
        }

    }




}