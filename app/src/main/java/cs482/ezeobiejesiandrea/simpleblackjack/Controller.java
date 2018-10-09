package cs482.ezeobiejesiandrea.simpleblackjack;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author Andrea
 * This class corresponds to the Table Activity for the View. In the Model-View-Contoller
 * hierarchy, this class is the Controller that interacts with the Game Model and the
 * XML View
 */
public class Controller extends AppCompatActivity {
    private ImageView dealerFaceCard1, dealerFaceCard2, dealerDraw1, dealerDraw2, dealerDraw3;
    private ImageView playerCard1, playerCard2, playerDraw1, playerDraw2, playerDraw3;
    private Button hitBtn, stopBtn;

    private SimpleBlackJackGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        game = new SimpleBlackJackGame();

        dealerDraw1 = findViewById(R.id.dealer_draw1);
        dealerDraw1.setImageResource(R.drawable.back);
        dealerDraw1.setBackgroundColor(Color.RED);

        dealerDraw2 = findViewById(R.id.dealer_draw2);
        dealerDraw2.setImageResource(R.drawable.back);

        dealerDraw3 = findViewById(R.id.dealer_draw3);
        dealerDraw3.setImageResource(R.drawable.back);

        dealerFaceCard1 = findViewById(R.id.face_up1);
        dealerFaceCard2 = findViewById(R.id.face_up2);


        playerCard1 = findViewById(R.id.player_card1);
        playerCard2 = findViewById(R.id.player_card2);

        playerDraw1 = findViewById(R.id.player_draw1);
        playerDraw1.setImageResource(R.drawable.back);

        playerDraw2 = findViewById(R.id.player_draw2);
        playerDraw2.setImageResource(R.drawable.back);

        playerDraw3 = findViewById(R.id.player_draw3);
        playerDraw3.setImageResource(R.drawable.back);

        hitBtn = findViewById(R.id.hit);
        stopBtn = findViewById(R.id.stop);

        setTable();
    }

    /**
     * This method creates the initial view for the table by drawing random
     * cards from the deck - 2 for the dealer and 2 for the player
     */
    private void setTable()
    {
        Card dealerCard1 = game.draw();
        Card dealerCard2 = game.draw();

        Card playerFaceCard1 = game.draw();
        Card playerFaceCard2 = game.draw();

        String dealerCard1Image = dealerCard1.getImage();
        int dealerCard1ID = getResources().getIdentifier(dealerCard1Image,"drawable",getPackageName());
//        System.out.println("CARD ID: " + dealerCard1ID);
        dealerFaceCard1.setImageResource(dealerCard1ID);
        dealerFaceCard1.setBackgroundColor(Color.WHITE);

        dealerFaceCard2.setImageResource(getResources().getIdentifier(dealerCard2.getImage(),"drawable",getPackageName()));
        dealerFaceCard2.setBackgroundColor(Color.WHITE);

        game.dealer.initCards(dealerCard1, dealerCard2);  //initialize dealer's points

        playerCard1.setImageResource(getResources().getIdentifier(playerFaceCard1.getImage(),"drawable",getPackageName()));
        playerCard1.setBackgroundColor(Color.WHITE);

        playerCard2.setImageResource(getResources().getIdentifier(playerFaceCard2.getImage(),"drawable",getPackageName()));
        playerCard2.setBackgroundColor(Color.WHITE);

        game.player.initCards(playerFaceCard1, playerFaceCard2);  //initialize player's points

        //before playing, check if either the dealer or the player got blackjack
        // on the initial hand
        if(game.dealerBlackJack() || game.playerBlackJack())  //System.out.println(game.getWinner() + " HAS WON");
            endGame();
        else
            play();
    }

    /**
     * This method interacts with the model and the view to make the app
     * play the game
     */
    private void play()
    {
        //allow the player to stop the game at anytime
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println("STOP CLICKED!!!!");
                game.stop();
                endGame();
            }
        });

        //in this loop, the player and the dealer will each get 3 turns to hit
        //after each draw, the points of the current player are checked to
        //see if the game has been won
        for(int i = 1; i <=3 ; i++){
            //The game has not been won so it is the player's turn
            hitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Drawable.ConstantState constantState = getResources().getDrawable(R.drawable.back, null).getConstantState();
                    //System.out.println("HIT CLICKED!!!!");
                    Card playerDraw = game.draw();
                    System.out.println("PD1 ID: " + playerDraw1.getDrawable().toString());
                    //System.out.println("PD2 ID: " + playerDraw2.getId());
                    //boolean equal = playerDraw1.getDrawable().equals(getResources().getDrawable(R.drawable.back, null));
                    boolean equal = playerDraw1.getDrawable().getConstantState() == constantState;
                    System.out.println("EQUAL? : " + equal);

                    if (playerDraw1.getDrawable().getConstantState() == constantState) { //playerDraw1.setImageResource(getResources().getIdentifier(playerDraw.getImage(),"drawable",getPackageName()));
                        setCardView(playerDraw1, playerDraw);
                    } else if (playerDraw2.getDrawable().getConstantState() == constantState) {
                        setCardView(playerDraw2, playerDraw);
                    } else if (playerDraw3.getDrawable().getConstantState() == constantState) {
                        setCardView(playerDraw3, playerDraw);
                    }

                    game.playerTurn(playerDraw);

                    if(game.isWon){ //player has to be winner
                        //System.out.println(game.getWinner() + " HAS WON");
                        //System.out.println(game.getWinner() + " SCORE: " + game.player.getScore());
                        endGame();
                    }
                    else
                        afterHit();
                    }

                    private void afterHit() {
                        //now it's the dealer's turn
                        String text = "DEALER'S TURN!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();

                        Drawable.ConstantState constantState = getResources().getDrawable(R.drawable.back, null).getConstantState();
                        System.out.println("DEALER'S TURN!!!!");
                        Card dealerDraw = game.draw();

                        //I tried to delay the time between the player draw and dealer draw but this did not work
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                        if (dealerDraw1.getDrawable().getConstantState() == constantState) {
                            setCardView(dealerDraw1, dealerDraw);
                        } else if (dealerDraw2.getDrawable().getConstantState() == constantState) {
                            setCardView(dealerDraw2, dealerDraw);
                        } else if (dealerDraw3.getDrawable().getConstantState() == constantState) {
                            setCardView(dealerDraw3, dealerDraw);
                        }
                        game.dealerTurn(dealerDraw);
                        if(game.isWon){ //dealer has to be winner
                            //System.out.println(game.getWinner() + " HAS WON!! ");
                            //System.out.println(game.getWinner() + " SCORE: " + game.dealer.getScore());
                            endGame();
                        }
                    }
                });

            if(game.isWon){
                endGame(); break;
            }
        }
    }

    /**
     * This method sets the the Image for the Card in the View
     * @param cardView the ImageView object to be updated
     * @param cardDrawn the random Card object drawn from the Deck by the Model
     */
    private void setCardView(ImageView cardView, Card cardDrawn){
        cardView.setImageResource(getResources().getIdentifier(cardDrawn.getImage(),"drawable",getPackageName()));
        cardView.setBackgroundColor(Color.WHITE);
    }

    /**
     * This method ends the game. It is only called when the
     * Model has detected a winner. In this method, an Alert Dialog
     * is created and displays who won the game, and asks the player
     * if he/she would like to play again or exit the app
     */
    private void endGame(){
        System.out.println("END GAME HAS BEEN CALLED!!!!");
        final Intent intent = new Intent(this, Controller.class);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(game.getWinner() + " HAS WON");
        alertDialogBuilder.setPositiveButton("PLAY AGAIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //restart app
                finish();
                startActivity(intent);
                //onRestart();
//                Intent intent = getBaseContext().getPackageManager()
//                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
//                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
            }
        });

        alertDialogBuilder.setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                System.exit(0);
            }
        });
        alertDialogBuilder.show();

        swipePlayAgain();
    }

    /**
     * This method allows the player to swipe the
     * screen in order to start a new game
     */
    private void swipePlayAgain()
    {
        //create OnTouchListener
        //if screen is swiped, restart this activity
    }
}
