CREATE TABLE respostas (
                           id            BIGINT AUTO_INCREMENT PRIMARY KEY,
                           topico_id     BIGINT       NOT NULL,
                           autor_id      BIGINT       NOT NULL,
                           mensagem      TEXT         NOT NULL,
                           data_criacao  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           status        VARCHAR(20)  NOT NULL DEFAULT 'NAO_SOLUCIONADO',

                           CONSTRAINT fk_resposta_topico
                               FOREIGN KEY (topico_id)
                                   REFERENCES topicos(id)
                                   ON DELETE CASCADE,

                           CONSTRAINT fk_resposta_autor
                               FOREIGN KEY (autor_id)
                                   REFERENCES usuarios(id)
);


CREATE INDEX idx_respostas_topico   ON respostas(topico_id);
CREATE INDEX idx_respostas_autor    ON respostas(autor_id);