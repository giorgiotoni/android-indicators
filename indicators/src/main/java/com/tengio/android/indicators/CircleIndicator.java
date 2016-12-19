package com.tengio.android.indicators;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

public class CircleIndicator extends BaseIndicator {

    public CircleIndicator(Context context) {
        super(context);
    }

    public CircleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        getIndicator().setColor(ContextCompat.getColor(getContext(), android.R.color.black));
        getIndicator().setAntiAlias(true);

        getSelectedIndicator().setColor(ContextCompat.getColor(getContext(), android.R.color.white));
        getSelectedIndicator().setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float height = canvas.getHeight();
        float cy = height / 2;
        for (int i = 0; i < getPages(); i++) {
            canvas.drawCircle(getStart() + getDelta() * i, cy, getCxSize(), getIndicator());
        }
        canvas.drawCircle(getStart() + getDelta() * getSelected(), cy, getCxSelectedSize(), getSelectedIndicator());
    }
}
