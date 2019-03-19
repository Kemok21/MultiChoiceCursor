package com.example.multichoicecursor.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class FakeContract {

    private FakeContract() {
    }

    public static class FakeEntry implements BaseColumns {

        //Table name
        public static final String TABLE_NAME = "fake";

        public static final String CONTENT_AUTHORITY = "com.example.multichoicecursor";
        public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
        public static final String PATH_FAKE = "fake";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_FAKE);
        //Columns
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "fake_name";
        public static final String COLUMN_IS_CHECKED = "is_checked";

        /**
         * The MIME type of {@link #CONTENT_URI} for a list of fakes.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FAKE;

        /**
         * The MIME type of {@link #CONTENT_URI} for a single fake.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FAKE;
    }
}
