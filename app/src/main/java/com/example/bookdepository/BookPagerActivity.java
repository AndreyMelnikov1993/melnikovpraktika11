package com.example.bookdepository;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStateManagerControl;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class BookPagerActivity extends FragmentActivity {
    private static final String EXTRA_BOOK_ID="ru.rsue.android.bookdepository.book.id";
    private ViewPager mViewPager;
    private List<Book> mBooks;
    public static Intent newIntent(Context packadeContext, UUID bookId)
    {
        Intent intent=new Intent(packadeContext, BookPagerActivity.class);
        intent.putExtra(EXTRA_BOOK_ID,bookId);
        return intent;
    }
    @Override
    protected void onCreate (Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_book_pager);
        UUID bookId= (UUID) getIntent().getSerializableExtra(EXTRA_BOOK_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_book_pager_view_pager);
        mBooks = BookLab.get(this).getBooks();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Book book = mBooks.get(position);
                return BookFragment.newInstance(book.getId());
            }

            @Override
            public int getCount() {
                return mBooks.size();
            }
        });
        for (int i=0; i<mBooks.size(); i++){
            if (mBooks.get(i).getId().equals(bookId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        }
    }

