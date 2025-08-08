package br.com.alura.ForumHub.entity;

import br.com.alura.ForumHub.dto.TopicoAtualizacaoRequestDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "topicos",
        uniqueConstraints = @UniqueConstraint(columnNames = {"titulo", "mensagem"})
)
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Corrigido: "Id" para "id"

    @NotNull
    private String titulo;

    @NotNull
    private String mensagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    private String curso;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime criacao = LocalDateTime.now();

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resposta> resposta = new ArrayList<>();
    // Corrigido: mappedBy = "topico" (não "topicos"), deve bater com o nome do campo na classe Resposta

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTopico status = StatusTopico.NAO_RESOLVIDO;

    public Topico(String titulo, String mensagem, Usuario autor, String curso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.autor = autor;
        this.curso =  curso;
        this.criacao = LocalDateTime.now();
    }

    public void setResposta(List<Resposta> resposta){
        resposta.forEach(e -> e.setTopico(this)); // Corrigido: setTopico
        this.resposta = resposta;
    }

    public void resolver() {
        this.status = StatusTopico.RESOLVIDO;
    }

    public void atualizarInformacoes(@Valid TopicoAtualizacaoRequestDTO dto) {
        if (dto.titulo() != null) {
            this.titulo = dto.titulo();
        }
        if (dto.mensagem() != null) {
            this.mensagem = dto.mensagem();
        }
        if (dto.curso() != null) {
            this.curso = dto.curso();
        }
    }
}