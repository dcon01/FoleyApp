package com.example.foleyapp;

import android.content.Context;
import android.icu.text.Transliterator;
import android.media.SoundPool;
import android.util.Log;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// This code demonstrates one way to load several sounds
// into a sound pool. Each sound has a unique sampleId.
// SampleId's are not the same as the raw resource ids

public class AudioManager implements SoundPool.OnLoadCompleteListener {
    private final Map<MainActivity.SoundCategory, List<Integer>> soundLists;
    private MainActivity.SoundCategory currentSoundCategory;

    private final SoundPool soundPool;
    private int loadCount;

    public AudioManager(Context context) {
        soundLists = new HashMap<>();
        for (MainActivity.SoundCategory category : MainActivity.SoundCategory.values()) {
            soundLists.put(category, new ArrayList<>());
        }
        currentSoundCategory = MainActivity.SoundCategory.animal;

        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(10);
        soundPool = builder.build();
        soundPool.setOnLoadCompleteListener(this);

        // load order matches Sound's declaration order
        soundPool.load(context, R.raw.animal_1, 0);
        soundPool.load(context, R.raw.animal_2, 0);
        soundPool.load(context, R.raw.animal_3, 0);
        soundPool.load(context, R.raw.animal_4, 0);

        soundPool.load(context, R.raw.human_1, 0);
        soundPool.load(context, R.raw.human_2, 0);
        soundPool.load(context, R.raw.human_3, 0);
        soundPool.load(context, R.raw.human_4, 0);

        soundPool.load(context, R.raw.nature_1, 0);
        soundPool.load(context, R.raw.nature_2, 0);
        soundPool.load(context, R.raw.nature_3, 0);
        soundPool.load(context, R.raw.nature_4, 0);

        soundPool.load(context, R.raw.tech_1, 0);
        soundPool.load(context, R.raw.tech_2, 0);
        soundPool.load(context, R.raw.tech_3, 0);
        soundPool.load(context, R.raw.tech_4, 0);
    }

    @Override
    public void onLoadComplete(SoundPool soundPool,
                               int sampleId, int status) {

        // store the new sampleId in the current sound's sampleId list
        List<Integer> sampleIds = soundLists.get(currentSoundCategory);
        assert sampleIds != null;
        sampleIds.add(sampleId);
        ++loadCount;
        Log.i("AudioManager", "loadCount: " + loadCount + " current sound loaded: " + currentSoundCategory.toString());

        if (loadCount % 4 == 0) { // every 4 loads change to the next sound
            int index = currentSoundCategory.ordinal();
            if (index < MainActivity.SoundCategory.values().length - 1) {
                currentSoundCategory = MainActivity.SoundCategory.values()[index + 1];
            }
        }

    }

    public boolean isReady() {
        return loadCount == 16;
    }

//    public void play(MainActivity.SoundCategory soundCategory, Transliterator.Position position) {
public void play(MainActivity.SoundCategory soundCategory, Transliterator.Position position) {
        if (!isReady()) return;

        List<Integer> sampleIds = soundLists.get(soundCategory);
        assert sampleIds != null;

        //int index = position.ordinal();
        int index = soundCategory.ordinal();
        int sampleId = sampleIds.get(index);

        soundPool.play(sampleId, 1, 1,
                1, 0, 1);
    }

    public void resume() {
        soundPool.autoResume();
    }

    public void pause() {
        soundPool.autoPause();
    }

}