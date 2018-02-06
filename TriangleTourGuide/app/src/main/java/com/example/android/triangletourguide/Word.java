package com.example.android.triangletourguide;

/**
 * {@link Word} represents an area of interest.
 * It contains resource IDs for the name, address, and picture
 */
public class Word {


    private int mNameId;

    private int mAddressId;


    /**
     * Image resource ID for the word
     */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /**
     * Constant value that represents no image was provided for this word
     */
    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Create a new Word object.
     */
    public Word(int nameId, int addressId) {
        mNameId = nameId;
        mAddressId = addressId;
    }

    /**
     * Create a new Word object.

     */
    public Word(int nameId, int addressId, int imageResourceId) {
        mNameId = nameId;
        mAddressId = addressId;
        mImageResourceId = imageResourceId;

    }

    public int getNameId() {
        return mNameId;
    }


    public int getAddressId() {
        return mAddressId;
    }

    /**
     * Return the image resource ID of the word.
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

}
