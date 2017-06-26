package savyinfotech.com.product_phase_2.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import savyinfotech.com.product_phase_2.R;
import savyinfotech.com.product_phase_2.customcomponent.FragmentCustomDialogLanguage;
import savyinfotech.com.product_phase_2.model.SscModel;


/**
 * FragmentPdfAdapter class created on 01/05/17.
 */

public class FragmentSscAdapter extends RecyclerView.Adapter<FragmentSscAdapter.MyViewHolder> {

    private String TAG = FragmentSscAdapter.class.getSimpleName();
    private ArrayList<SscModel> offLinePdfModels;
    private Context context;
    private Fragment fragment;
    private static boolean isSSC;
    private FragmentManager manager;


    public FragmentSscAdapter(Context context, ArrayList<SscModel> offLinePdfModels, Fragment fragment, boolean isSSC, FragmentManager manager) {
        this.context = context;
        this.offLinePdfModels = offLinePdfModels;
        this.fragment = fragment;
        this.manager = manager;
        this.isSSC = isSSC;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_fragment_ssc, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvTitle.setText(offLinePdfModels.get(position).getName());

        String url = offLinePdfModels.get(position).getUrl();
        Glide.with(context)
                .load(url)
//               .placeholder(R.drawable.piwo_48)
                .into(holder.ivProfile);


        holder.ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Pressed=" + position, Toast.LENGTH_SHORT).show();
                Log.d(TAG, offLinePdfModels.get(position).getId());

                Log.d(TAG, "SSC=" + isSSC + "Statename=" + offLinePdfModels.get(position).getId());
//
                final FragmentTransaction fragmentTransaction = manager.beginTransaction();
//                final DialogFragment newFragment = FragmentCustomDialogLanguage.newInstance();
                FragmentCustomDialogLanguage newFragment=new FragmentCustomDialogLanguage(fragment);
                Bundle bundle = new Bundle();
                String stateName = offLinePdfModels.get(position).getId();
                bundle.putString("STATENAME", stateName);
                bundle.putBoolean("ISSSC", isSSC);
                newFragment.setArguments(bundle);
                newFragment.show(fragmentTransaction, "");

            }
        });
        holder.ivMoreOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Pressed=" + position, Toast.LENGTH_SHORT).show();
//                //creating a popup menu
//                PopupMenu popup = new PopupMenu(context, holder.ivMoreOption);
//                //inflating menu from xml resource
//                popup.inflate(R.menu.options_menu_delete);
//                //adding click listener
//                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        switch (item.getItemId()) {
//                            case R.id.menu1:
//                                //handle menu1 click
//
//
//                                File fdelete = new File(offLinePdfModels.get(position).getPath());
//                                if (fdelete.exists()) {
//                                    if (fdelete.delete()) {
//                                        offLinePdfModels.remove(position);
//                                        notifyDataSetChanged();
//
//                                    } else {
//
//                                    }
//                                }
//                                break;
//                        }
//                        return false;
//                    }
//                });
//                //displaying the popup
//                popup.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return offLinePdfModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public ImageView ivProfile;
        public ImageView ivMoreOption;

        public MyViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.row_fragment_ssc_tv_title);
            ivProfile = (ImageView) view.findViewById(R.id.row_fragment_ssc_iv_logo);
            ivMoreOption = (ImageView) view.findViewById(R.id.row_fragment_ssc_iv_more_option);
        }
    }


}

