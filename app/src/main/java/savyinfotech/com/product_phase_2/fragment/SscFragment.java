package savyinfotech.com.product_phase_2.fragment;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import savyinfotech.com.product_phase_2.R;
import savyinfotech.com.product_phase_2.adapter.FragmentSscAdapter;
import savyinfotech.com.product_phase_2.model.SscModel;
import savyinfotech.com.product_phase_2.utill.Util;

/**
 * SscFragment class created on 19/06/17.
 */

public class SscFragment extends BaseFragment {

    String TAG = SscFragment.class.getSimpleName();
    private FragmentSscAdapter fragmentSscAdapter;
    private FragmentManager manager;
    private ArrayList<SscModel> stateArrayList = new ArrayList<>();
    private RecyclerView rvGridView;
    private GridLayoutManager lLayout;
    private ProgressDialog progressDialog;
    private View viewSsc;
    private DatabaseReference mDatabase;
    private static boolean isSSC;
    private FragmentManager getManager;

    public SscFragment(boolean isSSC) {
        this.isSSC = isSSC;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewSsc = inflater.inflate(R.layout.fragment_ssc, container, false);
        return viewSsc;
    }

    @Override
    public void initView(View view) {

        rvGridView = (RecyclerView) viewSsc.findViewById(R.id.fragment_ssc_rv_container);
        getManager = getFragmentManager();

        /**
         * Here ,Get reference of firebase database
         * Master is table name
         */
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query query = mDatabase.child("master");

        progressDialog = Util.showProgressDialogNew(getActivity(), getString(R.string.msg_loading), false);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    Util.dismissProgressDialogNew(progressDialog);

                    for (final DataSnapshot user_details : dataSnapshot.getChildren()) {

                        Log.d(TAG, user_details.child("name").getValue().toString());
                        Log.d(TAG, user_details.child("url").getValue().toString());
                        SscModel statListModel = new SscModel();
//                        statListModel.set(user_details.getKey());
                        statListModel.setId(user_details.getKey());
                        statListModel.setName(user_details.child("name").getValue().toString());
                        statListModel.setUrl(user_details.child("url").getValue().toString());
                        stateArrayList.add(statListModel);

                        Log.d(TAG, "ID=" + user_details.getKey() + "Name=" + user_details.child("name").getValue().toString());

                    }

                    fragmentSscAdapter = new FragmentSscAdapter(getActivity(), stateArrayList, SscFragment.this, isSSC, getManager);
                    rvGridView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                    rvGridView.setAdapter(fragmentSscAdapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
