package com.generalmobile.face_ai_gm;

import android.app.Dialog;
import android.app.Fragment;
import android.content.ContentProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.face_recognition);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Load the image
                ImageView image = findViewById(R.id.image1);
                BitmapFactory.Options options= new BitmapFactory.Options();
                options.inMutable = true;
                Bitmap bitmap1 = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.test4,options);

                //Create a paint object for drawing with
                Paint myRectPaint = new Paint();
                myRectPaint.setStrokeWidth(25);
                myRectPaint.setColor(Color.RED);
                myRectPaint.setStyle(Paint.Style.STROKE);

                //Create a canvas object for drawing on
                Bitmap tempBitmap = Bitmap.createBitmap(bitmap1.getWidth(),bitmap1.getHeight(), Bitmap.Config.RGB_565);
                Canvas tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawBitmap(bitmap1,0,0,null);

                //Create the Face Detector
                FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext()).setTrackingEnabled(false).build();
                if(!faceDetector.isOperational()) {
                    new AlertDialog.Builder(v.getContext()).setMessage("Impossible to detect").show();

                }

                //Detect the faces
                Frame frame = new Frame.Builder().setBitmap(bitmap1).build();
                SparseArray<Face> faces = faceDetector.detect(frame);

                //Draw Rectangles on the faces
                for(int i=0; i<faces.size(); i++) {

                    Face thisFace = faces.valueAt(i);
                    float x1 = thisFace.getPosition().x;
                    float y1 = thisFace.getPosition().y;
                    float x2 = thisFace.getWidth();
                    float y2 = thisFace.getHeight();
                    tempCanvas.drawRoundRect(new RectF(x1,y1,x2,y2), 2, 2,myRectPaint);
                }
                image.setImageDrawable(new BitmapDrawable(getResources(),tempBitmap));



            }
        });





    }
}
