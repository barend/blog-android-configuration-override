/*
 * Copyright 2015, Barend Garvelink
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.garvelink.blog.resourceconfigurationoverride;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView systemLocale;
    private TextView configLocale;
    private TextView configFontScale;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        final Configuration override = new Configuration();
        override.locale = new Locale("nl", "NL");
        override.fontScale = Settings.System.getFloat(
                newBase.getContentResolver(),
                Settings.System.FONT_SCALE,
                1.0f);
        applyOverrideConfiguration(override);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        systemLocale = (TextView) findViewById(R.id.system_locale);
        configLocale = (TextView) findViewById(R.id.configuration_locale);
        configFontScale = (TextView) findViewById(R.id.configuration_fontScale);

        systemLocale.setText(getString(R.string.system_locale, Locale.getDefault().toString()));
        configLocale.setText(getString(R.string.config_locale, getResources().getConfiguration().locale.toString()));
        configFontScale.setText(getString(R.string.config_font_scale, getResources().getConfiguration().fontScale));
    }
}
