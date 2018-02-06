package com.example.android.inventoryproject;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.inventoryproject.data.InventoryContract.InventoryEntry;

import java.text.DecimalFormat;

/**
 * {@link InventoryCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link Cursor} of item data as its data source. This adapter knows
 * how to create list items for each row of inventory data in the {@link Cursor}.
 */
public class InventoryCursorAdapter extends CursorAdapter {

    Activity activity;
    private Uri mCurrentInventoryUri;

    /**
     * Constructs a new {@link InventoryCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return view;
    }

    /**
     * This method binds the inventory data (in the current row pointed to by cursor) to the given
     * list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(final View view, final Context context, final Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView qtyTextView = (TextView) view.findViewById(R.id.qty);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);
        ImageView imageImageView = (ImageView) view.findViewById(R.id.item_image);

        // Find the columns of the item attributes that we're interested in
        int nameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_ITEM_NAME);
        int qtyColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_QTY_AVAILABLE);
        int priceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRICE);
        int imageColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PICTURE_TITLE);

        // Read the item attributes from the Cursor for the current item
        String inventoryName = cursor.getString(nameColumnIndex);
        int inventoryQty = cursor.getInt(qtyColumnIndex);
        Double inventoryPrice = cursor.getDouble(priceColumnIndex);
        String inventoryImage = cursor.getString(imageColumnIndex);

        // Update the TextViews with the attributes for the current item
        nameTextView.setText(inventoryName);
        qtyTextView.setText(String.format("%,d", inventoryQty));
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        String formattedValue = decimalFormat.format(inventoryPrice);
        priceTextView.setText(formattedValue);
        if (inventoryImage == null) {
            imageImageView.setImageResource(R.drawable.ic_do_not_disturb_black_24dp);
        } else {
            imageImageView.setImageBitmap(BitmapFactory.decodeFile(inventoryImage));
            imageImageView.setTag(inventoryImage);
        }

        Button sellBtn = (Button) view.findViewById(R.id.sell_one);
        sellBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view != null) {

                    View parentRow = (View) view.getParent();
                    ListView listView = (ListView) parentRow.getParent();
                    final int position = listView.getPositionForView(parentRow);
                    final long id = listView.getItemIdAtPosition(position);

                    Intent myIntent = new Intent(view.getContext(),
                            SoldActivity.class);
                    // Form the content URI that represents the specific item that was clicked on,
                    // by appending the "id" (passed as input to this method) onto the
                    // {@link InventoryEntry#CONTENT_URI}.
                    Uri currentInventoryUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI, id);

                    // Set the URI on the data field of the intent
                    myIntent.setData(currentInventoryUri);
                    context.startActivity(myIntent);
                }
            }
        });
    }
}
