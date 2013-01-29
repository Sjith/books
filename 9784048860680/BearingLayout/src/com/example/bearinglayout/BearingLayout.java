package com.example.bearinglayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.bearinglayout.BearingLayout.LayoutParams.Direction;

public class BearingLayout extends ViewGroup {
	public BearingLayout(Context context) {
		super(context);
	}   

	public BearingLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}   

	public BearingLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}   

	private Rect calculateViewRect(Rect myRect, View child) {
		int width = child.getMeasuredWidth();
		int height = child.getMeasuredHeight();
		if (width > myRect.width()) width = myRect.width();
		if (height > myRect.height()) height = myRect.height();
		Rect result = new Rect();
		BearingLayout.LayoutParams params = (BearingLayout.LayoutParams)child.getLayoutParams();

		switch (params.direction & Direction.LONGITUDE_MASK) {
		case Direction.EAST:
			result.left = myRect.right - width - params.rightMargin;
			result.right = myRect.right - params.rightMargin;
			break;
		case Direction.CENTER_LONGITUDE:
			result.left = (myRect.right + myRect.left) / 2 - width / 2;
			result.right = result.left + width;
			break;
		case Direction.WEST:
		default:
			result.left = myRect.left + params.leftMargin;
			result.right = myRect.left + width + params.leftMargin;
			break;
		}

		switch (params.direction & Direction.LATITUDE_MASK) {
		case Direction.SOUTH:
			result.top = myRect.bottom - height - params.bottomMargin;
			result.bottom = myRect.bottom - params.bottomMargin;
			break;
		case Direction.CENTER_LATITUDE:
			result.top = (myRect.bottom + myRect.top)/2 - height/2;
			result.bottom = result.top + height;
			break;
		case Direction.NORTH:
		default:
			result.top = myRect.top + params.topMargin;
			result.bottom = myRect.top + height + params.topMargin;
			break;
		}

		return result;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		Rect myRect = new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			Rect childRect = calculateViewRect(myRect, child);
			child.layout(childRect.left, childRect.top, childRect.right, childRect.bottom);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		int width = resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec);
		int height = resolveSize(getSuggestedMinimumHeight(), heightMeasureSpec);
		setMeasuredDimension(width, height);
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new BearingLayout.LayoutParams(getContext(), attrs);
	}

	// デフォルトのLayoutParamsのインスタンスを返す
	@Override
	protected LayoutParams generateDefaultLayoutParams() {
		return new BearingLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	}

	// 渡されたViewGroup.LayoutParamsを基に、独自に生成したLayoutParamsを生成する
	@Override
	protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
		return new BearingLayout.LayoutParams(p);
	}

	// 渡されたLayoutParamsが処理可能なものであるか判定する
	@Override
	protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
		return p instanceof BearingLayout.LayoutParams;
	}

	public static class LayoutParams extends ViewGroup.MarginLayoutParams {
		public static class Direction {
			public static final int NORTH            = 0x00000000;
			public static final int CENTER_LATITUDE  = 0x00000001;
			public static final int SOUTH            = 0x00000002;
			public static final int LATITUDE_MASK    = 0x0000000F;
			public static final int WEST             = 0x00000000;
			public static final int CENTER_LONGITUDE = 0x00000010;
			public static final int EAST             = 0x00000020;
			public static final int LONGITUDE_MASK   = 0x000000F0;
		}
		private int direction;
		public LayoutParams(Context c, AttributeSet attrs) {
			super(c, attrs);
			TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.BearingLayout_Layout);
			direction = a.getInt(R.styleable.BearingLayout_Layout_layout_direction, 0);
			a.recycle();
		}

		public LayoutParams(int width, int height) {
			super(width, height);
		}

		public LayoutParams(android.view.ViewGroup.LayoutParams params) {
			super(params);
		}

		public LayoutParams(MarginLayoutParams params) {
			super(params);
		}
	}

}
