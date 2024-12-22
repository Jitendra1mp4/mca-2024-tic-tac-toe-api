package in.ac.prsu.mca.mca2025_tictactoeapi.service.game.impl;


import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.extern.java.Log;
import in.ac.prsu.mca.mca2025_tictactoeapi.constants.Constants;
import in.ac.prsu.mca.mca2025_tictactoeapi.service.dto.DiagonalSum;
import in.ac.prsu.mca.mca2025_tictactoeapi.service.dto.Position;

@Log
class Game {

	public static final int INIT_VAL = Constants.INITIAL_MARK;
	private static final int NUMBER_OF_CELLS = 9;
	
	private int numberOfCellUpdated  ;
	private boolean startedByHuman ;
	
	
	Game() {
		numberOfCellUpdated = 0; 
	}
	
	@Getter
	int[][] state = new int[][] {
			{ INIT_VAL, INIT_VAL, INIT_VAL },
			{ INIT_VAL, INIT_VAL, INIT_VAL },
			{ INIT_VAL, INIT_VAL, INIT_VAL }
	};

	boolean updateState(int i, int j, int val) {

		
		numberOfCellUpdated = NUMBER_OF_CELLS - getAvailablePositionList().size() ;
		
		
		if (state[i][j] == INIT_VAL) {
			state[i][j] = val;
			numberOfCellUpdated += 1;
			return true;
		}

		log.info("Invalid position:"+"i:"+i+"j:"+j+",try again!");
		return false;

	}

	public boolean updateState(Position p, int symbol) {
			return updateState(p.getX(), p.getY(), symbol);
	}

	public void updateStateForced(Position p, int symbol) {
		state[p.getX()][p.getY()] = symbol;
	}

	int getRowSum(final int row) {
		int sum = 0;
		for (int i = 0; i < 3; i++)
			sum += state[row][i];
		return sum;
	}

	int getColSum(final int col) {
		int sum = 0;
		for (int i = 0; i < 3; i++)
			sum += state[i][col];
		return sum;
	}

	DiagonalSum getDiagonalSum() {
		DiagonalSum digSum = new DiagonalSum();

		final int sumOfD1 = state[0][0] + state[1][1] + state[2][2];
		final int sumOfD2 = state[0][2] + state[1][1] + state[2][0];

		digSum.setSumOfD1(sumOfD1);
		digSum.setSumOfD2(sumOfD2);

		return digSum;
	}

	
	public List<Position> getAvailablePositionList() {

		Position position;

		List<Position> positionList = new LinkedList<>();

		for (int cell = 1; cell <= NUMBER_OF_CELLS; cell++) {
			position = new Position(cell);

			if (state[position.getX()][position.getY()] == INIT_VAL) {
				positionList.add(position);
			}
		}
		
		numberOfCellUpdated = NUMBER_OF_CELLS - positionList.size() ;
		
		return positionList;
	}
	
	
	@Override
	public String toString() {
		StringBuilder stateAsString = new StringBuilder("\n");
		
		String playerAiSymbol = "";
		String playerHumanSymbol = "" ;
		
		if (startedByHuman){
			playerHumanSymbol = Constants.SYMBOL_X ;
			playerAiSymbol = Constants.SYMBOL_O ;
		}
		else {
			playerAiSymbol = Constants.SYMBOL_X ;
			playerHumanSymbol = Constants.SYMBOL_O ;
		}
		
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				
				if (state[i][j] == Constants.INITIAL_MARK) {
					stateAsString.append(Constants.INITIAL_SYMBOL);
					
				} else if (state[i][j] == Constants.PLAYER_AI_MARK)
					stateAsString.append(playerAiSymbol);
				
				else if (state[i][j] == Constants.PLAYER_HUMAN_MARK)
					stateAsString.append(playerHumanSymbol);
			}
			stateAsString.append("\n\n");
		}
		return stateAsString.toString(); 
	}


	public int getNumberOfCellUpdated() {
		
		int numberOfAvailablePosition = getAvailablePositionList().size() ;
		numberOfCellUpdated = NUMBER_OF_CELLS - numberOfAvailablePosition ;
		
		return numberOfCellUpdated;
	}

	public void setNumberOfCellUpdated(int numberOfCellUpdated) {
		this.numberOfCellUpdated = numberOfCellUpdated;
	}

	public boolean isStartedByHuman() {
		return startedByHuman;
	}

	public void setStartedByHuman(boolean startedByHuman) {
		this.startedByHuman = startedByHuman;
	}

	public boolean isDraw() {
		return getNumberOfCellUpdated() >= 9 ;
	}
	
}
