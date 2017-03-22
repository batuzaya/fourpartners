package com.charpixel.fourthpartnerenergy.PlantDetailModule.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.charpixel.baseandroidproject.common.SortedListAdapter;
import com.charpixel.baseandroidproject.databinding.InverterMttpItemBinding;
import com.charpixel.fourthpartnerenergy.Models.Inverter;

import java.util.Comparator;

/**
 * Created by ashu on 21-12-2016.
 */

public class InverterTypeMAdapter  extends SortedListAdapter<Inverter> {

    public interface Listener {
        void onCompanyClick(Inverter companyRate);
    }

    private final InverterTypeMAdapter.Listener mListener;
    private final Context context;

    private static final Comparator<Inverter> COMPARATOR = new Comparator<Inverter>() {
        @Override
        public int compare(Inverter a, Inverter b) {
            return -1;
        }
    };



    public InverterTypeMAdapter(Context context, Comparator<Inverter> comparator, InverterTypeMAdapter.Listener listener) {

        super(context, Inverter.class, comparator == null ? COMPARATOR : comparator);
        mListener = listener;
        this.context = context;

    }



    @Override
    protected SortedListAdapter.ViewHolder<? extends Inverter> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {



        final InverterMttpItemBinding binding = InverterMttpItemBinding.inflate(inflater, parent, false);
        // binding.companyName.setBackgroundColor(randomColors.getRandomColor());
        return new InverterTypeMAdapter.ViewHolder(binding,mListener,context);
    }

    @Override
    protected boolean areItemsTheSame(Inverter item1, Inverter item2) {
        return true;
    }

    @Override
    protected boolean areItemContentsTheSame(Inverter oldItem, Inverter newItem) {
        return oldItem.equals(newItem);
    }






    public static class ViewHolder extends  SortedListAdapter.ViewHolder<Inverter> {
        // each data item is just a string in this case


        private final InverterMttpItemBinding mBinding;


        public ViewHolder(InverterMttpItemBinding binding , InverterTypeMAdapter.Listener listener , Context context) {
            super(binding.getRoot());
            mBinding = binding;
        }


        @Override
        protected void performBind(Inverter item) {
            mBinding.setModel(item);
        }


    }
}


