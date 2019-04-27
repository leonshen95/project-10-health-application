package bu.edu.ec500.sshealthapp;

public class RecognizedActivityResult implements AppResultInterface {
    RecognizedActivity[] activities;

    public double time = 2.56;

    private AppResult result = null;

    public RecognizedActivity getMostProbableActivity() {
        if (activities == null || activities.length == 0) {
            return null;
        }

        RecognizedActivity act = activities[0];
        for (RecognizedActivity activity:
                activities) {
            if(act.getActivityPossibility() < activity.getActivityPossibility()){
                act = activity;
            }
        }
        return act;
    }

    public RecognizedActivity[] getProbableActivities(){
        return activities;
    }

    @Override
    public AppResult getError() {
        return result;
    }
}
