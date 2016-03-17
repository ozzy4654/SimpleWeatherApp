package kalan.ozan.weathercodechallenge.dialogs;


import android.app.*;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import kalan.ozan.weathercodechallenge.R;

public class SearchError extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

        alertDialog.setMessage(R.string.search_error_msg)
                .setTitle(R.string.invalid_search)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

//        R.string.invalid_search
        return alertDialog.create();
    }

    public static String changeString(String change){
        return change;
    }

    @Override
    public void onStart(){super.onStart();}

}
