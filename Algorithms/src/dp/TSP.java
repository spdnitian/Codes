package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import dp.timetesting.MeasureTimings;
import dp.timetesting.TimeTesting;
import utils.CommonUtilities;
import utils.InputParser;

public class TSP implements TimeTesting{
	
	private Integer totalCities;
	private Integer[][] distances;
	private Integer[] cities;
	
	public Integer getTotalCities() {
		return totalCities;
	}

	public void setTotalCities(Integer totalCities) {
		this.totalCities = totalCities;
	}

	public Integer[][] getDistances() {
		return distances;
	}
	
	public void setDistances(Integer[][] distances) {
		this.distances = distances;
	}

	public Integer[] getCities() {
		return cities;
	}

	public void setCities(Integer[] cities) {
		this.cities = cities;
	}

	public static Integer calculateTspCostWithPermutation(Integer[][] dist, Integer[] cities, Integer startCityIndex, int fromCityIndex) {
		int totalCities = dist[0].length;
		Integer minCost = Integer.MAX_VALUE;
		if(fromCityIndex == totalCities - 1) {
			minCost = dist[cities[fromCityIndex]][startCityIndex];
		}else {
			Integer nextCityIndex = fromCityIndex + 1;
			for(int toCityIndex = nextCityIndex; toCityIndex <= totalCities - 1; toCityIndex++) {
				swapCities(cities, nextCityIndex, toCityIndex);
				Integer tspCostFromNextCity = calculateTspCostWithPermutation(dist, cities, startCityIndex, nextCityIndex);
				minCost = getMinimumCost(dist[cities[fromCityIndex]][cities[nextCityIndex]], tspCostFromNextCity, minCost);
				swapCities(cities, nextCityIndex, toCityIndex);
			}
		}
		return minCost;
	}
	
	
	
	private static Integer getMinimumCost(Integer cost1, Integer cost2, Integer minCost) {
		if(cost1 == Integer.MAX_VALUE || cost2 == Integer.MAX_VALUE) {
			return minCost;
		}
		return Math.min(cost1 + cost2, minCost);
	}



	private static void swapCities(Integer[] cities, Integer from, int to) {
		Integer city = cities[from];
		cities[from] = cities[to];
		cities[to] = city;
	}

	private static void findTspCostFromInputs() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter total cities : ");
		String inputStr = "";
		inputStr = bufferedReader.readLine();
		int totalCities = InputParser.readIntegerInputWithSeparator(inputStr, null)[0];
		System.out.println("Directed cities ? Y / N");
		inputStr = bufferedReader.readLine();
		Character response = InputParser.readCharacterInputWithSeparator(inputStr, null)[0];
		Integer[][] distances = new Integer[totalCities][totalCities];
		Integer[] cities = new Integer[totalCities];
		for(int i = 0; i < totalCities ; i++) {
			cities[i] = i;
			if(Character.toLowerCase(response) == 'n') {
				System.out.println(String.format("Enter city distance from %d to %d (total %d distances) : ", i, totalCities - 1, totalCities - i));
			}else {
				System.out.println(String.format("Enter city distance from %d to rest all from %d to %d (total %d distances) : ", i, 0, totalCities - 1, totalCities));
			}
			inputStr = bufferedReader.readLine();
			Integer[] distInputs = InputParser.readIntegerInputWithSeparator(inputStr, ",");
			for(int j = 0; j < totalCities ; j++) {
				if(Character.toLowerCase(response) == 'y') {
					distances[i][j] = distInputs[j];
				}else {
					if(j < i) {
						distances[i][j] = distances[j][i];
					}else {
						distances[i][j] = distInputs[j - i];
					}
				}
			}
		}
		System.out.println("Distances : ");
		for(int i = 0; i < totalCities ; i++) {
			System.out.println(Arrays.toString(distances[i]));
		}
		System.out.println("Min Cost : " + calculateTspCostWithPermutation(distances, cities, 0, 0));
	}
	
	public static int getTspMinCost(Integer[][] dist, Integer[] cities, Integer startCityIndex) {
		Integer totalCities = dist[0].length;
		boolean[] citiesIncluded = new boolean[totalCities];
		swapCities(cities, startCityIndex, 0);
		citiesIncluded[0] = true;
		int minCost = getTspCostUtil(dist, cities, citiesIncluded, totalCities - 1, cities[0]);
		swapCities(cities, startCityIndex, 0);
		return minCost;
	}

	private static int getTspCostUtil(Integer[][] dist, Integer[] cities, boolean[] citiesIncluded, int remainingCities, Integer endCity) {
		int minCost = Integer.MAX_VALUE;
		if(remainingCities == 0) {
			minCost = dist[cities[0]][endCity];
		}else {
			int nextRemainingCities = remainingCities;
			for(int cityIndex = 1; cityIndex <= citiesIncluded.length - 1; cityIndex++) {
				if(!citiesIncluded[cities[cityIndex]]) {
					nextRemainingCities--;
					citiesIncluded[cities[cityIndex]] = true;
					minCost = Math.min(CommonUtilities.getSum(getTspCostUtil(dist, cities, citiesIncluded, nextRemainingCities, cities[cityIndex]),dist[cities[cityIndex]][endCity]), minCost);
					nextRemainingCities++;
					citiesIncluded[cities[cityIndex]] = false;
				}
			}
		}
		return minCost;
	}
	
	public static int getTspMinCostDP(Integer[][] dist, Integer[] cities, Integer startCityIndex) {
		Integer totalCities = dist[0].length;
		boolean[] citiesIncluded = new boolean[totalCities];
		swapCities(cities, startCityIndex, 0);
		citiesIncluded[0] = true;
		Map<String, Integer> dpCache = new HashMap<>();
		int minCost = getTspCostUtilDP(dist, cities, citiesIncluded, totalCities - 1, cities[0], dpCache);
		swapCities(cities, startCityIndex, 0);
		return minCost;
	}

	private static int getTspCostUtilDP(Integer[][] dist, Integer[] cities, boolean[] citiesIncluded, int remainingCities, Integer endCity, Map<String, Integer> dpCache) {
		int minCost = Integer.MAX_VALUE;
		if(remainingCities == 0) {
			minCost = dist[cities[0]][endCity];
		}else {
			int nextRemainingCities = remainingCities;
			for(int cityIndex = 1; cityIndex <= citiesIncluded.length - 1; cityIndex++) {
				if(!citiesIncluded[cities[cityIndex]]) {
					nextRemainingCities--;
					citiesIncluded[cities[cityIndex]] = true;
					Integer tspCostWithEndCity = fetchFromCache(citiesIncluded, cities[cityIndex], dpCache);
					if(tspCostWithEndCity == null) {
						tspCostWithEndCity = getTspCostUtilDP(dist, cities, citiesIncluded, nextRemainingCities, cities[cityIndex], dpCache);
						insertIntoCache(citiesIncluded, cities[cityIndex], dpCache, tspCostWithEndCity);
					}
					minCost = Math.min(CommonUtilities.getSum(tspCostWithEndCity,dist[cities[cityIndex]][endCity]), minCost);
					nextRemainingCities++;
					citiesIncluded[cities[cityIndex]] = false;
				}
			}
		}
		return minCost;
	}
	
	private static void insertIntoCache(boolean[] citiesIncluded, Integer endCity, Map<String, Integer> dpCache, int value) {
		String key = "";
		key += endCity;
		for (int i = 1; i < citiesIncluded.length; i++) {
			if(citiesIncluded[i]) {
				key += "-" + i;
			}
		}
		dpCache.put(key, value);
	}
	
	private static Integer fetchFromCache(boolean[] citiesIncluded, Integer endCity, Map<String, Integer> dpCache) {
		String key = "";
		key += endCity;
		for (int i = 1; i < citiesIncluded.length; i++) {
			if(citiesIncluded[i]) {
				key += "-" + i;
			}
		}
		return dpCache.get(key);
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Execute benchmarking : Y/N");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = bufferedReader.readLine();
		Character response = InputParser.readCharacterInputWithSeparator(inputStr, null)[0];
		if(Character.toLowerCase(response) == 'y') {
			System.out.println("Enter total test cases : ");
			inputStr = bufferedReader.readLine();
			int testCasesWithTotalCities = InputParser.readIntegerInputWithSeparator(inputStr, null)[0];
			for(int testCseWithCity = 0; testCseWithCity < testCasesWithTotalCities; testCseWithCity++) {
				TSP tsp = new TSP();
				tsp.setTotalCities(testCseWithCity + 1);
				Integer[] cities = new Integer[tsp.getTotalCities()];
				Integer[][] distances = new Integer[testCseWithCity + 1][testCseWithCity + 1];
				for (int i = 0; i < distances.length; i++) {
					cities[i] = i;
					for (int j = 0; j < distances.length; j++) {
						distances[i][j] = (i == j) ? 0 : new Random().nextInt(testCasesWithTotalCities + 1);
					}
				}
				tsp.setDistances(distances);
				tsp.setCities(cities);
				System.out.print("Time taken for " + (tsp.getTotalCities()) + " cities : ");
				//MeasureTimings.meauseRecursiveTimeTaken(tsp);
				MeasureTimings.measusreDPTimeTaken(tsp);
				//System.out.println("DP : " + getTspMinCostDP(distances, cities, 0));
				//System.out.println("Recursive : " + getTspMinCost(distances, cities, 0));
				System.out.println();
			}
		}else {
			findTspCostFromInputs();
		}
	}



	@Override
	public void testNormal() {
		Integer startCity = 0;
		swapCities(this.getCities(), startCity, 0);
		//calculateTspCostWithPermutation(this.getDistances(), cities, cities[0], cities[0]);
		getTspMinCost(distances, cities, cities[0]);
		swapCities(this.getCities(), startCity, 0);
	}



	@Override
	public void testDP() {
		Integer startCity = 0;
		swapCities(this.getCities(), startCity, 0);
		//calculateTspCostWithPermutation(this.getDistances(), cities, cities[0], cities[0]);
		getTspMinCostDP(distances, cities, cities[0]);
		swapCities(this.getCities(), startCity, 0);
	}
}
