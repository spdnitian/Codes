package misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class InfixToPostfix {

	private static final Map<Character, Integer> OPERATOR_PRIORITIES = new HashMap<>();
	
	static {
		OPERATOR_PRIORITIES.put('+', 0);
		OPERATOR_PRIORITIES.put('-', 0);
		OPERATOR_PRIORITIES.put('/', 1);
		OPERATOR_PRIORITIES.put('*', 1);
		OPERATOR_PRIORITIES.put('^', 2);
	}
	
	private static String convertToPostFix(String input) {
		StringBuilder output = new StringBuilder();
		Stack<Character> inputOperators = new Stack<>();
		int totalChars = input.length();
		for (int i = 0; i < totalChars; i++) {
			char curIndexChar = input.charAt(i);
			if(curIndexChar == ' ') {
				continue;
			}else if(curIndexChar == '(') {
				inputOperators.push('(');
			}else if(curIndexChar == ')') {
				boolean isBeginBracketFound = false;
				while (!isBeginBracketFound && !inputOperators.isEmpty()) {
					char oprChar = inputOperators.pop();
					if(oprChar == '(') {
						isBeginBracketFound = true;
					}else if(isOperator(oprChar)){
						output.append(oprChar);
					}else {
						throw new RuntimeException("Invalid char found : " + oprChar);
					}
				}
				if(!isBeginBracketFound) {
					throw new RuntimeException("Invalid input. No opening found for closing bracket at index : " + i);
				}
			}else if(isOperator(curIndexChar)) {
				while (!inputOperators.isEmpty()) {
					char topOperator = inputOperators.peek();
					if(isOperator(topOperator) && OPERATOR_PRIORITIES.get(curIndexChar) <= OPERATOR_PRIORITIES.get(topOperator)) {
						inputOperators.pop();
						output.append(topOperator);
					}else {
						break;
					}
				}
				inputOperators.push(curIndexChar);
			}else if (isOperand(curIndexChar)) {
				output.append(curIndexChar);
			}else {
				throw new RuntimeException("Invalid input char : " + curIndexChar);
			}
		}
		while (!inputOperators.isEmpty()) {
			char topOperator = inputOperators.pop();
			output.append(topOperator);
		}
		return output.toString();
	}
	
	private static boolean isOperand(char c) {
		return (Character.isLetter(c) || Character.isDigit(c));
	}

	private static boolean isOperator(char oprChar) {
		return OPERATOR_PRIORITIES.get(oprChar) != null;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader brBufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter input : ");
		String input = brBufferedReader.readLine();
		System.out.println("Postfix : " + convertToPostFix(input));
	}

}
