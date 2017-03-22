package com.charpixel.baseandroidproject.common;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.charpixel.baseandroidproject.Application;
import com.charpixel.baseandroidproject.Utilities.NetworkHelper;
import com.charpixel.baseandroidproject.Utilities.UIHelper;
import com.charpixel.baseandroidproject.common.listener.FragmentEventListener;
import com.charpixel.baseandroidproject.common.widget.error.AlertDialogFragment;
import com.charpixel.baseandroidproject.common.widget.error.NetworkErrorListener;


/**
 * BaseFragment
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    protected FragmentEventListener mListener;
    private InputMethodManager mInputManager;
    private static AlertDialogFragment alertDialogFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createView(inflater, container, savedInstanceState);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((Application) getActivity().getApplication()).getNetComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (!isNetworkRequired()) {
            return;
        }

        if (!NetworkHelper.isNetworkAvailable(Application._getContext())) {

            if (alertDialogFragment != null && alertDialogFragment.isAdded()) {
                alertDialogFragment.dismiss();
                alertDialogFragment = null;
            }

            alertDialogFragment = AlertDialogFragment.networkError("You are not connected to internet. Please try again");
            alertDialogFragment.setOnClickListener(new NetworkErrorListener() {
                @Override
                public void onRetry() {
                    if (mListener != null) {
                        mListener.onRetryClicked();
                        alertDialogFragment.dismiss();
                    }
                }
            });
            alertDialogFragment.show(getFragmentManager(), AlertDialogFragment.class.getSimpleName());
        }
    }

    protected boolean isNetworkRequired() {
        return true;
    }


    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (FragmentEventListener) activity;
        } catch (ClassCastException e) {
            UIHelper.log("Activity doesn't instance of FragmentEventListener. You must implement FragmentEventListener");
        }
        mInputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        attach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public boolean isDestroy() {
        return getActivity() == null;
    }

    protected void hideKeyboard() {
        if (mInputManager == null || getActivity().getCurrentFocus() == null) {
            return;
        }
        mInputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    protected void updateActionButtonState() {
    }


    protected void attach(Activity activity) {
    }

    public boolean onBackPress() {
        return false;
    }

    public abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void showSnackbar(String s) {



    }
}
