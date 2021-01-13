package com.ebookfrenzy.game2048;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BoardViews {
    private final Activity activity;
    private final int size;

    private final String[] numbers;
    private final int[] colors;

    private final LinearLayout tableLayout;
    private TextView tvScore, tvBest;
    private List<List<TextView>> squareViews;

    public BoardViews(Activity activity, LinearLayout tableLayout, int size) {
        this.activity = activity;
        this.tableLayout = tableLayout;
        this.size = size;

        numbers = activity.getResources().getStringArray(R.array.numbers);
        colors = activity.getResources().getIntArray(R.array.view_colors);

        findViews();
        formatSquares();
    }

    private void findViews() {
        tvScore = activity.findViewById(R.id.tvScore);
        tvBest = activity.findViewById(R.id.tvBest);
        LinearLayout rawLayout;
        squareViews = new ArrayList<List<TextView>>(size);
        List<TextView> rawViews;
        for (int i = 0; i < tableLayout.getChildCount(); ++i) {
            rawLayout = (LinearLayout) tableLayout.getChildAt(i);
            rawViews = new ArrayList<TextView>(size);
            for (int j = 0; j < rawLayout.getChildCount(); ++j) {
                rawViews.add((TextView) rawLayout.getChildAt(j));
            }
            squareViews.add(rawViews);
        }
    }

    private void formatSquares() {
        for (List<TextView> rawLayout : squareViews) {
            for (TextView textView : rawLayout) {
                textView.post(() -> {
                    textView.getLayoutParams().height = textView
                            .getWidth();
                    textView.requestLayout();
                });
            }
        }
    }

    public void setTable(int[][] arr, int size) {
        TextView textView;
        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                textView = squareViews.get(i).get(j);
                textView.setText(numbers[arr[i][j]]);
                textView.setBackgroundColor(colors[arr[i][j]]);
                textView.setTextColor(activity.getColor((arr[i][j] < 3)
                        ? R.color.text_color_1 : R.color.text_color_2));
            }
        }
    }

    public void setScore(long score) {
        tvScore.setText(String.valueOf(score));
    }

    public void setBest(long best) {
        tvBest.setText(String.valueOf(tvBest));
    }
}
