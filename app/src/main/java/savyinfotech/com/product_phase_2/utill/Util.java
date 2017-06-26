package savyinfotech.com.product_phase_2.utill;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import savyinfotech.com.product_phase_2.R;

/**
 * Util class created on 19/06/17.
 */

public class Util {

    public static void hideSoftKeyboard(Activity activity) {
        final InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            if (activity.getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    /**
     * Replace the fragment in container
     *
     * @param fragment
     */
    public static void replaceFragment(final Context context, final Fragment fragment, final int container) {
        ((Activity) context).getFragmentManager()
                .beginTransaction()
                .replace(container, fragment, fragment.getClass().getSimpleName()).commit();
    }

    /**
     * Hide current fragment and add new fragment to container
     *
     * @param currentFragment
     * @param newFragment
     */
    public static void addFragment(final Context context, final Fragment currentFragment, final Fragment newFragment, final int container) {
        ((Activity) context).getFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .add(container, newFragment, newFragment.getClass().getSimpleName())
                .hide(currentFragment)
                .addToBackStack(currentFragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    /**
     * To show progress dialog
     *
     * @param progressDialog
     */
    public static final void dismissProgressDialogNew(ProgressDialog progressDialog) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * To dissmiss progress dialog
     *
     * @param mActivity
     * @param message
     * @param isCancelable
     * @return
     */

    public static ProgressDialog showProgressDialogNew(final Context mActivity, final String message, boolean isCancelable) {
        final ProgressDialog mDialog = new ProgressDialog(mActivity, R.style.customeDialog);
        mDialog.show();
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
        //mDialog.setMessage(message);
        mDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        return mDialog;
    }


}
