package TCG;

import java.util.Scanner;

public class TcgMain {
	// http://codingdojo.org/kata/TradingCardGame/
	public static void main(String[] args) {
		var game = new TcgMain();
		game.setup();
		game.run();
	}

	Player player1;
	Player player2;

	private void setup() {
		player1 = new ConsolePlayer("Player 1");
		player2 = new ConsolePlayer("Player 2");

		player1.initialize();
		player2.initialize();
	}

	private void run() {
		int turn = 0;

		println("The battle begins!");
		while (gameContinues()) {
			turn++;
			doTurn(turn % 2 == 0 ? player2 : player1);
		}

		if (player1.isAlive()) {
			println(player1.getName() + " is victorious!");
		}
		else {
			println(player2.getName() + " wins!");
		}
	}

	private boolean gameContinues() {
		return player1.isAlive() && player2.isAlive();
	}

	private void doTurn(Player player) {
		println(player.getName() + " starts his turn:");
		player.increaseMaxMana();
		player.resetMana();
		player.drawCard();

		Player opponent = getOpponent(player);

		println("You are at " + player.getCurrentLife() + " life; Opponent has " + opponent.getCurrentLife() + " life and " + opponent.getHand().size() + " cards");

		while (takeTurnAction(player)) {
			println(player.getName() + " continues his turn:");
		}
	}

	private boolean takeTurnAction(Player player) {
		var action = player.chooseAction();
		if (action == null) {
			return false;
		}

		if (action.getValue() == 0) {
			println(player.getName() + " produces some noise and wind, but achieves nothing.");
		}
		else {
			if (action.getValue() > player.getAvailableMana()) {
				println(player.getName() + " tried to cast a powerful spell, but it fizzles in his hands.");
			}
			else {
				println(player.getName() + " deals " + action.getValue() + " damage to " + getOpponent(player).getName() + "!");
				player.spendMana(action.getValue());
				getOpponent(player).modifyLife(-action.getValue());
				if (!getOpponent(player).isAlive()) {
					println(getOpponent(player).getName() + " was burnt to crisp!");
					return false;
				}
			}
		}

		return true;
	}

	private Player getOpponent(Player self) {
		if (player1 == self) {
			return player2;
		}
		else {
			return player1;
		}
	}

	static void print(String out) {
		System.out.print(out);
	}

	static void println(String out) {
		System.out.println(out);
	}

	static String readInput() {
		return new Scanner(System.in).nextLine();
	}
}
