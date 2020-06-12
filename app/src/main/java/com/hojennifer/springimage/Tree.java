package com.hojennifer.springimage;


import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.Random;

public class Tree {
    int left, right, top, bottom, treeOffset, botTree, direction;
    int color;

    Random rand = new Random();
    public Tree(int left, int top, int right, int bottom, int color){
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        this.color = color;
        treeOffset = 0;
        botTree = (bottom - top) / 2 + top;
        direction = 1;
    }
    public RectF getLeaves(){
        return new RectF(left + treeOffset, top, right + treeOffset, botTree);
    }
    public int getTrunkX() {
        return (left + right) / 2;
    }
    public Path getTrunkAndBranches(){
        int trunkHeight = (int) ((bottom  - top) * 0.85);
        int trunkTop = bottom - trunkHeight;
        int trunkMiddle = (left + right) / 2;
        int trunkWidth = (right - left) / 9;
        Path path = new Path();
        path.moveTo(trunkMiddle, trunkTop);
        path.lineTo(trunkMiddle - trunkWidth, bottom);
        path.lineTo(trunkMiddle + trunkWidth, bottom);
        path.lineTo(trunkMiddle, trunkTop);

        path.moveTo(trunkMiddle, trunkTop);
        path.lineTo(trunkMiddle - trunkWidth, bottom);
        path.lineTo(trunkMiddle + trunkWidth, bottom);
        path.lineTo(trunkMiddle, trunkTop);



        int topOfRBranch = (int)((botTree - top) * 0.23) + top; //top of right branch
        int halfBranchWidth = (right - left) / 11;
        //int branchOffset = (int)(treeOffset * ((float)(bottom-((botTree+topOfRBranch)/2)) / trunkHeight));

        path.moveTo(trunkMiddle, (botTree + topOfRBranch) / 2 - halfBranchWidth);
        path.lineTo(right - (right - left) / 4, topOfRBranch);
        path.lineTo(trunkMiddle, (botTree + topOfRBranch) / 2 + halfBranchWidth);
        path.lineTo(trunkMiddle, (botTree + topOfRBranch) / 2 - halfBranchWidth);


        int topOfLBranch = (int)((botTree - top) * 0.53) + top; //top of left branch

        //branchOffset = (int)(treeOffset * ((float)(bottom-((botTree+topOfLBranch)/2)) / trunkHeight));
        path.moveTo(trunkMiddle, (botTree - trunkTop) * 3 / 4 + trunkTop - halfBranchWidth);
        path.lineTo(left + (right - left) / 4, topOfLBranch);
        path.lineTo(trunkMiddle, (botTree - trunkTop) * 3 / 4 + trunkTop + halfBranchWidth);
        path.lineTo(trunkMiddle, (botTree - trunkTop) * 3 / 4 + trunkTop - halfBranchWidth);
        //path.lineTo(trunkMiddle + treeOffset, trunkTop);


        return path;
    }



    public void windBlow(){ //IN DEGREES

        if(treeOffset > 3 && direction == 1){
            direction = -1;
        }
        else if(treeOffset < -3 && direction == -1){
            direction = 1;
        }
        treeOffset += direction * rand.nextInt(2);
    }
    public int getColor(){
        return color;
    }
}
