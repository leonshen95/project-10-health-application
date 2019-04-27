package bu.edu.ec500.sshealthapp;

import androidx.annotation.NonNull;
import bu.edu.ec500.sshealthapp.AppResultInterface;

public interface AppResultCallback<R extends AppResultInterface> {
    void onResult(@NonNull R var1);
}
