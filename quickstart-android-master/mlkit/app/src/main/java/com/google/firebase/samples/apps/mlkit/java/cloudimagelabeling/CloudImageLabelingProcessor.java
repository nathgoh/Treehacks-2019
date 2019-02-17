// Copyright 2018 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.firebase.samples.apps.mlkit.java.cloudimagelabeling;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.label.FirebaseVisionCloudImageLabelerOptions;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.samples.apps.mlkit.common.CameraImageGraphic;
import com.google.firebase.samples.apps.mlkit.common.FrameMetadata;
import com.google.firebase.samples.apps.mlkit.common.GraphicOverlay;
import com.google.firebase.samples.apps.mlkit.java.VisionProcessorBase;
import com.google.firebase.samples.apps.mlkit.java.AzureTableData;
import com.google.firebase.samples.apps.mlkit.java.FoodInfo;
import com.google.firebase.samples.apps.mlkit.java.imagelabeling.LabelGraphic;

import java.util.ArrayList;
import java.util.List;

/**
 * Cloud Label Detector Demo.
 */
public class CloudImageLabelingProcessor
        extends VisionProcessorBase<List<FirebaseVisionImageLabel>> {
    private static final String TAG = "CloudImgLabelProc";

    private final FirebaseVisionImageLabeler detector;
    private AzureTableData data = new AzureTableData();

    public CloudImageLabelingProcessor() {
        FirebaseVisionCloudImageLabelerOptions.Builder optionsBuilder =
                new FirebaseVisionCloudImageLabelerOptions.Builder();

        detector = FirebaseVision.getInstance().getCloudImageLabeler(optionsBuilder.build());
    }

    @Override
    protected Task<List<FirebaseVisionImageLabel>> detectInImage(FirebaseVisionImage image) {
        return detector.processImage(image);
    }

    @Override
    protected void onSuccess(
            @Nullable final Bitmap originalCameraImage,
            @NonNull final List<FirebaseVisionImageLabel> labels,
            @NonNull FrameMetadata frameMetadata,
            @NonNull final GraphicOverlay graphicOverlay) {
        graphicOverlay.clear();

        Thread T = new Thread(){
            @Override
            public void run() {
                if (originalCameraImage != null) {
                    CameraImageGraphic imageGraphic = new CameraImageGraphic(graphicOverlay, originalCameraImage);
                    graphicOverlay.add(imageGraphic);
                }
                if (labels.isEmpty()) {
                    return;
                }

                while(labels.size() > 0 && labels.get(0).getText().equals("Food")
                        || labels.get(0).getText().equals("Fruit")){
                    labels.remove(0);
                }
                if(labels.size() != 0) {
                    String foodName = labels.get(0).getText();
                    FoodInfo info = data.getEntity(foodName.toLowerCase());
                    while(info == null && labels.size() > 1){
                        labels.remove(0);
                        foodName = labels.get(0).getText();
                        info = data.getEntity(foodName.toLowerCase());
                    }
                    if (info != null) {
                        System.out.println(Double.toString(info.getCalories()));
                        CloudLabelGraphic labelGraphic = new CloudLabelGraphic(graphicOverlay, info ,foodName);
                        graphicOverlay.add(labelGraphic);
                        graphicOverlay.postInvalidate();
                    }
                }
            }

        };
        T.start();
    }

    @Override
    protected void onFailure(@NonNull Exception e) {
        Log.w(TAG, "Label detection failed." + e);
    }
}
