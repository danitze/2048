package com.ebookfrenzy.game2048;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private final String BEST_ID = "best";
    SharedPreferences sPref;

    private final int size = 4;

    LinearLayout tableLayout;

    BoardData boardData;
    BoardViews boardViews;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sPref = getPreferences(MODE_PRIVATE);

        tableLayout = findViewById(R.id.tableLayout);
        boardData = new BoardData(this, size, sPref.getLong(BEST_ID, 0));
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

    @Override
    protected void onStop() {
        saveBest();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        saveBest();
        super.onDestroy();
    }

    private void setBoardViewsChanges() {
        boardViews.setTable(boardData.getArr(), size);
        boardViews.setScore(boardData.getScore());
        boardViews.setBest(boardData.getBest());
        if(boardData.getGameStatus() != 0)
            restartGame();
    }

    private void saveBest() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putLong(BEST_ID, boardData.getBest());
        editor.apply();
    }

    private void restartGame() {
        CustomDialogFragment dialogFragment = new CustomDialogFragment(boardData, boardViews);
        String DIALOG_TAG = "finishGameDialog";
        dialogFragment.show(getSupportFragmentManager(), DIALOG_TAG);
    }

}