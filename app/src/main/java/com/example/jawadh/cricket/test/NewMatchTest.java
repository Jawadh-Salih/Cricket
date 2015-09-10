package com.example.jawadh.cricket.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jawadh.cricket.MainActivity;
import com.example.jawadh.cricket.R;
import com.example.jawadh.cricket.Register;
import com.robotium.solo.Solo;

/**
 * Created by Jawadh on 9/10/2015.
 */
public class NewMatchTest extends ActivityInstrumentationTestCase2 {
    private Solo solo;
    public NewMatchTest() {
        super(Register.class);
    }
    @Override
    public void setUp() throws Exception {
            super.setUp();
            solo = new Solo(getInstrumentation(), getActivity());
    }


    public void test_user_age() throws Exception{
        EditText eName,euName,eAge,epassWord,eClub;
        Button reg;
        TextView alert;

        eName =(EditText) solo.getView(R.id.name);
        euName = (EditText) solo.getView(R.id.userRname);
        eAge = (EditText) solo.getView(R.id.age);
        epassWord = (EditText) solo.getView(R.id.Rpassword);
        eClub = (EditText) solo.getView(R.id.club);
        alert = (TextView) solo.getView(R.id.tAlertReg);
        reg = (Button) solo.getView(R.id.signup);
        solo.unlockScreen();
        solo.enterText(eName,"Miraj");
        solo.enterText(euName,"miraj");
        solo.enterText(eAge,"23");
        solo.enterText(eClub,"ccc");
        solo.enterText(epassWord,"miraj");
        solo.clickOnView(reg);
        assertTrue(solo.waitForActivity(MainActivity.class));
        solo.finishOpenedActivities();

    }
    public void test_user_age_invalid() throws Exception{
        EditText eName,euName,eAge,epassWord,eClub;
        Button reg;
        TextView alert;

        eName =(EditText) solo.getView(R.id.name);
        euName = (EditText) solo.getView(R.id.userRname);
        eAge = (EditText) solo.getView(R.id.age);
        epassWord = (EditText) solo.getView(R.id.Rpassword);
        eClub = (EditText) solo.getView(R.id.club);
        alert = (TextView) solo.getView(R.id.tAlertReg);
        reg = (Button) solo.getView(R.id.signup);
        solo.unlockScreen();
        solo.enterText(eName,"Miraj");
        solo.enterText(euName,"bugs");
        solo.enterText(eAge,"23");
        solo.enterText(eClub,"ccc");
        solo.enterText(epassWord,"miraj");
        solo.clickOnView(reg);
        assertTrue(solo.waitForText(" Entered username is already in ",1,5000));
        solo.finishOpenedActivities();
    }
}
