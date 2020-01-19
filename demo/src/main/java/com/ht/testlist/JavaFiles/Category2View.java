package com.ht.testlist.JavaFiles;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;


import com.ht.testlist.JavaFiles.Adapter.HospMultiTypeAdapter;

import java.util.ArrayList;


/**
 * Created by Administrator on 2020/1/16.
 */

public final class Category2View extends ChildRecyclerView {
	private ArrayList mDataList;

	private final void initRecyclerView() {
		StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
		this.setLayoutManager(staggeredGridLayoutManager);
		this.setAdapter(new HospMultiTypeAdapter(this.mDataList));
	}

	private final void initLoadMore() {
		this.addOnScrollListener((new OnScrollListener() {
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);
				boolean needLoadMore = getLastVisibleItem(Category2View.this) >= Category2View.this.getTotalItemCount(Category2View.this) - 4;
				if (needLoadMore) {
					Category2View.this.onLoadMore();
				}

			}
		}));
	}

	public final int getLastVisibleItem(RecyclerView childRecyclerView) {
		LayoutManager layoutManager = childRecyclerView.getLayoutManager();
		int var10000;
		if (layoutManager != null && layoutManager instanceof StaggeredGridLayoutManager) {
			int[] iArr = new int[2];
			((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(iArr);
			var10000 = iArr[0] > iArr[1] ? iArr[0] : iArr[1];
		} else {
			var10000 = -1;
		}

		return var10000;
	}

	public final int getTotalItemCount(RecyclerView childRecyclerView) {
		Adapter var10000 = childRecyclerView.getAdapter();
		return var10000 != null ? var10000.getItemCount() : -1;
	}

	private final void initData() {
		int i = 0;

		for (byte var2 = 11; i < var2; ++i) {
			this.mDataList.add("default child item " + i);
		}

		Adapter var10000 = this.getAdapter();
		if (var10000 != null) {
			var10000.notifyDataSetChanged();
		}

	}

	private final void onLoadMore() {
		int loadMoreSize = 5;
		int i = 0;
		int var3 = loadMoreSize;

		while (true) {
			this.mDataList.add("load more child item " + i);
			if (i == var3) {
				Adapter var10000 = this.getAdapter();
				if (var10000 != null) {
					var10000.notifyItemRangeChanged(this.mDataList.size() - loadMoreSize, this.mDataList.size());
				}
				return;
			}
			++i;
		}
	}

	public Category2View(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.mDataList = new ArrayList();
		this.initRecyclerView();
		this.initLoadMore();
		this.initData();
	}

	// $FF: synthetic method
	public Category2View(Context var1, AttributeSet var2, int var3, int var4) {
		this(var1, var2, var3);
	}

	public Category2View(Context context, AttributeSet attrs) {
		this(context, attrs, 0, 0);
	}

	public Category2View(Context context) {
		this(context, null, 0, 0);
	}


}

