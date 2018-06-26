package fr.wcs.liftsimulator;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int currentFloor = 0;
    private boolean isLiftMoving = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bOne = findViewById(R.id.b_one);
        Button bTwo = findViewById(R.id.b_two);
        Button bThree = findViewById(R.id.b_three);
        Button bFour = findViewById(R.id.b_four);
        Button bFive = findViewById(R.id.b_five);
        Button bSix = findViewById(R.id.b_six);
        Button bSeven = findViewById(R.id.b_seven);
        Button bHeight = findViewById(R.id.b_height);
        Button bNine = findViewById(R.id.b_nine);
        Button bZero = findViewById(R.id.b_zero);


        bOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFloor(1);
            }
        });
        bTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFloor(2);
            }
        });
        bThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFloor(3);
            }
        });
        bFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFloor(4);
            }
        });
        bFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFloor(5);
            }
        });
        bSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFloor(6);
            }
        });
        bSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFloor(7);
            }
        });
        bHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFloor(8);
            }
        });
        bNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFloor(9);
            }
        });
        bZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFloor(0);
            }
        });


    }

    private void goToFloor(int floor) {
        if (!isLiftMoving && floor != currentFloor) {
            moveNextFloor(floor);
            isLiftMoving = false;
        }
    }

    private void moveNextFloor(int floor) {

            isLiftMoving = true;
            MoveLift moveLift = new MoveLift();
            moveLift.execute(floor);
    }

    private class MoveLift extends AsyncTask<Integer, Void, Integer> {

        private TextView isMoving = findViewById(R.id.tv_move);
        private TextView floorCount = findViewById(R.id.floor_count);

        protected void onPreExecute() {
            isMoving.setText("En marche");
            isLiftMoving = true;
        }

        protected Integer doInBackground(Integer... integers) {
            SystemClock.sleep(Math.abs(currentFloor - integers[0]) * 3000);
            return integers[0];
        }

        protected void onProgressUpdate(Void... values) {

        }

        protected void onPostExecute(Integer floor) {

            while (floor != currentFloor) {
                currentFloor = (floor > currentFloor) ? currentFloor + 1 : currentFloor - 1;
            }
                floorCount.setText(String.valueOf(currentFloor));
                isMoving.setText("Arrêt");

        }
    }
}

