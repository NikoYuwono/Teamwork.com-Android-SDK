package com.nikoyuwono.teamwork.sample.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.nikoyuwono.teamwork.sample.R;

/**
 * Created by niko-yuwono on 17/01/08.
 */

public class StarButton extends RadioButton {

    public StarButton(Context context) {
        super(context);
        initializeView();
    }

    public StarButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView();
    }

    public StarButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView();
    }

    private void initializeView() {
        this.setButtonDrawable(null);
        this.setBackgroundDrawable(getResources().getDrawable(R.drawable.star_background));
    }
}
