package tic_tac_toe.service.validate.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;
import tic_tac_toe.constants.Constants;
import tic_tac_toe.constants.ErrorEnum;
import tic_tac_toe.exception.TicTacToePlayerException;
import tic_tac_toe.pojo.GamePlayRequest;
import tic_tac_toe.pojo.NewGameRequest;
import tic_tac_toe.service.validate.Count;
import tic_tac_toe.service.validate.ValidationService;

@Service
@Log
public class ValidationServiceImpl implements ValidationService {


	@Override
	public void isValidNewGameRequest(NewGameRequest newGameRequest) {
		log.info("newGameRequest:"+newGameRequest);

		int position = newGameRequest.getPosition();

		if (newGameRequest.isStartByHuman()
			&& !(position >= 1 && position <= 9)) {

			throw new TicTacToePlayerException(
					ErrorEnum.INVALID_POSITION_RECEIVED.getErrorCode(),
					ErrorEnum.INVALID_POSITION_RECEIVED.getErrorMessage(),
					ErrorEnum.INVALID_POSITION_RECEIVED.getErrorDescription(),
					HttpStatus.BAD_REQUEST);
		}

	}	
	
	
	@Override
	public void isValidPlayRequest(GamePlayRequest gamePlayRequest) {

		log.info("gamePlayRequest:"+gamePlayRequest);

		
		int position = gamePlayRequest.getPosition();
		
		if (!(position >= 1 && position <= 9)) {
			TicTacToePlayerException ticTacToePlayerException = new TicTacToePlayerException(
					ErrorEnum.INVALID_POSITION_RECEIVED.getErrorCode(),
					ErrorEnum.INVALID_POSITION_RECEIVED.getErrorMessage(),
					ErrorEnum.INVALID_POSITION_RECEIVED.getErrorDescription(),
					HttpStatus.BAD_REQUEST);
			log.warning("occurred ex ticTacToePlayerException:"+ticTacToePlayerException);
			throw ticTacToePlayerException;
		}
		
		
		/*
		 * in a valid request if game is started by human is true then aiMarkCount will be equal to humanMarkCount 
		 * if not started by human then aiMarkCount will be 1 greater than humanMarkCount
		 */
		
		Count ct = getCount(gamePlayRequest.getGameState()) ;
		if(!gamePlayRequest.isStartedByHuman() 
				&& ( ct.getAiMarkCount() - 1 != ct.getHumanMarkCount() )){
			TicTacToePlayerException ticTacToePlayerException = new TicTacToePlayerException(
							ErrorEnum.INVALID_STATE_RECEIVED.getErrorCode(), 
							ErrorEnum.INVALID_STATE_RECEIVED.getErrorMessage(), 
							ErrorEnum.INVALID_STATE_RECEIVED.getErrorDescription(), 
							HttpStatus.BAD_REQUEST);
			log.warning("occurred ex ticTacToePlayerException:"+ticTacToePlayerException);

			throw ticTacToePlayerException ;
		}
		
		if(gamePlayRequest.isStartedByHuman() && ( ct.getAiMarkCount() != ct.getHumanMarkCount() )){
			TicTacToePlayerException ticTacToePlayerException = new TicTacToePlayerException(
					ErrorEnum.INVALID_STATE_RECEIVED.getErrorCode(), 
					ErrorEnum.INVALID_STATE_RECEIVED.getErrorMessage(), 
					ErrorEnum.INVALID_STATE_RECEIVED.getErrorDescription(), 
					HttpStatus.BAD_REQUEST);
			log.warning("occurred ex ticTacToePlayerException:"+ticTacToePlayerException);
			throw ticTacToePlayerException ;
		}

	}
	

	
	private Count getCount(final int[][] state) {
		
		int aiMarkCount = 0 ;
		int humanMarkCount = 0;
		
		for(int i = 0 ; i < 3 ; i++) {
			for (int j = 0 ; j < 3 ; j++) {
				
				if (state[i][j] == Constants.PLAYER_AI_MARK)
					aiMarkCount++ ;
				
				else if(state[i][j] == Constants.PLAYER_HUMAN_MARK)
					humanMarkCount++ ;
			}
		}
		
		Count count = new Count(aiMarkCount, humanMarkCount);
		log.info("returning count:"+count);
		return  count ;
		
	}

}










