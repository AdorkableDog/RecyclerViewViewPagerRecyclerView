package com.ht.testlist.JavaFiles;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.Intrinsics;

/**
 * Created by Administrator on 2020/1/16.
 */

public final class CategoryPagerAdapter extends PagerAdapter {
	private final ArrayList viewList;
	private final List<String> tabTitleList;

	public int getCount() {
		return this.viewList.size();
	}

	public boolean isViewFromObject(@NotNull View view, @NotNull Object obj) {
		return view == obj;
	}

	@NotNull
	public Object instantiateItem(@NotNull ViewGroup container, int position) {
		ChildRecyclerView view = (ChildRecyclerView)this.viewList.get(position);
		if(Intrinsics.areEqual(container, view.getParent())) {
			container.removeView(view);
		}
		container.addView(view);
		return view;
	}

	public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
	}

	@Nullable
	public CharSequence getPageTitle(int position) {
		return this.tabTitleList.get(position);
	}

	public CategoryPagerAdapter(@NotNull ArrayList<ChildRecyclerView> viewList, @NotNull List<String> tabTitleList) {
		super();
		this.viewList = viewList;
		this.tabTitleList = tabTitleList;
	}
}

