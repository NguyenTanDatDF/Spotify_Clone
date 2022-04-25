package hcmute.edu.vn.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import javax.sql.DataSource;

public class MainActivity extends AppCompatActivity {
    SimpleExoPlayer player;
    PlayerView playerView;
    SimpleExoPlayer absPlayerInternal;PlayerView pvMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "https://firebasestorage.googleapis.com/v0/b/algebraic-fin-332903.appspot.com/o/N%E1%BB%A5%20C%C6%B0%E1%BB%9Di%20Em%20L%C3%A0%20N%E1%BA%AFng%20-%20Green%E3%80%8CCukak%20Remix%E3%80%8D-%20Audio%20Lyrics%20Video.mp4?alt=media&token=557887a5-954b-4139-833c-a74d49bcf5cc";
        PlayMedia(url);
    }

    protected void PlayMedia(String url)
    {
        String CONTENT_URL = url;

        int appNameStringRes = R.string.app_name;

        pvMain = findViewById(R.id.pv_main); // creating player view

        TrackSelector trackSelectorDef = new DefaultTrackSelector();
        absPlayerInternal = ExoPlayerFactory.newSimpleInstance(this, trackSelectorDef); //creating a player instance

        String userAgent = Util.getUserAgent(this, this.getString(appNameStringRes));
        DefaultDataSourceFactory defdataSourceFactory = new DefaultDataSourceFactory(this,userAgent);
        Uri uriOfContentUrl = Uri.parse(CONTENT_URL);
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(defdataSourceFactory).createMediaSource(uriOfContentUrl);  // creating a media source

        absPlayerInternal.prepare(mediaSource);
        absPlayerInternal.setPlayWhenReady(true); // start loading video and play it at the moment a chunk of it is available offline (start and play immediately)

        pvMain.setPlayer(absPlayerInternal); // attach surface to the view
        pvMain.setControllerShowTimeoutMs(0);

       pvMain.showController();
       pvMain.setControllerHideOnTouch(false);


    }

}