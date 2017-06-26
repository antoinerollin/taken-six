package takensix.card;

/**
 * The Class Card. A Card has a unique number and a score used to calculate
 * Players score.
 * 
 */
public class Card {

	/** The number. */
	private int number;

	/** The score. */
	private int score;

	/**
	 * Instantiates a new card.
	 *
	 * @param number
	 *            the number
	 * @param score
	 *            the score
	 */
	public Card(int number, int score) {
		super();
		this.number = number;
		this.score = score;
	}

	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Sets the number.
	 *
	 * @param number
	 *            the new number
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets the score.
	 *
	 * @param score
	 *            the new score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "[N°" + number + " (" + score + "p)]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		result = prime * result + score;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (number != other.number)
			return false;
		if (score != other.score)
			return false;
		return true;
	}

}
