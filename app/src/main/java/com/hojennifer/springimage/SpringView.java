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

    int left = 400;
    int right = 500;
    int top = 300;
    int bottom;


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

        bottom = (int) (height * 0.8);
        //centerY += step;
        paint.setColor(0xFF6A4110);//brown
        canvas.drawRect(0, bottom, width, height, paint);
        paint.setColor(0xFF7BB369);//green
        canvas.drawRect(0, (int)(height * 0.85) , width, height, paint);



        if(treeOffset > 25 && direction == 1){
            direction = -1;
        }
        else if(treeOffset < -25 && direction == -1){
            direction = 1;
        }
        treeOffset += direction * rand.nextInt(7);

        int botTree = (bottom - top) / 2 + top;
        paint.setColor(0xFF228C22);
        canvas.drawOval(left + treeOffset, top, right + treeOffset, botTree, paint); //tree leaves
        //leavesLeft += treeOffset;
        //leavesRight += treeOffset;

        int groundY = 270;
        int trunkHeight = (int) ((bottom  - top) * 0.85);
        int trunkTop = bottom - trunkHeight;
        int trunkMiddle = (left + right) / 2;
        int trunkWidth = (right - left) / 9;
        paint.setColor(0xFF6A4110);
        Path path = new Path(); //trunk
        path.moveTo(trunkMiddle + treeOffset, trunkTop);
      //  path.lineTo()
        path.lineTo(trunkMiddle - trunkWidth, bottom);
        path.lineTo(trunkMiddle + trunkWidth, bottom);
        path.lineTo(trunkMiddle + treeOffset, trunkTop);
        path.close();
        canvas.drawPath(path, paint);
        //centerTree += treeOffset;

        //int shift =  treeOffset / (height - 270 - 200) ;

        int topOfRBranch = (int)((botTree - top) * 0.23) + top; //top of right branch
        int halfBranchWidth = (right - left) / 11;
        int branchOffset = (int)(treeOffset * ((float)(bottom-((botTree+topOfRBranch)/2)) / trunkHeight));


        path = new Path();//branch
        path.moveTo(right - (right - left) / 4  + branchOffset, topOfRBranch);
        path.lineTo(trunkMiddle + branchOffset, (botTree + topOfRBranch) / 2 - halfBranchWidth);
        path.lineTo(trunkMiddle + branchOffset, (botTree + topOfRBranch) / 2 + halfBranchWidth);
        path.close();
        canvas.drawPath(path, paint);

        int topOfLBranch = (int)((botTree - top) * 0.53) + top; //top of left branch

        branchOffset = (int)(treeOffset * ((float)(bottom-((botTree+topOfLBranch)/2)) / trunkHeight));
        path = new Path();//branch
        path.moveTo(trunkMiddle + branchOffset, (botTree - trunkTop) * 3 / 4 + trunkTop - halfBranchWidth);
        path.lineTo(left + (right - left) / 4 + branchOffset, topOfLBranch);
        path.lineTo(trunkMiddle + branchOffset, (botTree - trunkTop) * 3 / 4 + trunkTop + halfBranchWidth);
        path.close();
        canvas.drawPath(path, paint);


        postInvalidateDelayed(50);





    }
}
