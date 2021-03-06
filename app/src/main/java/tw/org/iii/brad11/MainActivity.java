package tw.org.iii.brad11;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar)findViewById(R.id.seekbar);
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter("brad");
        registerReceiver(receiver,filter);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    Intent it = new Intent(MainActivity.this, MyService.class);
                    it.putExtra("newpos", progress);
                    startService(it);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    @Override
    public void finish() {
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
        super.finish();
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

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int len = intent.getIntExtra("len",-1);
            int now = intent.getIntExtra("now", -1);
            if (len>0){
                seekBar.setMax(len);
            }
            if (now>0){
                seekBar.setProgress(now);
            }
        }
    }

}
