package bu.edu.ec500.sshealthapp;

public interface AppInitCallback {
    void onSucceeded();

    void onFailed(AppResult error);
}
