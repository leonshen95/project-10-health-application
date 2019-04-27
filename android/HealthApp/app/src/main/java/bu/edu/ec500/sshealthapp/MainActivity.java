package bu.edu.ec500.sshealthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import bu.edu.ec500.sshealthapp.ui.main.MainFragment;
import bu.edu.ec500.sshealthapp.ui.main.MainViewModel;

import android.graphics.Color;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnticipateInterpolator;
import android.widget.TextView;
import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.DecoDrawEffect;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;

/**
 * Sample to create an animated arc based chart in the style used by Google Fit
 * <p/>
 * This is achieved using the open source DecoView library.
 *
 * @see <a href="https://github.com/bmarrdev/android-DecoView-charting">DecoView on GitHub</a>
 */
public class MainActivity extends AppCompatActivity {

    /**
     * DecoView animated arc based chart
     */
    private DecoView mDecoView;

    /**
     * Data series index used for controlling animation of {@link DecoView}. These are set when
     * the data series is created then used in {@link #createEvents} to specify what series to
     * apply a given event to
     */
    private int mBackIndex;
    private int mSeries1Index;
    private int mSeries2Index;
    private int mSeries3Index;
    private int mSeries4Index;
    private int mSeries5Index;
    private int mSeries6Index;
    private float walktime = 300;
    private float walkuptime = 200;
    private float walkdntime = 100;
    private float standtime = 200;
    private float laytime = 300;
    private float sittime = 200;
    /**
     * Maximum value for each data series in the {@link DecoView}. This can be different for each
     * data series, in this example we are applying the same all data series
     */
    private final float mSeriesMax = 100f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, MainFragment.newInstance())
//                    .commitNow();
//        }

        mDecoView = (DecoView) findViewById(R.id.dynamicArcView);

        // Create required data series on the DecoView
        createBackSeries();
        createDataSeries1();
        createDataSeries2();
        createDataSeries3();
        createDataSeries4();
        createDataSeries5();
        createDataSeries6();

        // Setup events to be fired on a schedule
        createEvents();
    }

    private void createBackSeries() {
        SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#FFE2E2E2"))
                .setRange(0, mSeriesMax, 0)
                .setInitialVisibility(true)
                .build();

        mBackIndex = mDecoView.addSeries(seriesItem);
    }

    private void createDataSeries1() {
        final SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#FFFF8800"))
                .setRange(0, mSeriesMax, 0)
                .setInitialVisibility(false)
                .build();

        final TextView textPercentage = (TextView) findViewById(R.id.textPercentage);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                float percentFilled = ((currentPosition - seriesItem.getMinValue()) / (seriesItem.getMaxValue() - seriesItem.getMinValue()));
                textPercentage.setText(String.format("%.0f%%", percentFilled * 100f));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });


        final TextView textToGo = (TextView) findViewById(R.id.textRemaining);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {

                textToGo.setText(String.format("%.1f Cal to goal", seriesItem.getMaxValue() - currentPosition));

            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });

        final TextView walking = (TextView) findViewById(R.id.walking);
        final TextView textwalk = (TextView) findViewById(R.id.textwalking);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                textwalk.setText("WALK: ");
                walking.setText(String.format("%.0f Cal", currentPosition));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });

        mSeries1Index = mDecoView.addSeries(seriesItem);
    }

    private void createDataSeries2() {
        final SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#FFFF4444"))
                .setRange(0, mSeriesMax, 0)
                .setInitialVisibility(false)
                .build();
        final TextView textPercentage = (TextView) findViewById(R.id.textPercentage);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                float percentFilled = ((currentPosition + walktime*0.093f ) / (seriesItem.getMaxValue() - seriesItem.getMinValue()));
                textPercentage.setText(String.format("%.0f%%", percentFilled * 100f));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });


        final TextView textToGo = (TextView) findViewById(R.id.textRemaining);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                textToGo.setText(String.format("%.1f Cal to goal", seriesItem.getMaxValue() - (currentPosition + walktime*0.093f)));

            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });
        final TextView standing = (TextView) findViewById(R.id.standing);
        final TextView textstand = (TextView) findViewById(R.id.textstanding);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                textstand.setText("STAND: ");
                standing.setText(String.format("%.0f Cal", currentPosition));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });

        mSeries2Index = mDecoView.addSeries(seriesItem);
    }

    private void createDataSeries3() {
        final SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#FF6699FF"))
                .setRange(0, mSeriesMax, 0)
                .setInitialVisibility(false)
                .build();
        final TextView textPercentage = (TextView) findViewById(R.id.textPercentage);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                float percentFilled = ((currentPosition + walktime*0.093f + standtime*0.014f) / (seriesItem.getMaxValue() - seriesItem.getMinValue()));
                textPercentage.setText(String.format("%.0f%%", percentFilled * 100f));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });


        final TextView textToGo = (TextView) findViewById(R.id.textRemaining);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                textToGo.setText(String.format("%.1f Cal to goal", seriesItem.getMaxValue() - (currentPosition + walktime*0.093f + standtime*0.014f)));

            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });
        final TextView walkupstair = (TextView) findViewById(R.id.walkupstair);
        final TextView textwalkup = (TextView) findViewById(R.id.textwalkingup);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                textwalkup.setText("WALK-UP: ");
                walkupstair.setText(String.format("%.0f Cal", currentPosition));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        }

        );

        mSeries3Index = mDecoView.addSeries(seriesItem);
    }
    private void createDataSeries4() {
        final SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#70522a"))
                .setRange(0, mSeriesMax, 0)
                .setInitialVisibility(false)
                .build();
        final TextView textPercentage = (TextView) findViewById(R.id.textPercentage);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                float percentFilled = ((currentPosition + walktime*0.093f + + standtime*0.014f + walkuptime*0.17f) / (seriesItem.getMaxValue() - seriesItem.getMinValue()));
                textPercentage.setText(String.format("%.0f%%", percentFilled * 100f));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });


        final TextView textToGo = (TextView) findViewById(R.id.textRemaining);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                textToGo.setText(String.format("%.1f Cal to goal", seriesItem.getMaxValue() - (currentPosition + walktime*0.093f + standtime*0.014f + walkuptime*0.17f )));

            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });
        final TextView walkdownstair = (TextView) findViewById(R.id.walkdownstair);
        final TextView textwalkdn = (TextView) findViewById(R.id.textwalkdn);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                textwalkdn.setText("WALK-DN: ");
                walkdownstair.setText(String.format("%.0f Cal", currentPosition));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });

        mSeries4Index = mDecoView.addSeries(seriesItem);
    }

    private void createDataSeries5() {
        final SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#ffe4e1"))
                .setRange(0, mSeriesMax, 0)
                .setInitialVisibility(false)
                .build();
        final TextView textPercentage = (TextView) findViewById(R.id.textPercentage);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                float percentFilled = ((currentPosition + walktime*0.093f + standtime*0.014f + walkuptime*0.17f + walkdntime*0.17f ) / (seriesItem.getMaxValue() - seriesItem.getMinValue()));
                textPercentage.setText(String.format("%.0f%%", percentFilled * 100f));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });


        final TextView textToGo = (TextView) findViewById(R.id.textRemaining);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                textToGo.setText(String.format("%.1f Cal to goal", seriesItem.getMaxValue() - (currentPosition + walktime*0.093f + standtime*0.014f + walkuptime*0.17f + walkdntime*0.17f)));

            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });
        final TextView laying = (TextView) findViewById(R.id.laying);
        final TextView textlaying = (TextView) findViewById(R.id.textlaying);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                textlaying.setText("LAYING: ");
                laying.setText(String.format("%.0f Cal", currentPosition));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });

        mSeries5Index = mDecoView.addSeries(seriesItem);
    }
    private void createDataSeries6() {
        final SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#98dbc6"))
                .setRange(0, mSeriesMax, 0)
                .setInitialVisibility(false)
                .build();
        final TextView textPercentage = (TextView) findViewById(R.id.textPercentage);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                float percentFilled = ((currentPosition + walktime*0.093f + standtime*0.014f + walkuptime*0.17f + walkdntime*0.17f + laytime*0.0065f) / (seriesItem.getMaxValue() - seriesItem.getMinValue()));
                textPercentage.setText(String.format("%.0f%%", percentFilled * 100f));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });


        final TextView textToGo = (TextView) findViewById(R.id.textRemaining);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                textToGo.setText(String.format("%.1f Cal to goal", seriesItem.getMaxValue() - (currentPosition + walktime*0.093f + standtime*0.014f + walkuptime*0.17f + walkdntime*0.17f + laytime*0.0065f)));

            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });
        final TextView sitting = (TextView) findViewById(R.id.sitting);
        final TextView textsitting = (TextView) findViewById(R.id.textsitting);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                textsitting.setText("SITTING: ");
                sitting.setText(String.format("%.0f Cal", currentPosition));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });

        mSeries6Index = mDecoView.addSeries(seriesItem);
    }
    private void createEvents() {
        mDecoView.executeReset();

        mDecoView.addEvent(new DecoEvent.Builder(mSeriesMax)
                .setIndex(mBackIndex)
                .setDuration(3000)
                .setDelay(100)
                .build());

        mDecoView.addEvent(new DecoEvent.Builder(DecoDrawEffect.EffectType.EFFECT_SPIRAL_OUT)
                .setIndex(mSeries1Index)
                .setDuration(2000)
                .setDelay(1250)
                .build());

        mDecoView.addEvent(new DecoEvent.Builder(walktime * 0.093f)
                .setIndex(mSeries1Index)
                .setDelay(3250)
                .build());

        mDecoView.addEvent(new DecoEvent.Builder(DecoDrawEffect.EffectType.EFFECT_SPIRAL_OUT)
                .setIndex(mSeries2Index)
                .setDuration(1000)
                .setEffectRotations(1)
                .setDelay(7000)
                .build());

        mDecoView.addEvent(new DecoEvent.Builder(standtime * 0.014f)
                .setIndex(mSeries2Index)
                .setDelay(8500)
                .build());

        mDecoView.addEvent(new DecoEvent.Builder(DecoDrawEffect.EffectType.EFFECT_SPIRAL_OUT)
                .setIndex(mSeries3Index)
                .setDuration(1000)
                .setEffectRotations(1)
                .setDelay(12500)
                .build());

        mDecoView.addEvent(new DecoEvent.Builder(walkuptime * 0.17f)
                .setIndex(mSeries3Index)
                .setDelay(14000)
                .build());

        mDecoView.addEvent(new DecoEvent.Builder(DecoDrawEffect.EffectType.EFFECT_SPIRAL_OUT)
                .setIndex(mSeries4Index)
                .setDuration(1000)
                .setEffectRotations(1)
                .setDelay(18000)
                .build());

        mDecoView.addEvent(new DecoEvent.Builder(walkdntime * 0.17f)
                .setIndex(mSeries4Index)
                .setDelay(19500)
                .build());

        mDecoView.addEvent(new DecoEvent.Builder(DecoDrawEffect.EffectType.EFFECT_SPIRAL_OUT)
                .setIndex(mSeries5Index)
                .setDuration(1000)
                .setEffectRotations(1)
                .setDelay(23500)
                .build());

        mDecoView.addEvent(new DecoEvent.Builder(laytime * 0.0065f)
                .setIndex(mSeries5Index)
                .setDelay(25000)
                .build());

        mDecoView.addEvent(new DecoEvent.Builder(DecoDrawEffect.EffectType.EFFECT_SPIRAL_OUT)
                .setIndex(mSeries6Index)
                .setDuration(1000)
                .setEffectRotations(1)
                .setDelay(29000)
                .build());

        mDecoView.addEvent(new DecoEvent.Builder(sittime * 0.018f)
                .setIndex(mSeries6Index)
                .setDelay(30500)
                .build());

        mDecoView.addEvent(new DecoEvent.Builder(sittime * 0.018f).setIndex(mSeries6Index).setDelay(30500).build());

        mDecoView.addEvent(new DecoEvent.Builder(0).setIndex(mSeries6Index).setDelay(35000).build());

        mDecoView.addEvent(new DecoEvent.Builder(0).setIndex(mSeries5Index).setDelay(35000).build());

        mDecoView.addEvent(new DecoEvent.Builder(0).setIndex(mSeries4Index).setDelay(35000).build());

        mDecoView.addEvent(new DecoEvent.Builder(0).setIndex(mSeries3Index).setDelay(35000).build());

        mDecoView.addEvent(new DecoEvent.Builder(0).setIndex(mSeries2Index).setDelay(35000).build());

        mDecoView.addEvent(new DecoEvent.Builder(0)
                .setIndex(mSeries1Index)
                .setDelay(38000)
                .setDuration(1000)
                .setInterpolator(new AnticipateInterpolator())
                .setListener(new DecoEvent.ExecuteEventListener() {
                    @Override
                    public void onEventStart(DecoEvent decoEvent) {

                    }

                    @Override
                    public void onEventEnd(DecoEvent decoEvent) {
                        resetText();
                    }
                })
                .build());
        if (walktime * 0.093 + standtime * 0.014 + walkuptime * 0.17 + walkdntime * 0.17 + laytime * 0.0065 >= mSeriesMax) {

            mDecoView.addEvent(new DecoEvent.Builder(DecoDrawEffect.EffectType.EFFECT_SPIRAL_EXPLODE)
                    .setIndex(mSeries1Index)
                    .setDelay(39000)
                    .setDuration(3000)
                    .setDisplayText("GOAL!")
                    .setListener(new DecoEvent.ExecuteEventListener() {
                        @Override
                        public void onEventStart(DecoEvent decoEvent) {

                        }

                        @Override
                        public void onEventEnd(DecoEvent decoEvent) {
                            createEvents();
                        }
                    })
                    .build());

            resetText();
        }
        else {
            mDecoView.addEvent(new DecoEvent.Builder(DecoDrawEffect.EffectType.EFFECT_SPIRAL_EXPLODE)
                    .setIndex(mSeries1Index)
                    .setDelay(39000)
                    .setDuration(3000)
                    .setDisplayText("SO CLOSE!")
                    .setListener(new DecoEvent.ExecuteEventListener() {
                        @Override
                        public void onEventStart(DecoEvent decoEvent) {

                        }

                        @Override
                        public void onEventEnd(DecoEvent decoEvent) {
                            createEvents();
                        }
                    })
                    .build());

            resetText();
        }
    }
    private void resetText() {
        ((TextView) findViewById(R.id.walking)).setText("");
        ((TextView) findViewById(R.id.standing)).setText("");
        ((TextView) findViewById(R.id.walkdownstair)).setText("");
        ((TextView) findViewById(R.id.walkupstair)).setText("");
        ((TextView) findViewById(R.id.laying)).setText("");
        ((TextView) findViewById(R.id.sitting)).setText("");
        ((TextView) findViewById(R.id.textPercentage)).setText("");
        ((TextView) findViewById(R.id.textRemaining)).setText("");
    }
}
