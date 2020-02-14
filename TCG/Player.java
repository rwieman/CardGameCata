package TCG;

abstract class Player {
	private static final int STARTING_LIFE = 30;
	private static final int MANA_LIMIT = 10;
	private static final int MAX_HAND_SIZE = 5;
	private final String name;

	int life = STARTING_LIFE;
	int maxMana = 0;
	int currentMana = 0;
	Deck deck;
	Hand hand;

	protected Player(String name) {
		this.name = name;
	}

	final String getName() {
		return name;
	}

	protected void initialize() {
		deck = new Deck();
		hand = new Hand(deck, MAX_HAND_SIZE);
		drawCard();
		drawCard();
		drawCard();
	}

	/**
	 * Choose which card to play or {@code null} to pass turn.
	 * The card must be removed from the hand.
	 */
	abstract Card chooseAction();

	Deck getDeck() {
		return deck;
	}

	Hand getHand() {
		return hand;
	}

	final int getCurrentLife() {
		return life;
	}

	final void modifyLife(int amount) {
		life += amount;
	}

	final boolean isAlive() {
		return life > 0;
	}

	final int getMaxMana() {
		return maxMana;
	}

	final void increaseMaxMana() {
		if (maxMana == MANA_LIMIT) {
			return;
		}

		maxMana++;
	}

	final void resetMana() {
		currentMana = maxMana;
	}

	final int getAvailableMana() {
		return currentMana;
	}

	final void spendMana(int amount) {
		if (amount > currentMana) {
			throw new IllegalArgumentException("" + currentMana + " mana available, cannot spend " + amount);
		}

		currentMana -= amount;
	}

	final void drawCard() {
		hand.drawCard();
	}
}
