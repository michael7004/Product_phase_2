package savyinfotech.com.product_phase_2.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import savyinfotech.com.product_phase_2.R;
import savyinfotech.com.product_phase_2.fragment.PaperFragment;
import savyinfotech.com.product_phase_2.model.SscSubToSubModel;
import savyinfotech.com.product_phase_2.utill.Util;


/**
 * FragmentPdfAdapter class created on 01/05/17.
 */

public class FragmentSscSubAdapter extends RecyclerView.Adapter<FragmentSscSubAdapter.MyViewHolder> {

    private String TAG=FragmentSscSubAdapter.class.getSimpleName();
    private ArrayList<SscSubToSubModel> sscSubToSubModelArrayList;
    private Context context;
    private FragmentManager manager;
    private String stateName;
    private String langKey;
    private boolean isSSC;
    private Fragment fragment;


    public FragmentSscSubAdapter(Context context, ArrayList<SscSubToSubModel> sscSubToSubModelArrayList, Fragment fragment,String stateName,boolean isSSC,String langKey , FragmentManager manager) {
        this.context = context;
        this.sscSubToSubModelArrayList = sscSubToSubModelArrayList;
        this.stateName=stateName;
        this.isSSC=isSSC;
        this.langKey=langKey;
        this.fragment=fragment;
        this.manager = manager;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_fragment_ssc_sub, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvTitle.setText(sscSubToSubModelArrayList.get(position).getName());

        Log.d(TAG,"ISSSC="+isSSC+"id="+sscSubToSubModelArrayList.get(position).getId());





//        String url = sscSubToSubModelArrayList.get(position).getUrl();
//        Glide.with(context)
//                .load(url)
////                .placeholder(R.drawable.piwo_48)
//
//                .into(holder.ivProfile);


        holder.ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Pressed="+position, Toast.LENGTH_SHORT).show();

                Util.addFragment(context, fragment, new PaperFragment(sscSubToSubModelArrayList.get(position).getId(),stateName, isSSC,langKey), R.id.content_main_container);
//
//
            }
        });
        holder.ivMoreOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }

    @Override
    public int getItemCount() {
        return sscSubToSubModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public ImageView ivProfile;
        public ImageView ivMoreOption;

        public MyViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.row_fragment_years_tv_title);
            ivProfile = (ImageView) view.findViewById(R.id.row_fragment_years_tv_logo);
            ivMoreOption = (ImageView) view.findViewById(R.id.row_fragment_ssc_sub_iv_more_option);

        }


    }


}

