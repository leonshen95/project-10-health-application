package bu.edu.ec500.sshealthapp.ui.main;

//import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
//import bu.edu.ec500.sshealthapp.db.AppRepository;
//import bu.edu.ec500.sshealthapp.db.Dao.MotionDao;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Double> accX;
    private MutableLiveData<Double> accY;
    private MutableLiveData<Double> accZ;
    private MutableLiveData<Double> gyroX;
    private MutableLiveData<Double> gyroY;
    private MutableLiveData<Double> gyroZ;

    public MutableLiveData<Double> getAccX() {
        if (accX == null) {
            accX = new MutableLiveData<Double>();
            accX.setValue(0.0);
        }

        return accX;
    }

    public MutableLiveData<Double> getAccY() {
        if (accY == null) {
            accY = new MutableLiveData<Double>();
            accY.setValue(0.1);
        }

        return accY;
    }

    public MutableLiveData<Double> getAccZ() {
        if (accZ == null) {
            accZ = new MutableLiveData<Double>();
            accZ.setValue(0.2);
        }

        return accZ;
    }

    public MutableLiveData<Double> getGyroX() {
        if (gyroX == null) {
            gyroX = new MutableLiveData<Double>();
            gyroX.setValue(0.3);
        }

        return gyroX;
    }

    public MutableLiveData<Double> getGyroY() {
        if (gyroY == null) {
            gyroY = new MutableLiveData<Double>();
            gyroY.setValue(0.4);
        }

        return gyroY;
    }

    public MutableLiveData<Double> getGyroZ() {
        if (gyroZ == null) {
            gyroZ = new MutableLiveData<Double>();
            gyroZ.setValue(0.5);
        }

        return gyroZ;
    }
}
