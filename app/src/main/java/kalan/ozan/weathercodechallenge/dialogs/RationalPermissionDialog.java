package kalan.ozan.weathercodechallenge.dialogs;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import butterknife.*;
import kalan.ozan.weathercodechallenge.R;

public class RationalPermissionDialog extends DialogFragment {


    public static final int REQUEST_CODE = 102;
    private String mPermission;
    boolean btnPressed = true;

    @Bind(R.id.explainText) TextView mText;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.gps_permission, null);
        ButterKnife.bind(this, view);

        int mMsg = R.string.gps;
        mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

        mText.setText(mMsg);
        builder.setView(view);

        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if(btnPressed) getActivity().finish();
    }

    @OnClick(R.id.enable_button)
    public void enablePerm() {
        ActivityCompat.requestPermissions(getActivity()
                , new String[]{mPermission}, REQUEST_CODE);
        btnPressed = false;
        dismiss();
    }


    @Override
    public void onStart() { super.onStart();}


}
