package com.example.hzkto.patternrecognition;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.NonNull;

import com.github.gcacace.signaturepad.views.SignaturePad;

/**
 * Created by hzkto on 10/9/2016.
 */

public class Helper {

    private static final int dstWidth = 40;
    private static final int dstHeight = 40;
    private static Zond z1, z2, z3;
    private static int[] x, y;
    private static Boolean res[][];
    private static Bitmap bmp;
    private static int firstYCrop;
    private static int firstXCrop;

    @NonNull
    public static String Recognize(SignaturePad sv) {
        if (sv.isEmpty()) {
            return "Нарисуйте букву";
        }

        bmp = Bitmap.createScaledBitmap(sv.getSignatureBitmap(), dstWidth, dstHeight, false);

        res = BmpToBoolMapCrop(bmp);

        int bmpX = res.length-1;
        int bmpY = res[0].length-1;

        z1 = new Zond(bmpX / 2, 0, bmpX / 2, bmpY / 5);
        z2 = new Zond(bmpX /  2, 0, bmpX, bmpY / 2);
        z3 = new Zond(bmpX / 3, bmpY / 3 * 2, bmpX, bmpY / 3 * 2);

        z1.length = Bresenham.plot(z1.x1, z1.y1, z1.x2, z1.y2, z1.xArray, z1.yArray);
        z2.length = Bresenham.plot(z2.x1, z2.y1, z2.x2, z2.y2, z2.xArray, z2.yArray);
        z3.length = Bresenham.plot(z3.x1, z3.y1, z3.x2, z3.y2, z3.xArray, z3.yArray);

        CheckCross(z1);
        CheckCross(z2);
        CheckCross(z3);

        if (z1.cross > 0 && z2.cross > 0)
            return "Z";
        if (z1.cross == 0 && z2.cross > 0 && z3.cross > 0)
            return "Y";
        if (z3.cross == 0)
            return "L";

        return "Введенная буква не распознана";
    }

    public static Bitmap getPaintedBitmap() {
        return bmp;
    }

    private static Boolean[][] BmpToBoolMapCrop(Bitmap bmp) {
        firstYCrop = bmp.getHeight();
        firstXCrop = bmp.getWidth();
        int lastYCrop = 0;
        int lastXCrop = 0;
        Boolean res[][] = new Boolean[bmp.getWidth()][bmp.getHeight()];

        for (int x = 0; x < bmp.getWidth(); ++x) {
            for (int y = 0; y < bmp.getHeight(); ++y) {
                if (bmp.getPixel(x, y) != -1) {
                    res[x][y] = true;
                    /// FOR CROPPING ///
                    if (firstYCrop > y) firstYCrop = y;
                    if (firstXCrop > x) firstXCrop = x;
                    if (lastYCrop < y) lastYCrop = y;
                    if (lastXCrop < x) lastXCrop = x;
                    /// FOR CROPPING ///
                } else {
                    res[x][y] = false;
                }
            }
        }

        int resCropY = lastYCrop - firstYCrop + 1;
        int resCropX = lastXCrop - firstXCrop + 1;

        Boolean resCrop[][] = new Boolean[resCropX][resCropY];
        for (int x = 0; x < resCropX; ++x) {
            for (int y = 0; y < resCropY; ++y) {
                if (res[firstXCrop + x][firstYCrop + y]) {
                    resCrop[x][y] = true;
                } else {
                    resCrop[x][y] = false;
                }
            }
        }

        return resCrop;
    }

    private static void CheckCross(final Zond z) {
        boolean prevPixelWasBlack = false;
        for (int i = 0; i < z.length; i++) {
            if (z.yArray[i] >= 0 && z.xArray[i] >= 0) {
                bmp.setPixel(z.xArray[i] + firstXCrop, z.yArray[i] + firstYCrop, Color.parseColor("#FFFF4081"));
                if (res[z.xArray[i]][z.yArray[i]]) {
                    if (!prevPixelWasBlack) {
                        z.cross++;
                        prevPixelWasBlack = true;
                    }
                } else {
                    prevPixelWasBlack = false;
                }
            }
        }
    }

}