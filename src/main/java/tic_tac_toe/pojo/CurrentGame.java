package tic_tac_toe.pojo;

import lombok.Data;
import tic_tac_toe.constants.PlayResult;

@Data
public class CurrentGame {
	private int providedPosition ;
	
	private int aiSetPosition;
	private String aiSetSymbol;
	
	private String humanSetSymbol ;
	private boolean startedByHuman;
	
	private PlayResult playResult  ;
	private boolean stateUpdatedByHuman ;
	private boolean stateUpdatedByAI ;
	
	private int[][] providedState;
	private int[][] stateByApplyingGivenPosition ;
	private int[][] aiResponseState;
}
