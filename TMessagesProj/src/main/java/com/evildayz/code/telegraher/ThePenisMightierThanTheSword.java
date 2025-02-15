/**
 * Copyright 2022  Nikita S. <nikita@saraeff.net>
 * <p>
 * This file is part of Telegraher.
 * <p>
 * Telegraher is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * Telegraher is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with Telegraher.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.evildayz.code.telegraher;

import android.graphics.Typeface;

import com.evildayz.code.telegraher.ThePenisMightierThanTheSword;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;

import java.util.ArrayList;

public class ThePenisMightierThanTheSword {

    public static ArrayList<TLRPC.TL_dcOption> getTheDC(int dcId, ArrayList<TLRPC.TL_dcOption> options) {
        ArrayList<TLRPC.TL_dcOption> filtered = new ArrayList<>();
        for (TLRPC.TL_dcOption t : options) {
            if (t.id == dcId) filtered.add(t);
        }
        if (filtered.isEmpty()) return null;
        return filtered;
    }

    public static String getDCGeoDummy(int dcId) {
        switch (dcId) {
            case 1:
            case 3:
                return "USA, Miami";
            case 2:
            case 4:
                return "NLD, Amsterdam";
            case 5:
                return "SGP, Singapore";
            default:
                return "UNK, unknown";
        }
    }

    public static float getVideoRoundMult(int id) {
        switch (id) {
            case 0:
                return 0.5f;
            case 2:
                return 2f;
            case 3:
                return 4f;
            default:
                return 1f;
        }
    }

    public static int getVideoRoundSize(int id) {
        switch (id) {
            case 0:
                return 192;
            case 2:
                return 768;
            default:
                return 384;
        }
    }

    public static Typeface getFont(String font) {
        switch (font) {
            case "fonts/rmedium.ttf"://bold
            case "fonts/ritalic.ttf"://italic
            case "fonts/rmediumitalic.ttf"://bold italic
            case "fonts/rmono.ttf": //mono
            case "fonts/mw_bold.ttf"://normal bold
            case "fonts/rcondensedbold.ttf"://QR
                return AndroidUtilities.getTypeface(font);
            default:
                return Typeface.createFromFile(font);
        }
    }
}
