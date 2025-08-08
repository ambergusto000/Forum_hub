CREATE TABLE IF NOT EXISTS respostas (
                                         id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                         mensagem TEXT NOT NULL,
                                         criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         topico_id BIGINT NOT NULL,
                                         autor_id BIGINT NOT NULL,
                                         CONSTRAINT fk_resposta_topico FOREIGN KEY (topico_id) REFERENCES topicos(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_resposta_autor FOREIGN KEY (autor_id) REFERENCES usuarios(id) ON DELETE CASCADE ON UPDATE CASCADE
    );


CREATE INDEX idx_respostas_topico_id ON respostas (topico_id);
CREATE INDEX idx_respostas_autor_id ON respostas (autor_id);