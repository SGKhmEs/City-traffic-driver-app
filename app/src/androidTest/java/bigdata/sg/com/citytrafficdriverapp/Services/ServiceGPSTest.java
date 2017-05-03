package bigdata.sg.com.citytrafficdriverapp.Services;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ServiceTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

/**
 * Created by bonar on 5/3/2017.
 */

@RunWith(AndroidJUnit4.class)
@MediumTest
public class ServiceGPSTest {

    @Rule
    public final ServiceTestRule mServiceRule = new ServiceTestRule();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void testServiceStarted() throws Exception {
        Intent intent = ServiceGPS.newIntent(InstrumentationRegistry.getContext());
        mServiceRule.startService(intent);


    }
}