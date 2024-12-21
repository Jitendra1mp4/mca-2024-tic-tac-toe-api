package tic_tac_toe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.java.Log;
import tic_tac_toe.constants.ErrorEnum;
import tic_tac_toe.pojo.ErrorResponse;

@ControllerAdvice
@Log
public class TicTacToePlayerExceptionHandler {

	@ExceptionHandler(TicTacToePlayerException.class)
	public ResponseEntity<ErrorResponse> handleTicTacToePlayerException(TicTacToePlayerException ex) {
		
		log.warning("occured ex:"+ex);
		
		ErrorResponse errorResponse = new ErrorResponse(
				ex.getErrorCode(),
				ex.getErrorMessage(),
				ex.getErrorDescription()
				);
		
		ResponseEntity<ErrorResponse> responseEntity =
				new ResponseEntity<>(errorResponse,ex.getHttpStatusCode()) ;
		
		log.info("returning responseEntity:"+responseEntity);
		return responseEntity;
		
	}
	
	@ExceptionHandler(Exception.class)
public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
		
		log.warning("occured ex:"+ex);
		
		ErrorResponse errorResponse = new ErrorResponse(
				ErrorEnum.GENERIC_EXCEPTION.getErrorCode(),
				ErrorEnum.GENERIC_EXCEPTION.getErrorMessage(),
				ErrorEnum.GENERIC_EXCEPTION.getErrorDescription()
				);
		
		ResponseEntity<ErrorResponse> responseEntity =
				new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR) ;
		
		log.info("returning responseEntity:"+responseEntity);
		return responseEntity;
		
	}
	
	
}
