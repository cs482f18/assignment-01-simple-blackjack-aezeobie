package cs482.ezeobiejesiandrea.simpleblackjack;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Andrea on 10/3/2018.
 * This class represents the Dealer
 */

public class Dealer {
    protected ArrayList<Card> cards;
    private int score;

    /**
     * Default constructor
     */
    public Dealer()
    {
        cards = new ArrayList<>();
    }

    /**
     * initialize the dealer's set of cards and total points
     * @param card1 first dealt card
     * @param card2 second dealt card
     */
    protected void initCards(Card card1, Card card2)
    {
        cards.add(card1);
        cards.add(card2);
        for(Card card: cards)
            score += card.getValue();
        //System.out.println("DEALER START SCORE: " + score);
    }

    /**
     * This method calculates the dealer's current score
     * @return the dealer's score so far
     */
     protected int getScore()
     {
         int endScore = 0;
         for(int i = 0; i < this.cards.size(); i++){
             endScore += cards.get(i).getValue();
         }
         return endScore;
         //return score + (cards.get(cards.size() - 1).getValue());
     }

    /**
     * This method shuffles the deck
     * @param deck
     */
    protected void shuffle(Deck deck)
    {
        Collections.shuffle(deck);
    }

}
