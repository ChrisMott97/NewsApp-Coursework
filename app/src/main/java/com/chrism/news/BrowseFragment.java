package com.chrism.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class BrowseFragment extends Fragment {

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.browse, container, false);
        HTTPHandler httpHandler = new HTTPHandler(view);
        httpHandler.execute();
        return view;
    }
}
//        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
//        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);
//        MaterialButton nextButton = view.findViewById(R.id.next_button);

        // Set an error if the password is less than 8 characters.
//        nextButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!isPasswordValid(passwordEditText.getText())) {
//                    passwordTextInput.setError(getString(R.string.shr_error_password));
//                } else {
//                    passwordTextInput.setError(null); // Clear the error
//                    ((NavigationHost) getActivity()).navigateTo(new ProductGridFragment(), false); // Navigate to the next Fragment
//                }
//            }
//        });
