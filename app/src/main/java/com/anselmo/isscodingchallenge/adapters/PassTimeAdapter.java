package com.anselmo.isscodingchallenge.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.anselmo.isscodingchallenge.R;
import com.anselmo.isscodingchallenge.models.ISSResponse;
import com.anselmo.isscodingchallenge.models.Response;
import com.anselmo.isscodingchallenge.utils.TimeStampUtil;

/**
 * Created by Anselmo on 12/3/2017.
 *
 * No much to say here. Simple ViewHolder pattern.
 *
 * Could to be better using a view injection library like ButterKnife.
 */
public class PassTimeAdapter extends RecyclerView.Adapter<PassTimeAdapter.PassTimeHolder> {
    private Context mContext;
    private ISSResponse model;

    public PassTimeAdapter(Context context, ISSResponse model) {
        this.mContext = context;
        this.model = model;
    }

    public class PassTimeHolder extends RecyclerView.ViewHolder {
        public TextView duration;
        public TextView time;

        public PassTimeHolder(View view) {
            super(view);
            duration = (TextView) view.findViewById(R.id.item_duration);
            time     = (TextView) view.findViewById(R.id.item_time);
        }
    }

    @Override
    public PassTimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new PassTimeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PassTimeHolder holder, int position) {
        Response response = model.getResponse().get(position);
        holder.duration.setText( mContext.getString(R.string.duration) + " " + response.getDuration() / 60 +  " " + mContext.getString(R.string.seconds));
        holder.time.setText( mContext.getString(R.string.time) + " " + TimeStampUtil.getDateFromTimeStamp(response.getRisetime()));
    }

    @Override
    public int getItemCount() {
        return model.getResponse().size();
    }
}