package kr.co.kangwon.dongnemart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView text1;
    ImageView image1;
    ImageView image2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 =(TextView)findViewById(R.id.textview);
        image1 = (ImageView)findViewById((R.id.imageView));
        image2 = (ImageView)findViewById((R.id.imageView2));


    }
    public void showCameraBtn(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            Bitmap bitmap =(Bitmap)data.getParcelableExtra("data");
            image2.setImageBitmap(bitmap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater Inflater = getMenuInflater();
       Inflater.inflate(R.menu.option_menu,menu);
         return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       int id=item.getItemId();
       switch (id){
           case R.id.item1:
               text1.setText("첫번째 메뉴");
               image1.setImageResource(R.drawable.ic_launcher_background);
               break;
           case R.id.item2:
               text1.setText("두번째 메뉴");
               break;
           case R.id.item1_1:
               text1.setText("서브첫번째 메뉴");
               break;
           case R.id.item1_2:
               text1.setText("서브 두번째 메뉴");
               break;
           case R.id.item2_1:
               text1.setText("첫번째 메뉴");
               break;
       }
        return super.onOptionsItemSelected(item);

    }

}