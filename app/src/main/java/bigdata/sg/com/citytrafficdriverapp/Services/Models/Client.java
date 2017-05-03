package bigdata.sg.com.citytrafficdriverapp.Services.Models;

/**
 * Created by bonar on 5/2/2017.
 */

public interface Client {
    void connect();
    void disconnect();
    Client setPriority(int priority);
    Client setInterval(long interval);
    Client build();
    int getPriority();
    long getInterval();
}
