package com.zsl.myapplication.common.network;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;

import java.lang.ref.SoftReference;
import java.text.BreakIterator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zsl on 3/7/16.
 */
public class ImageCache {
    private static final int MAX_CAPACITY = 50;
    private static final long DELAY_BEFORE_PURGE = 10 * 1000;
    private HashMap<String,Bitmap> mFirstLevelCache = new LinkedHashMap<String,Bitmap>(MAX_CAPACITY/2,0.75f,true){
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Bitmap> eldest) {
            if (size()>MAX_CAPACITY){
                mSecondLevelCache.put(eldest.getKey(),new SoftReference<Bitmap>(eldest.getValue()));
                return true;
            }
            return false;
        }
    };

    private ConcurrentHashMap<String,SoftReference<Bitmap>> mSecondLevelCache = new ConcurrentHashMap<>(MAX_CAPACITY/2);

    public void addToCache(String url, Bitmap bitmap){
        if (url == null || bitmap == null)
            return;

        synchronized (mFirstLevelCache){
            mFirstLevelCache.put(url,bitmap);
        }
    }

    public Bitmap getBitmapFromCache(String url){
        Bitmap bitmap = null;
        bitmap = getBitMapFromFirstLevel(url);
        if (bitmap != null){
            return bitmap;
        }else{
            bitmap = getBitMapFromSecondMap(url);
        }

        return bitmap;

    }

    private Bitmap getBitMapFromFirstLevel(String url){
        Bitmap bitmap = null;
        synchronized(mFirstLevelCache){
            bitmap = mFirstLevelCache.get(url);
            if (bitmap != null){
                mFirstLevelCache.remove(url);
                mFirstLevelCache.put(url,bitmap);
            }
        }
        return bitmap;
    }

    private Bitmap getBitMapFromSecondMap(String url){
        Bitmap bitmap = null;
        SoftReference<Bitmap> softReference = mSecondLevelCache.get(url);
        if (softReference != null){
            bitmap = softReference.get();
            if (bitmap == null){
                mSecondLevelCache.remove(url);
            }
        }
        return bitmap;
    }

    public void clear(){
        mFirstLevelCache.clear();
        mSecondLevelCache.clear();
    }

}
