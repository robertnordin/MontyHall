package se.monty;

import java.util.Random;

public class GameSetup {
	private static Random rnd = new Random(System.currentTimeMillis());
	private int winner;
	private int remainingSelection;
	private int firstSelection;
	private int hostSelection;

	/**
	 * Creates a game setup where one door 1-3 is the winner.
	 */
	public GameSetup() {
		this.winner = rnd.nextInt(3) + 1;
	}

	/**
	 * Returns the game hosts alternative that is NOT the winner, nor the
	 * selection.
	 * 
	 * @return
	 */
	public int getGameHostsAlternative() {
		hostSelection = winner;
		while (hostSelection == winner || hostSelection == firstSelection) {
			hostSelection = rnd.nextInt(3) + 1;
		}		
		remainingSelection = (6 - firstSelection - hostSelection);
		return hostSelection;
	}
	
	/**
	 * Contestant selects one of the doors 1-3.
	 * @param firstSelection
	 */
	public int firstSelection(int firstSelection) {
		if (firstSelection < 1 && firstSelection > 4) {
			throw new IllegalArgumentException("Illegal value: " + firstSelection);
		}
		this.firstSelection = firstSelection;
		return firstSelection;
	}

	public int getRemainingSelection() {
		return remainingSelection;
	}

	public int getWinningDoor() {
		return winner;
	}

	public boolean isTheWinningDoor(int selectedDoor) {
		if (selectedDoor < 1 || selectedDoor > 3) {
			throw new IllegalArgumentException("illegal value. Valid value is in range 1-3 -> " + selectedDoor);
		}
		return (winner == selectedDoor);
	}

	@Override
	public String toString() {
		return "GameSetup [winner=" + winner + ", remainingSelection=" + remainingSelection + ", firstSelection="
				+ firstSelection + ", hostSelection=" + hostSelection + "]";
	}
	
	
}
