package com.afollestad.materialcamera.internal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

/**
 * Created by baiyunlong on 16/9/9.
 */
public class CarBackView extends View {

  public static final float SENSORHEIGHT = 1.5f;
  public static final float GREENLENGTH = 1.5f;
  public static final float YELLOWLENGTH = 1.0f;
  public static final float REDLENGTH = 0.5f;

  public static final int LINEWIDTH = 10;

  public static int CARWIDTH;
  public static int WIDTHBETWEEN = 20;
  public static final float CARREALWIDTH = 2.0f;




  private Paint paintStroke;//实线画笔
  private Paint paintHollow;//虚线画笔

  Context context;

  private int width,height;

  int[] point1,point2,point3,point4,point5,point6,point7,point8;



  public CarBackView(Context context) {
    this(context ,null);
  }

  public CarBackView(Context context, AttributeSet attrs) {
    this(context, attrs,0);
  }

  public CarBackView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    width = getMetrics(context).widthPixels;
    height = getMetrics(context).heightPixels;
    CARWIDTH = width/3;
    initPoints();
    initPaint();
    Log.e("TAG","diaoyong!");
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawStrokeLine(canvas);
    drawHollowLine(canvas);
  }

  private void initPaint(){
    paintStroke = new Paint();
    paintHollow = new Paint();

    paintStroke.setStrokeWidth(dp2px(LINEWIDTH));
    paintStroke.setStyle(Paint.Style.STROKE);
    paintStroke.setAntiAlias(true);

    paintHollow.setStrokeWidth(dp2px(LINEWIDTH)/2);
    paintHollow.setStyle(Paint.Style.STROKE);
    paintHollow.setAntiAlias(true);
    PathEffect effect = new DashPathEffect(new float[]{dp2px(LINEWIDTH),dp2px(LINEWIDTH/2),dp2px(LINEWIDTH),dp2px(LINEWIDTH/2)},1);
    paintHollow.setPathEffect(effect);
  }

  private void initPoints(){
    point1 = new int[]{width/2-CARWIDTH/2-dp2px(WIDTHBETWEEN)*2,height*2/3-dp2px(50)};
    point2 = new int[]{width/2+CARWIDTH/2+dp2px(WIDTHBETWEEN)*2,height*2/3-dp2px(50)};
    point3 = new int[]{width/2-CARWIDTH/2-dp2px(WIDTHBETWEEN),height*2/3-dp2px(150)};
    point4 = new int[]{width/2+CARWIDTH/2+dp2px(WIDTHBETWEEN),height*2/3-dp2px(150)};
    point5 = new int[]{width/2-CARWIDTH/2,height*2/3-dp2px(250)};
    point6 = new int[]{width/2+CARWIDTH/2,height*2/3-dp2px(250)};
    point7 = new int[]{width/2-CARWIDTH/2+dp2px(WIDTHBETWEEN),height*2/3-dp2px(350)};
    point8 = new int[]{width/2+CARWIDTH/2-dp2px(WIDTHBETWEEN),height*2/3-dp2px(350)};

  }

  private void drawStrokeLine(Canvas canvas){
    canvas.save();
    canvas.drawPath(getRedStrokePath(),paintStroke);
    canvas.drawPath(getYellowStrokePath(),paintStroke);
    canvas.drawPath(getGreenStrokePath(),paintStroke);
    canvas.restore();
  }

  private void drawHollowLine(Canvas canvas){
    canvas.save();
    canvas.drawPath(getRedHollowPath(),paintHollow);
    canvas.drawPath(getYellowHollowPath(),paintHollow);
    canvas.restore();

  }

  /**
   * 将 dp 转换为 px
   * @param dp
   * @return
   */
  private int dp2px(int dp) {
    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
  }

  private int sp2px(int sp){
    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
  }

  private DisplayMetrics getMetrics(Context context){
    DisplayMetrics metrics = new DisplayMetrics();
    ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
    return metrics;
  }

  private int getScreenLineHeight(float height,float length){
    return (int)(CARWIDTH*getBackLength(height,length)/CARREALWIDTH);
  }

  private double getBackLength(float height,float length){
    return Math.sqrt(height*height+length*length);
  }

  private Path getRedStrokePath(){
    paintStroke.setColor(Color.RED);
    Path path = new Path();
    path.moveTo(point1[0],point1[1]);
    path.lineTo(point3[0],point3[1]);
    path.moveTo(point2[0],point2[1]);
    path.lineTo(point4[0],point4[1]);
    return path;
  }

  private Path getRedHollowPath(){
    paintHollow.setColor(Color.RED);
    Path path = new Path();
    path.moveTo(point3[0]+dp2px(LINEWIDTH),point3[1]);
    path.lineTo(point4[0]-dp2px(LINEWIDTH),point4[1]);
    return path;
  }

  private Path getYellowStrokePath(){
    paintStroke.setColor(Color.YELLOW);
    Path path = new Path();
    path.moveTo(point3[0],point3[1]);
    path.lineTo(point5[0],point5[1]);
    path.moveTo(point4[0],point4[1]);
    path.lineTo(point6[0],point6[1]);
    return path;
  }

  private Path getYellowHollowPath(){
    paintHollow.setColor(Color.YELLOW);
    Path path = new Path();
    path.moveTo(point5[0]+dp2px(LINEWIDTH),point5[1]);
    path.lineTo(point6[0]-dp2px(LINEWIDTH),point6[1]);
    return path;
  }

  private Path getGreenStrokePath(){
    paintStroke.setColor(Color.GREEN);
    Path path = new Path();
    path.moveTo(point5[0],point5[1]);
    path.lineTo(point7[0],point7[1]);
    path.moveTo(point6[0],point6[1]);
    path.lineTo(point8[0],point8[1]);
    return path;
  }


}
