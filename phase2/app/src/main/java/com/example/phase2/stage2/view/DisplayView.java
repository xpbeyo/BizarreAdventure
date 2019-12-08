package com.example.phase2.stage2.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;

public interface DisplayView {
    void draw();

    double getCurX();

    double getCurY();

    void setCurX(double curX);

    void setCurY(double curY);

    Handler getHandler();

    Resources getResources();

    Context getContext();
}
