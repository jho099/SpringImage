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
    int treeOffset = 0;
    int direction = 1;
    Random rand = new Random();

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
        paint.setColor(0xFF6A4110);
        canvas.drawRect(0, height - 270, width, height - 200, paint);
        paint.setColor(0xFF7BB369);
        canvas.drawRect(0, height - 200, width, height, paint);

        if(treeOffset > 30 && direction == 1){
            direction = -1;
        }
        else if(treeOffset < -30 && direction == -1){
            direction = 1;
        }
        treeOffset += direction * rand.nextInt(7);

        paint.setColor(0xFF228C22);
        canvas.drawOval(100 + treeOffset, 100, 300 + treeOffset, 500, paint); //tree leaves
        //leavesLeft += treeOffset;
        //leavesRight += treeOffset;

        int groundY = 270;
        int trunkHeight = height - groundY - 250;

        paint.setColor(0xFF6A4110);
        Path path = new Path(); //trunk
        path.moveTo(200 + treeOffset, 200);
      //  path.lineTo()
        path.lineTo(180, height - 270);
        path.lineTo(220, height - 270);
        path.lineTo(200 + treeOffset, 200);
        path.close();
        canvas.drawPath(path, paint);
        //centerTree += treeOffset;

        //int shift =  treeOffset / (height - 270 - 200) ;


        int branchOffset = (int)(treeOffset * ((float)(height-groundY-350) / trunkHeight));

        path = new Path();//branch
        path.moveTo(250 + branchOffset, 205);
        path.lineTo(200 + branchOffset, 340);
        path.lineTo(200 + branchOffset, 360);
        path.close();
        canvas.drawPath(path, paint);


        branchOffset = (int)(treeOffset * ((float)(height-groundY-420) / trunkHeight));
        path = new Path();//branch
        path.moveTo(200 + branchOffset, 410);
        path.lineTo(150 + branchOffset, 285);
        path.lineTo(200 + branchOffset, 430);
        path.close();
        canvas.drawPath(path, paint);


        postInvalidateDelayed(50);





    }
}
