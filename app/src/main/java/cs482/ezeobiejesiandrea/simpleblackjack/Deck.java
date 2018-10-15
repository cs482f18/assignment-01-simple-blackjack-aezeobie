package cs482.ezeobiejesiandrea.simpleblackjack;

import java.util.ArrayList;

/**
 * -- Alwasy use the Javadoc tags @author and @version with the date in the header. e.g., @version 1.0 10/10/2018 --
 * Created by Andrea on 10/3/2018.
 * This class represents a deck of cards
 */

public class Deck extends ArrayList<Card> {
    
    private ArrayList<String> suits;
   // private static String EXT = ".png";

    /**
     * Default constructor
     */
    public Deck()
    {
        suits = new ArrayList<>();
        suits.add("clubs");
        suits.add("diamonds");
        suits.add("hearts");
        suits.add("spades");
    }

    /**
     * This method populates the deck of cards with the
     * appropriate suits, ranks, point values, and associated
     * images from the View
     * @return the populated deck of cards
     */
    protected Deck populateDeck()
    {
        Card card;
        String image;
        //should create deck of 52 cards (2 jokers at end for total of 54) FIXME: ADD JOKERS!!!
        for(String suit: suits)
        {
            card = new Card(suit, 1, 11);
            image = "ace_of_" + suit;  //+ EXT;
            card.setImage(image);
            this.add(card);

            card = new Card(suit, 11, 10);
            image = "jack_of_" + suit; // + EXT;
            card.setImage(image);
            this.add(card );

            card = new Card(suit, 12, 10);
            image = "queen_of_" + suit; // + EXT;
            card.setImage(image);
            this.add(card );

            card = new Card(suit, 13, 10);
            image = "king_of_" + suit; //+ EXT;
            card.setImage(image);
            this.add(card );

            int i;
            // use switch-case in these scenarios
            for(i = 2; i < 11; i++){
                card = new Card(suit, i, i);
                if(i == 2)
                    image = "two_of_" + suit; // + EXT;
                else if (i == 3)
                    image = "three_of_" + suit;  // + EXT;
                else if(i == 4)
                    image = "four_of_" + suit; // + EXT;
                else if(i == 5)
                    image = "five_of_" + suit;  // + EXT;
                else if(i == 6)
                    image = "six_of_" + suit; // + EXT;
                else if(i == 7)
                    image = "seven_of_" + suit; //+ EXT;
                else if(i == 8)
                    image = "eight_of_" + suit; // + EXT;
                else if(i == 9)
                    image = "nine_of_" + suit; // + EXT;
                else if(i == 10)
                    image = "ten_of_" + suit; // + EXT;

                card.setImage(image);
                this.add(card );
            }
        }
        //System.out.println("DECK HAS " + this.size() + " CARDS AFTER POPULATING!!!!!!!!!!!!!!");
        return this;
    }


    /**
     * This method prints the deck of cards
     */
    // why do you want these methods to be protected?
    protected void printDeck()
    {
        for(Card card: this)
        {
            System.out.println(card.toString());
        }
    }


}
