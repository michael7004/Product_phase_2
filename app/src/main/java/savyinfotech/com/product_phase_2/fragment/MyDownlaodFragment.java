package savyinfotech.com.product_phase_2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import savyinfotech.com.product_phase_2.R;

/**
 * SscFragment class created on 19/06/17.
 */

public class MyDownlaodFragment extends BaseFragment {


    private View viewDownload;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewDownload= inflater.inflate(R.layout.fragment_my_download,container,false);
        return viewDownload;
    }

    @Override
    public void initView(View view) {

    }
}
