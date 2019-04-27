package bu.edu.ec500.sshealthapp;

public class HumanActivityRecognizer extends RecognizerAbstractClass {
    public HumanActivityRecognizer(AppResultCallback resultCallback) {
        super(resultCallback);
    }

    @Override
    void onResult(double[] confidences) {
        RecognizedActivityResult result = new RecognizedActivityResult();
        int[] labels = {
                RecognizedActivity.WALKING,
                RecognizedActivity.STANDING,
                RecognizedActivity.WALKING_UP,
                RecognizedActivity.WALKING_DOWN,
                RecognizedActivity.SITTING,
                RecognizedActivity.LYING };
        result.activities = new RecognizedActivity[labels.length];
        for (int index = 0; index < labels.length; ++index) {
            result.activities[index] = new RecognizedActivity(labels[index], confidences[index]);
        }
        if (resultCallback != null) {
            resultCallback.onResult(result);
        }
    }
}
