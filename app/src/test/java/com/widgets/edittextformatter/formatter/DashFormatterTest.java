package com.widgets.edittextformatter.formatter;

import com.widgets.edittextformatter.utils.Result;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class DashFormatterTest {

    @Test
    @Parameters({
            "1,1 | 1,1",
            "111,1 | 11 1,1",
            "11 1,1 | 11 1,1"
    })
    public void shouldReturnFormattedOutput(String input, int currentCursorPosition, String output, int nextCursorPosition) {
        DashFormatter formatter = new DashFormatter("-- --");

        Result result = formatter.format(input, currentCursorPosition);

        assertEquals(output, result.getFormattedUserInput());
        assertEquals(nextCursorPosition, result.getFormattedCursorPosition());
    }
}