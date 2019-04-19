package com.example.jharwalshoes.myshoes.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScanner;
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder;
import com.example.jharwalshoes.myshoes.R;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import in.myinnos.androidscratchcard.ScratchCard;


public class RewardsFragment extends Fragment {

    private static String TAG = RewardsFragment.class.getName();
    private Context context;
    private Activity activity;
    public View view;
    private ScratchCard mScratchCard;
    public static final String BARCODE_KEY = "BARCODE";
    private Barcode barcodeResult;
    private TextView result;

    public RewardsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.activity = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rewards, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //    ((ApplicationAppContext) activity.getApplication()).getNetworkComponent().inject(ImageFragment.this);

        result = view.findViewById(R.id.barcodeResult);
        mScratchCard = view.findViewById(R.id.scratchCard);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(activity, R.id.my_nav_host_fragment);
                navController.navigate(R.id.action_rewardsFragment_to_qrScannerFragment);
            }
        });
        mScratchCard.setOnScratchListener(new ScratchCard.OnScratchListener() {
            @Override
            public void onScratch(ScratchCard scratchCard, float visiblePercent) {
                if (visiblePercent > 0.5) {
                    mScratchCard.setVisibility(View.GONE);
                    result.setVisibility(View.VISIBLE);
                    Toast.makeText(context, "Content Visible", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (savedInstanceState != null) {
            Barcode restoredBarcode = savedInstanceState.getParcelable(BARCODE_KEY);
            if (restoredBarcode != null) {
                result.setText(restoredBarcode.rawValue);
                barcodeResult = restoredBarcode;
            }
        }
        startScan();
    }

    private void startScan() {
        try {
            final MaterialBarcodeScanner materialBarcodeScanner = new MaterialBarcodeScannerBuilder()
                    .withActivity(activity)
                    .withEnableAutoFocus(true)
                    .withBleepEnabled(true)
                    .withBackfacingCamera()
                    .withCenterTracker()
                    .withText("Scanning...")
                    .withResultListener(new MaterialBarcodeScanner.OnResultListener() {
                        @Override
                        public void onResult(Barcode barcode) {
                            barcodeResult = barcode;
                            result.setText(barcode.rawValue);
                        }
                    }).build();
            materialBarcodeScanner.startScan();
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(BARCODE_KEY, barcodeResult);
        super.onSaveInstanceState(outState);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != MaterialBarcodeScanner.RC_HANDLE_CAMERA_PERM) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }
        if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startScan();
            return;
        }
    }
}
