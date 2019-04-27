package bu.edu.ec500.sshealthapp;

import androidx.lifecycle.MutableLiveData;

public class SensorRepo {
    private static SensorRepo instance = null;

    private int datasetIndex;

    private int sampleCounts;

    private long lastTimeUpdate;

    private MutableLiveData<Double> walkingClo;
    private MutableLiveData<Double> standingClo;
    private MutableLiveData<Double> walkingUpClo;
    private MutableLiveData<Double> walkingDownClo;
    private MutableLiveData<Double> lyingClo;
    private MutableLiveData<Double> sittingClo;

    private MutableLiveData<Double> totalClo;

    public SensorData[] dataset;

    private SensorRepo() {
        dataset = new SensorData[128];
        datasetIndex = 0;
        sampleCounts = 0;
        lastTimeUpdate = 0;
    }

    public static SensorRepo getInstance() {
        if (instance == null)
            instance = new SensorRepo();

        return instance;
    }

    public int getDatasetIndex() {return datasetIndex;}

    public int getSampleCounts() {return sampleCounts;}

    public MutableLiveData<Double> getLyingClo() {
        if (lyingClo == null) {
            lyingClo = new MutableLiveData<>();
            lyingClo.setValue(0.0);
        }

        return lyingClo;
    }

    public MutableLiveData<Double> getSittingClo() {
        if (sittingClo == null) {
            sittingClo = new MutableLiveData<>();
            sittingClo.setValue(0.0);
        }

        return sittingClo;
    }

    public MutableLiveData<Double> getStandingClo() {
        if (standingClo == null) {
            standingClo = new MutableLiveData<>();
            standingClo.setValue(0.0);
        }

        return standingClo;
    }

    public MutableLiveData<Double> getWalkingClo() {
        if (walkingClo == null) {
            walkingClo = new MutableLiveData<>();
            walkingClo.setValue(0.0);
        }

        return walkingClo;
    }

    public MutableLiveData<Double> getWalkingDownClo() {
        if (walkingDownClo == null) {
            walkingDownClo = new MutableLiveData<>();
            walkingDownClo.setValue(0.0);
        }

        return walkingDownClo;
    }

    public MutableLiveData<Double> getWalkingUpClo() {
        if (walkingUpClo == null) {
            walkingUpClo = new MutableLiveData<>();
            walkingUpClo.setValue(0.0);
        }

        return walkingUpClo;
    }

    public MutableLiveData<Double> getTotalClo() {
        if (totalClo == null) {
            totalClo = new MutableLiveData<>();
            totalClo.setValue(0.0);
        }

        return totalClo;
    }
}
