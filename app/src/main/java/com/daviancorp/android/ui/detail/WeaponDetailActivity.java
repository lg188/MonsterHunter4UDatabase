package com.daviancorp.android.ui.detail;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.daviancorp.android.data.database.DataManager;
import com.daviancorp.android.mh4udatabase.R;
import com.daviancorp.android.ui.adapter.WeaponDetailPagerAdapter;
import com.daviancorp.android.ui.dialog.WishlistDataAddDialogFragment;
import com.daviancorp.android.ui.general.GenericTabActivity;
import com.daviancorp.android.ui.list.adapter.MenuSection;

public class WeaponDetailActivity extends GenericTabActivity {
    /**
     * A key for passing a weapon ID as a long
     */
    public static final String EXTRA_WEAPON_ID =
            "com.daviancorp.android.android.ui.detail.weapon_id";

    private static final String DIALOG_WISHLIST_ADD = "wishlist_add";
    private static final int REQUEST_ADD = 0;

    private ViewPager viewPager;
    private WeaponDetailPagerAdapter mAdapter;

    private long id;
    private String name;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        id = getIntent().getLongExtra(EXTRA_WEAPON_ID, -1);
        name = DataManager.get(getApplicationContext()).getWeapon(id).getName();
        setTitle(name);
        // Initialization
        viewPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new WeaponDetailPagerAdapter(getSupportFragmentManager(), getApplicationContext(), id);
        viewPager.setAdapter(mAdapter);

        mSlidingTabLayout.setViewPager(viewPager);
    }

    @Override
    protected MenuSection getSelectedSection() {
        return MenuSection.WEAPONS;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = new MenuInflater(getApplicationContext());
        inflater.inflate(R.menu.menu_wishlist_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.wishlist_add:
                FragmentManager fm = getSupportFragmentManager();
                WishlistDataAddDialogFragment dialogCopy = WishlistDataAddDialogFragment
                        .newInstance(id, name);
                dialogCopy.show(fm, DIALOG_WISHLIST_ADD);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
