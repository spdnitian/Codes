package stateAutomata;

public class State {
	private final String state;
	private final STATES type;
	public static final State INIT_STATE = new State("INIT", STATES.INIT);
	public static final State BLOCKING_STATE = new State("BLOCKING", STATES.BLOCKING);
	public static final State FINAL_STATE = new State("FINAL", STATES.FINAL);
	private static enum STATES{
		INIT,
		INTERMEDIATE,
		BLOCKING,
		FINAL
	};
	public State(String state,STATES type) {
		this.state = state;
		this.type = type;
	}
	
	public State gotToNextState(char nextChar){
		State nextState = null;
		switch (type) {
		case INIT:
			if(nextChar >= 'A' && nextChar <= 'Z'){
				nextState = new State("S1", STATES.INTERMEDIATE);
			}else{
				nextState = BLOCKING_STATE;
			}
			break;
		case BLOCKING:
			nextState = BLOCKING_STATE;
			break;
		case FINAL:
			nextState = BLOCKING_STATE;
			break;
		case INTERMEDIATE:
			nextState = getNextState(this, nextChar);
			break;
		}
		return nextState;
	}

	private State getNextState(State currentState, char nextChar) {
		String state = currentState.state;
		State nextState = null;
		switch (state) {
		case "S1":
			if(nextChar >= 'a' && nextChar <= 'z'){
				nextState = new State("S2", STATES.INTERMEDIATE);
			}else if(nextChar == ' '){
				nextState = new State("S3", STATES.INTERMEDIATE);
			}else if(nextChar == '.'){
				nextState = FINAL_STATE;
			}else{
				nextState = BLOCKING_STATE;
			}
			break;
		case "S2":
			if(nextChar >= 'a' && nextChar <= 'z'){
				nextState = currentState;
			}else if(nextChar == ' '){
				nextState = new State("S3", STATES.INTERMEDIATE);
			}else if(nextChar == '.'){
				nextState = FINAL_STATE;
			}else{
				nextState = BLOCKING_STATE;
			}
			break;
		case "S3":
			if((nextChar >= 'a' && nextChar <= 'z')){
				nextState = new State("S2", STATES.INTERMEDIATE);
			}else if((nextChar >= 'A' && nextChar <= 'Z')){
				nextState = new State("S1", STATES.INTERMEDIATE);
			}else{
				nextState = BLOCKING_STATE;
			}
			break;
		}
		return nextState;
	}
}
