package com.example.multichoicecursor;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.multichoicecursor.data.FakeContract.FakeEntry;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private Cursor mCursor;
    private static final int FAKE_LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button multiChoiceButton = findViewById(R.id.multi_choice_button);

        multiChoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // setup the alert builder
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Choose a fake");
                builder.setCancelable(false);

//                final String[] projection = new String[]{
//                        FakeEntry._ID,
//                        FakeEntry.COLUMN_NAME,
//                        FakeEntry.COLUMN_IS_CHECKED};
//
//                final Cursor cursor = getContentResolver().query(FakeEntry.CONTENT_URI, projection, null, null, null);

                /**
                 * Problem is here.
                 * What am I wrong in?
                 */
                builder.setMultiChoiceItems(mCursor,
                        FakeEntry.COLUMN_IS_CHECKED,
                        FakeEntry.COLUMN_NAME,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                                mCursor.moveToPosition(which);
                                int id = mCursor.getInt(mCursor.getColumnIndexOrThrow(FakeEntry._ID));
                                Uri currentClimberUri = ContentUris.withAppendedId(FakeEntry.CONTENT_URI, id);

                                ContentValues values = new ContentValues();
                                values.put(FakeEntry.COLUMN_IS_CHECKED, (isChecked) ? 1 : 0);
                                getContentResolver().update(currentClimberUri, values, null, null);
                            }
                        });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        getLoaderManager().initLoader(FAKE_LOADER, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_fake:
                addFakeData();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addFakeData() {
        String fakeName = "[" + System.currentTimeMillis() + "]";

        ContentValues values = new ContentValues();
        values.put(FakeEntry.COLUMN_NAME, fakeName);

        Uri newUri = getContentResolver().insert(FakeEntry.CONTENT_URI, values);
        Log.v("newUri", newUri.toString());
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = new String[]{
                FakeEntry._ID,
                FakeEntry.COLUMN_NAME,
                FakeEntry.COLUMN_IS_CHECKED
        };
        return new CursorLoader(
                this,
                FakeEntry.CONTENT_URI,
                projection,
                null, null, null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mCursor = cursor;
        Log.v("CURSOR", cursor.toString());
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursor = null;
    }
}
