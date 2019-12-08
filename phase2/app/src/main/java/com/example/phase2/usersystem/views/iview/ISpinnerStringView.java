package com.example.phase2.usersystem.views.iview;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Activity which needs to set text on spinner implements this interface.
 */

public interface ISpinnerStringView {
    void setSpinner(Spinner spinner, ArrayAdapter adapter);
}
