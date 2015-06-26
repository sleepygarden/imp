package com.sleepygarden.imp;

import com.sleepygarden.imp.pojo.Color;
import com.sun.javafx.tools.ant.DeployFXTask;
import com.sun.tools.javac.comp.Check;
import processing.core.PConstants;
import com.sleepygarden.imp.views.*;

/**
 * Created by mcornell on 6/23/15.
 *
 */

public class Templates implements PConstants {

    public ViewTemplate View = new ViewTemplate();
    public LabelTemplate Label = new LabelTemplate();
    public ButtonTemplate Button = new ButtonTemplate();
    public CheckboxTemplate Checkbox = new CheckboxTemplate();
    public SliderTemplate Slider = new SliderTemplate();
    public TextFieldTemplate TextField = new TextFieldTemplate();

    public class ViewTemplate {
        public Color fill = new Color(255);
        public Color hoverFill = null;
        public Color stroke = null;
        public Color hoverStoke = null;
        public int strokeWeight = 0;

        public void style(View view){
            view.fill = this.fill;
            view.hoverFill = this.hoverFill;
            view.stroke = this.stroke;
            view.hoverStoke = this.hoverStoke;
            view.strokeWeight = this.strokeWeight;
        }
    }
    public class LabelTemplate extends ViewTemplate {
        public ImpFont font = new ImpFont("Helvetica",16);
        public Color textColor = new Color(0);
        public int leftPadding = 5;
        public int topPadding = 5;
        public int rightPadding = 5;
        public int bottomPadding = 5;

        public int hAlignment = LEFT;
        public int vAlignment = CENTER;

        public void style(Label label){
            super.style(label);
            label.font = this.font;
            label.textColor = this.textColor;

            label.hAlignment = this.hAlignment;
            label.vAlignment = this.vAlignment;

            label.leftPadding = this.leftPadding;
            label.topPadding = this.topPadding;
            label.rightPadding = this.rightPadding;
            label.bottomPadding = this.bottomPadding;
        }
    }

    public class ButtonTemplate extends LabelTemplate {
        public Color downFill = new Color(33);
        public Color downStroke = null;
        public ButtonTemplate() {
            super();
            this.stroke = new Color(0);
            this.strokeWeight = 1;
            this.hoverFill = new Color(100);
            this.hAlignment = CENTER;
        }
        public void style(Button button){
            super.style(button);
            button.downFill = this.downFill;
            button.downStroke = this.downStroke;
        }
    }

    public class CheckboxTemplate extends ButtonTemplate {
        public Color checkedFill = new Color(33,33,255);
        public float size = 30;

        public void style(Checkbox checkbox){
            super.style(checkbox);
            checkbox.checkedFill = this.checkedFill;
            checkbox.frame.width = this.size;
            checkbox.frame.height = this.size;
        }
    }

    public class SliderTemplate extends ButtonTemplate {
        public int value = 50;
        public int minValue = 0;
        public int maxValue = 100;

        public Color sliderIndicatorFill = new Color(50,50,50);
        public float indicatorSize = 30;


        public SliderTemplate() {
            super();
            this.stroke = null;
            this.strokeWeight = 0;
            this.downFill = null;
        }

        public void style(Slider slider){
            super.style(slider);
            slider.value = this.value;
            slider.minValue = this.minValue;
            slider.maxValue = this.maxValue;
            slider.sliderIndicatorFill = this.sliderIndicatorFill;
            slider.indicatorSize = this.indicatorSize;
        }
    }


    public class TextFieldTemplate extends ButtonTemplate {
        public Color focusFill = null;
        public Color focusStroke = new Color(0);
        public TextFieldTemplate() {
            this.hoverFill = null;
            this.hoverStoke = new Color(55);
            this.hAlignment = LEFT;
        }
        public void style(TextField textfield){
            super.style(textfield);
            textfield.focusFill = this.focusFill;
            textfield.focusStroke = this.focusStroke;
        }
    }

    private static Templates sharedTemplates;
    private Templates() { super(); }
    public static Templates Templates() {
        if (sharedTemplates == null){
            sharedTemplates = new Templates();
        }
        return sharedTemplates;
    }
}
