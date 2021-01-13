package com.ebookfrenzy.game2048;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnSwipeTouchListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;

    private final Context context;
    private final int minDist;
    private final Board board;

    public OnSwipeTouchListener(Context context, int minDist, Board board) {
        gestureDetector = new GestureDetector(context, new GestureListener());

        this.context = context;
        this.minDist = minDist;
        this.board = board;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffX = e2.getX() - e1.getX();
            float diffY = e2.getY() - e1.getY();
            if(Math.abs(diffX) > Math.abs(diffY) && Math.abs(diffX) >= minDist) {
                if(diffX > 0)
                    board.moveRight();
                else
                    board.moveLeft();
            } else if(Math.abs(diffY) >= Math.abs(diffX) && Math.abs(diffY) >= minDist) {
                if(diffY > 0)
                    board.moveDown();
                else
                    board.moveUp();
            }
            return true;
        }
    }
}
