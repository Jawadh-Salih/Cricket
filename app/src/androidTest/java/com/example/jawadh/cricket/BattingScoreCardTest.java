package com.example.jawadh.cricket;

import android.content.Intent;
import android.test.ActivityUnitTestCase;

/**
 * Created by Jawadh on 9/2/2015.
 */
public class BattingScoreCardTest extends ActivityUnitTestCase<BattingScoreCard> {

    public BattingScoreCardTest(Class<BattingScoreCard> activityClass) {
        super(activityClass);
    }

    public void test() throws Exception {
        final int expected = 1;
        final int reality = 5;
        assertEquals(expected, reality);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent =new Intent(String.valueOf(getInstrumentation().getTargetContext()));
    }
}
