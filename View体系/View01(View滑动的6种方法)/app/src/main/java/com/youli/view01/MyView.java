package com.youli.view01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * 作者: zhengbin on 2017/12/13.
 * <p>
 * 邮箱:2381447237@qq.com
 * <p>
 * github:2381447237
 */

public class MyView extends View{


    private Scroller mScroller;

    public MyView(Context context) {
        this(context,null);
    }


    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller=new Scroller(context);
    }


    private  int lastX,lastY;

//    方法1：layout()
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        //获取到手指处的横坐标和纵坐标
//
//        int x= (int) event.getX();
//        int y= (int) event.getY();
//
//        switch (event.getAction()){
//
//            case MotionEvent.ACTION_DOWN:
//
//                lastX=x;
//                lastY=y;
//
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//
//                //计算移动的距离
//                int offsetX=x-lastX;
//                int offsetY=y-lastY;
//
//                //调用layout方法来重新放置它的位置
//                layout(getLeft()+offsetX, getTop()+offsetY,
//                        getRight()+offsetX , getBottom()+offsetY);
//
//                break;
//
//        }
//
//        return true;
//    }


    //    方法2：offsetLeftAndRight()与offsetTopAndBottom()
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        int x= (int) event.getX();
//
//        int y= (int) event.getY();
//
//        switch (event.getAction()){
//
//            case MotionEvent.ACTION_DOWN:
//
//                lastX=x;
//                lastY=y;
//
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                //计算移动的距离
//                int offsetX=x-lastX;
//                int offsetY=y-lastY;
//                //对left和right进行偏移
//                offsetLeftAndRight(offsetX);
//                //对top和bottom进行偏移
//                offsetTopAndBottom(offsetY);
//                break;
//        }
//
//        return true;
//    }


//方法3：LayoutParams（改变布局参数）
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        int x= (int) event.getX();
//
//        int y= (int) event.getY();
//
//        switch (event.getAction()){
//
//
//            case MotionEvent.ACTION_DOWN:
//
//                lastX=x;
//                lastY=y;
//
//
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//
//                int offsetX=x-lastX;
//                int offsetY=y-lastY;
//
//
//                LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) getLayoutParams();
//
//                layoutParams.leftMargin=getLeft()+offsetX;
//                layoutParams.topMargin=getTop()+offsetY;
//                setLayoutParams(layoutParams);
//
//
//                break;
//        }
//
//        return true;
//    }


    //方法5：scollTo与scollBy
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        int x= (int) event.getX();
//
//        int y= (int) event.getY();
//
//        switch (event.getAction()){
//
//            case MotionEvent.ACTION_DOWN:
//
//                lastX=x;
//                lastY=y;
//
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//
//                int offsetX=x-lastX;
//                int offsetY=y-lastY;
//              //scollTo(x,y)表示移动到一个具体的坐标点，
//                // 而scollBy(dx,dy)则表示移动的增量为dx、dy。其中scollBy最终也是要调用scollTo的。
//                // scollTo、scollBy移动的是View的内容，如果在ViewGroup中使用则是移动他所有的子View。
//                ((View)getParent()).scrollBy(-offsetX,-offsetY);
//
//
//
//                break;
//
//        }
//
//        return true;
//    }


    //方法6
    //重写computeScroll()方法，系统会在绘制View的时候在draw()方法中调用该方法，
    // 这个方法中我们调用父类的scrollTo()方法并通过Scroller来不断获取当前的滚动值，
    // 每滑动一小段距离我们就调用invalidate()方法不断的进行重绘，
    // 重绘就会调用computeScroll()方法，
    // 这样我们就通过不断的移动一个小的距离并连贯起来就实现了平滑移动的效果
    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){

            ((View)getParent()).scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
                //通过不断的重绘不断的调用computeScroll方法
            invalidate();
        }
    }


    public void smoothScrollTo(int destX,int destY){

         int scrollX=getScrollX();
        int delta=destX-scrollX;
        //1000秒内滑向destX
        mScroller.startScroll(scrollX,0,delta,0,2000);

        invalidate();


    }

}
