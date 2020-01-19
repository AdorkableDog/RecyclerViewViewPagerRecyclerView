package com.ht.testlist.JavaFiles;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ht.testlist.weight.FlingHelper;
import com.ht.testlist.weight.UIUtils;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

/**
 * Created by Administrator on 2020/1/16.
 */

public final class ParentRecyclerView extends RecyclerView {
	private FlingHelper mFlingHelper;
	private float lastY;
	private int totalDy;
	private boolean isStartFling;
	private int velocityY;

	public final int getTotalDy() {
		return this.totalDy;
	}

	public final void setTotalDy(int var1) {
		this.totalDy = var1;
	}

	public final boolean isStartFling() {
		return this.isStartFling;
	}

	public final void setStartFling(boolean var1) {
		this.isStartFling = var1;
	}

	private final void dispatchChildFling() {
		if (this.isScrollEnd() && this.velocityY != 0) {
			double splineFlingDistance = this.mFlingHelper.getSplineFlingDistance(this.velocityY);
			if (splineFlingDistance > (double) this.totalDy) {
				this.childFling(this.mFlingHelper.getVelocityByDistance(splineFlingDistance - (double) this.totalDy));
			}
		}

		this.totalDy = 0;
		this.velocityY = 0;

	}

	private final void childFling(int velY) {
		ChildRecyclerView childRecyclerView = this.findNestedScrollingChildRecyclerView();
		if (childRecyclerView != null) {
			childRecyclerView.fling(0, velY);
		}

	}

	public final void initLayoutManager() {
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext()) {
			public int scrollVerticallyBy(int dy, @Nullable Recycler recycler, @Nullable State state) {
				int scrollby;
				try {
					scrollby = super.scrollVerticallyBy(dy, recycler, state);
				} catch (Exception e) {
					e.printStackTrace();
					scrollby = 0;
				}
				return scrollby;
			}

			public void onLayoutChildren(@Nullable Recycler recycler, @Nullable State state) {
				try {
					super.onLayoutChildren(recycler, state);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			public boolean canScrollVertically() {
				ChildRecyclerView childRecyclerView = ParentRecyclerView.this.findNestedScrollingChildRecyclerView();
				return childRecyclerView == null || childRecyclerView.isScrollTop();
			}

			public void addDisappearingView(@Nullable View child) {
				try {
					super.addDisappearingView(child);
				} catch (Exception var3) {
					var3.printStackTrace();
				}
			}

			public boolean supportsPredictiveItemAnimations() {
				return false;
			}
		};

		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		this.setLayoutManager(linearLayoutManager);
	}

	public boolean dispatchTouchEvent(@Nullable MotionEvent event) {
		if (event != null && event.getAction() == 0) {
			this.velocityY = 0;
			this.stopScroll();
		}
		if (event != null && event.getAction() != 2) {
			this.lastY = 0.0F;
		}

		boolean dispatch;
		try {
			dispatch = super.dispatchTouchEvent(event);
		} catch (Exception var4) {
			var4.printStackTrace();
			dispatch = false;
		}
		return dispatch;
	}

	public boolean onTouchEvent(MotionEvent e) {

		if (this.lastY == 0.0F) {
			this.lastY = e.getY();
		}

		if (this.isScrollEnd()) {
			ChildRecyclerView childRecyclerView = this.findNestedScrollingChildRecyclerView();
			if (childRecyclerView != null) {
				int deltaY = (int) (this.lastY - e.getY());
				if (deltaY != 0) {
					childRecyclerView.scrollBy(0, deltaY);
				}
			}
		}

		this.lastY = e.getY();

		boolean onTounch;
		try {
			onTounch = super.onTouchEvent(e);
		} catch (Exception var7) {
			var7.printStackTrace();
			onTounch = false;
		}

		return onTounch;
	}

	public boolean fling(int velX, int velY) {
		boolean fling = super.fling(velX, velY);
		if (fling && velY > 0) {
			this.isStartFling = true;
			this.velocityY = velY;
		} else {
			this.velocityY = 0;
		}

		return fling;
	}

	private final boolean isScrollEnd() {
		return !this.canScrollVertically(1);
	}

	private final ChildRecyclerView findNestedScrollingChildRecyclerView() {
		Adapter adapter = this.getAdapter();
		if (!(adapter instanceof MultiTypeAdapter)) {
			adapter = null;
		}

		MultiTypeAdapter multiTypeAdapter = (MultiTypeAdapter) adapter;
		if (multiTypeAdapter != null) {
			MultiTypeAdapter mTypeAdapter = multiTypeAdapter;
			return mTypeAdapter.getCurrentChildRecyclerView();
		} else {
			return null;
		}
	}

	public ParentRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		this.mFlingHelper = new FlingHelper(context);
		this.addOnScrollListener(new OnScrollListener() {
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);
				if (newState == 0) {
					ParentRecyclerView.this.dispatchChildFling();
				}
			}

			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				if (ParentRecyclerView.this.isStartFling()) {
					ParentRecyclerView.this.setTotalDy(0);
					ParentRecyclerView.this.setStartFling(false);
				}
				ParentRecyclerView.this.setTotalDy(ParentRecyclerView.this.getTotalDy() + dy);
			}
		});
	}


	public ParentRecyclerView(Context var1, AttributeSet var2, int var3, int var4) {
		this(var1, var2, var3);

	}

	public ParentRecyclerView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0, 4);
	}

	public ParentRecyclerView(Context context) {
		this(context, (AttributeSet) null, 0, 6);
	}


}
