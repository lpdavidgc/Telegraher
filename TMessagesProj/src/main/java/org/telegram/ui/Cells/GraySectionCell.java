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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import com.evildayz.code.telegraher.ThePenisMightierThanTheSword;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;

import java.util.List;

public class GraySectionCell extends FrameLayout {

    private TextView textView;
    private TextView rightTextView;
    private final Theme.ResourcesProvider resourcesProvider;

    public GraySectionCell(Context context) {
        this(context, null);
    }

    public GraySectionCell(Context context, Theme.ResourcesProvider resourcesProvider) {
        super(context);
        this.resourcesProvider = resourcesProvider;

        setBackgroundColor(getThemedColor(Theme.key_graySection));

        textView = new TextView(getContext());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        textView.setTypeface(ThePenisMightierThanTheSword.getFont(MessagesController.getGlobalTelegraherUICustomFont("fonts/rmedium.ttf", "rmedium")));
        textView.setTextColor(getThemedColor(Theme.key_graySectionText));
        textView.setGravity((LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.CENTER_VERTICAL);
        addView(textView, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT, (LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP, 16, 0, 16, 0));

        rightTextView = new TextView(getContext()) {
            @Override
            public CharSequence getAccessibilityClassName() {
                return Button.class.getName();
            }
        };
        rightTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        rightTextView.setTextColor(getThemedColor(Theme.key_graySectionText));
        rightTextView.setGravity((LocaleController.isRTL ? Gravity.LEFT : Gravity.RIGHT) | Gravity.CENTER_VERTICAL);
        addView(rightTextView, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.MATCH_PARENT, (LocaleController.isRTL ? Gravity.LEFT : Gravity.RIGHT) | Gravity.TOP, 16, 0, 16, 0));

        ViewCompat.setAccessibilityHeading(this, true);
        setTypeface(ThePenisMightierThanTheSword.getFont(MessagesController.getGlobalTelegraherUICustomFont("fonts/rmedium.ttf", "regular")));
    }

    public void setTypeface(Typeface font) {
        if (this.textView != null) this.textView.setTypeface(font);
        if (this.rightTextView != null) this.rightTextView.setTypeface(font);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32), MeasureSpec.EXACTLY));
    }

    public void setTextColor(String key) {
        int color = getThemedColor(key);
        textView.setTextColor(color);
        rightTextView.setTextColor(color);
    }

    public void setText(String text) {
        textView.setText(text);
        rightTextView.setVisibility(GONE);
    }

    public void setText(String left, String right, OnClickListener onClickListener) {
        textView.setText(left);
        rightTextView.setText(right);
        rightTextView.setOnClickListener(onClickListener);
        rightTextView.setVisibility(VISIBLE);
    }

    public static void createThemeDescriptions(List<ThemeDescription> descriptions, RecyclerListView listView) {
        descriptions.add(new ThemeDescription(listView, 0, new Class[]{GraySectionCell.class}, new String[]{"textView"}, null, null, null, Theme.key_graySectionText));
        descriptions.add(new ThemeDescription(listView, 0, new Class[]{GraySectionCell.class}, new String[]{"rightTextView"}, null, null, null, Theme.key_graySectionText));
        descriptions.add(new ThemeDescription(listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{GraySectionCell.class}, null, null, null, Theme.key_graySection));
    }

    public TextView getTextView() {
        return textView;
    }

    private int getThemedColor(String key) {
        Integer color = resourcesProvider != null ? resourcesProvider.getColor(key) : null;
        return color != null ? color : Theme.getColor(key);
    }
}
