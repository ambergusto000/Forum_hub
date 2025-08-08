package br.com.alura.ForumHub.service;

import br.com.alura.ForumHub.dto.RespostaResponseDTO;
import br.com.alura.ForumHub.entity.Resposta;
import br.com.alura.ForumHub.entity.Topico;
import br.com.alura.ForumHub.entity.Usuario;
import br.com.alura.ForumHub.infra.ValidacaoException;
import br.com.alura.ForumHub.repository.RespostaRepository;
import br.com.alura.ForumHub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RespostaService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private RespostaRepository respostaRepository;

    @Transactional
    public RespostaResponseDTO criar(Long topicoId, Usuario autor, String mensagem ) {
        Topico topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new ValidacaoException("tópico não encontrado"));


        Resposta resposta = new Resposta(autor, mensagem, topico);
        respostaRepository.save(resposta);

        return new RespostaResponseDTO(
                resposta.getId(),
                resposta.getMensagem(),
                autor.getUsername(),
                resposta.getCriacao()
        );

    }


    public void deletar(Long id) {
        var resposta = respostaRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Erro ao deletar!"));
        respostaRepository.delete(resposta);
    }
}