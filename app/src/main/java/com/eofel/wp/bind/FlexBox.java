package com.eofel.wp.bind;

import android.view.*;
import android.widget.*;

import androidx.recyclerview.widget.RecyclerView;
import com.eofel.wp.R;
import com.eofel.wp.utils.ItemContent;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class FlexBox extends RecyclerView.Adapter<FlexBox.ViewHolder> {
	private ArrayList<ItemContent> item;

	private OnItemClicked clicked;

	public interface OnItemClicked {
		void itemClicked(ItemContent content)
	}

	public FlexBox(ArrayList<ItemContent> item, OnItemClicked clicked) {
		this.item = item;
		this.clicked = clicked;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.flex_box, parent, false);
		return new ViewHolder(view);

	}

	@Override
	public int getItemCount() {
		return item.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.bind(item.get(position), clicked);
	}

	static class ViewHolder extends RecyclerView.ViewHolder {

		public ImageView imageView;
		public TextView textView;

		public ViewHolder(View itemView) {
			super(itemView);
			imageView = itemView.findViewById(R.id.flexImage);
			textView = itemView.findViewById(R.id.flexName);
		}

		public void bind(final ItemContent item, final OnItemClicked clicked) {
			Picasso.with(itemView.getContext())
				.load(item.getUrl())
				.fit().centerCrop()
				.into(imageView);
			itemView.setTag(textView);
			itemView.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View view) {
						clicked.itemClicked(item);
					}

				});
		}
	}
	
}
