package bu.edu.ec500.sshealthapp.ui.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatDialogFragment;
import bu.edu.ec500.sshealthapp.R;

public class myDialog extends AppCompatDialogFragment {
    private NumberPicker np1;
    private myDialogListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);
        builder.setView(view)
                .setTitle("Set Your Goal")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.Resume();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        float max = np1.getValue();
                        listener.applyVal(max);
                    }
                });
        np1 = view.findViewById(R.id.np1);
        np1.setMinValue(0);
        np1.setMaxValue(1000);
        np1.setWrapSelectorWheel(true);
        return builder.create();
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        try {
            listener = (myDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException((context.toString()+"must implement myDialogListener"));
        }
    }
    public interface myDialogListener{
        void applyVal(float max);
        void Resume();
    }

}
