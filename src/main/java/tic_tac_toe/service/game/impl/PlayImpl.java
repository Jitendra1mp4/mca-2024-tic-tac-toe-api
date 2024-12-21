package tic_tac_toe.service.game.impl;

import org.springframework.stereotype.Service;

import lombok.extern.java.Log;
import tic_tac_toe.constants.Constants;
import tic_tac_toe.constants.Constants.PLAYER;
import tic_tac_toe.constants.PlayResult;
import tic_tac_toe.pojo.CurrentGame;
import tic_tac_toe.pojo.GamePlayRequest;
import tic_tac_toe.pojo.NewGameRequest;
import tic_tac_toe.service.dto.Position;
import tic_tac_toe.service.game.Play;

@Log
@Service
public class PlayImpl implements Play {

	private static final String AI = "AI";
	private static final String HUMAN = "HUMAN";

	private final Generator generator = new Generator();

	// would be called for new game request as it does not checks for wining
	public CurrentGame newGamePlay(NewGameRequest gameRequest) {

		log.info("gameRequest:" + gameRequest);

		Game game = new Game();
		game.setStartedByHuman(gameRequest.isStartByHuman());

		CurrentGame currentGame = new CurrentGame();
		currentGame.setProvidedPosition(gameRequest.getPosition());
		currentGame.setStartedByHuman(gameRequest.isStartByHuman());
		currentGame.setProvidedState(null);
		currentGame.setPlayResult(PlayResult.IN_PROGRESS);
		
		
		Position position;
		boolean stateUpdated;

		if (gameRequest.isStartByHuman()) {
			log.info("Human started game...");
			position = new Position(gameRequest.getPosition());
			stateUpdated = game.updateState(position, Constants.PLAYER_HUMAN_MARK);
			currentGame.setStateUpdatedByHuman(stateUpdated);
			currentGame.setStateByApplyingGivenPostion(getDeepCopy(game.getState())); // set new game state
		} 
//		else {
			log.info("AI's turn...");
			position = generator.searchAndGetPosition(game);
			stateUpdated = game.updateState(position, Constants.PLAYER_AI_MARK);
//		}
		
		currentGame.setFinalStateSetPosition(position.getIntValue());
		currentGame.setFinalStateSetSymbol(Constants.SYMBOL_X.trim());
		currentGame.setFinalState(getDeepCopy(game.getState()));
		currentGame.setStateUpdatedByAI(stateUpdated);
		
		return currentGame;

	}

	public CurrentGame gamePlay(GamePlayRequest playRequest) {

		log.info("playRequest:" + playRequest);

		Game game = new Game();

		game.setStartedByHuman(playRequest.isStartedByHuman());

		game.state = getDeepCopy(playRequest.getGameState());
		
		log.info("requested state\n"+game);
		// working here

		/*
		 * we'll get the position to be set from human side but need to decide which
		 * symbol to put by default I am putting symbol human
		 * 
		 */

		CurrentGame currentGame = new CurrentGame();
		
		currentGame.setProvidedState(getDeepCopy(playRequest.getGameState()));
		currentGame.setStartedByHuman(playRequest.isStartedByHuman());

		
		Position position = new Position(playRequest.getPosition());

	
		boolean stateUpdatedByHuman = game.updateState(position, Constants.PLAYER_HUMAN_MARK);
		currentGame.setStartedByHuman(stateUpdatedByHuman);
		currentGame.setStateByApplyingGivenPostion(getDeepCopy(game.getState())); // set new game state

		
		if (!stateUpdatedByHuman) {
			throw new RuntimeException("Invalid position received unable to process...");
		}
		
		log.info("state after setting request position\n"+game);
		
		if (Test.won(game, PLAYER.HUMAN, position)) {
			currentGame.setPlayResult(PlayResult.HUMAN_WIN);

			log.info("\nHUMAN WON:"+game);
			return currentGame;
		}

		position = generator.searchAndGetPosition(game);
		boolean stateUpdatedByAI = game.updateState(position, Constants.PLAYER_AI_MARK);
		currentGame.setStateUpdatedByAI(stateUpdatedByAI);

		currentGame.setFinalState(getDeepCopy(game.getState())); // set new game state
		

		log.info("state after setting AI position\n"+game);
		
		
		if (Test.won(game, PLAYER.AI, position)) {
			currentGame.setPlayResult(PlayResult.AI_WIN);
			log.info("\nAI WON:"+game);
			return currentGame;
		}

		log.info("numberof cell updated:"+game.getNumberOfCellUpdated());
		
		currentGame.setPlayResult(PlayResult.IN_PROGRESS);
		
		if (game.isDraw()) {
			currentGame.setPlayResult(PlayResult.DRAW);
			log.info("\nDRAW:"+game);
		}
		
		
		return currentGame;
	}

	private static int[][] getDeepCopy(int[][] matrix) {
		int[][] copy = new int[3][];

		for (int i = 0; i < matrix.length; i++) { // *state.length returns number of rows
			copy[i] = matrix[i].clone();
		}

		return copy;
	}
	
	
//    public Play() {
//
//        Scanner sc = new Scanner(System.in);
//
//        Game game = new Game();
//
//        System.out.println("press 1 or any ODD Number to start first") ;
//        System.out.println("press 0 or any Even Number to let AI start first : ");
//
//        int i = sc.nextInt() ;
//
//        if (i%2==0) {
//        	game.setStartedByHuman(false);
//        	System.out.println("AI's Symbol : " + Constants.SYMBOL_X);
//        	System.out.println("YOUR Symbol : " + Constants.SYMBOL_O);
//        }else {
//        	game.setStartedByHuman(true);
//        	System.out.println("AI's Symbol : " + Constants.SYMBOL_O);
//        	System.out.println("YOUR Symbol : " + Constants.SYMBOL_X);
//
//        }
//        
//        
//
//        System.out.println("initial game state:");
//        System.out.println(game) ;
//
//
//
//        do {
//        	
//        	Position position ;
//
//        	boolean stateUpdated = false ;
//            if (i % 2 == 0) { 
//                System.out.println("AI's turn...");
//                Generator generator = new Generator();
//                position = generator.searchAndGetPosition(game);
//                stateUpdated = game.updateState(position,  Constants.PLAYER_AI_MARK);
//            }
//
//            else { // for player human
//                System.out.println("Your turn");
//                System.out.print("Enter position : ");
//                position = new Position(sc.nextInt());
//                stateUpdated = game.updateState(position, Constants.PLAYER_HUMAN_MARK);
//            }
//            
//            
//            System.out.println("position set, now game is : ");
//            System.out.println(game) ;
//            
//            if(Test.won(game,PLAYER.AI, position)) {
//            	System.out.println("AI WON!");
//            	break ;
//            }
//            
//            if(Test.won(game,PLAYER.HUMAN, position)) {
//                System.out.println("YOU WON!");
//                break ;
//            }
//
//            if (stateUpdated) i++ ;
//        } while (game.getNumberOfCellUpdated()<9);
//
//        sc.nextLine();
//        System.out.println("Game Over!");
//        sc.close();
//    }

	
}
