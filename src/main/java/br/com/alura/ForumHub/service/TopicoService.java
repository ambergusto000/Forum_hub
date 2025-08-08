package br.com.alura.ForumHub.service;


import br.com.alura.ForumHub.dto.TopicoAtualizacaoRequestDTO;
import br.com.alura.ForumHub.dto.TopicoRequestDTO;
import br.com.alura.ForumHub.dto.TopicoResponseDTO;
import br.com.alura.ForumHub.entity.Topico;
import br.com.alura.ForumHub.entity.Usuario;
import br.com.alura.ForumHub.infra.ValidacaoException;
import br.com.alura.ForumHub.repository.TopicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Cria um novo tópico no sistema.
     *
     * Etapas:
     *  Verifica se já existe um tópico com o mesmo título e mensagem para evitar duplicidade
     *  Recupera o usuário autenticado e persistido no banco de dados
     *  Cria a instância do tópico associando o autor e o curso
     *  Salva o tópico no banco de dados
     *  Retorna o tópico criado (com ID gerado)
     *
     *  dadosPostTopico DTO com os dados do novo tópico
     *  usuarioAutenticado Usuário autenticado, extraído do token JWT
     *  retorna o tópico criado e persistido
     *  retorna ValidacaoException se já existir um tópico igual
     */

    public TopicoResponseDTO criarTopico(TopicoRequestDTO topicoRequestDTO, @AuthenticationPrincipal Usuario usuarioAutenticado) {
        validarTopicoDuplicado(topicoRequestDTO);
        Usuario usuarioLogado = usuarioService.obterUsuarioAutenticado(usuarioAutenticado);

        Topico novoTopico = construirTopico(topicoRequestDTO, usuarioLogado);

        Topico topicoSalvo = topicoRepository.save(novoTopico);

        return new TopicoResponseDTO(topicoSalvo);

    }




    public TopicoResponseDTO atualizar(TopicoAtualizacaoRequestDTO dto){
        validarTopicoDuplicadoNoAtualizar(dto);

        Topico topicoEscolhido = procurarTopicoId(dto.id());

        topicoEscolhido.atualizarInformacoes(dto);

        return new TopicoResponseDTO(topicoEscolhido);
    }


    /**
     * Lista os tópicos de forma paginada.
     * Usa o Pageable para controlar página, tamanho e ordenação.
     * Atenção: o mét0do findTop10ByOrderByCriacaoAsc limita sempre a 10 resultados,
     * mesmo que o parâmetro 'size' seja maior.
     */
    public Page<TopicoResponseDTO> listar(Pageable paginacao) {
        Page<Topico> topicos = buscarTop10PorOrdemDeCriacao(paginacao);
        return topicos.map(TopicoResponseDTO::new);
    }



    public Page<TopicoResponseDTO> listarPorCurso(Pageable page, String curso) {
        Page<TopicoResponseDTO> cursosEncontrados = topicoRepository.findByCurso(curso, page).map(TopicoResponseDTO::new);
        return cursosEncontrados;
    }


    public TopicoResponseDTO marcarComoResolvido(Long id) {
        var topicoResolvido = procurarTopicoId(id);
        topicoResolvido.resolver();
        return new TopicoResponseDTO(topicoResolvido);
    }


    public TopicoResponseDTO detalharTopico(Long id) {
        var topicoEscolhido = procurarTopicoId(id);
        return new TopicoResponseDTO(topicoEscolhido);
    }


    public void deletarTopico(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Tópico não encontrado"));

        topicoRepository.delete(topico);
    }







    //Métodos auxiliares


    private void validarTopicoDuplicado(TopicoRequestDTO dto) {
        boolean existe = topicoRepository.existsByTituloAndMensagem(dto.titulo(), dto.mensagem());
        if (existe) {
            throw new ValidacaoException("Já existe um tópico com esse título e mensagem. Tente novamente!");
        }
    }


    private void validarTopicoDuplicadoNoAtualizar(TopicoAtualizacaoRequestDTO dto) {
        boolean existe = topicoRepository.existsByTituloAndMensagem(dto.titulo(), dto.mensagem());
        if (existe) {
            throw new ValidacaoException("Já existe um tópico com esse título e mensagem. Tente novamente!");
        }
    }




    private Topico construirTopico(TopicoRequestDTO dto, Usuario usuario) {
        return new Topico(
                dto.titulo(),
                dto.mensagem(),
                usuario,
                dto.curso());
    }

    private Topico procurarTopicoId(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Tópico não encontrado"));
        return topico;
    }

    private Page<Topico> buscarTop10PorOrdemDeCriacao(Pageable paginacao){
        return topicoRepository.findTop10ByOrderByCriacaoAsc(paginacao);

    }



}