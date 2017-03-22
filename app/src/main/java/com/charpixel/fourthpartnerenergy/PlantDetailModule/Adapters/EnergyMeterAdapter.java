package com.charpixel.fourthpartnerenergy.PlantDetailModule.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.charpixel.baseandroidproject.common.SortedListAdapter;
import com.charpixel.baseandroidproject.databinding.EnergyMeterItemBinding;
import com.charpixel.fourthpartnerenergy.Models.Meter_;

import java.util.Comparator;

/**
 * Created by ashu on 21-12-2016.
 */

public class EnergyMeterAdapter extends SortedListAdapter<Meter_> {

    public interface Listener {
        void onCompanyClick(Meter_ companyRate);
    }

    private final EnergyMeterAdapter.Listener mListener;
    private final Context context;

    private static final Comparator<Meter_> COMPARATOR = new Comparator<Meter_>() {
        @Override
        public int compare(Meter_ a, Meter_ b) {
            return -1;
        }
    };



    public EnergyMeterAdapter(Context context, Comparator<Meter_> comparator, EnergyMeterAdapter.Listener listener) {

        super(context, Meter_.class, comparator == null ? COMPARATOR : comparator);
        mListener = listener;
        this.context = context;

    }



    @Override
    protected SortedListAdapter.ViewHolder<? extends Meter_> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {



        final EnergyMeterItemBinding binding = EnergyMeterItemBinding.inflate(inflater, parent, false);
        // binding.companyName.setBackgroundColor(randomColors.getRandomColor());
        return new EnergyMeterAdapter.ViewHolder(binding,mListener,context);
    }

    @Override
    protected boolean areItemsTheSame(Meter_ item1, Meter_ item2) {
        return true;
    }

    @Override
    protected boolean areItemContentsTheSame(Meter_ oldItem, Meter_ newItem) {
        return oldItem.equals(newItem);
    }






    public static class ViewHolder extends  SortedListAdapter.ViewHolder<Meter_> {
        // each data item is just a string in this case


        private final EnergyMeterItemBinding mBinding;


        public ViewHolder(EnergyMeterItemBinding binding , EnergyMeterAdapter.Listener listener , Context context) {
            super(binding.getRoot());
            mBinding = binding;
        }


        @Override
        protected void performBind(Meter_ item) {
            mBinding.setModel(item);
        }


    }
}
