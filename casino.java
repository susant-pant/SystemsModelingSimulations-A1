import java.util.Random;

class Casino {
	static final int GAMES = 1000000;
	static final float BOSS_WINNINGS = 10.f;
	static final float TWINS_WINNINGS = 50.f;
	static final float BIGGIE_WINNINGS = 2.f;
	static final float RUNNY_WINNINGS = 5.f;
	static final float DEMOCRACY_WINNINGS = 5.f;

	public static boolean getBossResult(int card) {
		return ((card % 13) == 0);
	}

	public static boolean getTwinsResult(int card1, int card2) {
		return (card1 == card2);
	}

	public static boolean getBiggieResult(int card1, int card2) {
		return (card2 > card1);
	}

	public static int getSweetheartsResult(int card1, int card2, int card3) {
		int numHearts = 0;
		if (card1 < 13) numHearts++;
		if (card2 < 13) numHearts++;
		if (card3 < 13) numHearts++;
		return numHearts;
	}

	public static boolean getRunnyResult(int[] cards) {
		int[] values = new int[5];
		for (int i = 0; i < 5; i++) {
			values[i] = cards[i] % 13;
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (i == j) continue;
				for (int k = 0; k < 5; k++) {
					if ((i == k) || (j == k)) continue;
					if (((values[i] - 1) == values[j]) && ((values[j] - 1) == values[k])) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean getDemocracyResult(int[] cards) {
		int numHearts, numSpades, numClubs, numDiamonds;
		numHearts = numSpades = numClubs = numDiamonds = 0;

		for (int i = 0; i < cards.length; i++) {
			int suit = cards[i] / 13;
			if (suit == 0) numHearts++;
			else if (suit == 1) numSpades++;
			else if (suit == 2) numClubs++;
			else if (suit == 3) numDiamonds++;
			else System.out.printf("YA MESSED UP BOI\n");
		}

		return ((numHearts >= 5) || (numSpades >= 5) || (numClubs >= 5) || (numDiamonds >= 5));
	}

	/*playing Boss on an Infinite Deck*/
	public static void playBossInf(Random rng, Deck deck) {
		int bossInfGames = 0;
		int bossInfWins = 0;

		while (bossInfGames < GAMES) {
			int card = deck.pullCardInf(rng.nextFloat());

			if (getBossResult(card)) bossInfWins++;
			bossInfGames++;
		}

		float bossInfChance = (float)(bossInfWins) / (float)(bossInfGames);
		System.out.printf("BOSS (infinite deck)\n\tTotal games: %d\tTotal wins: %d\tProbability of occurence: %f\n\tExpected Casino Winnings per Game: %f\n",
							bossInfGames, bossInfWins, bossInfChance, 1 - (bossInfChance * BOSS_WINNINGS));
	}

	/*playing Boss on a Single Deck*/
	public static void playBossSingle(Random rng, Deck deck) {
		int bossSingleGames = 0;
		int bossSingleWins = 0;

		while (bossSingleGames < GAMES) {
			int card = deck.pullCardSingle(rng);

			if (getBossResult(card)) bossSingleWins++;
			bossSingleGames++;

			deck.rebuild();
		}

		float bossSingleChance = (float)(bossSingleWins) / (float)(bossSingleGames);
		System.out.printf("BOSS (single deck)\n\tTotal games: %d\tTotal wins: %d\tProbability of occurence: %f\n\tExpected Casino Winnings per Game: %f\n",
							bossSingleGames, bossSingleWins, bossSingleChance, 1 - (bossSingleChance * BOSS_WINNINGS));
	}

	/*playing Twins on an Infinite Deck*/
	public static void playTwinsInf(Random rng, Deck deck) {
		int twinsInfGames = 0;
		int twinsInfWins = 0;

		while (twinsInfGames < GAMES) {
			int card1 = deck.pullCardInf(rng.nextFloat());
			int card2 = deck.pullCardInf(rng.nextFloat());

			if (getTwinsResult(card1, card2)) twinsInfWins++;
			twinsInfGames++;
		}

		float twinsInfChance = (float)(twinsInfWins) / (float)(twinsInfGames);
		System.out.printf("TWINS (infinite deck)\n\tTotal games: %d\tTotal wins: %d\tProbability of occurence: %f\n\tExpected Casino Winnings per Game: %f\n",
							twinsInfGames, twinsInfWins, twinsInfChance, 1 - (twinsInfChance * TWINS_WINNINGS));
	}

	/*playing Twins on a Single Deck*/
	public static void playTwinsSingle(Random rng, Deck deck) {
		int twinsSingleGames = 0;
		int twinsSingleWins = 0;

		while (twinsSingleGames < GAMES) {
			int card1 = deck.pullCardSingle(rng);
			int card2 = deck.pullCardSingle(rng);

			if (getTwinsResult(card1, card2)) twinsSingleWins++;
			twinsSingleGames++;

			deck.rebuild();
		}

		float twinsSingleChance = (float)(twinsSingleWins) / (float)(twinsSingleGames);
		System.out.printf("TWINS (single deck)\n\tTotal games: %d\tTotal wins: %d\tProbability of occurence: %f\n\tExpected Casino Winnings per Game: %f\n",
							twinsSingleGames, twinsSingleWins, twinsSingleChance, 1 - (twinsSingleChance * TWINS_WINNINGS));
	}

	/*playing Biggie on an Infinite Deck*/
	public static void playBiggieInf(Random rng, Deck deck) {
		int biggieInfGames = 0;
		int biggieInfWins = 0;

		while (biggieInfGames < GAMES) {
			int card1 = deck.pullCardInf(rng.nextFloat());
			int card2 = deck.pullCardInf(rng.nextFloat());

			if (getBiggieResult(card1, card2)) biggieInfWins++;
			biggieInfGames++;
		}

		float biggieInfChance = (float)(biggieInfWins) / (float)(biggieInfGames);
		System.out.printf("BIGGIE (infinite deck)\n\tTotal games: %d\tTotal wins: %d\tProbability of occurence: %f\n\tExpected Casino Winnings per Game: %f\n",
							biggieInfGames, biggieInfWins, biggieInfChance, 1 - (biggieInfChance * BIGGIE_WINNINGS));
	}

	/*playing Biggie on a Single Deck*/
	public static void playBiggieSingle(Random rng, Deck deck) {
		int biggieSingleGames = 0;
		int biggieSingleWins = 0;

		while (biggieSingleGames < GAMES) {
			int card1 = deck.pullCardSingle(rng);
			int card2 = deck.pullCardSingle(rng);

			if (getBiggieResult(card1, card2)) biggieSingleWins++;
			biggieSingleGames++;

			deck.rebuild();
		}

		float biggieSingleChance = (float)(biggieSingleWins) / (float)(biggieSingleGames);
		System.out.printf("BIGGIE (single deck)\n\tTotal games: %d\tTotal wins: %d\tProbability of occurence: %f\n\tExpected Casino Winnings per Game: %f\n",
							biggieSingleGames, biggieSingleWins, biggieSingleChance, 1 - (biggieSingleChance * BIGGIE_WINNINGS));
	}

	/*playing Sweethearts on an Infinite Deck*/
	public static void playSweetheartsInf(Random rng, Deck deck) {
		int sweetheartsInfGames = 0;
		int sweetheartsInfWins = 0;
		int sweetheartsWinnings = 0;

		while (sweetheartsInfGames < GAMES) {
			int card1 = deck.pullCardInf(rng.nextFloat());
			int card2 = deck.pullCardInf(rng.nextFloat());
			int card3 = deck.pullCardInf(rng.nextFloat());

			int sweethearts = getSweetheartsResult(card1, card2, card3);
			if (sweethearts > 0) sweetheartsInfWins++;
			sweetheartsInfGames++;
			sweetheartsWinnings += sweethearts;
		}

		float sweetheartsInfChance = (float)(sweetheartsInfWins) / (float)(sweetheartsInfGames);
		System.out.printf("Sweethearts (infinite deck)\n\tTotal games: %d\tTotal wins: %d\tProbability of occurence: %f\n\tExpected Casino Winnings per Game: %f\n",
							sweetheartsInfGames, sweetheartsInfWins, sweetheartsInfChance, 1 - ((float)(sweetheartsWinnings) / (float)(sweetheartsInfGames)));
	}

	/*playing Sweethearts on a Single Deck*/
	public static void playSweetheartsSingle(Random rng, Deck deck) {
		int sweetheartsSingleGames = 0;
		int sweetheartsSingleWins = 0;
		int sweetheartsWinnings = 0;

		while (sweetheartsSingleGames < GAMES) {
			int card1 = deck.pullCardSingle(rng);
			int card2 = deck.pullCardSingle(rng);
			int card3 = deck.pullCardSingle(rng);

			int sweethearts = getSweetheartsResult(card1, card2, card3);
			if (sweethearts > 0) sweetheartsSingleWins++;
			sweetheartsSingleGames++;
			sweetheartsWinnings += sweethearts;

			deck.rebuild();
		}

		float sweetheartsSingleChance = (float)(sweetheartsSingleWins) / (float)(sweetheartsSingleGames);
		System.out.printf("Sweethearts (single deck)\n\tTotal games: %d\tTotal wins: %d\tProbability of occurence: %f\n\tExpected Casino Winnings per Game: %f\n",
							sweetheartsSingleGames, sweetheartsSingleWins, sweetheartsSingleChance, 1 - ((float)(sweetheartsWinnings) / (float)(sweetheartsSingleGames)));
	}

	/*playing Runny on an Infinite Deck*/
	public static void playRunnyInf(Random rng, Deck deck) {
		int runnyInfGames = 0;
		int runnyInfWins = 0;

		while (runnyInfGames < GAMES) {
			int[] cards = new int[5];
			for (int i = 0; i < 5; i++) {
				cards[i] = deck.pullCardInf(rng.nextFloat());
			}

			if (getRunnyResult(cards)) runnyInfWins++;
			runnyInfGames++;
		}

		float runnyInfChance = (float)(runnyInfWins) / (float)(runnyInfGames);
		System.out.printf("Runny (infinite deck)\n\tTotal games: %d\tTotal wins: %d\tProbability of occurence: %f\n\tExpected Casino Winnings per Game: %f\n",
							runnyInfGames, runnyInfWins, runnyInfChance, 1 - (runnyInfChance * RUNNY_WINNINGS));
	}

	/*playing Runny on a Single Deck*/
	public static void playRunnySingle(Random rng, Deck deck) {
		int runnySingleGames = 0;
		int runnySingleWins = 0;

		while (runnySingleGames < GAMES) {
			int[] cards = new int[5];
			for (int i = 0; i < 5; i++) {
				cards[i] = deck.pullCardSingle(rng);
			}

			if (getRunnyResult(cards)) runnySingleWins++;
			runnySingleGames++;

			deck.rebuild();
		}

		float runnySingleChance = (float)(runnySingleWins) / (float)(runnySingleGames);
		System.out.printf("Runny (single deck)\n\tTotal games: %d\tTotal wins: %d\tProbability of occurence: %f\n\tExpected Casino Winnings per Game: %f\n",
							runnySingleGames, runnySingleWins, runnySingleChance, 1 - (runnySingleChance * RUNNY_WINNINGS));
	}

	/*playing Democracy on an Infinite Deck*/
	public static void playDemocracyInf(Random rng, Deck deck) {
		int democracyInfGames = 0;
		int democracyInfWins = 0;

		while (democracyInfGames < GAMES) {
			int[] cards = new int[9];
			for (int i = 0; i < 9; i++) {
				cards[i] = deck.pullCardInf(rng.nextFloat());
			}

			if (getDemocracyResult(cards)) democracyInfWins++;
			democracyInfGames++;
		}

		float democracyInfChance = (float)(democracyInfWins) / (float)(democracyInfGames);
		System.out.printf("Democracy (infinite deck)\n\tTotal games: %d\tTotal wins: %d\tProbability of occurence: %f\n\tExpected Casino Winnings per Game: %f\n",
							democracyInfGames, democracyInfWins, democracyInfChance, 1 - (democracyInfChance * DEMOCRACY_WINNINGS));
	}

	/*playing Democracy on a Single Deck*/
	public static void playDemocracySingle(Random rng, Deck deck) {
		int democracySingleGames = 0;
		int democracySingleWins = 0;

		while (democracySingleGames < GAMES) {
			int[] cards = new int[9];
			for (int i = 0; i < 9; i++) {
				cards[i] = deck.pullCardSingle(rng);
			}

			if (getDemocracyResult(cards)) democracySingleWins++;
			democracySingleGames++;

			deck.rebuild();
		}

		float democracySingleChance = (float)(democracySingleWins) / (float)(democracySingleGames);
		System.out.printf("Democracy (single deck)\n\tTotal games: %d\tTotal wins: %d\tProbability of occurence: %f\n\tExpected Casino Winnings per Game: %f\n",
							democracySingleGames, democracySingleWins, democracySingleChance, 1 - (democracySingleChance * DEMOCRACY_WINNINGS));
	}

	public static void main(String args[]) {
		Random rng = new Random();
		Deck deck = new Deck();

		playBossInf(rng, deck);
		playBossSingle(rng, deck);

		playTwinsInf(rng, deck);
		playTwinsSingle(rng, deck);

		playBiggieInf(rng, deck);
		playBiggieSingle(rng, deck);

		playSweetheartsInf(rng, deck);
		playSweetheartsSingle(rng, deck);

		playRunnyInf(rng, deck);
		playRunnySingle(rng, deck);

		playDemocracyInf(rng, deck);
		playDemocracySingle(rng, deck);
	}
}