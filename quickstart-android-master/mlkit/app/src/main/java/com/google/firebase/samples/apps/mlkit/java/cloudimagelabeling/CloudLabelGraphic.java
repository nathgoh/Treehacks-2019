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

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.samples.apps.mlkit.common.GraphicOverlay;
import com.google.firebase.samples.apps.mlkit.common.GraphicOverlay.Graphic;
import com.google.firebase.samples.apps.mlkit.java.AzureTableData;
import com.google.firebase.samples.apps.mlkit.java.FoodInfo;

import java.util.ArrayList;
import java.util.List;

/** Graphic instance for rendering detected label. */
public class CloudLabelGraphic extends Graphic {
  private final Paint textPaint;
  private final GraphicOverlay overlay;
  private final FoodInfo label;
  private String foodName;

  private AzureTableData data = new AzureTableData();
  private List<String> labels;

  public CloudLabelGraphic(GraphicOverlay overlay, FoodInfo label, String foodName) {
      super(overlay);
      this.overlay = overlay;
      this.label = label;
      this.foodName = foodName;
      textPaint = new Paint();
      textPaint.setColor(Color.WHITE);
      textPaint.setTextSize(60.0f);
    }

    @Override
    public synchronized void draw(Canvas canvas) {
      float x = overlay.getWidth() / 4.0f;
      float y = overlay.getHeight() / 2.0f;
      canvas.drawText(foodName, x, y - 60f, textPaint);
      canvas.drawText("Calories: " + String.valueOf(label.getCalories()), x, y, textPaint);

    }
}
