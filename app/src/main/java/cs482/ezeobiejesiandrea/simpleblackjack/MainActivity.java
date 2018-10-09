package cs482.ezeobiejesiandrea.simpleblackjack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Andrea
 * This class represents the View that starts the app
 */
public class MainActivity extends AppCompatActivity {
    //FIXME Make GAME CLASS A SINGLETON - MAYBE
    //protected static HashMap<Card, String> cardMap;
    private Button newGameButton;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //cardMap = new HashMap<>();
        newGameButton = findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });
        //populateMap();
    }

    /**
     * This method starts the actual game by calling the Controller class and its
     * corresponding view
     */
    private void start()
    {
        intent = new Intent(this, Controller.class);
        startActivity(intent);
    }

   //protected void populateMap(){
//        Card card;
//        String image;
//        ArrayList<String> suits = new ArrayList<>();
//        suits.add("clubs");
//        suits.add("diamonds");
//        suits.add("hearts");
//        suits.add("spades");
//
//        //put all cards except joker
//        for(String suit: suits)
//        {
//            card = new Card(suit, 1);
//            image = "ace_of_" + suit + EXT;
//            cardMap.put(card , image);
//
//            card = new Card(suit, 11);
//            image = "jack_of_" + suit + EXT;
//            cardMap.put(card , image);
//
//            card = new Card(suit, 12);
//            image = "queen_of_" + suit + EXT;
//            cardMap.put(card , image);
//
//            card = new Card(suit, 13);
//            image = "king_of_" + suit + EXT;
//            cardMap.put(card , image);
//
//            for(int i = 2; i <= 10; i++){
//                card = new Card(suit, i);
//                image = String.valueOf(i) + "_of_" + suit + EXT;
//                cardMap.put(card , image);
//            }
//        }
  //  }

}
