package tic_tac_toe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;
import tic_tac_toe.pojo.CurrentGame;
import tic_tac_toe.pojo.GamePlayRequest;
import tic_tac_toe.pojo.NewGameRequest;
import tic_tac_toe.service.game.Play ;


@RestController	
@RequestMapping("/api/v1/tic-tac-toe")
@Log
public class GameController {
	
	@Autowired
	private Play play ;

@PostMapping(value ={"/new","/new/"})
public ResponseEntity<CurrentGame> startNewGame(@RequestBody NewGameRequest gameRequest) {

	log.info("gameRequest : "+gameRequest) ;

	CurrentGame currentGame = play.newGamePlay(gameRequest);
	
	log.info("returning currentGame:"+currentGame) ;
	
    return new ResponseEntity<>(currentGame,HttpStatus.OK);
}


@PostMapping(value ={"/play","/play/"})
public ResponseEntity<CurrentGame>  gamePlay(@RequestBody GamePlayRequest playRequest) {
   
	log.info("playRequest : "+playRequest) ;

	CurrentGame currentGame = play.gamePlay(playRequest);
	
	log.info("returning currentGame:"+currentGame) ;
	
    return new ResponseEntity<>(currentGame,HttpStatus.OK);
}

	
}
















