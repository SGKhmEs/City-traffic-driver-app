package bigdata.sg.com.citytrafficdriverapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import bigdata.sg.com.citytrafficdriverapp.Presenters.MainPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.mainActivityButtonControl)
    Button mButtonControl;

    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MainPresenter(this);
        mPresenter.requestPermissions();
        mPresenter.validate();

        mButtonControl.setOnClickListener(mPresenter);
    }

    public void changeButtonState(boolean state)
    {
        if(state)
        {
            //if already running
            mButtonControl.setText(R.string.Stop);
        }
        else
        {
            //if stopped
            mButtonControl.setText(R.string.Start);
        }
    }



}