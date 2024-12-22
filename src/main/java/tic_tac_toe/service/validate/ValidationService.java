package tic_tac_toe.service.validate;

import tic_tac_toe.pojo.GamePlayRequest;
import tic_tac_toe.pojo.NewGameRequest;

public interface ValidationService {
	void isValidPlayRequest(GamePlayRequest gamePlayRequest) ;
	void isValidNewGameRequest(NewGameRequest newGameRequest);
}
