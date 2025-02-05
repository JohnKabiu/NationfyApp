package com.apps.nationfy.Item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.apps.nationfy.Constants.Constants;
import com.apps.nationfy.Models.CityModels;
import com.apps.nationfy.R;

import java.util.ArrayList;

/**
 * Created by apps on 4/4/2019.
 */

public class CityFilterItem extends RecyclerView.Adapter<CityFilterItem.ItemRowHolder> {

    public ArrayList<CityModels> dataList;
    private Context mContext;
    private int lastSelectedPosition = -1;
    private CompoundButton lastCheckedRB = null;
    public CityFilterItem(Context context, ArrayList<CityModels> dataList) {
        this.dataList = dataList;
        this.mContext = context;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter, parent, false);
        return new ItemRowHolder(v);
    }

    @Override
    public void onBindViewHolder(final ItemRowHolder holder, final int position) {
        final CityModels singleItem = dataList.get(position);

        holder.radioButtonType.setText(singleItem.getCityName());
        holder.radioButtonType.setTag(position);
        holder.radioButtonType.setTag(R.id.filter_name, singleItem);
        holder.radioButtonType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                int tag = (int) compoundButton.getTag();
                if (lastCheckedRB == null) {
                    lastCheckedRB = compoundButton;
                } else if (tag != (int) lastCheckedRB.getTag()) {
                    lastCheckedRB.setChecked(false);
                    lastCheckedRB = compoundButton;
                }
                Constants.FILTERCITYID = singleItem.getCityId();
            }
        });




    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        private TextView text;
        private RelativeLayout relativeLayout;
        RadioGroup checkbox_fil_type;
        RadioButton radioButtonType;

        private ItemRowHolder(View itemView) {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.rootLayout);
            checkbox_fil_type = itemView.findViewById(R.id.myRadioGroupType);
            radioButtonType = itemView.findViewById(R.id.filter_name);

        }
    }
}
