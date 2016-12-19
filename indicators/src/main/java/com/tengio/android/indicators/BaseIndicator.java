package com.tengio.android.indicators;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BaseIndicator extends View{

    private static final Handler HANDLER = new Handler();
    private ViewPager pager;
    private Paint paint;
    private Paint selectedPaint;
    private int selected;
    private int pages;
    private float maxDistanceBetweenPoints;
    private float cxSize;
    private float cxSelectedSize;
    private float delta;
    private float start;
    private float density;
    private Runnable pageSelectionTimeFilter;

    public BaseIndicator(Context context) {
        super(context);
        init();
    }

    public BaseIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        density = getContext().getResources().getDisplayMetrics().density;
        cxSize = 1F * density;
        cxSelectedSize = 4F * density;
        maxDistanceBetweenPoints = 16F * density;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        paint = new Paint();
        selectedPaint = new Paint();

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                if (BaseIndicator.this.isOnIndicator(x)) {
                    BaseIndicator.this.onPageSelected((int) ((x - start) / delta));
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getPages() == 0) {
            return;
        }

        float width = canvas.getWidth();
        delta = Math.min((width - cxSelectedSize * 2) / (pages), maxDistanceBetweenPoints);
        float necessaryWidth = delta * pages;
        start = ((width - necessaryWidth) / 2) + cxSelectedSize;
    }

    private void onPageSelected(int page) {
        if (pager != null) {
            if (page != selected) {
                setPageIndicator(pages, page);
                if (pageSelectionTimeFilter == null) {
                    updateCurrentPage();
                }
            }
        }
    }

    private void updateCurrentPage() {
        pager.setCurrentItem(selected);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        HANDLER.removeCallbacks(pageSelectionTimeFilter);
    }

    private boolean isOnIndicator(float x) {
        return pages != 0 && x > start && x < start + pages * delta;
    }

    public void setPageIndicator(int pages, int selected) {
        this.pages = pages;
        this.selected = selected;
        invalidate();
    }

    public void bindTo(final ViewPager pager) {
        this.pager = pager;
        setPageIndicator(pager.getAdapter().getCount(), 0);
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                setPageIndicator(pager.getAdapter().getCount(), position);
            }
        });
    }

    protected int getPages() {
        return pages;
    }

    protected float getStart() {
        return start;
    }

    protected float getDelta() {
        return delta;
    }

    protected float getSelected() {
        return selected;
    }

    protected float getCxSize() {
        return cxSize;
    }

    protected float getCxSelectedSize() {
        return cxSelectedSize;
    }

    public Paint getIndicator() {
        return paint;
    }

    public Paint getSelectedIndicator() {
        return selectedPaint;
    }

    public void setIndicatorSize(float newSize) {
        cxSize = newSize * density;
    }

    public void setSelectedIndicatorSize(float newSize) {
        cxSelectedSize = newSize * density;
    }

    public void setMaxDistanceBetweenIndicators(float newSize){
        maxDistanceBetweenPoints = newSize * density;
    }

    public void setCircleIndicatorSelectionDelay(long millis) {
        pageSelectionTimeFilter = new Runnable() {
            @Override
            public void run() {
                updateCurrentPage();
            }
        };
        HANDLER.removeCallbacks(pageSelectionTimeFilter);
        HANDLER.postDelayed(pageSelectionTimeFilter, millis);
    }
}
