package se.monty;

import java.text.DecimalFormat;
import java.util.Random;

import javax.print.attribute.standard.NumberUpSupported;

public class MontyHall {

	private static Random rnd = new Random(System.currentTimeMillis());

	private static int random() {
		return (rnd.nextInt(3) + 1);
	}

	public static void main(String[] args) {
		int numberOfGamesToRun = 10000;
		if (args.length == 1) {
			try {
				numberOfGamesToRun = Integer.valueOf(args[0]);
			}
			catch (NumberFormatException ex) {
				System.err.print("Could not parse: " + args[0] + " as integer, using default: " + numberOfGamesToRun);
			}
		}
		int firstSelectionWins = 0;
		int switchSelectionWins = 0;

		for (int i = 0; i < numberOfGamesToRun; i++) {
			GameSetup game = new GameSetup();
			int firstSelection = game.firstSelection(random());
			game.getGameHostsAlternative(); // Could be called from inside firstSelection, but this shows game flow better.
			int remainingDoorSelection = game.getRemainingSelection();
			if (game.isTheWinningDoor(firstSelection)) {
				firstSelectionWins++;
			} else if (game.isTheWinningDoor(remainingDoorSelection)) {
				switchSelectionWins++;
			}			
			System.out.println(game);
		}
		
		DecimalFormat format = new DecimalFormat(".##");		
		double firstResult = ((double)firstSelectionWins/(double)numberOfGamesToRun) * 100;
		double switchResult = ((double)switchSelectionWins/(double)numberOfGamesToRun) * 100;
		System.out.println("======   RESULT  ======");
		System.out.println("FirstSelection wins: " + firstSelectionWins + "/" + numberOfGamesToRun + " (" + format.format(firstResult) + "%)");
		System.out.println("RemainSelection wins: " + switchSelectionWins +"/" + numberOfGamesToRun + " (" + format.format(switchResult) + "%)");

	}
}
