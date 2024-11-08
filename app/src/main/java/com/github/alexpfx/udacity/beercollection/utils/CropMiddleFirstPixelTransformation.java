package com.github.alexpfx.udacity.beercollection.utils;

import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

/**
 * Transformation usado para cortar bordas brancas das imagens carregadas pelo Picasso.
 */
public class CropMiddleFirstPixelTransformation implements Transformation {

    private int mWidth;

    private int mHeight;


    @Override
    public Bitmap transform(Bitmap source) {
        int width = source.getWidth();
        int height = source.getHeight();

        int[] horizontalMiddleArray = new int[width];
        int div = 2;

        source.getPixels(horizontalMiddleArray, 0, width, 0, height / div, width, 1);

        int[] verticalMiddleArray = new int[height];
        source.getPixels(verticalMiddleArray, 0, 1, width / div, 0, 1, height);

        int left = getFirstNonWhitePosition(horizontalMiddleArray);
        int right = getLastNonWhitePosition(horizontalMiddleArray);

        int top = getFirstNonWhitePosition(verticalMiddleArray);
        int bottom = getLastNonWhitePosition(verticalMiddleArray);

        mWidth = right - left;
        mHeight = bottom - top;

        if (!isNegative(left, right, top, bottom)) {

            return source;
        }

        Bitmap bitmap = Bitmap.createBitmap(source, left, top, mWidth, mHeight);
        source.recycle();

        return bitmap;
    }


    private int getFirstNonWhitePosition(int[] horizontalMiddleArray) {
        int left = 0;
        for (int i = 0; i < horizontalMiddleArray.length; i++) {
            if (i == 0) {
                left = horizontalMiddleArray[i];
            }
            if (left != horizontalMiddleArray[i]) {
                return i;
            }
        }
        return -1;
    }


    private int getLastNonWhitePosition(int[] horizontalMiddleArray) {
        int right = 0;
        int length = horizontalMiddleArray.length;
        for (int i = length - 1; i > 0; i--) {
            if (i == length - 1) {
                right = horizontalMiddleArray[i];
            }
            if (right != horizontalMiddleArray[i]) {
                return i;
            }
        }
        return -1;
    }


    private boolean isNegative(int... values) {
        for (int i : values) {
            if (i < 0) {
                return false;
            }
        }
        return true;
    }


    @Override
    public String key() {
        return "CropMiddleFirstPixelTransformation(width=" + mWidth + ", height=" + mHeight + ")";
    }
}