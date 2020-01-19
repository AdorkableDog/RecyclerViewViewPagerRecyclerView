package com.ht.testlist.JavaFiles;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import com.ht.testlist.JavaFiles.Adapter.GoodMultiTypeAdapter;

import java.util.ArrayList;


/**
 * Created by Administrator on 2020/1/16.
 */

public final class CategoryView extends ChildRecyclerView {
	private ArrayList mDataList;

	private final void initRecyclerView() {
		StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
		this.setLayoutManager(staggeredGridLayoutManager);
		this.setAdapter(new GoodMultiTypeAdapter(this.mDataList));
	}

	private final void initLoadMore() {
		this.addOnScrollListener((new OnScrollListener() {
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);
				boolean needLoadMore = getLastVisibleItem(CategoryView.this) >= CategoryView.this.getTotalItemCount(CategoryView.this) - 4;
				if (needLoadMore) {
					CategoryView.this.onLoadMore();
				}
			}
		}));
	}

	public final int getLastVisibleItem(RecyclerView childRecyclerView) {
		LayoutManager layoutManager = childRecyclerView.getLayoutManager();
		int LastVisibleItem;
		if (layoutManager != null && layoutManager instanceof StaggeredGridLayoutManager) {
			int[] iArr = new int[2];
			((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(iArr);
			LastVisibleItem = iArr[0] > iArr[1] ? iArr[0] : iArr[1];
		} else {
			LastVisibleItem = -1;
		}
		return LastVisibleItem;
	}

	public final int getTotalItemCount(RecyclerView childRecyclerView) {
		Adapter adapter = childRecyclerView.getAdapter();
		return adapter != null ? adapter.getItemCount() : -1;
	}

	private final void initData() {
		int i = 0;
		for (byte var2 = 11; i < var2; ++i) {
			this.mDataList.add("default child item " + i);
		}
		Adapter adapter = this.getAdapter();
		if (adapter != null) {
			adapter.notifyDataSetChanged();
		}

	}

	private final void onLoadMore() {
		int loadMoreSize = 5;
		int i = 0;
		int var3 = loadMoreSize;

		while (true) {
			this.mDataList.add("load more child item " + i);
			if (i == var3) {
				Adapter adapter = this.getAdapter();
				if (adapter != null) {
					adapter.notifyItemRangeChanged(this.mDataList.size() - loadMoreSize, this.mDataList.size());
				}
				return;
			}
			++i;
		}
	}

	public CategoryView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.mDataList = new ArrayList();
		this.initRecyclerView();
		this.initLoadMore();
		this.initData();
	}

	// $FF: synthetic method
	public CategoryView(Context var1, AttributeSet var2, int var3, int var4) {
		this(var1, var2, var3);
	}

	public CategoryView(Context context, AttributeSet attrs) {
		this(context, attrs, 0, 4);
	}

	public CategoryView(Context context) {
		this(context,null, 0, 6);
	}


}

