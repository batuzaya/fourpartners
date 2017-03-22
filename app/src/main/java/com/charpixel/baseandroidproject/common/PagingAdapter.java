package com.charpixel.baseandroidproject.common;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.charpixel.baseandroidproject.R;
import com.charpixel.baseandroidproject.Utilities.UIHelper;

import java.util.ArrayList;
import java.util.List;


public abstract class PagingAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int REFRESH_VIEW = 1;
    public static final int NO_DATA_VIEW = 2;
    private List<T> mData = new ArrayList<>();
    private boolean mHasMore = true;
    private DataSetObserver<T> mDataSetObserver = new DataSetObserver<T>() {
        @Override
        public void onDataSetChanged(List<T> data, boolean p_hasMore) {
            mHasMore = p_hasMore;
            if (data != null) {
                mData.addAll(data);
            }
            notifyDataSetChanged();
        }
    };

    @Override
    public final int getItemCount() {
        int size = getDataSize();
        if (mHasMore || isNoDataView()) {
            return size + 1;
        }
        return size;
    }

    private boolean isNoDataView() {
        return mData.isEmpty();
    }

    public List<T> getData() {
        return mData;
    }

    @Nullable
    public T getItem(int position) {
        if (position < mData.size()) {
            return mData.get(position);
        }
        return null;
    }

    @Override
    public final long getItemId(int position) {
        return position;
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        UIHelper.log("onCreateViewHolder");

        if (viewType == REFRESH_VIEW) {
            return onCreateFooterViewHolder(parent);
        } else if (viewType == NO_DATA_VIEW) {
            return onCreateNoDataViewHolder(parent);
        }
        return onCreateBasicItemViewHolder(parent, viewType);
    }

    public abstract RecyclerView.ViewHolder onCreateBasicItemViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindBasicItemView(RecyclerView.ViewHolder genericHolder, int position);

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder genericHolder, int position) {

        UIHelper.log("onBindViewHolder");

        int itemViewType = getItemViewType(position);
        if (itemViewType == REFRESH_VIEW) {
            getNextPage(mDataSetObserver);
            onBindFooterView(genericHolder, position);
        } else if (itemViewType == NO_DATA_VIEW) {
            // Todo For customize view while No Data on Search
        } else {
            onBindBasicItemView(genericHolder, position);
        }
    }

    public DataSetObserver<T> getDataSetObserver() {
        return mDataSetObserver;
    }

    @Override
    public final int getItemViewType(int position) {
        return isAdapterEmpty() ? NO_DATA_VIEW : isLastCell(position) ? REFRESH_VIEW : super.getItemViewType(position);
    }

    private boolean isLastCell(int position) {
        return position == getDataSize();
    }

    private boolean isAdapterEmpty() {
        return mData.isEmpty();
    }

    private int getDataSize() {
        return mData.size();
    }

    public boolean hasMoreData() {
        return mHasMore;
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        }
    }

    public static class NoDataViewHolder extends RecyclerView.ViewHolder {
        public NoDataViewHolder(View v) {
            super(v);
        }
    }

    public RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.progress_bar, parent, false);
        return new ProgressViewHolder(v);
    }

    public RecyclerView.ViewHolder onCreateNoDataViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_view_no_data, parent, false);
        return new ProgressViewHolder(v);
    }

    public void onBindFooterView(RecyclerView.ViewHolder genericHolder, int position) {
        ((ProgressViewHolder) genericHolder).progressBar.setIndeterminate(true);
    }

    protected abstract void getNextPage(DataSetObserver<T> dataSetObserver);

    public void setHasMoreTrue(boolean hasMore) {
        this.mHasMore = hasMore;
    }

    public interface DataSetObserver<T> {
        void onDataSetChanged(List<T> data, boolean hasMore);
    }
}