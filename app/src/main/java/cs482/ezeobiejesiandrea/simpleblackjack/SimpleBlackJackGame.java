package cs482.ezeobiejesiandrea.simpleblackjack;

import java.util.Random;

/**
 * Created by Andrea on 10/3/2018.
 * This class represents the Model in the Model-View-Controller structure.
 * This class coordinates with the Controller to enforce the rules of
 * a simple BlackJack game between the dealer (CPU) and 1 player (the user)
 */

public class SimpleBlackJackGame {
    protected Dealer dealer;
    protected Player player;
    private Deck deck1, deck2;
    private String winner;
    protected boolean isWon;

    private Random random;
    private Deck deck;

    /**
     * Default constructor that initializes fields
     */
    protected SimpleBlackJackGame(){
        dealer = new Dealer();
        player = new Player();
        deck1 = new Deck();
        deck2 = new Deck();
        deck = new Deck();

        random = new Random();

        deck1.populateDeck();
        System.out.println("DECK 1 SIZE: " + deck1.size());
        deck2.populateDeck();
        System.out.println("DECK 2 SIZE: " + deck2.size());
        deck.addAll(deck1);
        deck.addAll(deck2);
        System.out.println("DECK SIZE: " + deck.size());
        isWon = false;
    }

    /**
     * This method draws a random card from the deck
     * @return the Card drawn
     */
    protected Card draw()
    {
        int index = random.nextInt(this.deck.size() -1);  //(deck1 + deck2) -1
        //System.out.println("INDEX: " + index);
        Card card = deck.get(index);
        this.deck.remove(card);
        return card;
    }

    /**
     * This method is called whenever the user hits
     * the stop button
     */
    protected void stop()
    {
        if(player.getScore() > dealer.getScore() ){
            isWon = true;
            winner = "PLAYER";
        }
        else if(dealer.getScore() > player.getScore() ){
            isWon = true;
            winner = "DEALER";
        }
    }


    //protected void playerTurn(Card card, int draw)

    /**
     * This method is called when the player hits. The card
     * drawn is added to the player's list of cards and
     * the player's points are tallied
     * @param card the random card drawn from the deck for the player
     */
    protected void playerTurn(Card card)
    {
        player.cards.add(card);
        if(player.getScore() > 21) {
            isWon = true;
            winner = "DEALER";
        }
        else if(player.getScore() == 21)
        {
            isWon = true;
            winner = "PLAYER";
        }
        checkTie();
    }

    /**
     * This method is called when the dealer hits. The card
     * drawn is added to the dealer's list of cards and
     * the dealer's points are tallied
     * @param card the random card drawn from the deck by the dealer
     */
    protected void dealerTurn(Card card)
    {
        dealer.cards.add(card);

        if(dealer.getScore() > 21){
        isWon = true;
        winner = "PLAYER";
        }
        else if(dealer.getScore() > player.getScore()){
        isWon = true;
        winner = "DEALER";
        }
        checkTie();
    }

    /**
     * This method checks if the dealer has blackjack
     * in the initial hand
     * @return true if dealer has blackjack, false otherwise
     */
    protected boolean dealerBlackJack(){
        if(dealer.getScore() == 21)
        {
            isWon = true;
            this.winner = "DEALER";
            return true;
        }
        else
            return false;
    }

    /**
     * This method checks if the player has blackjack
     * in the initial hand
     * @return true if player has blackjack, false otherwise
     */
    protected boolean playerBlackJack(){
        if (player.getScore() == 21)
        {
            isWon = true;
            this.winner = "PLAYER";
            return true;
        }
        else
            return false;
    }

    /**
     * This method checks if the dealer and the player are tied
     */
    private void checkTie()
    {
        if(player.getScore() == dealer.getScore())
        {
            isWon = true;
            winner = "TIE";
        }
    }

    /**
     * This method gets the winner from the Model so that the
     * Controller can display it through the View
     * @return
     */
    protected String getWinner()
    {
        return winner;
    }

}
