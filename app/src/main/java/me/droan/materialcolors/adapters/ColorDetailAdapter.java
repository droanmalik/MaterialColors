package me.droan.materialcolors.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/**
 * Created by drone on 09-05-2016.
 */
public class ColorDetailAdapter extends RecyclerView.Adapter<ColorMainAdapter.Holder> {
  public ColorDetailAdapter(Context context, ArrayList list) {

  }

  @Override public ColorMainAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override public void onBindViewHolder(ColorMainAdapter.Holder holder, int position) {

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
