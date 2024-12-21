package tic_tac_toe.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class TicTacToePlayerException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final String errorCode ;
	private final String errorMessage ;
	private final String errorDescription ;
	private final HttpStatus httpStatusCode ;
}
