package bigdata.sg.com.citytrafficdriverapp.Services.Models;

import android.content.Context;
import android.test.mock.MockContext;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by bonar on 5/3/2017.
 */

public class GoogleGPSClientTest extends TestCase {

    Context mContext;
    GoogleGPSClient mClient;

    @Before
    public void setUp() throws Exception {
        mContext = new MockContext();
        mClient = new GoogleGPSClient(mContext, null);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreation() throws Exception {
        assertNotNull(mContext);
        assertNotNull(mClient);
    }

    @Test
    public void testConfig() throws Exception {
        long interval = 2000;
        int priority = 2;

        mClient.setInterval(interval);
        assertEquals(mClient.getInterval(), interval);

        mClient.setPriority(priority);
        assertEquals(mClient.getPriority(), priority);
    }
}