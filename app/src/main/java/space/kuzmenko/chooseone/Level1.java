package space.kuzmenko.chooseone;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level1 extends AppCompatActivity {
    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft; //переменная для левой картинки + текст
    public int numRight;//переменная для правой картинки + текст
    Array array = new Array();
    Random random = new Random();
    public int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //создаю переменную text_levels
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level1);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) final ImageView img_left = (ImageView) findViewById(R.id.img_leftt) ;
        //скругление углов для левой картинки
        img_left.setClipToOutline(true);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) final ImageView img_right = (ImageView) findViewById(R.id.img_right) ;
        //скругление углов для правой картинки
        img_right.setClipToOutline(true);

        //путь к левой textview
        final TextView text_left = findViewById(R.id.text_left);
        //путь к правой textview
        final TextView text_right = findViewById(R.id.text_right);

        //развернуть игру на весь экран
        Window w = getWindow();
        ((Window) w).setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //вызов диалогового окна в начале игры
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //стираю заголовок
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон диалог окна
        dialog.setCancelable(false); //окно нельзя закрыть кнопкой назад

        // Кнопка которая закрывает диалог окно
        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
                dialog.dismiss();
            }
        });
        //кнопка продолжить
        Button btncontinue = (Button)dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

        //________________________________________________________

        //вызов диалогового окна в конце игры
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE); //стираю заголовок
        dialogEnd.setContentView(R.layout.dialogend);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон диалог окна
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false); //окно нельзя закрыть кнопкой назад

        // Кнопка которая закрывает диалог окно
        TextView btnclose2 = (TextView)dialogEnd.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
                dialogEnd.dismiss();
            }
        });
        //кнопка продолжить
        Button btncontinue2 = (Button)dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level1.this, Level3.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
                dialogEnd.dismiss();
            }
        });
        //______________________________________________________

        //кнопка назад начало
        Button btn_back = (Button) findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch(Exception e){

                }
            }
        });
        // массив для прогресса игры
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20,
        };
        //анимация
        final Animation a = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha);


        numLeft = random.nextInt(9); // случайное число от 1 до 9
        img_left.setImageResource(array.images1[numLeft]);
        text_left.setText(array.texts1[numLeft]);

        numRight = random.nextInt(9);

        //цикл проверяющий равенство чисел
        while (numLeft==numRight){
            numRight = random.nextInt(9);
        }
        img_right.setImageResource(array.images1[numRight]);
        text_right.setText(array.texts1[numRight]);


        //обрабатываю нажатие на левую картинку
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //условие касания картинки
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    //если коснулся
                    img_right.setEnabled(false);
                    if (numLeft>numRight){
                        img_left.setImageResource(R.drawable.img_true);
                    }else{
                        img_left.setImageResource(R.drawable.img_false);
                    }
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    //если отпустил
                    if(numLeft>numRight){
                        if(count<20){
                            count=count+1;
                        }

                        //закрашиваю прогресс серым цветом
                        for (int i=0; i<20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //определяем ответы и закрашиваем зеленым
                        for(int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else{
                        //сли левая картинка меньше
                        if(count>0){
                            if(count==1){
                                count=0;
                            }else{
                                count=count-2;
                            }
                        }
                        for (int i=0; i<19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //определяем ответы и закрашиваем зеленым
                        for(int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if(count==20){
                        //выход из уровня
                        dialogEnd.show();
                    }else{
                        numLeft = random.nextInt(9); // случайное число от 1 до 9
                        img_left.setImageResource(array.images1[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts1[numLeft]);

                        numRight = random.nextInt(9);

                        //цикл проверяющий равенство чисел
                        while (numLeft==numRight){
                            numRight = random.nextInt(9);
                        }
                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numRight]);

                        img_right.setEnabled(true);
                    }
                }
                return true;
            }
        });
        //обрабатываю нажатие на правую картинку
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //условие касания картинки
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    //если коснулся
                    img_left.setEnabled(false);
                    if (numLeft<numRight){
                        img_right.setImageResource(R.drawable.img_true);
                    }else{
                        img_right.setImageResource(R.drawable.img_false);
                    }
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    //если отпустил
                    if(numLeft<numRight){
                        if(count<20){
                            count=count+1;
                        }

                        //закрашиваю прогресс серым цветом
                        for (int i=0; i<20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //определяем ответы и закрашиваем зеленым
                        for(int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else{
                        //сли левая картинка меньше
                        if(count>0){
                            if(count==1){
                                count=0;
                            }else{
                                count=count-2;
                            }
                        }
                        for (int i=0; i<19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //определяем ответы и закрашиваем зеленым
                        for(int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if(count==20){
                        //выход из уровня
                        dialogEnd.show();
                    }else{
                        numLeft = random.nextInt(9); // случайное число от 1 до 9
                        img_left.setImageResource(array.images1[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts1[numLeft]);

                        numRight = random.nextInt(9);

                        //цикл проверяющий равенство чисел
                        while (numLeft==numRight){
                            numRight = random.nextInt(9);
                        }
                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numRight]);

                        img_left.setEnabled(true);
                    }
                }
                return true;
            }
        });
    }
}