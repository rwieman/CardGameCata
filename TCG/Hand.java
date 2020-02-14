package TCG;

import java.util.*;

import static java.util.Collections.unmodifiableList;

class Hand {
	private final Deck deck;
	private final int maxHandSize;
	private List<Card> inHand = new ArrayList<>();

	Hand(Deck deck, int maxHandSize) {
		this.deck = deck;
		this.maxHandSize = maxHandSize;
	}

	/**
	 * Draw a card. Can only be done if the deck still has cards.
	 *
	 * @return True iff the card was drawn and added to the hand, false if the hand overflowed and discarded the card.
	 */
	boolean drawCard() {
		if (inHand.size() < maxHandSize) {
			inHand.add(deck.draw());
			return true;
		}
		else {
			deck.draw();
			return false;
		}
	}

	Card peek(int index) {
		return inHand.get(index);
	}

	Card removeCard(int index) {
		return inHand.remove(index);
	}

	List<Card> getCards() {
		return unmodifiableList(inHand);
	}

	int size() {
		return inHand.size();
	}
}
