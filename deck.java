import java.util.Random;

class Deck {
	boolean[] deck;

	Deck() {
		deck = new boolean[52];
		for (int i = 0; i < 52; i++) {
			deck[i] = true;
		}
	}

	public int pullCardSingle(Random rng) {
		int card;
		do {
			card = (int) (rng.nextFloat() * 52.0f);
			if (deck[card]) {
				deck[card] = false;
				break;
			}
		} while (!(deck[card]));
		return card;
	}

	public void rebuild(){
		for (int i = 0; i < 52; i++) {
			deck[i] = true;
		}
	}

	public int pullCardInf(float random) {
		return ((int) (random * 52.0f));
	}
}