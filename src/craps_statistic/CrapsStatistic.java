package craps_statistic;

public class CrapsStatistic {

	public static void main(String[] args) {
		// Postavljamo brojac odigranih partija.
		final int count = 10000;
		// Kreiramo brojac za pobjede.
		int win = 0;
		
		for (int i = 0; i < count; i++) {
			// Ako je rezultat true uvecavamo brojac pobjeda.
			if (checkResult(play())) {
				win++;
			}
		}

		// Ispisujemo konacan rezultat.
		System.out.println("Win " + win + "\nLoss " + (count - win));
	}

	
	// Metoda za bacanje kocke vraca izmedju 1 i 6.
	public static int roll() {
		return (int) (Math.random() * 6) + 1;
	}

	
	// Metoda provjerava rezultat.
	public static boolean checkResult(int result) {
		// Ako je zbir kocki 2, 3, ili 12 izgubli smo.
		if (result == 2 || result == 3 || result == 12) {
			return false;
			// Ili ako je zbir kocki 7 ili 11 pobijedili smo.
		} else if (result == 7 || result == 11) {
			return true;
		} else {
			// Postavljamo uslov petlje.
			int prekid = 1;
			/*
			 * Prvo bacanje smo postavili kao point, zatim bacamo ponovo kocke.
			 * Petlja vrti sve dok ne dobijemo 7, sto znaci da smo izgubili, ili
			 * dok ne dobijemo rezultat jednak prvom bacanju(pointu), onda smo
			 * pobijedili.
			 */
			while (prekid != 0) {
				// Ponovo bacamo kocke.
				int result1 = play();
				// Ako je rezultat 7 onda smo izgubili.
				if (result1 == 7) {
					prekid = 0;
					return false;
				}
				// Ako je rezultat jednak pointu onda smo pobijedili.
				if (result1 == result) {
					prekid = 0;
					return true;
				}
			}
		}
		return false;
	}

	
	public static int play() {
		// Pozivamo metodu za bacanje prve kocke.
		int dice1 = roll();
		// Pozivamo metodu za bacanje druge kocke.
		int dice2 = roll();
		// Vracamo rezultat.
		return dice1 + dice2;
	}

}
