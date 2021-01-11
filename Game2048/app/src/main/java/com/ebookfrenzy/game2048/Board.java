package com.ebookfrenzy.game2048;

import android.content.Context;
import android.util.Pair;
import android.widget.TextView;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

public class Board {

    private final Context context;
    private final int size;
    int[][] arr;
    String[] numbers;
    List<List<TextView>> squareViews;

    public Board(Context context, int size, List<List<TextView>> squareViews) {
        this.context = context;
        this.size = size;
        this.squareViews = squareViews;
        arr = new int[size][size];
        numbers = context.getResources().getStringArray(R.array.numbers);
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j)
                arr[i][j] = 0;
        }
        Pair<Integer, Integer> first = new Pair<Integer, Integer>(0, 0);
        Pair<Integer, Integer> second = new Pair<Integer, Integer>(0, 0);
        while(first.equals(second)) {
            first = getRandomCords();
            second = getRandomCords();
        }
        arr[first.first][first.second] = 2;
        arr[second.first][second.second] = 2;
        setChanges();
    }

    public Pair<Integer, Integer> getRandomCords() {
        Random random = new Random();
        int first = random.nextInt(), second = random.nextInt();
        random.setSeed(System.currentTimeMillis() + first);
        first = random.nextInt(size);
        random.setSeed(System.currentTimeMillis() + second);
        second = random.nextInt(size);
        return new Pair<Integer, Integer>(first, second);
    }

    public void setChanges() {
        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                if(arr[i][j] != 0)
                    squareViews.get(i).get(j).setText(String.valueOf(arr[i][j]));
                else
                    squareViews.get(i).get(j).setText(" ");
            }
        }
    }

}
