package com.coldy.organizzeclone.activity.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import com.google.android.material.textfield.TextInputLayout;

public class EditTextController implements TextWatcher {

    private TextInputLayout textLayout;

    public EditTextController(TextInputLayout textLayout) {
        this.textLayout = textLayout;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (textLayout.getError() != null) {
            textLayout.setError(null);
        }
    }
}
