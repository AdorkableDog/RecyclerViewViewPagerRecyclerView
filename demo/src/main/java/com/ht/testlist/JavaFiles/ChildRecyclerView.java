package com.ht.testlist.JavaFiles;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;

import com.ht.testlist.weight.FlingHelper;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;



/**
 * Created by Administrator on 2020/1/16.
 */

public class ChildRecyclerView extends RecyclerView {
	private  FlingHelper mFlingHelper;
	private int mVelocityY;
	private boolean isStartFling;
	private int totalDy;
	@Nullable
	private ParentRecyclerView mParentRecyclerView;

	public final boolean isStartFling() {
		return this.isStartFling;
	}

	public final void setStartFling(boolean var1) {
		this.isStartFling = var1;
	}

	public final int getTotalDy() {
		return this.totalDy;
	}

	public final void setTotalDy(int var1) {
		this.totalDy = var1;
	}

	@Nullable
	public final ParentRecyclerView getMParentRecyclerView() {
		return this.mParentRecyclerView;
	}

	public final void setMParentRecyclerView(@Nullable ParentRecyclerView var1) {
		this.mParentRecyclerView = var1;
	}


	private final void initScrollListener() {
		this.addOnScrollListener((new OnScrollListener() {
			public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				if(ChildRecyclerView.this.isStartFling()) {
					ChildRecyclerView.this.setTotalDy(0);
					ChildRecyclerView.this.setStartFling(false);
				}
				ChildRecyclerView.this.setTotalDy(ChildRecyclerView.this.getTotalDy() + dy);
			}

			public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState) {
				if(newState == 0) {
					ChildRecyclerView.this.dispatchParentFling();
				}

				super.onScrollStateChanged(recyclerView, newState);
			}
		}));
	}

	private final void dispatchParentFling() {
		this.mParentRecyclerView = this.findParentRecyclerView();
		ParentRecyclerView parentRecyclerView = this.mParentRecyclerView;
		if(this.mParentRecyclerView != null) {
			if(this.isScrollTop() && this.mVelocityY != 0) {
				double flingDistance = this.mFlingHelper.getSplineFlingDistance(this.mVelocityY);
				if(flingDistance > (double)Math.abs(parentRecyclerView.getTotalDy())) {
					parentRecyclerView.fling(0, -this.mFlingHelper.getVelocityByDistance(flingDistance + (double)parentRecyclerView.getTotalDy()));
				}
				parentRecyclerView.setTotalDy(0);
				this.mVelocityY = 0;
			}
		}

	}

	public boolean dispatchTouchEvent(@Nullable MotionEvent ev) {
		if(ev != null && ev.getAction() == 0) {
			this.mVelocityY = 0;
		}

		return super.dispatchTouchEvent(ev);
	}

	public boolean fling(int velocityX, int velocityY) {
		if(!this.isAttachedToWindow()) {
			return false;
		} else {
			boolean fling = super.fling(velocityX, velocityY);
			if(fling && velocityY < 0) {
				this.isStartFling = true;
				this.mVelocityY = velocityY;
			} else {
				this.mVelocityY = 0;
			}
			return fling;
		}
	}

	public final boolean isScrollTop() {
		return !this.canScrollVertically(-1);
	}

	private final ParentRecyclerView findParentRecyclerView() {
		ViewParent parentView = this.getParent();
		if(!(parentView instanceof ParentRecyclerView)) {
			parentView = null;
		}
		return (ParentRecyclerView)parentView;
	}

	public ChildRecyclerView(@NotNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.mFlingHelper = new FlingHelper(context);
		this.setOverScrollMode(2);
		this.initScrollListener();
	}

	// $FF: synthetic method
	public ChildRecyclerView(Context var1, AttributeSet var2, int var3, int var4) {
		this(var1, var2, var3);
	}

	public ChildRecyclerView(@NotNull Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0, 4 );
	}

	public ChildRecyclerView(@NotNull Context context) {
		this(context, null, 0, 6);
	}

}

