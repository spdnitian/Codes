package dp.timetesting;

public class MeasureTimings {
	private static String getFormatedTime(long millis) {
		long second = (millis / 1000) % 60;
		long minute = (millis / (1000 * 60)) % 60;
		long hour = (millis / (1000 * 60 * 60)) % 24;
		String timeFormatted = String.format("%02d:%02d:%02d:%03d", hour, minute, second, millis);
		return timeFormatted;
	}
	public static void meauseRecursiveTimeTaken(TimeTesting test) {
		long begin = System.currentTimeMillis();
		test.testNormal();
		long end = System.currentTimeMillis();
		long timeTaken = end - begin;
		String timeFormatted = getFormatedTime(timeTaken);
		System.out.println("Recursive Time : " + timeFormatted);
	}
	
	public static void measusreDPTimeTaken(TimeTesting test) {
		long begin = System.currentTimeMillis();
		test.testDP();
		long end = System.currentTimeMillis();
		long timeTaken = end - begin;
		String timeFormatted = getFormatedTime(timeTaken);
		System.out.println("DP Time : " + timeFormatted);
	}
}
