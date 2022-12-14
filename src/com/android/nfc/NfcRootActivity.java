/*
 * Copyright (C) 2011 The Android Open Source Project
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

package com.android.nfc;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;

public class NfcRootActivity extends Activity {

    static final String EXTRA_LAUNCH_INTENT = "launchIntent";
    static final String EXTRA_LAUNCH_INTENT_USER_HANDLE = "launchIntentUserHandle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_LAUNCH_INTENT)) {
            final Intent launchIntent = intent.getParcelableExtra(EXTRA_LAUNCH_INTENT);
            if (launchIntent != null) {
                try {
                    UserHandle user = intent.hasExtra(EXTRA_LAUNCH_INTENT_USER_HANDLE)
                            ? intent.getParcelableExtra(EXTRA_LAUNCH_INTENT_USER_HANDLE)
                            : UserHandle.CURRENT;
                    startActivityAsUser(launchIntent, user);
                } catch (ActivityNotFoundException e) {
                } catch (SecurityException e) {
                }
            }
        }
        finish();
    }
}
