package com.example.national_ski_resorts;

import android.graphics.Bitmap;

public class ReviewImgData {
    private Bitmap Review_img;

    public ReviewImgData(Bitmap review_img) {
        Review_img = review_img;
    }

    public Bitmap getReview_img() {
        return Review_img;
    }

    public void setReview_img(Bitmap review_img) {
        Review_img = review_img;
    }
}
