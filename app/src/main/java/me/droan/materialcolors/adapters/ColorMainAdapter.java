package me.droan.materialcolors.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.droan.materialcolors.R;
import me.droan.materialcolors.model.Data;

/**
 * Created by drone on 09-05-2016.
 */
public class ColorMainAdapter extends RecyclerView.Adapter<ColorMainAdapter.Holder> {
  private Context context;

  public ColorMainAdapter(Context context, Data data) {
    this.context = context;
  }

  @Override public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.item_main_color, parent, false);
    return new Holder(view);
  }

  @Override public void onBindViewHolder(Holder holder, int position) {

  }

  @Override public int getItemCount() {
    return 0;
  }

  class Holder extends RecyclerView.ViewHolder {

    public Holder(View itemView) {
      super(itemView);
    }
  }
}
