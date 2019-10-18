package tj.python.Utils;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Sarboz on 06.04.2018.
 */

public class Speech {


    private Context cont;
    private static MediaPlayer player;

    public Speech(Context ct) {
        this.cont = ct;
    }


    public void speechMedia(String id) {


        try {
            int idres = cont.getResources().getIdentifier(id, "raw", cont.getPackageName());

            player = MediaPlayer.create(cont, idres);

            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    player.release();
                }
            });

            player.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void stop() {
        player.stop();
    }

}

