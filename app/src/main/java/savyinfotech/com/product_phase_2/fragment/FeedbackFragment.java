package savyinfotech.com.product_phase_2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import savyinfotech.com.product_phase_2.R;

/**
 * SscFragment class created on 19/06/17.
 */

public class FeedbackFragment extends BaseFragment {


    private View viewFeedback;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewFeedback= inflater.inflate(R.layout.fragment_feedback,container,false);
        return viewFeedback;
    }

    @Override
    public void initView(View view) {

    }
}
