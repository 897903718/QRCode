package chanlytech.qrcode.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.activity.CaptureActivity;
import com.xys.libzxing.encoding.EncodingUtils;

import chanlytech.qrcode.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton;
    private TextView mTextView;
    private EditText mEditText;
    private ImageView mImageView;
    private CheckBox mCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.search_btn).setOnClickListener(this);
        findViewById(R.id.btn_ok).setOnClickListener(this);
        mTextView= (TextView) findViewById(R.id.result);
        mEditText= (EditText) findViewById(R.id.et);
        mImageView= (ImageView) findViewById(R.id.iv_img);
        mCheckBox= (CheckBox) findViewById(R.id.checkbox);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_btn:
                startActivityForResult(new Intent(this,CaptureActivity.class),0);
                break;
            case R.id.btn_ok:
                if(mEditText.getText().length()!=0){
                    Bitmap bitmap=null;
                    if(mCheckBox.isChecked()){
                        bitmap = EncodingUtils.createQRCode(mEditText.getText().toString().trim(), 300, 300, BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
                    }else {
                         bitmap = EncodingUtils.createQRCode(mEditText.getText().toString().trim(), 300, 300, null);
                    }

                    mImageView.setImageBitmap(bitmap);
                }else {
                    Toast.makeText(this,"请输入要生成的二维码内容",Toast.LENGTH_LONG).show();
                }


                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(data!=null){
                Bundle bundle=data.getExtras();
                String result=bundle.getString("result");
                mTextView.setText(result);

            }
        }
    }
}
