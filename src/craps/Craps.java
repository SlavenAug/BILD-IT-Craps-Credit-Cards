/*
 * Craps is a popular dice game played in casinos. Write a program
 * to play a variation of the game, as follows:
 * Roll two dice. Each die has six faces representing values 1, 2, …, and 6, respectively.
 * Check the sum of the two dice. If the sum is 2, 3, or 12 (called craps), you
 * lose; if the sum is 7 or 11 (called natural), you win; if the sum is another value
 * (i.e., 4, 5, 6, 8, 9, or 10), a point is established. Continue to roll the dice until either
 * a 7 or the same point value is rolled. If 7 is rolled, you lose. Otherwise, you win.
 * Your program acts as a single player.
 */
package craps;

public class Craps {

	public static void main(String[] args) {
		// Pozivamo metodu za provjeru rezultata.
		checkResult(play());
	}

	// Metoda za bacanje kocke vraca izmedju 1 i 6.
	public static int roll() {
		return (int) (Math.random() * 6) + 1;
	}

	// Metoda provjerava rezultat.
	public static void checkResult(int result) {
		// Ako je zbir kocki 2, 3, ili 12 ispsuje da smo izgubili.
		if (result == 2 || result == 3 || result == 12) {
			System.out.println("You lose");
			// Ili ako je zbir kocki 7 ili 11 ispisuje da smo pobijedili.
		} else if (result == 7 || result == 11) {
			System.out.println("You win");
		} else {
			// Postavljamo uslov petlje.
			boolean prekid = true;
			// Ispisujemo prvo bacanje kao point.
			System.out.println("Point is " + result);
			/*
			 * Prvo bacanje smo postavili kao point, zatim bacamo ponovo kocke.
			 * Petlja vrti sve dok ne dobijemo 7, sto znaci da smo izgubili, ili dok ne 
			 * dobijemo rezultat jednak prvom bacanju(pointu), onda smo pobijedili.
			 */
			while (prekid) {
				// Ponovo bacamo kocke.
				int result1 = play();
				// Ako je rezultat 7 onda smo izgubili.
				if (result1 == 7) {
					System.out.println("You lose");
					prekid = false;
					break;
					// Ako je rezultat jednak pointu onda smo pobijedili.
				} else if (result1 == result) {
					System.out.println("You win");
					prekid = false;
					break;
				}
			}
		}
	}

	public static int play() {
		// Pozivamo metodu za bacanje prve kocke.
		int dice1 = roll();
		// Pozivamo metodu za bacanje druge kocke.
		int dice2 = roll();
		// Dobijamo rezultat bacanja dve kocke.
		int result = dice1 + dice2;
		// Ispisujemo rezultat.
		System.out.println("You rolled " + dice1 + " + " + dice2 + " = " + result);
		// Vracamo rezultat.
		return result;
	}

}
