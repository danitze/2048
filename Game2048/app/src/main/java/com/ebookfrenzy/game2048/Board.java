package com.ebookfrenzy.game2048;

import android.content.Context;
import android.util.Log;
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
    int[] colors;
    List<List<TextView>> squareViews;

    public Board(Context context, int size, List<List<TextView>> squareViews) {
        this.context = context;
        this.size = size;
        this.squareViews = squareViews;

        arr = new int[size][size];
        numbers = context.getResources().getStringArray(R.array.numbers);
        colors = context.getResources().getIntArray(R.array.view_colors);
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
        ++arr[first.first][first.second];
        ++arr[second.first][second.second];

        setChanges();
    }

    public Pair<Integer, Integer> getRandomCords() {
        Random random = new Random();
        int first, second;
        Random random1 = new Random(System.currentTimeMillis()
                + random.nextInt());
        Random random2 = new Random(System.currentTimeMillis()
                + random.nextInt());
        do {
            first = random1.nextInt(size);
            second = random2.nextInt(size);
        } while (arr[first][second] != 0);
        return new Pair<Integer, Integer>(first, second);
    }

    public void moveDown() {
        boolean canMoveDown = false;
        for(int i = size - 1; i >= 1; --i) {
            for (int j = size - 1; j >= 0; --j) {
                if(arr[i][j] == 0) {
                    int k = i - 1;
                    while(k > 0 && arr[k][j] == 0)
                        --k;
                    if(arr[k][j] != 0) {
                        arr[i][j] = arr[k][j];
                        arr[k][j] = 0;
                        canMoveDown = true;
                    }
                }
                if(arr[i][j] == arr[i - 1][j] && arr[i][j] != 0) {
                    ++arr[i][j];
                    arr[i - 1][j] = 0;
                    canMoveDown = true;
                }
                else if(arr[i][j] != 0 && arr[i - 1][j] == 0) {
                    int k = i - 1;
                    while (k > 0 && arr[k][j] == 0)
                        --k;
                    if(arr[k][j] == arr[i][j]) {
                        ++arr[i][j];
                        arr[k][j] = 0;
                        canMoveDown = true;
                    }
                }
            }
        }
        addNewNum(canMoveDown);
    }

    public void moveUp() {
        boolean canMoveUp = false;
        for(int i = 0; i < size - 1; ++i) {
            for (int j = 0; j < size; ++j) {
                if(arr[i][j] == 0) {
                    int k = i + 1;
                    while(k < size - 1 && arr[k][j] == 0)
                        ++k;
                    if(arr[k][j] != 0) {
                        arr[i][j] = arr[k][j];
                        arr[k][j] = 0;
                        canMoveUp = true;
                    }
                }
                if(arr[i][j] == arr[i + 1][j] && arr[i][j] != 0) {
                    ++arr[i][j];
                    arr[i + 1][j] = 0;
                    canMoveUp = true;
                }
                else if(arr[i][j] != 0 && arr[i + 1][j] == 0) {
                    int k = i + 1;
                    while (k < size - 1 && arr[k][j] == 0)
                        ++k;
                    if(arr[k][j] == arr[i][j]) {
                        ++arr[i][j];
                        arr[k][j] = 0;
                        canMoveUp = true;
                    }
                }
            }
        }
        addNewNum(canMoveUp);
    }

    public void moveRight() {
        boolean canMoveRight = false;
        for(int i = size - 1; i >= 0; --i) {
            for (int j = size - 1; j >= 1; --j) {
                if(arr[i][j] == 0) {
                    int k = j - 1;
                    while(k > 0 && arr[i][k] == 0)
                        --k;
                    if(arr[i][k] != 0) {
                        arr[i][j] = arr[i][k];
                        arr[i][k] = 0;
                        canMoveRight = true;
                    }
                }
                if(arr[i][j] == arr[i][j - 1] && arr[i][j] != 0) {
                    ++arr[i][j];
                    arr[i][j - 1] = 0;
                    canMoveRight = true;
                }
                else if(arr[i][j] != 0 && arr[i][j - 1] == 0) {
                    int k = j - 1;
                    while (k > 0 && arr[i][k] == 0)
                        --k;
                    if(arr[i][k] == arr[i][j]) {
                        ++arr[i][j];
                        arr[i][k] = 0;
                        canMoveRight = true;
                    }
                }
            }
        }
        addNewNum(canMoveRight);
    }

    public void moveLeft() {
        boolean canMoveLeft = false;
        for(int i = 0; i < size; ++i) {
            for (int j = 0; j < size - 1; ++j) {
                if(arr[i][j] == 0) {
                    int k = j + 1;
                    while(k < size - 1 && arr[i][k] == 0)
                        ++k;
                    if(arr[i][k] != 0) {
                        arr[i][j] = arr[i][k];
                        arr[i][k] = 0;
                        canMoveLeft = true;
                    }
                }
                if(arr[i][j] == arr[i][j + 1] && arr[i][j] != 0) {
                    ++arr[i][j];
                    arr[i][j + 1] = 0;
                    canMoveLeft = true;
                }
                else if(arr[i][j] != 0 && arr[i][j + 1] == 0) {
                    int k = j + 1;
                    while (k < size - 1 && arr[i][k] == 0)
                        ++k;
                    if(arr[i][k] == arr[i][j]) {
                        ++arr[i][j];
                        arr[i][k] = 0;
                        canMoveLeft = true;
                    }
                }
            }
        }
        addNewNum(canMoveLeft);
    }

    private boolean isEmptyPlace() {
        for(int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (arr[i][j] == 0)
                    return true;
            }
        }
        return false;
    }

    public void setChanges() {
        TextView textView;
        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                textView = squareViews.get(i).get(j);
                textView.setText(String.valueOf(numbers[arr[i][j]]));
                textView.setBackgroundColor(colors[arr[i][j]]);
            }
        }
    }

    private void addNewNum(boolean moved) {
        if(moved) {
            if(isEmptyPlace()) {
                Pair<Integer, Integer> cords = getRandomCords();
                ++arr[cords.first][cords.second];
            }
            setChanges();
        }
    }

    private boolean canMove() {
        for(int i = 0; i < size - 1; ++i) {
            for (int j = 0; j < size - 1; ++j) {
                if(arr[i][j] == 0 || arr[i][j] == arr[i + 1][j]
                    || arr[i][j] == arr[i][j + 1])
                    return true;
            }
        }
        return false;
    }

}
