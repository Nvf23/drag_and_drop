package com.example.activity_6;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView cuadrado = findViewById(R.id.cuadrado);
        FrameLayout rootLayout = findViewById(android.R.id.content);

        cuadrado.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDragAndDrop(data, shadowBuilder, v, 0);
                    return true;
                } else {
                    return false;
                }
            }
        });

        rootLayout.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        return true;

                    case DragEvent.ACTION_DRAG_LOCATION:
                        return true;

                    case DragEvent.ACTION_DROP:
                        View draggedView = (View) event.getLocalState();
                        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                                draggedView.getWidth(), draggedView.getHeight());
                        params.leftMargin = (int) event.getX() - draggedView.getWidth() / 2;
                        params.topMargin = (int) event.getY() - draggedView.getHeight() / 2;
                        draggedView.setLayoutParams(params);
                        return true;

                    case DragEvent.ACTION_DRAG_ENDED:
                        return true;

                    default:
                        return false;
                }
            }
        });
    }
}