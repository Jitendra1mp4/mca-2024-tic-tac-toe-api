package in.ac.prsu.mca.mca2025_tictactoeapi.service.validate;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Count {

	private int aiMarkCount = 0 ;
	private int humanMarkCount = 0;
	
	public Count(int aiMarkCount, int humanMarkCount){
		this.aiMarkCount = aiMarkCount ;
		this.humanMarkCount = humanMarkCount ;
	}
}
