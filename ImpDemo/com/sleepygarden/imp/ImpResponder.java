package com.sleepygarden.imp;

import com.sleepygarden.imp.views.Button;
import com.sleepygarden.imp.views.TextField;

/**
 * Created by mcornell on 6/22/15.
 *
 */

public interface ImpResponder {
	void impButtonWasPressed(Button b);
	void impTextFieldDidReturn(TextField tf);
}