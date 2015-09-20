package dp;

public class CoinChange {
	int[] coinArr;
	public int coinChange(int cost){
		if(cost == 0){
			return 0;
		}
		int minCost = cost + 1;
		int currentCost = 0;
		for(int i = 0; i < coinArr.length; i++){
			if(cost >= coinArr[i]){
				currentCost = coinChange(cost - coinArr[i]);
			}
			if(currentCost < minCost){
				minCost = currentCost;
			}
		}
		return 1 + minCost;
	}
	
	public int noOfWays(int noOfCoins, int cost){
		int sum = 0;
		if(cost == 0){
			return 1;
		}
		if(cost < 0){
			return 0;
		}
		if(noOfCoins == 0 && cost > 0){
			return 0;
		}
		int lastCoin = this.coinArr[noOfCoins - 1];
		sum = noOfWays(noOfCoins - 1, cost) + noOfWays(noOfCoins, cost - lastCoin);
		return sum;
	}
	
	public static void main(String[] args) {
		CoinChange coinChange = new CoinChange();
		coinChange.coinArr = new int[] {2, 5, 3, 6};
		System.out.println(coinChange.coinChange(10));
		System.out.println(coinChange.noOfWays(coinChange.coinArr.length, 10));
	}
}
