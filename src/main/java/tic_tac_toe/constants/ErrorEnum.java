package tic_tac_toe.constants;

import lombok.Getter;

@Getter
public enum ErrorEnum {

	MISSING_OR_INVALID_REQUEST("40001","missing value or invalid request received",""),
	INVALID_STATE_RECEIVED("40002","invalid state received"
			,"number of marks of each player should be consistent, i.e. if startedByHuman=true than state will same number of mark for each AI & human otherwise AI's mark count will be one higher"), 
	
	INVALID_POSITION_RECEIVED("40003","invalid value for position received","may be position is already field or out of range") , 
	GENERIC_EXCEPTION("4000","Something went wrong try a","") ;
	
	private String errorCode ;
	private String errorMessage ;
	private String errorDescription ;
	

	ErrorEnum(String code, String message, String desc){
		errorCode = code ;
		errorMessage = message ;
		errorDescription = desc ;
	}
	
}
