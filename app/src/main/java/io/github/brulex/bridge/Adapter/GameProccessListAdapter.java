package io.github.brulex.bridge.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.brulex.bridge.DataTransferObject.Player;
import io.github.brulex.bridge.R;

public class GameProccessListAdapter extends RecyclerView.Adapter<GameProccessListAdapter.ViewHolder> {

    private final ArrayList<Player> mData;
    private final LayoutInflater mInflater;
    private Context context;
    private ViewHolder viewHolder;

    public GameProccessListAdapter(Context context, ArrayList<Player> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    @NonNull
    @Override
    public GameProccessListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.player_item, parent, false);
        return new GameProccessListAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final GameProccessListAdapter.ViewHolder holder, int position) {
        Player player = mData.get(position);
        holder.nickname.setText(player.getNickname());
        holder.points.setText(String.valueOf(player.getPoints()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nickname;
        final TextView points;

        ViewHolder(final View itemView) {
            super(itemView);
            nickname = itemView.findViewById(R.id.player_nickname);
            points = itemView.findViewById(R.id.player_points);
        }
    }
}
