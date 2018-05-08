package utils;

public class InputParser {

	private static String[] splitInputWithSeparator(String input, String separator) {
		String[] inputsStrTokens = null; 
		if(separator == null) {
			inputsStrTokens = new String[] {input};
		}else {
			inputsStrTokens = input.split(separator);
		}
		return inputsStrTokens;
	}
	
	public static Integer[] readIntegerInputWithSeparator(String input, String separator) {
		String[] inputTokens = splitInputWithSeparator(input, separator);
		Integer[] inputs = new Integer[inputTokens.length];
		for (int i = 0; i < inputTokens.length; i++) {
			inputs[i] = Integer.parseInt(inputTokens[i].trim());
		}
		return inputs;
	}
	
	public static Character[] readCharacterInputWithSeparator(String input, String separator) {
		String[] inputTokens = splitInputWithSeparator(input, separator);
		Character[] inputs = new Character[inputTokens.length];
		for (int i = 0; i < inputTokens.length; i++) {
			inputs[i] = inputTokens[i].trim().charAt(0);
		}
		return inputs;
	}

}
