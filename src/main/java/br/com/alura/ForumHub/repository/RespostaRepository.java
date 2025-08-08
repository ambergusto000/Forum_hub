package br.com.alura.ForumHub.repository;

import br.com.alura.ForumHub.entity.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
}