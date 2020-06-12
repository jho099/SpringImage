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

import java.util.ArrayList;
import java.util.Random;

public class SpringView extends View {

    Paint paint;
    int width, height;
    int centerY = 200;
    int step = 20;
    int numTrees = 5;

    int[] greens = {0xFF228C22, 0xFF67A239, 0xFF10921B, 0xFF97E378, 0xFF1E6B33};

    int ground;
    ArrayList<Tree> trees = new ArrayList<Tree>();


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
        int treeCount = numTrees;
        if(width > height){
            treeCount *= 2;

        }


        int w = width / treeCount;
        trees.clear();
        for(int i = 0; i < treeCount; i++){
            trees.add(new Tree(i*w, (int)(height*0.3), i*w+w, ground, greens[i % greens.length]));
        }



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
        canvas.drawRect(0, (int)(ground * 0.85), width, height, paint);


        for(Tree tree : trees){
            tree.windBlow();
            canvas.save();
            canvas.rotate(tree.treeOffset, tree.getTrunkX(), tree.bottom);


            //LEAVES
            paint.setColor(tree.getColor());
            canvas.drawOval(tree.getLeaves(), paint); //tree leaves

            //BRANCH + TRUNK
            paint.setColor(0xFF6A4110);
            canvas.drawPath(tree.getTrunkAndBranches(), paint);
            canvas.restore();
        }



        postInvalidateDelayed(50);





    }
}
