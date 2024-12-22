package in.ac.prsu.mca.mca2025_tictactoeapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;
import in.ac.prsu.mca.mca2025_tictactoeapi.pojo.CurrentGame;
import in.ac.prsu.mca.mca2025_tictactoeapi.pojo.GamePlayRequest;
import in.ac.prsu.mca.mca2025_tictactoeapi.pojo.NewGameRequest;
import in.ac.prsu.mca.mca2025_tictactoeapi.service.game.Play ;
import in.ac.prsu.mca.mca2025_tictactoeapi.service.validate.ValidationService;


@RestController	
@RequestMapping("/api/v1/tic-tac-toe")
@Log
public class GameController {
	
	private Play play ;
	
	ValidationService validate ;
	
	public GameController(Play play, ValidationService validate) {
		this.play = play ;
		this.validate = validate ;
	}

@PostMapping(value ={"/new","/new/"})
public ResponseEntity<CurrentGame> startNewGame(@RequestBody NewGameRequest gameRequest) {

	log.info("gameRequest : "+gameRequest) ;

	validate.isValidNewGameRequest(gameRequest) ;
	
	CurrentGame currentGame = play.newGamePlay(gameRequest);
	
	log.info("returning currentGame:"+currentGame) ;
	
    return new ResponseEntity<>(currentGame,HttpStatus.OK);
}


@PostMapping(value ={"/play","/play/"})
public ResponseEntity<CurrentGame>  gamePlay(@RequestBody GamePlayRequest playRequest) {
   
	log.info("playRequest : "+playRequest) ;

	validate.isValidPlayRequest(playRequest) ;
	
	CurrentGame currentGame = play.gamePlay(playRequest);
	
	log.info("returning currentGame:"+currentGame) ;
	
    return new ResponseEntity<>(currentGame,HttpStatus.OK);
}

	
}
















