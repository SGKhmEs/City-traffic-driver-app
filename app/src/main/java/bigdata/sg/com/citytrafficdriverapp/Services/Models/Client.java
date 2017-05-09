package bigdata.sg.com.citytrafficdriverapp.Services.Models;

public interface Client {
    void connect();
    void disconnect();
    Client setPriority(int priority);
    Client setInterval(long interval);
    Client build();
    int getPriority();
    long getInterval();
}
