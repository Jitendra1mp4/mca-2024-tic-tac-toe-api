package in.ac.prsu.mca.mca2025_tictactoeapi.service.validate;

import in.ac.prsu.mca.mca2025_tictactoeapi.pojo.GamePlayRequest;
import in.ac.prsu.mca.mca2025_tictactoeapi.pojo.NewGameRequest;

public interface ValidationService {
	void isValidPlayRequest(GamePlayRequest gamePlayRequest) ;
	void isValidNewGameRequest(NewGameRequest newGameRequest);
}
