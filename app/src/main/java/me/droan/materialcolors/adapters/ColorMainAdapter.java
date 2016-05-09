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

  public ColorMainAdapter(Context context, List<Data> list) {
    this.context = context;
    this.list = list;
  }

  @Override public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.item_main_color, parent, false);
    return new Holder(view);
  }

  @Override public void onBindViewHolder(Holder holder, int position) {
    holder.bindTo(list.get(position).code);
  }

  @Override public int getItemCount() {
    return list.size();
  }

  class Holder extends RecyclerView.ViewHolder {
    @Bind(R.id.color) CardView color;

    public Holder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void bindTo(String code) {

      color.setCardBackgroundColor(Utility.getColor(code));
    }
  }
}
