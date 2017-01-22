package tw.org.iii.brad11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar)findViewById(R.id.seekbar);
    }

    public void test1(View v){
        Intent it = new Intent(this, MyService.class);
        it.putExtra("isPause", false);
        startService(it);
    }
    public void test2(View v){
        Intent it = new Intent(this, MyService.class);
        stopService(it);
    }
    public void test3(View v){
        Intent it = new Intent(this, MyService.class);
        it.putExtra("isPause", true);
        startService(it);
    }
}
