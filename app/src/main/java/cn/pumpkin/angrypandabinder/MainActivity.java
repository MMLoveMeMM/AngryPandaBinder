package cn.pumpkin.angrypandabinder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.pumpkin.angrypandabinder.proxy.MediaServiceProxy;

public class MainActivity extends Activity {

    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn = (Button)findViewById(R.id.button);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = MediaServiceProxy.instance.addSum(11,22);
                Toast.makeText(MainActivity.this,"result : "+result,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        MediaServiceProxy.init(MainActivity.this,null,"cn.pumpkin.angrypandabinder");
        MediaServiceProxy.instance.onBindService();

    }

    @Override
    public void finish() {
        super.finish();
        MediaServiceProxy.instance.unBindService();
    }
}
