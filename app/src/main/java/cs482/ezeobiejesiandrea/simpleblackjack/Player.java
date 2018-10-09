package cs482.ezeobiejesiandrea.simpleblackjack;

import java.util.ArrayList;

/**
 * Created by Andrea on 10/3/2018.
 * This class represents the player
 */
public class Player {
    protected ArrayList<Card> cards;
    private int score;

    /**
     * Default constructor
     */
    public Player()
    {
        cards = new ArrayList<>();
    }

    /**
     * initialize the player's set of cards and total points
     * @param card1 first dealt card
     * @param card2 second dealt card
     */
    protected void initCards(Card card1, Card card2)
    {
        cards.add(card1);
        cards.add(card2);
        for(Card card: cards)
            score += card.getValue();
        //System.out.println("PLAYER START SCORE: " + score);
    }

    /**
     * This method calculates the player's current score
     * @return the player's score so far
     */
    protected int getScore(){
        int endScore = 0;
        for(int i = 0; i < this.cards.size(); i++){
            endScore += cards.get(i).getValue();
        }
        return endScore;
    }
}
