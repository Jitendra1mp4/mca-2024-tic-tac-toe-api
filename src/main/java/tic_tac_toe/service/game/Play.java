package tic_tac_toe.service.game;

import tic_tac_toe.pojo.CurrentGame;
import tic_tac_toe.pojo.GamePlayRequest;
import tic_tac_toe.pojo.NewGameRequest;

public interface Play {
	public CurrentGame newGamePlay(NewGameRequest gameRequest)  ; 
	public CurrentGame gamePlay(GamePlayRequest playRequest) ;

}
