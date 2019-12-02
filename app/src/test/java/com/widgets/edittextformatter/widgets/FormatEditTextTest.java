package com.widgets.edittextformatter.widgets;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class FormatEditTextTest {

    @Test
    @Parameters({
            "$$ -- $$, $$ -- $$, 0 | 3",
            "$$ -- $$, , 4 | 0"
    })
    public void shouldReturnFirstPossibleCursorPosition(String format, String input, int startSelection, int expectedCursorPosition) {
        int newSelectionStart = FormatEditText.getStartSelection(startSelection, format, input);

        assertEquals(expectedCursorPosition, newSelectionStart);
    }

    @Test
    @Parameters({
            "$$ -- $$, $$ -- $$, 4 | -1"
    })
    public void shouldReturnMinus1_whenCursorIsInValidPosition(String format, String input, int startSelection, int expectedCursorPosition) {
        int newSelectionStart = FormatEditText.getStartSelection(startSelection, format, input);
        int newSelectionEnd = FormatEditText.getLastSelection(startSelection, format, input);

        assertEquals(expectedCursorPosition, newSelectionStart);
        assertEquals(expectedCursorPosition, newSelectionEnd);
    }

    @Test
    @Parameters({
            "$$ -- $$, $$ -- $$, 7 | 5",
            "$$ -- $$, , 4 | 0"
    })
    public void shouldReturnLastPossibleCursorPosition(String format, String input, int startSelection, int expectedCursorPosition) {
        int newSelectionStart = FormatEditText.getLastSelection(startSelection, format, input);

        assertEquals(expectedCursorPosition, newSelectionStart);
    }
}