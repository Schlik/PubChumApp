/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.schlik.pubchumapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import com.example.android.contactmanager.R;
import com.schlik.pubmate.pubmodelendpoint.*;



public final class PubChumMainActivity extends Activity
{

    public static final String TAG = "ContactManager";
    
    //public static final String WEB_CLIENT_ID = "177171809719.apps.googleusercontent.com";
    //public static final String ANDROID_CLIENT_ID = "177171809719-AIzaSyB7D5zRIw2VE4bHVkBVWLoBc_JuJt9bFx4.apps.googleusercontent.com";
    //public static final String ANDROID_AUDIENCE = WEB_CLIENT_ID;
    

    public static final String AUDIENCE = "server:client_id:177171809719-AIzaSyB7D5zRIw2VE4bHVkBVWLoBc_JuJt9bFx4.apps.googleusercontent.com";
    public static final String DEFAULT_ROOT_URL = "https://pubchum.appspot.com/_ah/api/";



    private Button mAddAccountButton, mShowPubsButton;
    private ListView mPubList;
 
//    private boolean mShowInvisible;
//    private CheckBox mShowInvisibleControl;

    /**
     * Called when the activity is first created. Responsible for initializing the UI.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        Log.v(TAG, "Activity State: onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_manager);

        // Obtain handles to UI objects
        mAddAccountButton = (Button) findViewById(R.id.addContactButton);
        mShowPubsButton = (Button) findViewById(R.id.showListButton);
        mPubList = (ListView) findViewById(R.id.pubList);


        // Register handler for UI elements
        mAddAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(TAG, "mAddAccountButton clicked");
                //launchContactAdder();
            }
        });
        
        mShowPubsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(TAG, "mShowPubsButton clicked");
                populatePubList();
            }
        });
        
    }

    /**
     * Populate the contact list based on account currently selected in the account spinner.
     */
    private void populatePubList() {
       new PubMateDBAsyncTask(this, mPubList).execute();
    }

    /**
     * Obtains the contact list for the currently selected account.
     *
     * @return A cursor for for accessing the contact list.
     */
    private Cursor getContacts()
    {
        // Run query
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
        };
        String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '" + "'";
        String[] selectionArgs = null;
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

        return managedQuery(uri, projection, selection, selectionArgs, sortOrder);
    }

    /**
     * Launches the ContactAdder activity to add a new contact to the selected accont.
     */
    protected void launchContactAdder() {
//        Intent i = new Intent(this, ContactAdder.class);
//        startActivity(i);
    }
}
