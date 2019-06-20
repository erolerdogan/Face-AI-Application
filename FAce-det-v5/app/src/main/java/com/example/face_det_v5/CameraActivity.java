package com.example.face_det_v5;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.hardware.Camera;
import com.example.face_det_v5.Camera.CameraPreview;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

import static com.example.face_det_v5.MainActivity.getCameraInstance;

public class CameraActivity extends Activity {
    private Camera mCamera;
    private CameraPreview mPreview;
    private CameraSource mCameraSource;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_layout);

        // Create an instance of Camera
        mCamera = getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
      //  GetDetectorFace(getApplicationContext());
        //mPreview = new CameraPreview(mCameraSource);
        //mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
    }
/*    private void GetDetectorFace(Context context){
        FaceDetector detector = new FaceDetector.Builder(context)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .setMode(FaceDetector.FAST_MODE)
                .build();
        detector.setProcessor(
               // new MultiProcessor.Builder<Face>(new GraphicFaceTrackerFactory()).build());
        mCameraSource = new CameraSource.Builder(context,detector)
                .setRequestedPreviewSize(640, 480)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedFps(30.0f)
                .build();

    }*/

}
