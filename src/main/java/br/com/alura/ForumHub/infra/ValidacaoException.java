package br.com.alura.ForumHub.infra;

public class ValidacaoException extends RuntimeException {
    public ValidacaoException(String message) {
        super(message);
    }
}