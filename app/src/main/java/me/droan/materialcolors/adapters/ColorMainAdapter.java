package me.droan.materialcolors.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import java.util.List;
import me.droan.materialcolors.R;
import me.droan.materialcolors.Utility;
import me.droan.materialcolors.model.Data;

/**
 * Created by drone on 09-05-2016.
 */
public class ColorMainAdapter extends RecyclerView.Adapter<ColorMainAdapter.Holder> {
  private Context context;
  private List<Data> list;
  private ItemClickListener listener;

  public ColorMainAdapter(Context context, List<Data> list, ItemClickListener listener) {
    this.context = context;
    this.list = list;
    this.listener = listener;
  }

  @Override public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.item_main_color, parent, false);
    return new Holder(view);
  }

  @Override public void onBindViewHolder(Holder holder, int position) {
    holder.bindTo(list.get(position).code, listener);
  }

  @Override public int getItemCount() {
    return list.size();
  }

  public interface ItemClickListener {
    void onItemClick(int position);
  }

  class Holder extends RecyclerView.ViewHolder {
    @Bind(R.id.color) CardView color;

    public Holder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void bindTo(String code, final ItemClickListener listener) {

      color.setCardBackgroundColor(Utility.getColor(code));
      color.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          listener.onItemClick(getAdapterPosition());
        }
      });
    }
  }
}
