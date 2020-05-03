package com.hojennifer.springimage;


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
    public Path getTrunkAndBranches(){
        int trunkHeight = (int) ((bottom  - top) * 0.85);
        int trunkTop = bottom - trunkHeight;
        int trunkMiddle = (left + right) / 2;
        int trunkWidth = (right - left) / 9;
        Path path = new Path();
        path.moveTo(trunkMiddle + treeOffset, trunkTop);
        path.lineTo(trunkMiddle - trunkWidth, bottom);
        path.lineTo(trunkMiddle + trunkWidth, bottom);
        path.lineTo(trunkMiddle + treeOffset, trunkTop);

        path.moveTo(trunkMiddle + treeOffset, trunkTop);
        path.lineTo(trunkMiddle - trunkWidth, bottom);
        path.lineTo(trunkMiddle + trunkWidth, bottom);
        path.lineTo(trunkMiddle + treeOffset, trunkTop);



        int topOfRBranch = (int)((botTree - top) * 0.23) + top; //top of right branch
        int halfBranchWidth = (right - left) / 11;
        int branchOffset = (int)(treeOffset * ((float)(bottom-((botTree+topOfRBranch)/2)) / trunkHeight));

        path.moveTo(trunkMiddle + branchOffset, (botTree + topOfRBranch) / 2 - halfBranchWidth);
        path.lineTo(right - (right - left) / 4  + branchOffset, topOfRBranch);
        path.lineTo(trunkMiddle + branchOffset, (botTree + topOfRBranch) / 2 + halfBranchWidth);
        path.lineTo(trunkMiddle + branchOffset, (botTree + topOfRBranch) / 2 - halfBranchWidth);


        int topOfLBranch = (int)((botTree - top) * 0.53) + top; //top of left branch

        branchOffset = (int)(treeOffset * ((float)(bottom-((botTree+topOfLBranch)/2)) / trunkHeight));
        path.moveTo(trunkMiddle + branchOffset, (botTree - trunkTop) * 3 / 4 + trunkTop - halfBranchWidth);
        path.lineTo(left + (right - left) / 4 + branchOffset, topOfLBranch);
        path.lineTo(trunkMiddle + branchOffset, (botTree - trunkTop) * 3 / 4 + trunkTop + halfBranchWidth);
        path.lineTo(trunkMiddle + branchOffset, (botTree - trunkTop) * 3 / 4 + trunkTop - halfBranchWidth);
        //path.lineTo(trunkMiddle + treeOffset, trunkTop);


        return path;
    }



    public void windBlow(){
        if(treeOffset > 25 && direction == 1){
            direction = -1;
        }
        else if(treeOffset < -25 && direction == -1){
            direction = 1;
        }
        treeOffset += direction * rand.nextInt(7);
    }
    public int getColor(){
        return color;
    }
}
