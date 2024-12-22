package in.ac.prsu.mca.mca2025_tictactoeapi.service.game;

import in.ac.prsu.mca.mca2025_tictactoeapi.pojo.CurrentGame;
import in.ac.prsu.mca.mca2025_tictactoeapi.pojo.GamePlayRequest;
import in.ac.prsu.mca.mca2025_tictactoeapi.pojo.NewGameRequest;

public interface Play {
	public CurrentGame newGamePlay(NewGameRequest gameRequest)  ; 
	public CurrentGame gamePlay(GamePlayRequest playRequest) ;

}
