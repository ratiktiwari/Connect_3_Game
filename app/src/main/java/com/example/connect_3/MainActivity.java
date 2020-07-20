package com.example.connect_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //Below variable is used to bring in the alternate colour counters
    int activePlayer=0; //0 is yellow and 1 is red

    int winValue=0;

    int[] gameState={2,2,2,2,2,2,2,2,2}; //where there is 2, it means that those positions  are empty
    //i.e. neither yellow nor red is at that tag

    int[][] winningPositions={{0,1,2}/*First Row is same*/,{3,4,5}/*Second Row is same*/,{6,7,8}/*Third Row is same*/,{0,3,6}/*First column is same*/,{1,4,7}/*Second column is same*/,{2,5,8}/*Third column is same*/,{0,4,8}/*Backslash Diagonal is same*/,{2,4,6}}/*Frontslash Diagonal is same*/;

    int isTwoPresent=1;

    public void startGame(View view)
    {
        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
        ImageView appLogo = findViewById(R.id.applogo);
        Button playButton = findViewById(R.id.playButton);
        TextView text = findViewById(R.id.text);

        gridLayout.setTranslationX(-2500);
        gridLayout.setVisibility(View.VISIBLE);

        appLogo.setVisibility(View.INVISIBLE);
        playButton.setVisibility(View.INVISIBLE);
        text.setVisibility(View.INVISIBLE);

        gridLayout.animate().translationXBy(2500).setDuration(1000);
    }

    public int tieCheck()    //Checking for tie - if 2 is not present in gameState and winValue=0 then it is tie.
    {
        for(int i=0; i<gameState.length;i++)
        {
            if(gameState[i]==2)
            {
                isTwoPresent=1;
                break;
            }
            else
            {
                isTwoPresent=0;
            }
        }

        return isTwoPresent;

    }

    public void dropIn(View view)
    {
        ImageView counter=(ImageView) view;  //this means that we don't know what the image id is, but put that image in the counter
        //whose matrix box is touched  on the screen of phone
        //that is why we have used the view object of type View and type casted it to ImageView
        //because we want an image in counter


        Button playAgainButton = (Button) findViewById(R.id.button);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);


        int tappedCounter=Integer.parseInt(counter.getTag().toString());  //Getting tags (that we have assigned to our grid images) that are touched or tapped

        if(gameState[tappedCounter]==2 && winValue==0) //Checking if that grid image is already tapped or not and if untapped (i.e. == 2) then execute everything in braces
        {

            gameState[tappedCounter] = activePlayer;  //Assigning current player to te array

            counter.setTranslationY(-1500);  //This will set the image beyond the top most Y axis of screen


            if (activePlayer == 0)
            {
                counter.setImageResource(R.drawable.yellow); // setting our imageView to yellow image which is in our drawable folder
                activePlayer = 1;
            }
            else
            {
                counter.setImageResource(R.drawable.red); // setting our imageView to red image which is in our drawable folder
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            String message;

            for (int[] winningPosition : winningPositions)
            {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2)
                {
                    winValue=1;

                    if (gameState[winningPosition[0]]==0)
                    {
                        message="Yellow Is Winner!!!!!";
                    }

                    else
                    {
                        message="Red Is Winner!!!!!";
                    }

                    //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

                    winnerTextView.setText(message);

                    playAgainButton.setTranslationX(-1500);
                    playAgainButton.animate().translationXBy(1500).rotation(3600).setDuration(1500);
                    playAgainButton.setVisibility(View.VISIBLE);  //making visible

                    winnerTextView.setTranslationX(-1500);
                    winnerTextView.setVisibility(View.VISIBLE);  //making visible
                    winnerTextView.animate().translationXBy(1500).rotation(3600).setDuration(1500);


                }
                else if(tieCheck()==0 && winValue==0 )  //Checking for tie
                {
                    winValue=2;

                    message="Tie!!!";

                    //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

                    winnerTextView.setText(message);

                    playAgainButton.setTranslationX(-1500);
                    playAgainButton.setVisibility(View.VISIBLE);  //making visible
                    playAgainButton.animate().translationXBy(1500).rotation(3600).setDuration(1500);

                    winnerTextView.setTranslationX(-1500);
                    winnerTextView.setVisibility(View.VISIBLE);  //making visible
                    winnerTextView.animate().translationXBy(1500).rotation(3600).setDuration(1500);

                }
            }
        }
    }

    public void playAgain(View view)
    {
        Button playAgainButton = (Button) findViewById(R.id.button);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);  //making invisible
        playAgainButton.animate().rotation(-3600); //This is making our play button like original one at the beginning so that we comes rotating everytime. Comment it and play game twice you will see change in animation
        winnerTextView.setVisibility(View.INVISIBLE);  //making invisible
        winnerTextView.animate().rotation(-3600);  //This is making our winner text view like original one at the beginning so that we comes rotating everytime. Comment it and play game twice you will see change in animation

        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        //below we are removing images from the imageviews of the grid layout so that they are free again to be played

        for(int i=0; i<gridLayout.getChildCount();i++)
        {
            ImageView counter= (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        //Below variable is used to bring in the alternate colour counters
        activePlayer=0; //0 is yellow and 1 is red

        winValue=0;

        //Now changing the gameState again for replaying

        for(int i=0; i<gameState.length;i++)
        {
            gameState[i]=2;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
