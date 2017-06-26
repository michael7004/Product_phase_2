package savyinfotech.com.product_phase_2.adapter;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import savyinfotech.com.product_phase_2.R;
import savyinfotech.com.product_phase_2.fragment.SscSubFragment;
import savyinfotech.com.product_phase_2.model.LangaugeModel;
import savyinfotech.com.product_phase_2.utill.Util;


/**
 * FragmentPdfAdapter class created on 01/05/17.
 */

public class FragmentLanguageAdapter extends RecyclerView.Adapter<FragmentLanguageAdapter.MyViewHolder> {

    private String TAG = FragmentLanguageAdapter.class.getSimpleName();
    private ArrayList<LangaugeModel> langaugeModelArrayList = new ArrayList<>();
    private Context context;
    private static boolean isSSC;
    private FragmentManager manager;
    private String stateName;
    private Fragment fragment;
    private DialogFragment fragmentParent;


    public FragmentLanguageAdapter(Context context, ArrayList<LangaugeModel> langaugeModelArrayList, Fragment fragment, FragmentManager manager,String stateName,boolean isSSC,DialogFragment fragmentParent) {
        this.context = context;
        this.langaugeModelArrayList = langaugeModelArrayList;
        this.fragment = fragment;
        this.manager = manager;
        this.stateName=stateName;
        this.fragment=fragment;
        this.fragmentParent=fragmentParent;
        this.isSSC = isSSC;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_fragment_language, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvTitle.setText(langaugeModelArrayList.get(position).getName());


        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.addFragment(context, fragment, new SscSubFragment(langaugeModelArrayList.get(position).getId(),stateName, isSSC), R.id.content_main_container);
                fragmentParent.dismiss();

            }
        });

    }

    @Override
    public int getItemCount() {
        return langaugeModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
//        public CheckBox ivProfile;


        public MyViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.row_fragment_language_tv_title);
//            ivProfile = (CheckBox) view.findViewById(R.id.row_fragment_language_iv_pic);
        }


    }


}

