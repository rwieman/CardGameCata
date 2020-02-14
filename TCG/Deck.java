package TCG;

import java.util.*;

class Deck {
	private static final int[] INITIAL_CARD_VALUES = new int[] {0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 8};

	private List<Card> deckOfCards = new ArrayList<>();

	Deck() {
		initialize();
	}

	private void initialize() {
		for (int value : INITIAL_CARD_VALUES) {
			deckOfCards.add(new Card(value));
		}

		Collections.shuffle(deckOfCards);
	}

	int getDeckSize() {
		return deckOfCards.size();
	}

	Card draw() {
		if (deckOfCards.size() > 0) {
			return deckOfCards.remove(0);
		}

		return null;
	}
}

