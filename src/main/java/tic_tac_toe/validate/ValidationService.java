package tic_tac_toe.validate;

import tic_tac_toe.pojo.GamePlayRequest;
import tic_tac_toe.pojo.NewGameRequest;

public interface ValidationService {
	void isValidPlayRequest(GamePlayRequest gamePlayRequest) ;
	void isValidNewGameRequest(NewGameRequest newGameRequest);
}
