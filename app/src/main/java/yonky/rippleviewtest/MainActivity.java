package yonky.rippleviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RippleView rippleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rippleView = findViewById(R.id.ripple);
        RippleViewHelper mHelper = new RippleViewHelper(rippleView);
        mHelper.start();
    }
}
