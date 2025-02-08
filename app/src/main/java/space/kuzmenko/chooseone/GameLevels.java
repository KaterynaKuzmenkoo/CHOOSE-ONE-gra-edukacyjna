package space.kuzmenko.chooseone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameLevels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelevels);

        Button button_back = (Button) findViewById(R.id.button_back);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, MainActivity.class);
                    startActivity(intent);finish();
                } catch (Exception e) {

                }
            }
        });
        //кнопка для перехода на первый уровень
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(GameLevels.this, Level1.class);
                    startActivity(intent);finish();
                }catch(Exception e){

                }
            }
        });
        //кнопка для перехода на 2 уровень
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                        Intent intent = new Intent(GameLevels.this, Level3.class);
                        startActivity(intent);finish();
                }catch(Exception e){

                }
            }
        });
        //кнопка для перехода на 3 уровень
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                        Intent intent = new Intent(GameLevels.this, Level2.class);
                        startActivity(intent);finish();

                }catch(Exception e){

                }
            }
        });



        final int[] x = {
         R.id.textView1,
         R.id.textView2,
         R.id.textView3,
        };


        Window w = getWindow();
        ((Window) w).setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
}