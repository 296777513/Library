package com.knight.common;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.knight.basetools.bubble.BubblePopupWindow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtn1, mBtn2;
    private BubblePopupWindow bubblePopupWindow;
    private int mClickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn1 = findViewById(R.id.button1);
        mBtn2 = findViewById(R.id.button2);
        bubblePopupWindow = new BubblePopupWindow(this);
        bubblePopupWindow.setBubbleText("这是一条气泡消息");
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                if (bubblePopupWindow.isShowing()) {
                    bubblePopupWindow.dismiss();
                } else {
                    mClickCount = (mClickCount + 1) % 4;
                    mBtn1.post(new Runnable() {
                        @Override
                        public void run() {
                            switch (mClickCount) {
                                case 0:
                                    bubblePopupWindow.show(mBtn1);
                                    break;
                                case 1:
                                    bubblePopupWindow.show(mBtn1, Gravity.TOP);
                                    break;
                                case 2:
                                    bubblePopupWindow.show(mBtn1, Gravity.RIGHT);
                                    break;
                                case 3:
                                    bubblePopupWindow.show(mBtn1, Gravity.LEFT);
                                    break;
                            }

                        }
                    });
                }

                break;
            case R.id.button2:
                if (bubblePopupWindow.isShowing()) {
                    bubblePopupWindow.dismiss();
                } else {
                    mClickCount = (mClickCount + 1) % 4;
                    mBtn2.post(new Runnable() {
                        @Override
                        public void run() {
                            switch (mClickCount) {
                                case 0:
                                    bubblePopupWindow.setXOffset(0);
                                    bubblePopupWindow.show(mBtn2, Gravity.BOTTOM, false, 0);
                                    break;
                                case 1:
                                    bubblePopupWindow.setXOffset(100);
                                    bubblePopupWindow.show(mBtn2, Gravity.TOP, false, 0);

                                    break;
                                case 2:
                                    bubblePopupWindow.setYOffset(100);
                                    bubblePopupWindow.show(mBtn2, Gravity.RIGHT, false, 0);

                                    break;
                                case 3:
                                    bubblePopupWindow.setYOffset(100);
                                    bubblePopupWindow.show(mBtn2, Gravity.LEFT, false, 0);
                                    break;
                            }

                        }
                    });
                }
                break;
        }
    }
}
