package tic_tac_toe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;
import tic_tac_toe.pojo.CurrentGame;
import tic_tac_toe.pojo.GameRequest;


@RestController	
@RequestMapping("/api/v1/tic-tac-toe")
@Log
public class GameController {

@PostMapping("/new")
public ResponseEntity<CurrentGame> startNewGame(@RequestBody GameRequest gameRequest) {
    //TODO: process POST request
	log.info("gameRequest : "+gameRequest) ;
	
    return new ResponseEntity<>(new CurrentGame(),HttpStatus.OK);
}

	
}
















