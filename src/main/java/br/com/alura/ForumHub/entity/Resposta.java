package br.com.alura.ForumHub.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "respostas")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensagem;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime criacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    public Resposta(Usuario autor, String mensagem, Topico topico) {
        this.autor = autor;
        this.mensagem = mensagem;
        this.topico = topico;
    }
}