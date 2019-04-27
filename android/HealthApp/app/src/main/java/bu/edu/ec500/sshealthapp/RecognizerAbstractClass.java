package bu.edu.ec500.sshealthapp;

import android.hardware.Sensor;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.SparseIntArray;

public abstract class RecognizerAbstractClass {
    abstract void onResult(double[] confidences);

    private SparseIntArray chosenSensors;

    private int samplingInterval;

    private int samplingCounts;

    public int dataSetIndex;

    public Handler dataFusionHandler;

    public Handler recognitionHandler;

    public SensorData[] dataSet;

    RecognizerAbstractClass() {
        chosenSensors = new SparseIntArray();
        samplingInterval = 20;
        samplingCounts = 128;
        dataSetIndex = 0;

        HandlerThread dataFusionHandlerThread = new HandlerThread("dataFusionHandlerThread");
        dataFusionHandlerThread.start();
        dataFusionHandler = new Handler(dataFusionHandlerThread.getLooper());
        HandlerThread recognitionHandlerThread = new HandlerThread("recognitionHandlerThread");
        recognitionHandlerThread.start();
        recognitionHandler = new Handler(recognitionHandlerThread.getLooper());

        dataSet = new SensorData[samplingCounts];

        setDefaultSensors();
    }

    public void resetRecognizer() {
        dataSetIndex = 0;
        dataSet = new SensorData[samplingCounts];
        dataFusionHandler.removeCallbacksAndMessages(null);
        recognitionHandler.removeCallbacksAndMessages(null);
    }

    public synchronized void addSensorType(int sensorType) {
        chosenSensors.put(sensorType, sensorType);
    }

    public synchronized void removeSensorType(int sensorType) {
        chosenSensors.delete(sensorType);
    }

    public void setSamplingInterval(int duration){
        samplingInterval = duration;
    }

    public int getSamplingInterval(){
        return samplingInterval;
    }

    public void setSamplingPointCount(int pointCount){
        samplingCounts = pointCount;
        dataSet = new SensorData[samplingCounts];
    }

    public int getSamplingPointCount(){
        return samplingCounts;
    }

    public synchronized SparseIntArray getAllSensor() {
        return chosenSensors;
    }

    private void setDefaultSensors() {
        addSensorType(Sensor.TYPE_ACCELEROMETER);
        addSensorType(Sensor.TYPE_GYROSCOPE);
    }
}
