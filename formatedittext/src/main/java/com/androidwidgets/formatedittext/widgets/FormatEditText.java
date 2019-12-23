package com.androidwidgets.formatedittext.widgets;

import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

import com.androidwidgets.formatedittext.formatter.DashFormatter;
import com.androidwidgets.formatedittext.presenter.FormatEditTextPresenter;
import com.androidwidgets.formatedittext.utils.FormatTextWatcher;
import com.androidwidgets.formatedittext.view.FormatEditTextView;

public class FormatEditText extends AppCompatEditText implements FormatEditTextView {

    private FormatEditTextPresenter formatEditTextPresenter;
    private FormatTextWatcher.Formatter formatter;
    private FormatTextWatcher.Validator validator;
    private FormatTextWatcher.ValidationListener validationListener;
    private FormatTextWatcher textWatcher;
    private String format;

    public FormatEditText(Context context) {
        super(context);
        init();
    }

    public FormatEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FormatEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setFormat(final String format) {
        this.format = format;
        this.setOnFocusChangeListener(new OnFocusChangeListener());
    }

    public void setValidator(FormatTextWatcher.Validator validator, FormatTextWatcher.ValidationListener listener) {
        this.validationListener = listener;
        this.validator = validator;
    }

    @SuppressWarnings("unused")
    public void addFilter(InputFilter.LengthFilter inputFilter) {
        formatEditTextPresenter.addInputFilterTo(getFilters(), inputFilter);
    }

    @SuppressWarnings("unused")
    public void removeFilter(InputFilter.LengthFilter inputFilter) {
        formatEditTextPresenter.removeInputFilterTo(getFilters(), inputFilter);
    }

    public void disableOnSelectionChange() {
        formatEditTextPresenter.setIsOnSelectionChangeEnable(false);
    }

    public void enableOnSelectionChange() {
        formatEditTextPresenter.setIsOnSelectionChangeEnable(true);
    }

    @Override
    public void setCursorPosition(int cursorPosition) {
        boolean isCursorIsInInvalidPosition = cursorPosition != -1;
        if (isCursorIsInInvalidPosition) {
            setSelection(cursorPosition, cursorPosition);
        }
    }

    @Override
    public void addWatcherOnFocus() {
        formatter = new DashFormatter(format);
        textWatcher = new FormatTextWatcher(FormatEditText.this, formatter, validator, validationListener);
        enableOnSelectionChange();
        textWatcher.init();
        textWatcher.setInitialText();
        addTextChangedListener(textWatcher);
    }

    @Override
    public void removeWatcherOnLostFocus() {
        removeTextChangedListener(textWatcher);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        if (!isEditTextEditable(selStart, selEnd)) {
            return;
        }

        formatEditTextPresenter.setCursorPosition(selStart, formatter.getFormat(), getText().toString());
    }

    private void init() {
        formatEditTextPresenter = new FormatEditTextPresenter(this);
    }

    private boolean isEditTextEditable(int selStart, int selEnd) {
        return selStart == selEnd
                && formatter != null
                && formatEditTextPresenter.isOnSelectionChangeEnable();
    }

    private class OnFocusChangeListener implements View.OnFocusChangeListener {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            formatEditTextPresenter.onTextFieldHas(true);
        }
    }
}
