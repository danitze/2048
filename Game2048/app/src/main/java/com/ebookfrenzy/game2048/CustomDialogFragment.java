package com.ebookfrenzy.game2048;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CustomDialogFragment extends DialogFragment {

    private BoardData boardData;
    private BoardViews boardViews;

    public CustomDialogFragment(BoardData boardData, BoardViews boardViews) {
        this.boardData = boardData;
        this.boardViews = boardViews;
    }

    LinearLayout linearLayout;

    @NonNull
    @Override
    public Dialog onCreateDialog (@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        linearLayout = (LinearLayout) layoutInflater.inflate(
                R.layout.dialog_window, null
        );
        builder.setView(linearLayout);
        builder.setCancelable(false);
        builder.setTitle(R.string.dialog_title);
        TextView dialogTv = linearLayout.findViewById(R.id.dialogTv);
        Button dialogBtn = linearLayout.findViewById(R.id.dialogBtn);
        dialogTv.setText(getString(R.string.dialog_text, boardData.getScore()));
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardData = new BoardData(getContext(), boardData.getSize(),
                        boardData.getBest());
                boardViews.setTable(boardData.getArr(), boardData.getSize());
                dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }


}
