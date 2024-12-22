package in.ac.prsu.mca.mca2025_tictactoeapi.exception;

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
