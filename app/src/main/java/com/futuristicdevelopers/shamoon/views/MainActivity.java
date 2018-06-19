package com.futuristicdevelopers.shamoon.views;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.futuristicdevelopers.shamoon.R;
import com.futuristicdevelopers.shamoon.controller.MyAdapter;
import com.futuristicdevelopers.shamoon.controller.circular_linkedlist.CircularLinkedList;
import com.futuristicdevelopers.shamoon.controller.circular_linkedlist.Node;
import com.futuristicdevelopers.shamoon.views.custom.video_player.EasyVideoCallback;
import com.futuristicdevelopers.shamoon.views.custom.video_player.EasyVideoPlayer;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity implements EasyVideoCallback, View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String TEST_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    private EasyVideoPlayer player;
    private static boolean dialog_shown = false;
    ArrayList<String> listPhotos = new ArrayList<>();
    ImageView imageView_first, imageView_second, imageView_third;
    Node<String> first, second, third;
    LinearLayout layout_previous_photo, layout_next_photo;
    CircularLinkedList<String> circularLinkedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        init();
    }

    private void init() {
        // Grabs a reference to the player view
        player = findViewById(R.id.player);
        imageView_first = findViewById(R.id.imageView_first);
        layout_previous_photo = findViewById(R.id.layout_previous_photo);
        layout_next_photo = findViewById(R.id.layout_next_photo);
        imageView_second = findViewById(R.id.imageView_second);
        imageView_third = findViewById(R.id.imageView_third);

        layout_next_photo.setOnClickListener(this);
        layout_previous_photo.setOnClickListener(this);
        // Sets the callback to this Activity, since it inherits EasyVideoCallback
        player.setCallback(this);
        // Sets the source to the HTTP URL held in the TEST_URL variable.
        // To play files, you can use Uri.fromFile(new File("..."))
        player.setAutoPlay(true);
        player.setSource(Uri.parse(getResources().getStringArray(R.array.public_videos)[0]));

        player.setAutoFullscreen(true);
        player.hideControls();
        // From here, the player view will show a progress indicator until the player is prepared.
        // Once it's prepared, the progress indicator goes away and the controls become enabled for the user to begin playback.

        Collections.addAll(listPhotos, getResources().getStringArray(R.array.images_carrousel));
        circularLinkedList = new CircularLinkedList<>();
        circularLinkedList.addAll(listPhotos);

        first = circularLinkedList.get(0);
        second = first.getNext();
        third = second.getNext();

        setData();
    }

    private void setData(){
        Picasso.get()
                .load(first.getValue())
                .into(imageView_first);

        Picasso.get()
                .load(second.getValue())
                .into(imageView_second);

        Picasso.get()
                .load(third.getValue())
                .into(imageView_third);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
    }

    // Methods for the implemented EasyVideoCallback

    @Override
    public void onPreparing(EasyVideoPlayer player) {
        // TODO handle if needed
        Log.d(TAG, "onPreparing");
    }

    @Override
    public void onPrepared(EasyVideoPlayer player) {
        // TODO handle
        Log.d(TAG, "onPrepared");
        player.setVolume(0f, 0f);

    }

    @Override
    public void onBuffering(int percent) {
        // TODO handle if needed
        Log.d(TAG, "onBuffering");
    }

    @Override
    public void onError(EasyVideoPlayer player, Exception e) {
        // TODO handle
        Log.d(TAG, "onError");
    }

    @Override
    public void onCompletion(final EasyVideoPlayer player) {
        // TODO handle if needed
        Log.d(TAG, "onCompletion");
        //show dialog to stop or to continue on loop
        final Dialog dialogOptions = new Dialog(MainActivity.this);
        dialogOptions.setContentView(R.layout.video_option_dialog);
        dialogOptions.setTitle("Options !");
        dialogOptions.findViewById(R.id.btn_play_in_loop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_shown = true;
                player.setLoop(true);
                dialogOptions.dismiss();
            }
        });
        dialogOptions.findViewById(R.id.btn_stop_playing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_shown = true;
                player.setLoop(false);
                dialogOptions.dismiss();
            }
        });

        if (!dialog_shown){
            dialogOptions.show();
        }
    }

    @Override
    public void onRetry(EasyVideoPlayer player, Uri source) {
        // TODO handle if used
        Log.d(TAG, "onRetry ");
    }

    @Override
    public void onSubmit(EasyVideoPlayer player, Uri source) {
        // TODO handle if used
        Log.d(TAG, "onSubmit");
    }

    @Override
    public void onClickVideoFrame(EasyVideoPlayer player) {
        Log.d(TAG, "onClickVideoFrame");
    }

    @Override
    public void onStarted(EasyVideoPlayer player) {
        // TODO handle if needed
        Log.d(TAG, "onStarted");
    }

    @Override
    public void onPaused(EasyVideoPlayer player) {
        // TODO handle if needed
        Log.d(TAG, "onPaused");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_next_photo:
                first = first.getNext();
                second = second.getNext();
                third = third.getNext();
                setData();
                break;
            case R.id.layout_previous_photo:
                first = first.getPrevious();
                second = second.getPrevious();
                third = third.getPrevious();
                setData();
                break;
                default:
                    break;
        }
    }
}
