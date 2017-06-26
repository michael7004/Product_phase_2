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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import savyinfotech.com.product_phase_2.R;
import savyinfotech.com.product_phase_2.model.PaperModel;


/**
 * FragmentPdfAdapter class created on 01/05/17.
 */

public class FragmentPaperAdapter extends RecyclerView.Adapter<FragmentPaperAdapter.MyViewHolder> {

    private String TAG = FragmentPaperAdapter.class.getSimpleName();
    private ArrayList<PaperModel> paperArrayList;
    private Context context;
    private Fragment fragment;
    private static boolean isSSC;
    private FragmentManager manager;
    private String year;


    public FragmentPaperAdapter(Context context, ArrayList<PaperModel> paperArrayList,String year) {
        this.context = context;
        this.paperArrayList = paperArrayList;
        this.year=year;


    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_fragment_paper, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvTitle.setText(paperArrayList.get(position).getName());

//        String url = paperArrayList.get(position).getUrl();
//        Glide.with(context)
//                .load(url)
////                .placeholder(R.drawable.piwo_48)
//
//                .into(holder.ivProfile);

//        holder.ivProfile.setContentDescription(year);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Pressed=" + position, Toast.LENGTH_SHORT).show();
                Log.d(TAG, paperArrayList.get(position).getId());

                Log.d(TAG, "SSC=" + isSSC + "Statename=" + paperArrayList.get(position).getId());
            }
        });




    }

    @Override
    public int getItemCount() {
        return paperArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public ImageView ivProfile;
        public RelativeLayout relativeLayout;

        public MyViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.row_fragment_paper_tv_title);
            ivProfile = (ImageView) view.findViewById(R.id.row_fragment_paper_tv_logo);
            relativeLayout=view.findViewById(R.id.row_fragment_paper_rl_container);
        }
    }
}

