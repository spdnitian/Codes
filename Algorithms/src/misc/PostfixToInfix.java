package misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class PostfixToInfix {
	
private static final Map<Character, Integer> OPERATOR_PRIORITIES = new HashMap<>();
	
	static {
		OPERATOR_PRIORITIES.put('+', 0);
		OPERATOR_PRIORITIES.put('-', 0);
		OPERATOR_PRIORITIES.put('/', 1);
		OPERATOR_PRIORITIES.put('*', 1);
		OPERATOR_PRIORITIES.put('^', 2);
	}
	
	private static String convertToInfix(String input) {
		StringBuilder output = new StringBuilder();
		Stack<StringBuilder> outputStack = new Stack<>();
		int totalChars = input.length();
		Character prevOprChar = null;
		for (int i = 0; i < totalChars; i++) {
			char curIndexChar = input.charAt(i);
			if(curIndexChar == ' ') {
				continue;
			}else if(isOperator(curIndexChar)) {
				if(outputStack.size() < 2) {
					throw new RuntimeException("Invalid input : " + input);
				}else {
					StringBuilder operand2 = outputStack.pop();
					StringBuilder operand1 = outputStack.pop();
					if(prevOprChar != null && (OPERATOR_PRIORITIES.get(curIndexChar) >= OPERATOR_PRIORITIES.get(prevOprChar))) {
						if(operand1.length() > 1 && OPERATOR_PRIORITIES.get(curIndexChar) > OPERATOR_PRIORITIES.get(prevOprChar)) {
							operand1 = operand1.insert(0, '(').append(')');
						}
						if(operand2.length() > 1) {
							operand2 = operand2.insert(0, '(').append(')');
						}
					}
					StringBuilder operand = operand1.append(curIndexChar).append(operand2);
					outputStack.push(operand);
					prevOprChar = curIndexChar;
				}
			}else if (isOperand(curIndexChar)) {
				StringBuilder sbBuilder = new StringBuilder(String.valueOf(curIndexChar));
				outputStack.push(sbBuilder);
			}else {
				throw new RuntimeException("Invalid input char : " + curIndexChar);
			}
		}
		while (!outputStack.isEmpty()) {
			StringBuilder sBuilder = outputStack.pop();
			output = sBuilder.append(output);
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
		System.out.println("Infix : " + convertToInfix(input));

	}

}
