package com.generalmobile.face_ai_gm;

import java.io.IOException;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.hardware.Camera;


public class Camera_Preview extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Camera mCamera;

    public Camera_Preview(Context context, Camera camera) {
        super(context);
        mCamera = camera;
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        try {
            // create the surface and start camera preview
            if(mCamera == null) {
                mCamera.setPreviewDisplay(holder);
                mCamera.startPreview();
            }
        }catch (Exception e) {

            Log.d(VIEW_LOG_TAG,"Error setting camera preview:" + e.getMessage());

        }


    }

    public setCamera (Camera camera) {
        
        mCamera = camera;
    }
    public void Refresh_camera(SurfaceHolder holder, int format, int w, int h) {

        if(mHolder.getSurface() == null) {

            //preview surface does not exist
            return;
        }

        try {

            mCamera.stopPreview();   //to stop camera preview before making changes

        } catch (Exception e) {
            //ignore
        }

        setCamera(mCamera);

        try {
            mCamera.setPreviewDisplay(mHolder);

        }catch (Exception e) {

            Log.d(VIEW_LOG_TAG,"Error starting camera preview" + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        // TODO Auto-generated method stub
        // mCamera.release();

    }
}
