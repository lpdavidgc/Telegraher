/*
 * This is the source code of Telegram for Android v. 5.x.x.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013-2018.
 */

package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.evildayz.code.telegraher.ThePenisMightierThanTheSword;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class LetterSectionCell extends FrameLayout {

    private TextView textView;

    public LetterSectionCell(Context context) {
        super(context);
        setLayoutParams(new ViewGroup.LayoutParams(AndroidUtilities.dp(54), AndroidUtilities.dp(64)));

        textView = new TextView(getContext());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
        textView.setTypeface(ThePenisMightierThanTheSword.getFont(MessagesController.getGlobalTelegraherUICustomFont("fonts/rmedium.ttf", "rmedium")));
        textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4));
        textView.setGravity(Gravity.CENTER);
        addView(textView, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT));
        setTypeface(ThePenisMightierThanTheSword.getFont(MessagesController.getGlobalTelegraherUICustomFont("fonts/rmedium.ttf", "regular")));
    }

    public void setTypeface(Typeface font) {
        if (this.textView != null) this.textView.setTypeface(font);
    }

    public void setLetter(String letter) {
        textView.setText(letter.toUpperCase());
    }

    public void setCellHeight(int height) {
        setLayoutParams(new ViewGroup.LayoutParams(AndroidUtilities.dp(54), height));
    }
}
