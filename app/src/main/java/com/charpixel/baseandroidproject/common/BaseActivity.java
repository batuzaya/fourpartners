package com.charpixel.baseandroidproject.common;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.charpixel.baseandroidproject.Application;
import com.charpixel.baseandroidproject.R;
import com.charpixel.baseandroidproject.Utilities.UIHelper;
import com.charpixel.baseandroidproject.common.listener.DrawerListener;
import com.charpixel.baseandroidproject.common.listener.FragmentEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * BaseActivity
 */
public abstract class BaseActivity extends AppCompatActivity implements FragmentEventListener, View.OnClickListener  {

    private static final String KEY_FRAGMENT_CONTAINER_IDS = "frg_container_list_key";

    private Toolbar mToolbar;
    private ProgressDialog mProgressDialog;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    CustomDrawerAdapter adapter;

    List<DrawerItem> dataList;
    private LinearLayout mNavDrawerLayout;

    private ActionBarDrawerToggle mActionBarDrawerToggle;

    protected List<Integer> containerList = new ArrayList<>();
    private DrawerListener mDrawerListener;

    public LinearLayout navhead;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Application) getApplication()).getNetComponent().inject(this);
     //   FacebookSdk.sdkInitialize(getApplicationContext());
        LayoutInflater layoutInflater = getLayoutInflater();
        @SuppressLint("InflateParams") ViewGroup rootView = (ViewGroup) layoutInflater.inflate(R.layout.activity_base, null);

        //add child layout to main container
        layoutInflater.inflate(getLayoutResourceId(), (ViewGroup) rootView.findViewById(R.id.main_container));
        setContentView(rootView);
        initToolbar();
        initDrawer();
        initTabLayout();
        UIHelper.setUpforKeyboard(rootView, this);

        if (savedInstanceState == null) {
            create(null);
        }
    }

    protected  void initTabLayout(){
        if(isTabLayoutRequired()){
            setTabView();
        }else{
            findViewById(R.id.tab_layout).setVisibility(View.GONE);
        }
    }

    protected boolean isTabLayoutRequired(){
        return false;
    }

        protected void setTabView(){
            throw new ExceptionInInitializerError("tabs not initialised");
        }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(KEY_FRAGMENT_CONTAINER_IDS, (ArrayList<Integer>) containerList);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        containerList = savedInstanceState.getIntegerArrayList(KEY_FRAGMENT_CONTAINER_IDS);
        if (containerList == null) {
            containerList = new ArrayList<>();
        }
    }

    private void initDrawer() {
        dataList = new ArrayList<DrawerItem>();
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);

        View headerview = navigationView.getHeaderView(0);

//        LinearLayout header = (LinearLayout) headerview.findViewById(R.id.nav_head);
//        header.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(BaseActivity.this, LoginActivityTest.class));
//                //drawer.closeDrawer(GravityCompat.START);
//                mDrawerLayout.closeDrawers();
//            }
//        });



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id){


                }
                return true;
            }
        });
      //  mNavDrawerLayout = (LinearLayout) findViewById(R.id.nav_drawer);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open,
                R.string.close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (mDrawerListener != null) {
                    mDrawerListener.onDrawerClosed();
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (mDrawerListener != null) {
                    mDrawerListener.onDrawerOpened();
                }
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, 0);
            }
        };

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        if (!showDrawer()) {
            lockDrawer();
            return;
        }

        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();

        BaseFragment drawerFragment = getDrawerFragment();

        if (showDrawer() && drawerFragment == null) {
            throw new NullPointerException("Drawer fragment should not be null");
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        String tag = drawerFragment.getClass().getName();
        fragmentTransaction.replace(R.id.nav_container, drawerFragment, tag);
        fragmentTransaction.commit();

    }

    public void lockDrawer() {
        if (mDrawerLayout == null) return;
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(false);
    }

    public void unlockDrawer() {
        if (mDrawerLayout == null) return;
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
    }


    protected boolean showDrawer() {
        return false;
    }


    protected void finishWithResult()
    {
        Bundle conData = new Bundle();
        conData.putString("param_result", "Thanks Thanks");
        Intent intent = new Intent();
        intent.putExtras(conData);
        setResult(RESULT_OK, intent);
        finish();
    }

   /* public void closeDrawer() {
        mDrawerLayout.closeDrawer(mNavDrawerLayout);
    }*/

    public void setDrawerListener(DrawerListener mDrawerListener) {
        this.mDrawerListener = mDrawerListener;
    }

  /*  @Override
    public void onBackPressed() {
        if (handleOnBackPressed()) {
            return;
        }
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mNavDrawerLayout)) {
            closeDrawer();
            return;
        }

        if (handleBackPressed()) {
            return;
        }
        super.onBackPressed();
    }*/

    public boolean handleBackPressed() {
        return false;
    }

    protected boolean handleOnBackPressed() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        boolean actionConsumed = false;
        for (Integer containerId : containerList) {
            final BaseFragment fragment = (BaseFragment) fragmentManager.findFragmentById(containerId);
            if (fragment != null) {
                if (fragment.onBackPress()) {
                    actionConsumed = true;
                }
            }
        }
        return actionConsumed;
    }

    protected abstract void create(Bundle savedInstanceState);

    protected abstract
    @LayoutRes
    int getLayoutResourceId();

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setToolbarTitle(getResources().getString(R.string.app_name));
        if(!showToolbar())
        {
            getSupportActionBar().hide();
        }
    }




    @Override
    public void setToolbarTitle(String title) {
        if (title == null) {
            title = "";
        }

        if (mToolbar != null) {
            TextView toolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
            toolbarTitle.setText(title);
        }
    }

    @Override
    public void setToolbarTitle(String title, @ColorRes int color) {
        if (mToolbar != null) {
            TextView toolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
            toolbarTitle.setTextColor(UIHelper.getColor(this, color));
        }
    }

    public void showLoadingDialog() {
        if (isDestroyingActivity()) return;

        if (mProgressDialog == null||!mProgressDialog.isShowing()) {
            mProgressDialog = new ProgressDialog(this, R.style.DialogTheme);
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
            mProgressDialog.setContentView(R.layout.dialog_progress);
        }
        mProgressDialog.show();
    }

    public boolean isShowingProgressDialog() {
        return !(mProgressDialog == null) && mProgressDialog.isShowing();
    }

    public void hideLoadingDialog() {
        if (isDestroyingActivity()) return;

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    public boolean isDestroyingActivity() {
        return isFinishing() || Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && isDestroyed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isShowingProgressDialog()) {
            hideLoadingDialog();
            mProgressDialog = null;
        }
    }


    public void replaceFragment(BaseFragment fragment, boolean addToBackStack) {
        if (containerList.size() == 1) {
            replaceFragment(fragment, containerList.get(0), addToBackStack);
        }
    }

    @Nullable
    protected Fragment getTopFragment() {
        if (containerList.isEmpty()) {
            return null;
        }
        return getSupportFragmentManager().findFragmentById(containerList.get(0));
    }


    public void replaceFragment(BaseFragment fragment, int containerId, boolean addToBackStack) {
        if (fragment == null) {
            return;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(containerId, fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) {
            ft.addToBackStack(getClass().getSimpleName());
        }
        ft.commitAllowingStateLoss();
    }

    public void replaceFragment(BaseFragment fragment, boolean addToBackStack, String TAG, int enterAnimation, int exitAnimation) {
        int containerId = containerList.get(0);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (enterAnimation != -1 || exitAnimation != -1)
            ft.setCustomAnimations(enterAnimation, exitAnimation);

        ft.replace(containerId, fragment, TAG);
        if (addToBackStack) {
            ft.addToBackStack(TAG);
        }
        ft.commitAllowingStateLoss();
    }


    public void addFragment(BaseFragment fragment, boolean addToBackStack) {
        if (containerList.size() == 1) {
            addFragment(fragment, containerList.get(0), addToBackStack);
        }
    }


    public final void addFragment(BaseFragment fragment, int containerId,
                                  boolean addToBackStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(containerId, fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }


    public final void addFragment(BaseFragment fragment, boolean addToBackStack,
                                  int enterAnimation, int exitAnimation) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(enterAnimation, exitAnimation);
        ft.add(containerList.get(0), fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }


    public final void addFragment(BaseFragment fragment, boolean addToBackStack,
                                  int enterAnimation, int exitAnimation, int popEnterAnim, int popExitAnim) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnim, popExitAnim);
        ft.add(containerList.get(0), fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }


    public final void replaceFragment(BaseFragment fragment, boolean addToBackStack,
                                      int enterAnimation, int exitAnimation) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(enterAnimation, exitAnimation);
        ft.replace(containerList.get(0), fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }


    public final void replaceFragment(BaseFragment fragment, boolean addToBackStack,
                                      int enterAnimation, int exitAnimation, int popEnterAnim, int popExitAnim) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnim, popExitAnim);
        ft.replace(containerList.get(0), fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle != null && mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

    }


    public BaseFragment getDrawerFragment() {
        return null;
    }

//    @Override
//    public BaseFragment getDrawerFragment() {
//        return new NavDrawerFragment();
//    }

    protected int fetchPrimaryColor() {
        TypedValue typedValue = new TypedValue();

        TypedArray a = obtainStyledAttributes(typedValue.data, new int[]{R.attr.colorPrimary});
        int color = a.getColor(0, 0);

        a.recycle();

        return color;
    }

    @Override
    public void onRetryClicked() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction ft = fragmentManager.beginTransaction();
        for (Integer containerId : containerList) {
            final Fragment f = fragmentManager.findFragmentById(containerId);
            ft.detach(f);
            ft.attach(f);
        }
        ft.commitAllowingStateLoss();
    }


    protected final void addContainer(int containerId) {
        containerList.add(containerId);
    }


    public void replaceFragment(BaseFragment fragment, boolean addToBackStack, String tag) {
        replaceFragment(fragment, addToBackStack, tag, -1, -1);
    }




    protected boolean showToolbar()
    {
        return true;
    }


    protected void showToolbar(Boolean b){

        if(b){
            getSupportActionBar().show();
        }else {
            getSupportActionBar().hide();
        }

    }
}
