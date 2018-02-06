package com.example.android.inventoryproject;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.inventoryproject.data.InventoryContract.InventoryEntry;

/**
 * Allows user to create a new item or edit an existing one.
 */
public class SoldActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {
    /**
     * Identifier for the inventory data loader
     */
    private static final int EXISTING_INVENTORY_LOADER = 0;

    /**
     * Content URI for the existing inventory
     */
    private Uri mCurrentInventoryUri;
    private int mQty;
    private Boolean finishOnce = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        // Examine the intent that was used to launch this activity,
        // in order to figure out if we're creating a new item or editing an existing one.
        Intent intent = getIntent();
        mCurrentInventoryUri = intent.getData();

        // Initialize a loader to read the inventory data from the database
        // and display the current values in the editor
        getLoaderManager().initLoader(EXISTING_INVENTORY_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // Since the editor shows all inventory attributes, define a projection that contains
        // all columns from the inventory table
        String[] projection = {
                InventoryEntry._ID,
                InventoryEntry.COLUMN_ITEM_NAME,
                InventoryEntry.COLUMN_PRICE,
                InventoryEntry.COLUMN_SUPPLIER,
                InventoryEntry.COLUMN_QTY_AVAILABLE,
                InventoryEntry.COLUMN_PICTURE_TITLE};

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                mCurrentInventoryUri,         // Query the content URI for the current inventory
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (finishOnce) {
            // Bail early if the cursor is null or there is less than 1 row in the cursor
            if (cursor == null || cursor.getCount() < 1) {
                return;
            }

            // Proceed with moving to the first row of the cursor and reading data from it
            // (This should be the only row in the cursor)
            if (cursor.moveToFirst()) {
                // Find the columns of inventory attributes that we're interested in
                int qtyColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_QTY_AVAILABLE);

                // Extract out the value from the Cursor for the given column index
                mQty = cursor.getInt(qtyColumnIndex);
            }
            int qtyInt = mQty - 1;
            String qtyFinal = String.valueOf(qtyInt);

            // Create a ContentValues object where column names are the keys,
            // and inventory attributes from the editor are the values.
            ContentValues values = new ContentValues();

            values.put(InventoryEntry.COLUMN_QTY_AVAILABLE, qtyFinal);
            // Otherwise this is an EXISTING item, so update the item with content URI: mCurrentInventoryUri
            // and pass in the new ContentValues. Pass in null for the selection and selection args
            // because mCurrentInventoryUri will already identify the correct row in the database that
            // we want to modify.
            getContentResolver().update(mCurrentInventoryUri, values, null, null);
            finishOnce = false;
            finish();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {}
}

