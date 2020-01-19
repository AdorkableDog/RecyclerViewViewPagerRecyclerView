package com.ht.testlist.weight;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.ht.testlist.fragment.PagerFragment;
import java.util.List;

/**
 * Created by Administrator on 2020/1/15.
 */

public final class CategoryPagerAdapter extends FragmentPagerAdapter {



	private List<String> data;
	private List<PagerFragment> fragments;

	public CategoryPagerAdapter(FragmentManager fm, List<String> data, List<PagerFragment> fragments) {
		super(fm);
		this.data = data;
		this.fragments=fragments;
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		return data.get(position);
	}
}

