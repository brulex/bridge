package io.github.brulex.bridge.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.brulex.bridge.DataTransferObject.DatabaseHandler;
import io.github.brulex.bridge.DataTransferObject.GameSetting;
import io.github.brulex.bridge.GameActivity;
import io.github.brulex.bridge.R;

public class ActiveGamesListAdapter extends RecyclerView.Adapter<ActiveGamesListAdapter.ViewHolder> {

    private final ArrayList<GameSetting> mData;
    private final LayoutInflater mInflater;
    private Context context;

    public ActiveGamesListAdapter(Context context, ArrayList<GameSetting> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.game_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position)  {
        GameSetting gameSetting = mData.get(position);
        holder.game_name.setText(gameSetting.getGame_name());
        holder.current_round.setText(String.valueOf(gameSetting.getCurrent_round()));
        holder.player_cnt.setText(String.valueOf(gameSetting.getPlayers().size()));
        holder.date.setText(gameSetting.getDate());
        holder.delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(context);
                int pos = holder.getAdapterPosition();
                db.deleteGameSetting(mData.get(pos).getI_setting());
                mData.remove(pos);
                notifyItemRemoved(pos);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                Intent game = new Intent(context, GameActivity.class);
                game.putExtra("i_setting",mData.get(pos).getI_setting());
                context.startActivity(game);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView game_name;
        final TextView current_round;
        final TextView player_cnt;
        final TextView date;
        final ImageButton delete_item;

        ViewHolder(final View itemView) {
            super(itemView);
            game_name = itemView.findViewById(R.id.active_game_name);
            player_cnt = itemView.findViewById(R.id.active_number_of_players);
            current_round = itemView.findViewById(R.id.active_current_round);
            date = itemView.findViewById(R.id.active_date);
            delete_item = itemView.findViewById(R.id.active_delete);
        }
    }

}
