package bu.edu.ec500.sshealthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import bu.edu.ec500.sshealthapp.ui.main.MainViewModel;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class TestActivity extends AppCompatActivity {

    private MainViewModel mViewModel;
    private TextView[] textViews = new TextView[6];
    private double[] caloris = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

    private SensorRecognizeImpl mSensorRecogImpl;

    public void registerAllObserver() {
        final Observer<Double> accXObserver = new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                TextView accXTextView = (TextView) findViewById(R.id.accXTextView);
                accXTextView.setText(String.format(Locale.ENGLISH, "%.5f", aDouble));
            }
        };

        final Observer<Double> accYObserver = new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                TextView accYTextView = (TextView) findViewById(R.id.accYTextView);
                accYTextView.setText(String.format(Locale.ENGLISH, "%.5f", aDouble));
            }
        };

        final Observer<Double> accZObserver = new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                TextView accZTextView = (TextView) findViewById(R.id.accZTextView);
                accZTextView.setText(String.format(Locale.ENGLISH, "%.5f", aDouble));
            }
        };

        final Observer<Double> gyroXObserver = new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                TextView gyroXTextView = (TextView) findViewById(R.id.gyroXTextView);
                gyroXTextView.setText(String.format(Locale.ENGLISH, "%.5f", aDouble));
            }
        };

        final Observer<Double> gyroYObserver = new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                TextView gyroYTextView = (TextView) findViewById(R.id.gyroYTextView);
                gyroYTextView.setText(String.format(Locale.ENGLISH, "%.5f", aDouble));
            }
        };

        final Observer<Double> gyroZObserver = new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                TextView gyroZTextView = (TextView) findViewById(R.id.gyroZTextView);
                gyroZTextView.setText(String.format(Locale.ENGLISH, "%.5f", aDouble));
            }
        };

        mViewModel.getAccX().observe(this, accXObserver);
        mViewModel.getAccY().observe(this, accYObserver);
        mViewModel.getAccZ().observe(this, accZObserver);
        mViewModel.getGyroX().observe(this, gyroXObserver);
        mViewModel.getGyroY().observe(this, gyroYObserver);
        mViewModel.getGyroZ().observe(this, gyroZObserver);
    }

    private void recordAllTextViews() {
        int[] ids = {
                R.id.accXTextView,
                R.id.accYTextView,
                R.id.accZTextView,
                R.id.gyroXTextView,
                R.id.gyroYTextView,
                R.id.gyroZTextView };

        for (int i = 0; i < ids.length; ++i) {
            textViews[i] = (TextView) findViewById(ids[i]);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        recordAllTextViews();

        updateCaloris();

//        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

//        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        mAccSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        mGyroSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

//        registerAllObserver();

        mSensorRecogImpl = new SensorRecognizeImpl(new MyInitCallback(), new MyCallback());
        mSensorRecogImpl.init(this);
    }

//    @Override
//    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
//        // Do something here if sensor accuracy changes.
//    }
//
//    @Override
//    public final void onSensorChanged(SensorEvent event) {
//        // The light sensor returns a single value.
//        // Many sensors return 3 values, one for each axis.
//        double x = event.values[0];
//        double y = event.values[1];
//        double z = event.values[2];
//        // Do something with this sensor value.
//        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//            mViewModel.getAccX().setValue(x);
//            mViewModel.getAccY().setValue(y);
//            mViewModel.getAccZ().setValue(z);
//        }
//        else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
//            mViewModel.getGyroX().setValue(x);
//            mViewModel.getGyroY().setValue(y);
//            mViewModel.getGyroZ().setValue(z);
//        }
//    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mSensorRecogImpl.isInitialized()) {
            mSensorRecogImpl.start();
        }
    }

    @Override
    protected void onPause() {
        if (mSensorRecogImpl.isInitialized()) {
            mSensorRecogImpl.stop();
        }
        super.onPause();
    }

    class MyInitCallback implements AppInitCallback {
        @Override
        public void onSucceeded() {
            Toast.makeText(getApplicationContext(), "initialization succeeded", Toast.LENGTH_LONG).show();
            mSensorRecogImpl.start();
        }

        @Override
        public void onFailed(AppResult error) {
            Toast.makeText(getApplicationContext(), "initialization failed", Toast.LENGTH_LONG).show();
        }
    }

    class MyCallback implements AppResultCallback<RecognizedActivityResult> {
        @Override
        public void onResult(@NonNull RecognizedActivityResult results) {
            RecognizedActivity activity = results.getMostProbableActivity();
            caloris[activity.getActivityType()] += RecognizedActivity.getActivityCalorisByType(activity.getActivityType()) *
                    results.time;
            updateCaloris();
        }
    }

    private void updateCaloris() {
        for (int i = 0; i < caloris.length; ++i) {
            textViews[i].setText(String.format(Locale.ENGLISH, "%.5f", caloris[i]));
        }
    }
}
