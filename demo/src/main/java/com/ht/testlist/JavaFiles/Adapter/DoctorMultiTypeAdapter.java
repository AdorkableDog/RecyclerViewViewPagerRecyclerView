package com.ht.testlist.JavaFiles.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ht.testlist.JavaFiles.ChildRecyclerView;
import com.ht.testlist.JavaFiles.SimpleCategoryViewHolder;
import com.ht.testlist.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;


/**
 * Created by Administrator on 2020/1/16.
 */

public class DoctorMultiTypeAdapter extends RecyclerView.Adapter {

	private static String TAG = "MultiTypeAdapter";
	private SimpleCategoryViewHolder mCategoryViewHolder;
	private final ArrayList dataSet;



	public DoctorMultiTypeAdapter(@NotNull ArrayList dataSet) {
		this.dataSet = dataSet;
	}


	public int getItemViewType(int position) {
		return this.dataSet.get(position) instanceof String ? 0 : 1;
	}


	@NotNull
	public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int viewType) {
			return (new DoctorMultiTypeAdapter.SimpleTextViewHolder(
					LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_text, viewGroup, false)));

	}

	public int getItemCount() {
		return this.dataSet.size();
	}

	public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int pos) {
		if (holder instanceof DoctorMultiTypeAdapter.SimpleTextViewHolder) {
			Object itemData = this.dataSet.get(pos);
			Log.i(TAG, "onBindViewHolder: " + itemData);
			((SimpleTextViewHolder) holder).text.setText((String) itemData);
		}
	}




	public class SimpleTextViewHolder extends RecyclerView.ViewHolder {

		TextView text;

		public SimpleTextViewHolder(View itemView) {
			super(itemView);
			text = itemView.findViewById(R.id.textView);
		}
	}

}
