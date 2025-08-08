package br.com.alura.ForumHub.repository;

import br.com.alura.ForumHub.entity.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByCurso(String curso, Pageable pageable);

    Page<Topico> findTop10ByOrderByCriacaoAsc(Pageable paginacao);

    boolean existsByTituloAndMensagem(String titulo, String mensagem);
}