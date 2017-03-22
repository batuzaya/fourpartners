package com.charpixel.baseandroidproject.common.widget.error;


import com.charpixel.baseandroidproject.Application;
import com.charpixel.baseandroidproject.Utilities.NetworkHelper;

/**
 * NetworkErrorListener
 */
public abstract class NetworkErrorListener extends AlertDialogFragment.OnClickListener {

    @Override
    public boolean onPositiveButtonClick() {
        if (NetworkHelper.isNetworkAvailable(Application._getContext())) {
            onRetry();
            return true;
        }
        return false;
    }

    @Override
    public void onNegativeButtonClick() {

    }

    public abstract void onRetry();

}
