package com.ebookfrenzy.game2048;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.Pair;
import android.widget.TextView;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

public class BoardData {

    private final int size;
    private final int[][] arr;
    private final String[] numbers;
    private long score;
    private long best;

    public BoardData(Context context, int size, long best) {
        this.size = size;
        this.best = best;

        score = 0;
        arr = new int[size][size];
        numbers = context.getResources().getStringArray(R.array.numbers);
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j)
                arr[i][j] = 0;
        }

        addNewNum();
        addNewNum();
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
                    score += Long.parseLong(numbers[arr[i][j]]);
                    canMoveDown = true;
                }
                else if(arr[i][j] != 0 && arr[i - 1][j] == 0) {
                    int k = i - 1;
                    while (k > 0 && arr[k][j] == 0)
                        --k;
                    if(arr[k][j] == arr[i][j]) {
                        ++arr[i][j];
                        arr[k][j] = 0;
                        score += Long.parseLong(numbers[arr[i][j]]);
                        canMoveDown = true;
                    }
                }
            }
        }
        checkBest();
        if(canMoveDown)
            addNewNum();
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
                    score += Long.parseLong(numbers[arr[i][j]]);
                    canMoveUp = true;
                }
                else if(arr[i][j] != 0 && arr[i + 1][j] == 0) {
                    int k = i + 1;
                    while (k < size - 1 && arr[k][j] == 0)
                        ++k;
                    if(arr[k][j] == arr[i][j]) {
                        ++arr[i][j];
                        arr[k][j] = 0;
                        score += Long.parseLong(numbers[arr[i][j]]);
                        canMoveUp = true;
                    }
                }
            }
        }
        checkBest();
        if(canMoveUp)
            addNewNum();
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
                    score += Long.parseLong(numbers[arr[i][j]]);
                    canMoveRight = true;
                }
                else if(arr[i][j] != 0 && arr[i][j - 1] == 0) {
                    int k = j - 1;
                    while (k > 0 && arr[i][k] == 0)
                        --k;
                    if(arr[i][k] == arr[i][j]) {
                        ++arr[i][j];
                        arr[i][k] = 0;
                        score += Long.parseLong(numbers[arr[i][j]]);
                        canMoveRight = true;
                    }
                }
            }
        }
        checkBest();
        if(canMoveRight)
            addNewNum();
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
                    score += Long.parseLong(numbers[arr[i][j]]);
                    canMoveLeft = true;
                }
                else if(arr[i][j] != 0 && arr[i][j + 1] == 0) {
                    int k = j + 1;
                    while (k < size - 1 && arr[i][k] == 0)
                        ++k;
                    if(arr[i][k] == arr[i][j]) {
                        ++arr[i][j];
                        arr[i][k] = 0;
                        score += Long.parseLong(numbers[arr[i][j]]);
                        canMoveLeft = true;
                    }
                }
            }
        }
        checkBest();
        if(canMoveLeft)
            addNewNum();
    }

    public boolean isEmptyPlace() {
        for(int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (arr[i][j] == 0)
                    return true;
            }
        }
        return false;
    }


    private void addNewNum() {
            if(isEmptyPlace()) {
                Pair<Integer, Integer> cords = getRandomCords();
                ++arr[cords.first][cords.second];
            }
    }

    public boolean canMove() {
        for(int i = 0; i < size - 1; ++i) {
            for (int j = 0; j < size - 1; ++j) {
                if(arr[i][j] == 0 || arr[i][j] == arr[i + 1][j]
                    || arr[i][j] == arr[i][j + 1])
                    return true;
            }
        }
        return false;
    }

    private void checkBest() {
        if(score > best)
            best = score;
    }

    public int getSize() {
        return size;
    }

    public int[][] getArr() {
        return arr;
    }

    public long getScore() {
        return score;
    }

    public long getBest() {
        return best;
    }

}
