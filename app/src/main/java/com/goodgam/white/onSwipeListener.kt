package com.goodgam.white

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

open class onSwipeListener (context: Context?): View.OnTouchListener {

    var getstureDelector: GestureDetector

    override fun onTouch(p0: View?, motionEvent: MotionEvent?): Boolean {
        return getstureDelector.onTouchEvent(motionEvent)

    }
    inner class GestureListener: GestureDetector.SimpleOnGestureListener(){

        val SWIPE_THRESOLD=100
        val SWIPE_VELOCITY_THRESOLD=100
        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            var result=false
            val yDiff=e2.y-e1.y
            val xDiff=e2.x-e1.x
            if(Math.abs(xDiff)>Math.abs(yDiff)){
                if(Math.abs(xDiff)>SWIPE_THRESOLD
                    &&Math.abs(velocityX)>SWIPE_VELOCITY_THRESOLD){
                    if(xDiff>0){
                        onSwipeRight()
                    }
                    else{
                        onSwipeLift()
                    }
                    result=true
                }
            }
            else if(Math.abs(yDiff)>SWIPE_THRESOLD
                && Math.abs(velocityY)>SWIPE_VELOCITY_THRESOLD){
                if(yDiff>0){
                    onSwipeBottom()
                }
                else{
                    onSwipeTop()
                }
                result=true

            }
            return result
        }

    }

    open fun onSwipeBottom() {}

    open fun onSwipeTop() {}

    open fun onSwipeLift() {}

    open fun onSwipeRight() {}

    init {
        getstureDelector= GestureDetector(context,GestureListener())
    }
}