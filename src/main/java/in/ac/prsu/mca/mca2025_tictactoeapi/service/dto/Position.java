package in.ac.prsu.mca.mca2025_tictactoeapi.service.dto;

import lombok.extern.java.Log;
import in.ac.prsu.mca.mca2025_tictactoeapi.constants.Constants.PRIORITY;

@Log
public class Position implements Comparable<Position> {
    private int x;
    private int y;


    private PRIORITY priority ;
    
    
    public Position(int position) {
    	priority = PRIORITY.fromNumber(0) ; // default priority = 0 
        switch (position) {
            case 1:
                x = 0;
                y = 0;
                break;

            case 2:
                x = 0;
                y = 1;
                break;

            case 3:
                x = 0;
                y = 2;
                break;

            case 4:
                x = 1;
                y = 0;
                break;

            case 5:
                x = 1;
                y = 1;
                break;

            case 6:
                x = 1;
                y = 2;
                break;

            case 7:
                x = 2;
                y = 0;
                break;

            case 8:
                x = 2;
                y = 1;
                break;

            case 9:
                x = 2;
                y = 2;
                break;

            default:
                log.warning("invalid position:"+position);
                throw new RuntimeException("Invalid position received") ; 
                
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public PRIORITY getPriority() {
    	return priority ;
    }
    
    
    public void setPriority(PRIORITY priority) {
    	this.priority = priority ;
    }
    
    
	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]"+",priority:"+priority;
	}

	@Override
	public int compareTo(Position other) {
		return Integer.compare(this.priority.getPriorityID(), other.priority.getPriorityID());
	}

	public int getIntValue() {
		
		
		switch (x){
		case 0: 
			switch (y) {
			case 0 : return 1 ;
			case 1 : return 2 ;
			case 2 : return 3 ;
			default : return -1 ;
			}
			
		case 1: 
			switch (y) {
			case 0 : return 4 ;
			case 1 : return 5 ;
			case 2 : return 6 ;
			default : return -1 ;
			}
		
		case 2: 
			switch (y) {
			case 0 : return 7 ;
			case 1 : return 8 ;
			case 2 : return 9 ;
			default : return -1 ;
			}
			
		default : return -1 ;
			
		}
	
	}
    
}

























