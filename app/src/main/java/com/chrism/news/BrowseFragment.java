package com.chrism.news;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class BrowseFragment extends Fragment {
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.browse, container, false);
//        HTTPHandler httpHandler = new HTTPHandler(view);
//        httpHandler.execute();

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);


        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new TopNewsFragment(), "TOPNEWS");
        adapter.addFrag(new SportsFragment(), "SPORTS");
//        adapter.addFrag(new ThreeFragment(), "THREE");
//        adapter.addFrag(new FourFragment(), "FOUR");
//        adapter.addFrag(new FiveFragment(), "FIVE");
//        adapter.addFrag(new SixFragment(), "SIX");
//        adapter.addFrag(new SevenFragment(), "SEVEN");
//        adapter.addFrag(new EightFragment(), "EIGHT");
//        adapter.addFrag(new NineFragment(), "NINE");
//        adapter.addFrag(new TenFragment(), "TEN");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
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
