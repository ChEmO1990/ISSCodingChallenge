package com.anselmo.isscodingchallenge;

import com.anselmo.isscodingchallenge.utils.TimeStampUtil;
import org.junit.Test;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Anselmo on 12/3/2017.
 */

public class TimeStampUtilTest {
    @Test
    public void testConvertFahrenheitToCelsius() {
        CharSequence actual = TimeStampUtil.getDateFromTimeStamp(1512333488);
        assertNotNull(actual);
    }
}
