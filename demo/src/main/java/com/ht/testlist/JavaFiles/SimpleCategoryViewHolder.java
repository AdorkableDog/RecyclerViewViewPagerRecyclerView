package com.ht.testlist.JavaFiles;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ht.testlist.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2020/1/16.
 */

public class SimpleCategoryViewHolder extends RecyclerView.ViewHolder {

	@NotNull
	private ArrayList viewList;
	private ChildRecyclerView mCurrentRecyclerView;
	private TabLayout mTabLayout;
	private ViewPager mViewPager;

	public SimpleCategoryViewHolder(View itemView) {
		super(itemView);
		this.viewList = new ArrayList();
		mTabLayout = itemView.findViewById(R.id.tabs);
		mViewPager = itemView.findViewById(R.id.viewPager);
	}


	public final void bindData() {
		viewList.add(new CategoryView(this.itemView.getContext(), null));
		viewList.add(new Category2View(this.itemView.getContext(), null));
		viewList.add(new Category3View(this.itemView.getContext(), null));
		List<String> data = new ArrayList<>();
		data.add("精选");
		data.add("医生");
		data.add("医院");

		mCurrentRecyclerView = (ChildRecyclerView) viewList.get(mViewPager.getCurrentItem());
		int lastItem = mViewPager.getCurrentItem();
		CategoryPagerAdapter adapter = new CategoryPagerAdapter(viewList, data);
		mViewPager.setAdapter(adapter);
		mTabLayout.setupWithViewPager(mViewPager);
		mViewPager.setCurrentItem(lastItem);

		mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}

			@Override
			public void onPageSelected(int position) {
				if (viewList.size() > 0) {
					mCurrentRecyclerView = (ChildRecyclerView) viewList.get(position);
					Log.d("gaohui", "onPageSelected: $position $mCurrentRecyclerView");
				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
	}

	@Nullable
	public final ChildRecyclerView getCurrentChildRecyclerView() {
		return this.mCurrentRecyclerView;
	}


}
