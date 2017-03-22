package com.charpixel.fourthpartnerenergy.PlantDetailModule.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.charpixel.baseandroidproject.common.SortedListAdapter;
import com.charpixel.baseandroidproject.databinding.InverterItemBinding;
import com.charpixel.fourthpartnerenergy.Models.Inverter_;

import java.util.Comparator;

/**
 * Created by ashu on 21-12-2016.
 */

public class inverterAdapter extends SortedListAdapter<Inverter_> {

    public interface Listener {
        void onCompanyClick(Inverter_ companyRate);
    }

    private final inverterAdapter.Listener mListener;
    private final Context context;

    private static final Comparator<Inverter_> COMPARATOR = new Comparator<Inverter_>() {
        @Override
        public int compare(Inverter_ a, Inverter_ b) {
            return -1;
        }
    };



    public inverterAdapter(Context context, Comparator<Inverter_> comparator, inverterAdapter.Listener listener) {

        super(context, Inverter_.class, comparator == null ? COMPARATOR : comparator);
        mListener = listener;
        this.context = context;

    }



    @Override
    protected SortedListAdapter.ViewHolder<? extends Inverter_> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {



        final InverterItemBinding binding = InverterItemBinding.inflate(inflater, parent, false);
        // binding.companyName.setBackgroundColor(randomColors.getRandomColor());
        return new inverterAdapter.ViewHolder(binding,mListener,context);
    }

    @Override
    protected boolean areItemsTheSame(Inverter_ item1, Inverter_ item2) {
        return true;
    }

    @Override
    protected boolean areItemContentsTheSame(Inverter_ oldItem, Inverter_ newItem) {
        return oldItem.equals(newItem);
    }






    public static class ViewHolder extends  SortedListAdapter.ViewHolder<Inverter_> {
        // each data item is just a string in this case


        private final InverterItemBinding mBinding;


        public ViewHolder(InverterItemBinding binding , inverterAdapter.Listener listener , Context context) {
            super(binding.getRoot());
            mBinding = binding;
        }


        @Override
        protected void performBind(Inverter_ item) {
            mBinding.setModel(item);
        }


    }
}

