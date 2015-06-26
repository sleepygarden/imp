package com.sleepygarden.imp;

import com.sleepygarden.imp.views.Slider;

/**
 * Created by mcornell on 6/25/15.
 *
 */
public interface SliderResponder {
    void sliderDidUpdate(Slider slider, float value);
}
