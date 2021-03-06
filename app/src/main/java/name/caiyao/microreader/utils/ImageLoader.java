package name.caiyao.microreader.utils;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import name.caiyao.microreader.config.Config;

/**
 * Created by 蔡小木 on 2016/5/23 0023.
 */

public class ImageLoader {
    private ImageLoader() {
    }

    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }
}
