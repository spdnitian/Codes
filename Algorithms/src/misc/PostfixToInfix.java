package misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class PostfixToInfix {
	
private static final Map<Character, Integer> OPERATOR_PRIORITIES = new HashMap<>();
private static final Map<String, Boolean> ORDER_MATTERS = new HashMap<>();
	
	static {
		OPERATOR_PRIORITIES.put('+', 0);
		OPERATOR_PRIORITIES.put('-', 0);
		OPERATOR_PRIORITIES.put('/', 1);
		OPERATOR_PRIORITIES.put('*', 1);
		OPERATOR_PRIORITIES.put('^', 2);
		
		ORDER_MATTERS.put("+-", false);
		ORDER_MATTERS.put("-+", false);
	}
	
	private static String convertToInfix(String input) {
		StringBuilder output = new StringBuilder();
		Stack<StringBuilder> outputStack = new Stack<>();
		Stack<Character> operators = new Stack<>();
		int totalChars = input.length();
		for (int i = 0; i < totalChars; i++) {
			char curIndexChar = input.charAt(i);
			if(curIndexChar == ' ') {
				continue;
			}else if(isOperator(curIndexChar)) {
				if(outputStack.size() < 2) {
					throw new RuntimeException("Invalid input : " + input);
				}else {
					Character operator = curIndexChar;
					StringBuilder operand2 = outputStack.pop();
					StringBuilder operand1 = outputStack.pop();
					Character leftOperandBottomPriorityOp = null, rightOperandBottomPriorityOp = null;
					if(!operators.isEmpty()) {
						rightOperandBottomPriorityOp = operators.pop();
					}
					if(!operators.isEmpty()) {
						leftOperandBottomPriorityOp = operators.pop();
					}
					if(rightOperandBottomPriorityOp != null) {
						if(OPERATOR_PRIORITIES.get(operator) >= OPERATOR_PRIORITIES.get(rightOperandBottomPriorityOp)) {
							if(operand2.length() > 1 && orderMatters(operator, rightOperandBottomPriorityOp)) {
								operand2  = operand2.insert(0, '(').append(')');
							}
						}
					}
					if(leftOperandBottomPriorityOp != null) {
						if(OPERATOR_PRIORITIES.get(operator) > OPERATOR_PRIORITIES.get(leftOperandBottomPriorityOp)) {
							if(operand1.length() > 1) {
								operand1 = operand1.insert(0, '(').append(')');
							}
						}
					}
					StringBuilder operand = operand1.append(operator).append(operand2);
					outputStack.push(operand);
					operators.push(operator);
				}
			}else if (isOperand(curIndexChar)) {
				StringBuilder sbBuilder = new StringBuilder(String.valueOf(curIndexChar));
				outputStack.push(sbBuilder);
				operators.push(null);
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
	
	private static boolean orderMatters(Character operator1, Character operator2) {
		if(OPERATOR_PRIORITIES.get(operator1) == OPERATOR_PRIORITIES.get(operator2)) {
			Boolean orderMatters = ORDER_MATTERS.get(operator1.charValue()+""+operator2.charValue());
			if(orderMatters != null && !orderMatters) {
				return false;
			}
		}
		return true;
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
