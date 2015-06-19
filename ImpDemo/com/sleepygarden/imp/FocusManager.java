package com.sleepygarden.imp;

public class FocusManager extends Object {
    public Button focusedView;

    private static FocusManager sharedManager;
    public static FocusManager sharedManager(){
        if (sharedManager == null){
            sharedManager = new FocusManager();
        }
        return sharedManager;
    }

    public void reportGainFocus(Button b){
        if (this.focusedView != null) {
            this.focusedView.willLoseFocus();
        }
        this.focusedView = b;
        if (this.focusedView != null){
            this.focusedView.willGainFocus();
        }
    }
}