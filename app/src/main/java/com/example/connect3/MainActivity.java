package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0:yellow 1: red 2: empty
    int[][] winningPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int[] gridCondition = { 2,2,2,2,2,2,2,2,2};
    int currPlayer=0;
    boolean gameRunning=true;
    public void newGame(View view){
        Button button=(Button) findViewById(R.id.button);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        for(int i=0;i<gridCondition.length;i++){
            gridCondition[i]=2;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView imgView = (ImageView) gridLayout.getChildAt(i);
            imgView.setImageResource(0);
        }
        gameRunning=true;
        currPlayer=0;
    }
    public void clickFunc(View view){
        ImageView imageView = (ImageView) view;
        int tagNumber=Integer.parseInt(imageView.getTag().toString());
        if(gridCondition[tagNumber]==2&&gameRunning) {
            gridCondition[tagNumber]=currPlayer;
            imageView.setTranslationY(-1000);
            if (currPlayer == 0) {
                imageView.setImageResource(R.drawable.yellow);
                currPlayer = 1;
            } else {
                currPlayer = 0;
                imageView.setImageResource(R.drawable.red);
            }
            imageView.animate().translationYBy(1000).setDuration(300);
            for(int[] arr : winningPos){
                if(gridCondition[arr[0]]==gridCondition[arr[1]]&&gridCondition[arr[1]]==gridCondition[arr[2]]&&gridCondition[arr[0]]!=2){
                    gameRunning=false;
                    String winner="";
                    if(gridCondition[arr[0]]==0) {
                        winner="YELLOW";

                    }
                    else{
                        winner="RED";

                    }

                    Button button = (Button) findViewById(R.id.button);
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText(winner+" WON!!");
                    textView.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                }
            }
            int numOf2=0;
            for(int i=0;i<gridCondition.length;i++){
                if(gridCondition[i]==2){
                    numOf2++;
                }
            }
            if(numOf2==0){
                gameRunning=false;
                Button button = (Button) findViewById(R.id.button);
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText("DRAW!!");
                textView.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
            }
        }
        else if(!gameRunning){
            Toast.makeText(this, "Please start a new game !!", Toast.LENGTH_SHORT).show();
        }
        else if(gridCondition[tagNumber]==1||gridCondition[tagNumber]==0){
            Toast.makeText(this, "Chose a different position !!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
