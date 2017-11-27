package com.android.policyboss.notification;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.policyboss.R;
import com.android.policyboss.core.models.NotificationMasterEntity;
import com.bumptech.glide.Glide;

import java.util.List;

import static com.android.policyboss.core.controller.database.DatabaseController.getProfessionalID1;

/**
 * Created by IN-RB on 26-11-2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationItem> {

    Activity mContext;
    List<NotificationMasterEntity> NotificationLst;

    public NotificationAdapter(Activity mContext, List<NotificationMasterEntity> notificationLst) {
        this.mContext = mContext;
        NotificationLst = notificationLst;
    }

    public class NotificationItem extends RecyclerView.ViewHolder
    {
        public TextView txtTitle , txtMessage,txtDate;
        public ImageView ivNotify;
        public NotificationItem(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtMessage = (TextView)itemView.findViewById(R.id.txtMessage);
            txtDate = (TextView)itemView.findViewById(R.id.txtDate);
            ivNotify = (ImageView) itemView.findViewById(R.id.ivNotify);
        }
    }


    @Override
    public NotificationAdapter.NotificationItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_item, parent, false);

        return new NotificationAdapter.NotificationItem(itemView);
    }

    @Override
    public void onBindViewHolder(NotificationItem holder, int position) {

        final NotificationMasterEntity notificationEntity = NotificationLst.get(position);
        holder.txtTitle.setText( "" +notificationEntity.getTitle());
        holder.txtMessage.setText( "" +notificationEntity.getMessage());
        holder.txtDate.setText( "" +notificationEntity.getDate());
        Glide.with(mContext)
                .load(notificationEntity.getImgUrl())
                .into(holder.ivNotify);

    }


    @Override
    public int getItemCount() {
        return NotificationLst.size();
    }
}
