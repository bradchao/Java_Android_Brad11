package tw.org.iii.brad11;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private MediaPlayer mediaPlayer;
    private Timer timer;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        mediaPlayer = MediaPlayer.create(this,R.raw.try_everything);

        int len = mediaPlayer.getDuration();
        Intent it = new Intent("brad");
        it.putExtra("len", len);
        sendBroadcast(it);

        timer.schedule(new MyTask(), 0, 200);

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean isPause = intent.getBooleanExtra("isPause", false);
        if (isPause){
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        }else{
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            if (mediaPlayer != null && mediaPlayer.isPlaying()){
                int now = mediaPlayer.getCurrentPosition();
                Intent it = new Intent("brad");
                it.putExtra("now", now);
                sendBroadcast(it);

            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null){
            timer.cancel();
            timer.purge();
            timer = null;
        }

        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
