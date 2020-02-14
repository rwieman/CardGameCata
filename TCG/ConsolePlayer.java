package TCG;

import static TCG.TcgMain.*;

public class ConsolePlayer extends Player {
	protected ConsolePlayer(String name) {
		super(name);
	}

	@Override
	Card chooseAction() {
		if (getHand().size() == 0) {
			println("No more options. Passing turn...");
			return null;
		}

		Integer option = chooseOption();
		if (option == null) {
//			println("Pass the turn");
			return null;
		}
		else {
			Card chosen = hand.removeCard(option);
//			println("Casting power " + chosen.getValue());
			return chosen;
		}
	}

	private Integer chooseOption() {
		while (true) {
			println("Your hand contains " + getHand().size() + " cards:");
			for (int index = 0; index < getHand().size(); index++) {
				println("[" + index + "] Card with power " + getHand().peek(index).getValue());
			}
			println("Please choose an option or ENTER to pass turn (mana: " + getAvailableMana() + "/" + getMaxMana() + ")>");
			var input = readInput();

			if ("".equals(input)) {
				return null;
			}

			int intValue = -1;
			try {
				intValue = Integer.parseInt(input);
			} catch (NumberFormatException e) {}

			if (intValue >= 0 && intValue < getHand().size()) {
				return intValue;
			}

			println("Not a valid option: " + input);
		}
	}
}
