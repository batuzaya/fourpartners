package com.charpixel.fourthpartnerenergy.PlantListModule;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.charpixel.baseandroidproject.common.SortedListAdapter;
import com.charpixel.baseandroidproject.databinding.PlantItemBinding;

import java.util.Comparator;

import static com.twitter.sdk.android.core.TwitterCore.TAG;

/**
 * Created by ashu on 13-12-2016.
 */

public class PlantsAdapter  extends SortedListAdapter<Plant> {

    public interface Listener {
        void onPlantClick(Plant plant);
        void onLoadMore();
    }

    private final PlantsAdapter.Listener mListener;
    private final Context context;

    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;



    public PlantsAdapter(Context context, Comparator<Plant> comparator,RecyclerView recyclerView, PlantsAdapter.Listener listener) {
        super(context, Plant.class, comparator);
        mListener = listener;
        this.context = context;


        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                    .getLayoutManager();


            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView,
                                               int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);

                            Log.v(TAG,linearLayoutManager.findLastVisibleItemPosition()+"");

                            totalItemCount = linearLayoutManager.getItemCount();
                            lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                            if (totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                                // End has been reached
                                // Do something
                                if (mListener != null) {
                                    mListener.onLoadMore();
                                }
                                loading = true;
                            }
                        }
                    });
        }




      //  ((Application) context.getApplicationContext()).getNetComponent().inject(this);
    }



    @Override
    protected SortedListAdapter.ViewHolder<? extends Plant> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        final PlantItemBinding binding = PlantItemBinding.inflate(inflater, parent, false);
        // binding.companyName.setBackgroundColor(randomColors.getRandomColor());
        return new PlantsAdapter.ViewHolder(binding,mListener,context);
    }

    @Override
    protected boolean areItemsTheSame(Plant item1, Plant item2) {
        return item1.getId().equals(item2.getId());
    }

    @Override
    protected boolean areItemContentsTheSame(Plant oldItem, Plant newItem) {
        return oldItem.equals(newItem);
    }




    public static class ViewHolder extends  SortedListAdapter.ViewHolder<Plant> {
        // each data item is just a string in this case


        private final PlantItemBinding mBinding;


        public ViewHolder(PlantItemBinding binding , PlantsAdapter.Listener listener , Context context) {
            super(binding.getRoot());
            binding.setListener(listener);

            mBinding = binding;
        }


        @Override
        protected void performBind(Plant item)
        {
            mBinding.setModel(item);
        }


    }
}
