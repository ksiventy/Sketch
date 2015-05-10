/*
 * Copyright (C) 2013 Peng fei Pan <sky@xiaopan.me>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.xiaopan.sketch;

import android.content.Context;
import android.util.DisplayMetrics;

import me.xiaopan.sketch.process.ImageProcessor;

/**
 * 显示选项
 */
public class LoadOptions extends DownloadOptions{
    private ImageSize maxSize;	//最大图片尺寸，用于读取图片时计算inSampleSize
    private Resize resize;	// 新的尺寸，ImageProcessor会根据此尺寸来创建新的图片
    private ImageProcessor imageProcessor;	//图片处理器
    private boolean decodeGifImage = true; // 是否解码GIF图片

    public LoadOptions(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        setMaxSize((int) (displayMetrics.widthPixels * 1.5f), (int) (displayMetrics.heightPixels * 1.5f));
    }

    public LoadOptions(LoadOptions from){
        copyOf(from);
    }

    @Override
    public LoadOptions setEnableDiskCache(boolean isEnableDiskCache) {
        super.setEnableDiskCache(isEnableDiskCache);
        return this;
    }

    /**
     * 获取最大尺寸
     * @return 最大尺寸
     */
    public ImageSize getMaxSize() {
        return maxSize;
    }

    /**
     * 设置最大尺寸，在解码的时候会使用此Size来计算inSimpleSize
     * @param maxSize 最大尺寸
     * @return LoadOptions
     */
    public LoadOptions setMaxSize(ImageSize maxSize){
        this.maxSize = maxSize;
        return this;
    }

    /**
     * 设置最大尺寸，在解码的时候会使用此Size来计算inSimpleSize
     * @param width 宽
     * @param height 高
     * @return LoadOptions
     */
    public LoadOptions setMaxSize(int width, int height){
        this.maxSize = new ImageSize(width, height);
        return this;
    }

    /**
     * 获取新尺寸
     * @return 新尺寸
     */
    public Resize getResize() {
        return resize;
    }

    /**
     * 裁剪图片，ImageProcessor会根据此尺寸裁剪图片
     * @param resize 新的尺寸
     * @return LoadOptions
     */
    public LoadOptions setResize(Resize resize){
        this.resize = resize;
        return this;
    }

    /**
     * 裁剪图片，ImageProcessor会根据此尺寸来裁剪图片
     * @param width 宽
     * @param height 高
     * @return LoadOptions
     */
    public LoadOptions setResize(int width, int height){
        this.resize = new Resize(width, height);
        return this;
    }

    /**
     * 获取图片处理器
     * @return 图片处理器
     */
    public ImageProcessor getImageProcessor() {
        return imageProcessor;
    }

    /**
     * 设置图片处理器，图片处理器会根据resize和ScaleType创建一张新的图片
     * @param processor 图片处理器
     * @return LoadOptions
     */
    public LoadOptions setImageProcessor(ImageProcessor processor){
        this.imageProcessor = processor;
        return this;
    }

    /**
     * 是否解码GIF图片
     * @return true：是
     */
    public boolean isDecodeGifImage() {
        return decodeGifImage;
    }

    /**
     * 设置是否解码GIF图片
     * @param decodeGifImage true：是
     */
    public LoadOptions setDecodeGifImage(boolean decodeGifImage) {
        this.decodeGifImage = decodeGifImage;
        return this;
    }

    @Override
    public LoadOptions setRequestLevel(RequestLevel requestLevel) {
        super.setRequestLevel(requestLevel);
        return this;
    }

    public void copyOf(LoadOptions loadOptions){
        this.maxSize = loadOptions.getMaxSize();
        this.resize = loadOptions.getResize();
        this.imageProcessor = loadOptions.getImageProcessor();
        this.decodeGifImage = loadOptions.isDecodeGifImage();

        setEnableDiskCache(loadOptions.isEnableDiskCache());
        setRequestLevel(loadOptions.getRequestLevel());
    }
}
