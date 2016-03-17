package kalan.ozan.weathercodechallenge.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

import kalan.ozan.weathercodechallenge.R;

public class Progress extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View v = getActivity().getLayoutInflater().inflate(R.layout.progress, null);
        builder.setView(v);
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        int width = getResources().getDimensionPixelSize(R.dimen.popup_width);
        int height = getResources().getDimensionPixelSize(R.dimen.popup_hight);
        if (getDialog() != null) {
            getDialog().getWindow().setLayout(width, height);
        }
    }

    @Override public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }
}