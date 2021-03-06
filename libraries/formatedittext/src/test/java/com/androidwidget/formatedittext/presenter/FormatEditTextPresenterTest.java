package com.androidwidget.formatedittext.presenter;

import android.text.InputFilter;

import com.androidwidget.formatedittext.view.FormatEditTextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class FormatEditTextPresenterTest {

    private FormatEditTextPresenter presenter;
    private FormatEditTextView view;

    @Before
    public void setUp() {
        view = Mockito.mock(FormatEditTextView.class);
        presenter = new FormatEditTextPresenter(view);
    }

    @Test
    @Parameters({
            "$$ -- $$, $$ -- $$, 0 | 3",
            "$$ -- $$, $$ -- $$, 7 | 5",
            "$$ -- $$, , 4 | 0",
            "EUR ----, EUR 1, 9 | 5"
    })
    public void shouldSetPossibleCursorPosition(String format, String input, int startSelection, int expectedCursorPosition) {
        presenter.setCursorPosition(startSelection, format, input, '-');

        Mockito.verify(view).setCursorPosition(expectedCursorPosition);
    }

    @Test
    @Parameters({
            "$$ -- $$, $$ -- $$, 4 | -1"
    })
    public void shouldReturnMinus1_whenCursorIsInValidPosition(String format, String input, int startSelection, int expectedCursorPosition) {
        presenter.setCursorPosition(startSelection, format, input, '-');

        Mockito.verify(view).setCursorPosition(expectedCursorPosition);
    }

    @Test
    public void shouldAddGivenInputFilterToExistingFilters() {
        InputFilter[] existingFilters = new InputFilter[0];
        InputFilter newFilter = Mockito.mock(InputFilter.class);
        ArgumentCaptor<InputFilter[]> captor = ArgumentCaptor.forClass(InputFilter[].class);

        presenter.addInputFilterTo(existingFilters, newFilter);

        Mockito.verify(view).setFilters(captor.capture());
        assertEquals(existingFilters.length + 1, captor.getValue().length);
    }

    @Test
    public void shouldRemoveGivenInputFilterFromExistingFilters() {
        InputFilter[] existingFilters = new InputFilter[1];
        InputFilter newFilter = Mockito.mock(InputFilter.class);
        existingFilters[0] = newFilter;
        ArgumentCaptor<InputFilter[]> captor = ArgumentCaptor.forClass(InputFilter[].class);

        presenter.removeInputFilterTo(existingFilters, newFilter);

        Mockito.verify(view).setFilters(captor.capture());
        assertEquals(existingFilters.length - 1, captor.getValue().length);
    }

    @Test
    public void shouldAddTextWatcher_OnFocus() {
        boolean focus = true;
        presenter.onTextFieldHas(focus);

        Mockito.verify(view).addWatcherOnFocus();
    }

    @Test
    public void shouldRemoveTextWatcher_OnNoFocus() {
        boolean noFocus = false;
        presenter.onTextFieldHas(noFocus);

        Mockito.verify(view).removeWatcherOnLostFocus();
    }

    @Test
    public void shouldSetTrueFor_isInSelectionChangeEnable() {
        presenter.setIsOnSelectionChangeEnable(true);

        assertTrue(presenter.isOnSelectionChangeEnable());
    }
}