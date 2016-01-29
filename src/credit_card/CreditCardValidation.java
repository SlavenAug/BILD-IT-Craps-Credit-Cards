package credit_card;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CreditCardValidation {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a credit card number as a long integer: ");

		boolean prekid = true;
		while (prekid) {
			try {
				// Unosimo broj kartice.
				long number = input.nextLong();
				// Provjeravamo validnost broja kartice.
				System.out.println(number + " is " + (isValid(number) ? "valid!" : "invalid!"));
				prekid = false;
			} catch (InputMismatchException e) {
				System.out.println("Niste unijeli cjeli broj, pokusajte ponovo!");
				input.nextLine();
			}
		}
		input.close();

	}

	// Metoda koja provjerava validnost broja kartice.
	public static boolean isValid(long number) {
		// Ako broj kartice nije duzine 16 ispisujemo gresku.
		if (getSize(number) != 16) {
			System.out.println("Card number must be 16 number long");
			return false;
		}

		// Sabiremo sumu parnih i neparnih pozicija brojeva kartice.
		int sum = sumOfDoubleEvenPlace(number) + sumOfOddPlace(number);
		// Ako broj nije djeljiv sa 10 onda ispisujemo poruku i prekidamo program.
		if (sum % 10 != 0) {
			System.out.println("Unknown number card");
			return false;
		}
		// Provjeravamo prefix kartice i ispisujemo rezultat.
		if (prefixMatched(number, 4)) {
			System.out.println("Is VISA card");
		} else if (prefixMatched(number, 5)) {
			System.out.println("Is Master card");
		} else if (prefixMatched(number, 6)) {
			System.out.println("Is Discover card");
		} else if (prefixMatched(number, 37)) {
			System.out.println("Is American Expres card");
		} else { // Ako prefix nije validan ispisujemo poruku.
			System.out.println("Check your number");
			return false;
		}
		// Provjera je prosla bez greske i vracamo true;
		return true;
	}

	// Metoda sabire brojeve na parnim mjestima kartice.
	public static int sumOfDoubleEvenPlace(long number) {
		// Pretvaramo broj u string.
		String numbers = number + "";
		// Kreiramo sumu brojeva.
		int sum = 0;
		// Provjeravamo svaki drugi karakter stringa,
		// pocinjemo od 0 i biramo svaki drugi,
		// znaci biramo parna mjesta brojeva.
		for (int i = 0; i < numbers.length(); i += 2) {
			sum += getDigit((numbers.charAt(i) - '0') * 2);
		}
		// Vracamo sumu brojeva na parnim mjestima.
		return sum;
	}

	// Metoda provjerava da li je broj jednocifren, 
	// sabira cifra i vraca jednocifren broj.
	public static int getDigit(int number) {
		// Ako je broj veci od 9, sabiremo mu cifre,
		// i dobijamo jednocifren broj.
		if (number > 9) {
			int sum = 0;
			// Sabiremo cifre.
			while (number != 0) {
				sum += number % 10;
				number /= 10;
			}
			// Vracamo sumu.
			return sum;
		}
		return number;
	}

	// Metoda sabire brojeve na neparnim pozicijama kartice.
	public static int sumOfOddPlace(long number) {
		// Pretvaramo broj u string.
		String numbers = number + "";
		// Kreiramo sumu brojeva.
		int sum = 0;

		// Provjeravamo svaki drugi karakter stringa,
		// pocevsi od prve pozicije jer sabiremo
		// brojeve na neparnim pozicijama.
		for (int i = 1; i < numbers.length(); i += 2) {
			sum += (numbers.charAt(i) - '0');
		}
		// Vraca sumu na neparnim pozicijama.
		return sum;
	}

	// Metoda za provjeru prefixa u broju.
	public static boolean prefixMatched(long number, int d) {
		// Ako prefix nije jednak vracamo false,
		if (getPrefix(number, getSize(d)) != d) {
			return false;
		}
		// ako jeste vracamo true.
		return true;
	}

	// Metoda za provjeru duzine broja kartice.
	public static int getSize(long d) {
		return ("" + d).length();
	}

	// Metoda za dobijanje prefiksa broja kartice.
	public static long getPrefix(long number, int k) {
		String numbers = number + "";

		// Ako je broj kartice manji od broja prefixa vraca broj kartice.
		if (numbers.length() < k) {
			return number;
		} else {
			// Kreiramo varijablu za cuvanje znakova broja kartice.
			String prefix = "";
			for (int i = 0; i < k; i++) {
				// Izdvajamo broj prefixa.
				prefix += numbers.charAt(i);
			}
			// Parsujemo izdvojene znakove u long broj i vracamo prefix.
			return Long.parseLong(prefix);
		}
	}

}
