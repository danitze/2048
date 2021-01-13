package com.ebookfrenzy.game2048;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private final int size = 4;

    LinearLayout tableLayout;

    BoardData boardData;
    BoardViews boardViews;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableLayout = findViewById(R.id.tableLayout);
        boardData = new BoardData(this, size);
        boardViews = new BoardViews(this, tableLayout, size);
        setBoardViewsChanges();

        Display display = getWindowManager().getDefaultDisplay();
        Point screenSize = new Point();
        display.getSize(screenSize);
        int width = screenSize.x;
        tableLayout.setOnTouchListener(new OnSwipeTouchListener(this,
                width / size) {
            @Override
            public void moveUp() {
                boardData.moveUp();
                setBoardViewsChanges();
            }

            @Override
            public void moveDown() {
                boardData.moveDown();
                setBoardViewsChanges();
            }

            @Override
            public void moveLeft() {
                boardData.moveLeft();
                setBoardViewsChanges();
            }

            @Override
            public void moveRight() {
                boardData.moveRight();
                setBoardViewsChanges();
            }
        });
    }

    private void setBoardViewsChanges() {
        boardViews.setTable(boardData.getArr(), size);
        boardViews.setScore(boardData.getScore());
    }



}