package savyinfotech.com.product_phase_2.customcomponent;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import savyinfotech.com.product_phase_2.R;
import savyinfotech.com.product_phase_2.adapter.FragmentLanguageAdapter;
import savyinfotech.com.product_phase_2.model.LangaugeModel;
import savyinfotech.com.product_phase_2.utill.Util;


/**
 * FragmentCustomDialogLanguage class created on 11/05/17.
 */

public class FragmentCustomDialogLanguage extends DialogFragment {

    private String TAG = FragmentCustomDialogLanguage.class.getSimpleName();
    private RecyclerView rvListView;
    private ProgressDialog progressDialog;
    private DatabaseReference mDatabase;
    private Query query;
    private ArrayList<LangaugeModel> langArrayList = new ArrayList<>();
    private FragmentLanguageAdapter fragmentLanguageAdapter;
    private boolean isSSC;
    private String stateName;
    private FragmentManager getManager;
    private RecyclerView.LayoutManager mLayoutManager;
    private Fragment fragment;

    public FragmentCustomDialogLanguage(Fragment fragment) {
        this.fragment = fragment;
    }


//    public static FragmentCustomDialogLanguage newInstance() {
//        FragmentCustomDialogLanguage f = new FragmentCustomDialogLanguage();
//
//        return f;
//    }


    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        getManager = getFragmentManager();

        Bundle arguments = getArguments();

        if (arguments != null) {
            stateName = arguments.getString("STATENAME");
            isSSC = arguments.getBoolean("ISSSC");
        }

        // creating the fullscreen dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_custom_dialog_language_new, container, false);
        rvListView = v.findViewById(R.id.fragment_custom_dialog_language_rv_caontainer);

        /**
         * Here ,Get reference of firebase database
         * Master is table name
         */
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if (isSSC) {
            query = mDatabase.child("master").child(stateName).child("ssc").child("language");
        } else {
            query = mDatabase.child("master").child(stateName).child("hsc").child("language");
        }


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    Util.dismissProgressDialogNew(progressDialog);
                    // dataSnapshot is the "professional" node with all children with id 0
                    for (final DataSnapshot user_details : dataSnapshot.getChildren()) {
                        Log.d(TAG, "ID=" + user_details.getKey() + "Value=" + user_details.child("name").getValue().toString());
                        LangaugeModel langaugeModel = new LangaugeModel();
                        langaugeModel.setId(user_details.getKey());
                        langaugeModel.setName(user_details.child("name").getValue().toString());
                        langArrayList.add(langaugeModel);
                    }

                    fragmentLanguageAdapter = new FragmentLanguageAdapter(getActivity(), langArrayList, fragment, getManager, stateName, isSSC, FragmentCustomDialogLanguage.this);
                    // use this setting to improve performance if you know that changes
                    // in content do not change the layout size of the RecyclerView
                    rvListView.setHasFixedSize(true);

                    // use a linear layout manager
                    mLayoutManager = new LinearLayoutManager(getActivity());
                    rvListView.setLayoutManager(mLayoutManager);
                    rvListView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rvListView.setAdapter(fragmentLanguageAdapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return v;
    }

}
