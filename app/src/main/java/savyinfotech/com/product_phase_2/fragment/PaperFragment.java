package savyinfotech.com.product_phase_2.fragment;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import savyinfotech.com.product_phase_2.adapter.FragmentPaperAdapter;
import savyinfotech.com.product_phase_2.model.PaperModel;
import savyinfotech.com.product_phase_2.utill.Util;

/**
 * SscFragment class created on 19/06/17.
 */

public class PaperFragment extends BaseFragment {

    String TAG = PaperFragment.class.getSimpleName();
    private FragmentPaperAdapter fragmentPaperAdapter;
    private FragmentManager manager;
    private ArrayList<PaperModel> papersArrayList = new ArrayList<>();
    private RecyclerView rvListView;
    private GridLayoutManager lLayout;
    private ProgressDialog progressDialog;
    private View viewSsc;
    private DatabaseReference mDatabase;
    private Query query;
    private static boolean isSSC;
    private FragmentManager getManager;
    private String year;
    private String stateName;
    private String langKey;
    private ArrayList<String> subCat;
    private ArrayList<String> event;

    public PaperFragment(String year, String stateName, boolean isSSC, String langKey) {
        this.year = year;
        this.stateName = stateName;
        this.isSSC = isSSC;
        this.langKey = langKey;

        Log.d(TAG, "YEAR=" + year + "=stateName=" + stateName + "isssc=" + isSSC);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewSsc = inflater.inflate(R.layout.fragment_paper, container, false);
        return viewSsc;
    }

    @Override
    public void initView(View view) {


        Log.d(TAG, "Boolean=" + isSSC);
        subCat = new ArrayList<>();
        event = new ArrayList<>();

        rvListView = (RecyclerView) viewSsc.findViewById(R.id.fragment_paper_rv_container);
        getManager = getFragmentManager();

        /**
         * Here ,Get reference of firebase database
         * Master is table name
         */
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if (isSSC) {
            query = mDatabase.child("master").child(stateName).child("ssc").child("language").child(langKey).child("years").child(year);
        } else {
            query = mDatabase.child("master").child(stateName).child("hsc").child("language").child(langKey).child("years").child(year);
        }

        progressDialog = Util.showProgressDialogNew(getActivity(), getString(R.string.msg_loading), false);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    Util.dismissProgressDialogNew(progressDialog);
                    // dataSnapshot is the "professional" node with all children with id 0
                    for (final DataSnapshot user_details : dataSnapshot.getChildren()) {


                        if (user_details.hasChildren()) {
                            String subcat = user_details.getKey();
                            Log.d(TAG, String.valueOf(user_details.child("name").getValue()));
                            Log.d(TAG, String.valueOf(user_details.child("url").getValue()));
                            PaperModel paperModel = new PaperModel();
//                        statListModel.set(user_details.getKey());
                            paperModel.setId(user_details.getKey());
                            paperModel.setName(user_details.child("name").getValue().toString());
                            paperModel.setUrl(user_details.child("url").getValue().toString());
                            papersArrayList.add(paperModel);
                            subCat.add("" + subcat);

                        }
                        Log.d(TAG, String.valueOf(subCat));
                    }

                    fragmentPaperAdapter = new FragmentPaperAdapter(getActivity(), papersArrayList,year);

                    LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    rvListView.setLayoutManager(horizontalLayoutManager);
                    rvListView.setAdapter(fragmentPaperAdapter);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
