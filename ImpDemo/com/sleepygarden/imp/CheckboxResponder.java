package com.sleepygarden.imp;

import com.sleepygarden.imp.views.Checkbox;

/**
 * Created by mcornell on 6/25/15.
 *
 */
public interface CheckboxResponder {
    void checkboxDidUpdate(Checkbox checkbox, boolean isChecked);
}
