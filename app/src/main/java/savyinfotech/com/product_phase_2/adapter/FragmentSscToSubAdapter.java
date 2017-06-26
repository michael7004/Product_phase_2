package savyinfotech.com.product_phase_2.adapter;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import savyinfotech.com.product_phase_2.R;
import savyinfotech.com.product_phase_2.model.SscModel;


/**
 * FragmentPdfAdapter class created on 01/05/17.
 */

public class FragmentSscToSubAdapter extends RecyclerView.Adapter<FragmentSscToSubAdapter.MyViewHolder> {

    private ArrayList<SscModel> offLinePdfModels;
    private Context context;
    private FragmentManager manager;

    public FragmentSscToSubAdapter(Context context, ArrayList<SscModel> offLinePdfModels, FragmentManager manager) {
        this.context = context;
        this.offLinePdfModels = offLinePdfModels;
        this.manager = manager;
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
//                .placeholder(R.drawable.piwo_48)
                .into(holder.ivProfile);


        holder.ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Pressed=" + position, Toast.LENGTH_SHORT).show();
//
//                if (MyDownloadsFragment.isVideos) {
//                    final FragmentTransaction fragmentTransaction = manager.beginTransaction();
//                    final DialogFragment newFragment = CommonDialogVideoFragment.newInstance();
//                    newFragment.show(fragmentTransaction, "");
//                } else {
//
//                    File file = new File(offLinePdfModels.get(position).getPath());
//                    final FragmentTransaction fragmentTransaction = manager.beginTransaction();
//                    final DialogFragment newFragment = CommonDialogPdfLibFragment.newInstance();
//                    Bundle args = new Bundle();
//                    args.putString("path", offLinePdfModels.get(position).getPath());
//                    newFragment.setArguments(args);
//                    newFragment.show(fragmentTransaction, "");
//                }
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

