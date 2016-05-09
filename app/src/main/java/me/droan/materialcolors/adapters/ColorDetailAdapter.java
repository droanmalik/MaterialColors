package me.droan.materialcolors.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import java.util.List;
import me.droan.materialcolors.R;
import me.droan.materialcolors.Utility;
import me.droan.materialcolors.model.Color;

/**
 * Created by drone on 09-05-2016.
 */
public class ColorDetailAdapter extends RecyclerView.Adapter<ColorDetailAdapter.Holder> {
  private Context context;
  private List<Color> list;
  private ItemClickListener listener;

  public ColorDetailAdapter(Context context, List<Color> list, ItemClickListener listener) {
    this.context = context;
    this.list = list;
    this.listener = listener;
  }

  @Override public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.item_detail_color, parent, false);
    return new Holder(view);
  }

  @Override public void onBindViewHolder(Holder holder, int position) {
    holder.bindTo(list.get(position), listener);
  }

  @Override public int getItemCount() {
    return list.size();
  }

  public void refresh(List<Color> list) {
    this.list = list;
    notifyDataSetChanged();
  }

  public interface ItemClickListener {
    void onItemClick(String code);
  }

  class Holder extends RecyclerView.ViewHolder {
    @Bind(R.id.root) CardView root;
    @Bind(R.id.name) TextView name;
    @Bind(R.id.code) TextView code;

    public Holder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void bindTo(final Color color, final ItemClickListener listener) {
      root.setCardBackgroundColor(Utility.getColor(color.code));
      name.setText(color.name);
      code.setText(color.code);
      name.setTextColor(Utility.getColor(color.white));
      code.setTextColor(Utility.getColor(color.white));
      root.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          listener.onItemClick(color.code);
        }
      });
    }
  }
}
