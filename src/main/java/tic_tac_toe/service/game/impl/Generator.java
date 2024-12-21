package tic_tac_toe.service.game.impl;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import org.springframework.stereotype.Service;

import lombok.extern.java.Log;
import tic_tac_toe.constants.Constants;
import tic_tac_toe.constants.Constants.PLAYER;
import tic_tac_toe.constants.Constants.PRIORITY;
import tic_tac_toe.service.dto.Position;

@Service
@Log
public class Generator {



	
	private PriorityQueue<Position> positionQueue ;
	
	

	public Generator() {
		positionQueue = new PriorityQueue<>() ;
	}

	public Position searchAndGetPosition(Game game) {

		   if(Test.handleStartWithSideCenter(game)) {
		    	log.info("returned by handleStartWithSideCenter");
		    	return new Position(5) ;
		    }

		assignPrioritiesToPositions(game.getAvailablePositionList(), game.state) ; 
	
		return getBestPosition(game) ;
	}



	public void assignPrioritiesToPositions(List<Position> positionList, final int[][] currentState) {

		// make a new game
		Game game = new Game();

		for (Iterator<Position> iterator = positionList.iterator(); iterator.hasNext();) {

			// set the state of game to current state
			game.state = getDeepCopy(currentState);

			Position position = iterator.next();

			// if by applying that position AI wins it is winning position
			game.updateState(position, Constants.PLAYER_AI_MARK);
			if (Test.won(game, Constants.PLAYER.AI, position)) {
				position.setPriority(PRIORITY.AI_WON);
				positionQueue.add(position) ;
				continue;
			}

			// if human can win by marking same position it would be preventivePosition
			game.updateStateForced(position, Constants.PLAYER_HUMAN_MARK);
			if (Test.won(game, Constants.PLAYER.HUMAN, position)) {
				position.setPriority(PRIORITY.PREVENT_HUMAN_TO_WIN);
				positionQueue.add(position) ;
				
				System.out.println("set: "+position);
				continue;
			}

			// if by applying position AI's win is confirm in next inn it is better
			// position
			game.updateStateForced(position, Constants.PLAYER_AI_MARK);
			if (Test.willIWonInNextState(PLAYER.AI,game, position)) {
				position.setPriority(PRIORITY.AI_WIN_IN_NEXT_STATE);
				positionQueue.add(position) ;
				
				System.out.println("set: "+position);
				continue;
			} 
			
			game.updateStateForced(position, Constants.PLAYER_HUMAN_MARK);
			if (Test.willIWonInNextState(PLAYER.HUMAN,game, position)) {
				position.setPriority(PRIORITY.HUMAN_WIN_IN_NEXT_STATE);
				positionQueue.add(position) ;
				
				System.out.println("set: "+position);
				continue ;
			}
					
			
			else {
				game.updateStateForced(position, Constants.PLAYER_AI_MARK);
				int priority = (-1)*Test.numberOfOpenPostions(PLAYER.AI, game, position);
				
				position.setPriority(PRIORITY.fromNumber(priority));
				positionQueue.add(position) ;

				System.out.println("set: "+position);
			}
		}
	
	}

		
	private Position getBestPosition(Game game) {
//		
		if(Test.diagonalDangerForAi(game)) {
			System.out.println("diagonalDangerForAi, returning handleDoublePreventiveCase()");
			return handleDiagonalDangerForAi();
		}
		
		if (!positionQueue.isEmpty()) {
			
			Position position = positionQueue.poll();
			System.out.println("returning.. "+position);
			return position ;
		}

		System.out.println("NO position place left!");
		System.out.println("Game Over!");
		System.exit(1) ;	
		return null ;
	}

	private Position handleDiagonalDangerForAi() {
		System.out.println("Generator.handleDoublePreventiveCase()");
		return new Position(4) ;
	}
		
	private static int[][] getDeepCopy(int[][] matrix) {
		int[][] copy = new int[3][];

		for (int i = 0; i < matrix.length; i++) { // *state.length returns number of rows
			copy[i] = matrix[i].clone();
		}

		return copy;
	}


}
