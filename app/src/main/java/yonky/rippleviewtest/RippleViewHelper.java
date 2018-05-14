package yonky.rippleviewtest;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

public class RippleViewHelper {
    private RippleView mRippleView;
    private AnimatorSet animatorSet;

    public RippleViewHelper(RippleView mRippleView) {
        this.mRippleView = mRippleView;
        initAnimation();
    }

    private void initAnimation(){
        List<Animator> animators = new ArrayList<>();
        ObjectAnimator shiftAnimator = ObjectAnimator.ofFloat(mRippleView,"wave_shift_ritio",0f,1f);
        shiftAnimator.setRepeatCount(ValueAnimator.INFINITE);
        shiftAnimator.setDuration(1000);
        shiftAnimator.setInterpolator(new LinearInterpolator());
        animators.add(shiftAnimator);

        ObjectAnimator amplitudeAnimtor=ObjectAnimator.ofFloat(mRippleView,"amplitude_ritio",0.01f,0.2f);
        amplitudeAnimtor.setInterpolator(new LinearInterpolator());
        amplitudeAnimtor.setDuration(5000);
        amplitudeAnimtor.setRepeatCount(ValueAnimator.INFINITE);
        amplitudeAnimtor.setRepeatMode(ValueAnimator.REVERSE);
        animators.add(amplitudeAnimtor);

        animatorSet = new AnimatorSet();
        animatorSet.playTogether(animators);
    }

    public void start(){
        if(animatorSet !=null){
            animatorSet.start();
        }
    }

    public void cancel(){
        if(animatorSet !=null){
            animatorSet.cancel();
            animatorSet.end();
        }
    }
}
