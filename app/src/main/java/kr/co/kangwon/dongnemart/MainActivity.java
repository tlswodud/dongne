package kr.co.kangwon.dongnemart;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends FragmentActivity {

        private ViewPager2 mPager;
        private FragmentStateAdapter pagerAdapter;
        private int num_page = 4;
        private CircleIndicator3 mIndicator;
        ImageView imageView1;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            getToken();
            

            final String[] ListMenu = {"리스트1","리스트2","리스트3","리스트4"};
            final Integer[] item = {R.drawable.manman, R.drawable.ic_banner_foreground, R.drawable.ic_launcher_background,
                    R.drawable.ic_banner_foreground};



            //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ListMenu);
           // ListView listView = (ListView)findViewById(R.id.list);

            setTitle("ListView Example");
            ListView mylistview = (ListView)findViewById(R.id.list);
            registerForContextMenu(mylistview);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListMenu);
            mylistview.setAdapter(adapter);

            mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    final Dialog imgdialog = new Dialog(MainActivity.this);
                    imgdialog.setContentView(R.layout.dialog);

                    Button btn = (Button)imgdialog.findViewById(R.id.button);
                    final ImageView imageView = (ImageView)imgdialog.findViewById(R.id.imageView1);
                    Bitmap b = BitmapFactory.decodeResource(getResources(), item[i]);
                    imageView.setImageBitmap(b);

                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            imgdialog.dismiss();
                        }
                    });
                    imgdialog.show();
                }
            });



final String[] ListMenu2 = {"리스트1","리스트2","리스트3","리스트4"};
            ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ListMenu2);
            ListView listView2 = (ListView)findViewById(R.id.list2);
            listView2.setAdapter(adapter2);

            final String[] ListMenu3 = {"리스트1","리스트2","리스트3","리스트4"};
            ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ListMenu3);
            ListView listView3 = (ListView)findViewById(R.id.list3);
            listView3.setAdapter(adapter3);





            //ViewPager2
            mPager = findViewById(R.id.viewpager);
            //Adapter
            pagerAdapter = new MyAdapter(this, num_page);
            mPager.setAdapter(pagerAdapter);
            //Indicator
            mIndicator = findViewById(R.id.indicator);
            mIndicator.setViewPager(mPager);
            mIndicator.createIndicators(num_page,0);
            //ViewPager Setting
            mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
            mPager.setCurrentItem(1000);
            mPager.setOffscreenPageLimit(3);

            mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    if (positionOffsetPixels == 0) {
                        mPager.setCurrentItem(position);
                    }
                }

                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    mIndicator.animatePageSelected(position%num_page);
                }

            });


            final float pageMargin= getResources().getDimensionPixelOffset(R.dimen.pageMargin);
            final float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);

            mPager.setPageTransformer(new ViewPager2.PageTransformer() {
                @Override
                public void transformPage(@NonNull View page, float position) {
                    float myOffset = position * -(2 * pageOffset + pageMargin);
                    if (mPager.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                        if (ViewCompat.getLayoutDirection(mPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                            page.setTranslationX(-myOffset);
                        } else {
                            page.setTranslationX(myOffset);
                        }
                    } else {
                        page.setTranslationY(myOffset);
                    }
                }


            });








        }


           public  void getToken(){
               FirebaseMessaging.getInstance().getToken()
                       .addOnCompleteListener(new OnCompleteListener<String>() {
                           @Override
                           public void onComplete(@NonNull Task<String> task) {
                               if (!task.isSuccessful()) {
                                   Log.d("test123", "Fetching FCM registration token failed");
                                   return;
                               }

                               // Get new FCM registration token
                               String token = task.getResult();
                               Log.d("test123" ,"token: "+token);
                               // Log and toast

                           }
                       });
           }







    }





