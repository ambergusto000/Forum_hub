ALTER TABLE topicos
    CHANGE status status_tmp VARCHAR(15) NOT NULL;


UPDATE topicos
SET status_tmp = CASE
                     WHEN status_tmp IN ('1', 'true', 'TRUE')  THEN 'NAO_RESOLVIDO'
                     WHEN status_tmp IN ('0', 'false', 'FALSE') THEN 'RESOLVIDO'
                     ELSE 'NAO_RESOLVIDO'
    END;


ALTER TABLE topicos
    MODIFY status_tmp ENUM('NAO_RESOLVIDO','RESOLVIDO') NOT NULL
    DEFAULT 'NAO_RESOLVIDO';


ALTER TABLE topicos
    CHANGE status_tmp status ENUM('NAO_RESOLVIDO','RESOLVIDO') NOT NULL
    DEFAULT 'NAO_RESOLVIDO';