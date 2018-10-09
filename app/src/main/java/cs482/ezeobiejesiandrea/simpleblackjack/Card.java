package cs482.ezeobiejesiandrea.simpleblackjack;

/**
 * Created by Andrea on 10/3/2018.
 * This class represents a playing card
 */

public class Card {

    //joker = 0
    //ace = 1
    // 2-10
    //jack = 11
    //queen = 12
    //king =  13

    private String suit;
    private int rank;
    private int value;
    private String image;

    /**
     *  Parameterized Constructor that creates a Card
     * @param suit Card suit (clubs, hearts, spades, diamonds)
     * @param rank Card rank  (1-13)
     * @param points Card pip value
     */
    public Card(String suit, int rank, int points)
    {
        this.suit = suit;
        this.rank = rank;
        this.value = points;
    }

    /**
     * Setter method for Card to set an Image from View
     * @param image String file name of card image
     */
    protected void setImage(String image) {
        this.image = image;
    }

    /**
     * Getter method for card suit
     * @return the suit of the card
     */
    protected String getSuit()
    {
        return suit;
    }

    /**
     * Getter method for card rank
     * @return the rank of the card
     */
    protected int getRank()
    {
        return rank;
    }

    /**
     * Getter method for card image
     * @return the file name of the card image
     */
    protected String getImage() {
        return image;
    }

    /**
     * Getter method for card value
     * @return pip value of card
     */
    protected int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "SUIT: " + getSuit() + ", RANK: " + getRank() + " VALUE: " + getValue()+ ", IMAGE: " + getImage();
    }
}
