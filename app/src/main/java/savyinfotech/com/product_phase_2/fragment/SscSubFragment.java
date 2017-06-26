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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import savyinfotech.com.product_phase_2.R;
import savyinfotech.com.product_phase_2.adapter.FragmentSscSubAdapter;
import savyinfotech.com.product_phase_2.model.SscSubToSubModel;
import savyinfotech.com.product_phase_2.utill.Util;

/**
 * SscFragment class created on 19/06/17.
 */

public class SscSubFragment extends BaseFragment {

    String TAG = SscSubFragment.class.getSimpleName();
    private ArrayList<SscSubToSubModel> yearsArrayList = new ArrayList<>();
    private FragmentSscSubAdapter fragmentSscSubAdapter;
    private FragmentManager getManager;
    private RecyclerView rvListView;
    private GridLayoutManager lLayout;
    private ProgressDialog progressDialog;
    private View viewSscSub;
    private DatabaseReference mDatabase;
    private static boolean isSSC;
    private String key;
    private String stateName;
    private ArrayList<String> subCat;
    private ArrayList<String> event;
    private DatabaseReference ref;

    public SscSubFragment(String key, String stateName, boolean isSSC) {
        this.key = key;
        this.stateName = stateName;
        this.isSSC = isSSC;
        Log.d(TAG, "Key=" + key + "StatName=" + stateName + "isSSC=" + isSSC);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewSscSub = inflater.inflate(R.layout.fragment_ssc_sub, container, false);
        return viewSscSub;
    }

    @Override
    public void initView(View view) {
        subCat = new ArrayList<>();
        event = new ArrayList<>();
        getManager = getFragmentManager();

        rvListView = (RecyclerView) viewSscSub.findViewById(R.id.fragment_sss_sub_rv_container);
        // Get a reference to our posts
        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        if (isSSC) {
            ref = database.getReference().child("master").child(stateName).child("ssc").child("language").child(key).child("years");
        } else {
            ref = database.getReference().child("master").child(stateName).child("hsc").child("language").child(key).child("years");
        }

        progressDialog = Util.showProgressDialogNew(getActivity(), getString(R.string.msg_loading), false);

// Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot subCatDetail : dataSnapshot.getChildren()) {

                    if (subCatDetail.hasChildren()) {
                        String subcat = subCatDetail.getKey();
                        Log.d(TAG, String.valueOf(subCatDetail.child("name").getValue()));
                        Log.d(TAG, String.valueOf(subCatDetail.child("url").getValue()));
                        subCat.add("" + subcat);
                        SscSubToSubModel yearModel = new SscSubToSubModel();
                        yearModel.setId(subCatDetail.getKey());
                        yearModel.setName(String.valueOf(subCatDetail.child("name").getValue()));
                        yearsArrayList.add(yearModel);
                    }
                    Log.d(TAG, String.valueOf(subCat));
                }

                fragmentSscSubAdapter = new FragmentSscSubAdapter(getActivity(), yearsArrayList, SscSubFragment.this, stateName, isSSC, key, getManager);
                rvListView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                rvListView.setAdapter(fragmentSscSubAdapter);
                Util.dismissProgressDialogNew(progressDialog);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }


}
