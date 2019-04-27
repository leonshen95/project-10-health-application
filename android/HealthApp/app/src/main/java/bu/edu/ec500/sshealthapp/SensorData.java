package bu.edu.ec500.sshealthapp;

public class SensorData {
    public float[] accelerate, gyroscope;

    public long timestamp;

    public SensorData() {
        accelerate = new float[3];
        gyroscope = new float[3];
        timestamp = System.currentTimeMillis();
    }

    public void clone(SensorData sd) {
        System.arraycopy(sd.accelerate, 0, this.accelerate, 0, 3);
        System.arraycopy(sd.gyroscope, 0, this.gyroscope, 0, 3);
        this.timestamp = sd.timestamp;
    }
}
