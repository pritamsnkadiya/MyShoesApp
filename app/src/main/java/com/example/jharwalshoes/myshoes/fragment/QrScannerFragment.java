package com.example.jharwalshoes.myshoes.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jharwalshoes.myshoes.R;
import com.example.jharwalshoes.myshoes.adapter.ShowUserRewardsAdapter;
import com.example.jharwalshoes.myshoes.api.ApiInterface;
import com.example.jharwalshoes.myshoes.init.ApplicationAppContext;
import com.example.jharwalshoes.myshoes.model.UserRewards;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kaopiz.kprogresshud.KProgressHUD;

import javax.inject.Inject;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QrScannerFragment extends Fragment {

    private static String TAG = QrScannerFragment.class.getName();
    private Context context;
    private Activity activity;
    private View view;
    private FloatingActionButton fab;
    public DatabaseReference rootRef, demoRef;
    private RecyclerView gridRecycler;
    private RecyclerView.Adapter mAdapter;
    public GridLayoutManager mGrid;
    public KProgressHUD hud;
    @Inject
    Retrofit retrofit;

    public QrScannerFragment() {
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
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_qr_scanner, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ((ApplicationAppContext) activity.getApplication()).getNetworkComponent().inject(QrScannerFragment.this);

        fab = view.findViewById(R.id.fab);

        //database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();
        //database reference pointing to demo node
        demoRef = rootRef.child("users");

  /*      demoRef.orderByChild("user_name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
           *//*     String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, value);
                user_data.setText(value);*//*

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    Log.d(TAG, "User key's" + userSnapshot.getKey());
                    // user_data.setText(userSnapshot.getKey().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
*/

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String name = ds.child("user_name").getValue(String.class);

                    Log.d(TAG, name);

                    //  array.add(name);
                }
         /*       ArrayAdapter<String> adapter = new ArrayAdapter(OtherUsersActivity.this, android.R.layout.simple_list_item_1, array);

                mListView.setAdapter(adapter);*/

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        demoRef.addListenerForSingleValueEvent(eventListener);


        gridRecycler = view.findViewById(R.id.song_list);
        mGrid = new GridLayoutManager(context, 2);
        gridRecycler.setLayoutManager(mGrid);
        gridRecycler.setHasFixedSize(true);

        loadingProgressDialogCall(activity);
        callgetAllRewards();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(activity, R.id.my_nav_host_fragment);
                navController.navigate(R.id.action_qrScannerFragment_to_rewardsFragment);
            }
        });
    }

    private void callgetAllRewards() {

        ApiInterface mService = retrofit.create(ApiInterface.class);
        Call<UserRewards> userRewardsCall = mService.getAllRewards();
        userRewardsCall.enqueue(new Callback<UserRewards>() {
            @Override
            public void onResponse(Call<UserRewards> call, Response<UserRewards> response) {
                Log.d(TAG, "Result " + response.body());
                mAdapter = new ShowUserRewardsAdapter(context, getActivity(), response.body().getUserRewards());
                gridRecycler.setAdapter(mAdapter);
                hud.dismiss();
            }

            @Override
            public void onFailure(Call<UserRewards> call, Throwable t) {
                hud.dismiss();
                Log.d(TAG, "Display error code " + t.toString());
            }
        });
    }

    public void loadingProgressDialogCall(Activity activity) {
        hud = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setDetailsLabel("Data Loading....")
                .setDimAmount(0.5f)
                .setCancellable(true);
        hud.show();
    }
}
