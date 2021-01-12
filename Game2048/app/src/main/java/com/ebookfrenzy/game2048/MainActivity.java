package com.ebookfrenzy.game2048;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final int size = 4;

    LinearLayout tableLayout;
    List<List<TextView>> squareViews;

    Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findSquares();
        formatSquares();

        board = new Board(this, size, squareViews);
        tableLayout.
    }


    private void findSquares() {
        tableLayout = findViewById(R.id.tableLayout);
        LinearLayout rawLayout;
        squareViews = new ArrayList<List<TextView>>(size);
        List<TextView> rawViews;
        for(int i = 0; i < tableLayout.getChildCount(); ++i) {
            rawLayout = (LinearLayout)tableLayout.getChildAt(i);
            rawViews = new ArrayList<TextView>(size);
            for(int j = 0; j < rawLayout.getChildCount(); ++j) {
                rawViews.add((TextView)rawLayout.getChildAt(j));
            }
            squareViews.add(rawViews);
        }
    }


    private void formatSquares() {
        for(List<TextView> rawLayout: squareViews) {
            for (TextView textView: rawLayout) {
                textView.post(() -> {
                    textView.getLayoutParams().height = textView
                            .getWidth();
                    textView.requestLayout();
                });
            }
        }
    }


    public void onClick1(View view) {
        board.moveUp();
    }

    public void onClick2(View view) {
        board.moveDown();
    }

    public void onClick3(View view) {
        board.moveLeft();
    }

    public void onClick4(View view) {
        board.moveRight();
    }
}