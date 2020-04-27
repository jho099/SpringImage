package com.hojennifer.springimage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.graphics.Path;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.Random;

public class SpringView extends View {

    Paint paint;
    int width, height;
    int centerY = 200;
    int step = 20;

    int ground;
    Tree tree1;
    Tree tree2;


    public SpringView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        //getHolder().addCallback(this);
        setFocusable(true);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = right - left;
        height = bottom - top;
        ground = (int) (height * 0.8);
        tree1 = new Tree(200, 300, 300, ground, 0xFF228C22);
        tree2 = new Tree(400, 100, 600, ground, 0xFF67A239);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //paint.setColor(Color.YELLOW);
        //canvas.drawCircle(300, centerY, 100, paint);


        paint.setColor(Color.WHITE);
        canvas.drawCircle(500, centerY, 90, paint);
        paint.setColor(Color.RED);
        canvas.drawCircle(500, centerY, 80, paint);


        //centerY += step;
        paint.setColor(0xFF6A4110);//brown
        canvas.drawRect(0, ground, width, height, paint);
        paint.setColor(0xFF7BB369);//green
        canvas.drawRect(0, ground + 40 , width, height, paint);



        tree1.windBlow();

        //LEAVES
        paint.setColor(tree1.getColor());
        canvas.drawOval(tree1.getLeaves(), paint); //tree leaves

        //BRANCH + TRUNK
        paint.setColor(0xFF6A4110);
        canvas.drawPath(tree1.getTrunkAndBranches(), paint);
//-----------------------------------------------//
        tree2.windBlow();

        //LEAVES
        paint.setColor(tree2.getColor());
        canvas.drawOval(tree2.getLeaves(), paint); //tree leaves

        //BRANCH + TRUNK
        paint.setColor(0xFF6A4110);
        canvas.drawPath(tree2.getTrunkAndBranches(), paint);


        postInvalidateDelayed(50);





    }
}
