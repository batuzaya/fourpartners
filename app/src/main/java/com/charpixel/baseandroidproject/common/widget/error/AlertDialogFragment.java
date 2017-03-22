package com.charpixel.baseandroidproject.common.widget.error;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.charpixel.baseandroidproject.R;
import com.charpixel.baseandroidproject.Utilities.UIHelper;
import com.charpixel.baseandroidproject.exception.Validation;

import java.util.Timer;
import java.util.TimerTask;


public class AlertDialogFragment extends DialogFragment implements View.OnClickListener {

    private static final String KEY_TITLE = "key_title";
    private static final String KEY_CANCLABLE = "key_cancelable";
    private static final String KEY_MESSAGE = "key_message";
    private static final String KEY_BUTTON_POSITIVE = "key_button_positive";
    private static final String KEY_BUTTON_NEGATIVE = "key_button_negative";

    private OnClickListener onClickListener;

    private TextView mTextViewMessage;
    private ProgressBar mProgressBar;
    private Timer timer;
    private boolean isViewClicked;

    public AlertDialogFragment() {
        super();
    }
    
    public static AlertDialogFragment newInstance(String title, String message, String textButtonPositive, String textButtonNegative, boolean cancelable) {
        AlertDialogFragment fragment = new AlertDialogFragment();

        fragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, title);
        bundle.putString(KEY_MESSAGE, message);
        bundle.putString(KEY_BUTTON_POSITIVE, textButtonPositive);
        bundle.putString(KEY_BUTTON_NEGATIVE, textButtonNegative);
        bundle.putBoolean(KEY_CANCLABLE, cancelable);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static AlertDialogFragment networkError(String message) {
        return newInstance(null, message, "Retry", null, false);
    }

    public static AlertDialogFragment newInstance(String message, String textButtonPositive, String textButtonNegative, boolean cancelable) {
        return newInstance(null, message, textButtonPositive, textButtonNegative, cancelable);
    }

    public static AlertDialogFragment newInstance(String message, String textButtonPositive, String textButtonNegative) {
        return newInstance(null, message, textButtonPositive, textButtonNegative, false);
    }


    public static AlertDialogFragment newInstance(String title, String message) {
        return newInstance(title, message, "OK", "Cancel", false);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        if (onClickListener == null) {
            throw new NullPointerException("Listener must not be null");
        }
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        if (arguments == null) {
            throw new IllegalStateException("Use newInstance method to create Dialog");
        }

        String title = arguments.getString(KEY_TITLE);
        String message = arguments.getString(KEY_MESSAGE);
        String positiveButtonText = arguments.getString(KEY_BUTTON_POSITIVE);
        String negativeButtonText = arguments.getString(KEY_BUTTON_NEGATIVE);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        @SuppressLint("InflateParams") View dialogView = inflater.inflate(R.layout.layout_custom_dialog, null);
        dialogBuilder.setView(dialogView);

        TextView tvTitle = (TextView) dialogView.findViewById(R.id.customDialogTitle);
        if (title == null || title.isEmpty()) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setText(arguments.getString(KEY_TITLE));
        }

        mTextViewMessage = (TextView) dialogView.findViewById(R.id.customDialogMessage);
        mTextViewMessage.setText(message);

        mProgressBar = (ProgressBar) dialogView.findViewById(R.id.progress);

        Button btnPositive = (Button) dialogView.findViewById(R.id.customDialogButtonOk);
        if (Validation.isNullOrEmpty(positiveButtonText)) {
            btnPositive.setVisibility(View.GONE);
        } else {
            btnPositive.setText(positiveButtonText);
            btnPositive.setOnClickListener(this);
        }

        Button btnNegative = (Button) dialogView.findViewById(R.id.customDialogButtonCancel);
        if (Validation.isNullOrEmpty(negativeButtonText)) {
            btnNegative.setVisibility(View.GONE);
        } else {
            btnNegative.setText(negativeButtonText);
            btnNegative.setOnClickListener(this);
        }

        AlertDialog alertDialog = dialogBuilder.create();
        setCancelable(getArguments().getBoolean(KEY_CANCLABLE));
//        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        final ColorDrawable colorDrawable = new ColorDrawable(UIHelper.getColor(getActivity(), android.R.color.transparent));
        alertDialog.getWindow().setBackgroundDrawable(colorDrawable);

        return alertDialog;
    }

    @Override
    public void onClick(View v) {

        if (onClickListener == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.customDialogButtonOk:
                isViewClicked = true;
                if (onClickListener.onPositiveButtonClick()) {
                    dismiss();
                } else {
                    startLoading();
                }
                break;

            case R.id.customDialogButtonCancel:
                isViewClicked = true;
                dismiss();
                onClickListener.onNegativeButtonClick();
                break;
        }

    }

    private void startLoading() {
        if (mTextViewMessage.getVisibility() == View.GONE && mProgressBar.getVisibility() == View.VISIBLE) {
            return;
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new Timer();
        mTextViewMessage.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                hideLoading();
            }
        }, 1000);
    }

    private void hideLoading() {
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextViewMessage.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public static abstract class OnClickListener {

        public abstract boolean onPositiveButtonClick();

        public abstract void onNegativeButtonClick();

        public void onDismiss() {
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (!isViewClicked && onClickListener != null) {
            onClickListener.onDismiss();
        }
    }

    public static void showAlert(FragmentManager fragmentManager, String buttonText, String statusMsg, String tag) {
        if (tag == null) tag = "Tag";
        final AlertDialogFragment dialogFragment = AlertDialogFragment.newInstance(null, statusMsg, buttonText, null, true);
        dialogFragment.show(fragmentManager, tag);
        dialogFragment.setOnClickListener(new AlertDialogFragment.OnClickListener() {
            @Override
            public boolean onPositiveButtonClick() {
                dialogFragment.dismiss();
                return false;
            }

            @Override
            public void onNegativeButtonClick() {

            }
        });
    }

}