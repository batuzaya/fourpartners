package com.charpixel.baseandroidproject.common.listener;

import android.support.annotation.ColorRes;

import com.charpixel.baseandroidproject.common.BaseFragment;


/**
 * FragmentEventListener
 */
public interface FragmentEventListener {

    void addFragment(BaseFragment fragment, boolean addToBackStack);

    void addFragment(BaseFragment fragment, int containerId, boolean addToBackStack);

    void addFragment(BaseFragment fragment, boolean addToBackStack, int enterAnimation, int exitAnimation);

    void addFragment(BaseFragment fragment, boolean addToBackStack, int enterAnimation, int exitAnimation, int popEnterAnim, int popExitAnim);

    void replaceFragment(BaseFragment fragment, boolean addToBackStack);

    void replaceFragment(BaseFragment fragment, boolean addToBackStack, String tag);

    void replaceFragment(BaseFragment fragment, int containerId, boolean addToBackStack);

    void replaceFragment(BaseFragment fragment, boolean addToBackStack, int enterAnimation, int exitAnimation);

    /**
     * @param fragment       to be replaced
     * @param addToBackStack will add to back stack of the current transaction,
     *                       should be false in case of first fragment.
     * @param enterAnimation Set specific animation resources to run for the fragments that are
     *                       entering in this transaction.
     * @param exitAnimation  Set specific animation resources to run for the fragments that are
     *                       exiting in this transaction.
     * @param popEnterAnim   The animations will be played for enter/exit
     *                       operations specifically when popping the back stack.
     * @param popExitAnim    The animations will be played for enter/exit
     *                       operations specifically when popping the back stack.
     */
    void replaceFragment(BaseFragment fragment, boolean addToBackStack, int enterAnimation, int exitAnimation, int popEnterAnim, int popExitAnim);

    void showLoading(boolean show);

    void setToolbarTitle(String title);

    void setToolbarTitle(String name, @ColorRes int color);

    void onRetryClicked();

    void replaceFragment(BaseFragment fragment, boolean addToBackStack, String tag, int enterAnimation, int exitAnimation);
}
