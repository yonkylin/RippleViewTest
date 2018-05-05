package yonky.rippleviewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/5/5.
 */

public class RippleView extends View {
    /**
     * +------------------------+
     * |<--wave length->        |______
     * |   /\          |   /\   |  |
     * |  /  \         |  /  \  | amplitude
     * | /    \        | /    \ |  |
     * |/      \       |/      \|__|____
     * |        \      /        |  |
     * |         \    /         |  |
     * |          \  /          |  |
     * |           \/           | water level
     * +------------------------+__|____
     */

    private static final float DEFAULT_AMPLITUDE_RATIO = 0.05f;
    private static final float DEFAULT_WATER_LEVEL_RATIO = 0.5f;
    private static final float DEFAULT_WAVE_LENGTH_RATIO = 1.0f;
    private static final float DEFAULT_WAVE_SHIFT_RATIO = 0.0f;



    private float water_level_ritio=0.5f;
    private float amplitude_ritio=0.05f;
    private float wave_length_ritio=1f;


    private Paint viewPaint;
    private Matrix mShaderMatrix;

    private boolean showView =true;
    private BitmapShader shader;



    public RippleView(Context context) {
        super(context);
        init();
    }

    public RippleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RippleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        viewPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        shader = null;
        mShaderMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        createShader();
    }

    private void createShader(){
        Bitmap bitmap = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint shaderPaint;
        shaderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        shaderPaint.setStrokeWidth(2);

//        y = Asin(wx+t)+h
        int endX,endY;
        endX =getWidth();
        endY = getHeight();
        float[] waveY= new float[endX];
        for(int beginX = 0;beginX<endX;beginX++){
            int wx =(int) (beginX*2*Math.PI/getWidth());
            waveY[beginX] =DEFAULT_WATER_LEVEL_RATIO*getHeight()+DEFAULT_AMPLITUDE_RATIO*getHeight()*(float)Math.sin(wx);
            canvas.drawLine(beginX,waveY[beginX],beginX,endY,shaderPaint);
        }
        int wave2Shift =getWidth()/4;
        for(int beginX = 0;beginX<endX;beginX++){
            canvas.drawLine(beginX,waveY[(beginX+wave2Shift)%getWidth()],beginX,endY,shaderPaint);
        }

        shader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
        viewPaint.setShader(shader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(showView && shader!=null){
            if(viewPaint.getShader() ==null){
                viewPaint.setShader(shader);
            }
            mShaderMatrix.setScale(wave_length_ritio,amplitude_ritio,0,water_level_ritio*getHeight());
        }

    }

    public boolean isShowView() {
        return showView;
    }

    public void setShowView(boolean showView) {
        this.showView = showView;
        invalidate();
    }
}















