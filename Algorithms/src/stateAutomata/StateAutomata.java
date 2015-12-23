package stateAutomata;

import java.util.Scanner;

public class StateAutomata {
	
	public static boolean isValidSentence(String str){
		boolean isValid = false;
		State currentState = State.INIT_STATE;
		int length = str.length();
		for (int i = 0; i < length && currentState != State.BLOCKING_STATE; i++) {
			currentState = currentState.gotToNextState(str.charAt(i));
		}
		isValid = currentState == State.FINAL_STATE;
		return isValid;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		System.out.println(isValidSentence(string));
		scanner.close();
	}

}
