package iss.workshop.androidgrouphub.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import iss.workshop.androidgrouphub.Adapter.GroupHubAdapter;
import iss.workshop.androidgrouphub.Model.GroupHub;
import iss.workshop.androidgrouphub.Network.NetworkUtils;
import iss.workshop.androidgrouphub.R;
import iss.workshop.androidgrouphub.databinding.ActivityMainBinding;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private RecyclerView.Adapter adapterOngoing;
    private static final String BASE_URL = "http://192.168.18.35:8080/api/group-hub/";
    // 接收后台线程的消息，重要，不能什么都用提醒主线程数据已经更改了，因为团队合作中，当前的主线程并不是团队合作的主线程
    private static Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 接收后台线程的消息
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    // 收到消息1，执行更新UI操作
                    ArrayList<GroupHub> groupHubList = (ArrayList<GroupHub>) msg.obj;
                    Log.d("Data has changed", "Trying to modify UI");
                    initRecyclerView(groupHubList);
                }
                return true;
            }
        });

        new Thread(() -> {
            try {
//                OkHttpClient client = new OkHttpClient();
//                Request request = new Request.Builder().url("http://192.168.18.35:8080/api/group-hub/get").get().build();
//
//                Response response = client.newCall(request).execute();
//
//                if (!response.isSuccessful()) {
//                    Log.e(TAG, "Failed to fetch data: " + response);
//                    return;
//                }
//
//                String responseData = response.body().string();
//                Gson gson = new Gson();
//                Type listType = new TypeToken<List<GroupHub>>(){}.getType();
//                ArrayList<GroupHub> groupHubList = gson.fromJson(responseData, listType);
                // 目前是在新线程里
//                runOnUiThread(() -> {
//                    for (GroupHub groupHub : groupHubList) {
//                        initRecyclerView(groupHubList);
//                        Log.d(TAG,  "ID" + groupHub.getId() + " || " + "Name: " + groupHub.getName() + ", Quantity: " + groupHub.getQuantity());
//                    }
//                });

                ArrayList<GroupHub> groupHubList = NetworkUtils.fetchAllGroupHub(); // 通过网络获取到所有的对象
                Message message = new Message();  // 创建消息对象，传递给主线程处理
                message.what = 1; // 消息标识，
                message.obj = groupHubList;
                handler.sendMessage(message); // 发送消息给主线程

            } catch (Exception e) {
                Log.e(TAG, "Error fetching data", e);
            }
        }).start();

        // 点击bottom bar profile图标，跳转视图
        ImageView imageView = findViewById(R.id.imageViewBtn4);
        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        // 发布交互
        ImageView publishButton = findViewById(R.id.publishBtn);
        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PublishActivity.class);
                startActivity(intent);;
            }
        });
    }

    private void initRecyclerView(ArrayList<GroupHub> items) {
        binding.viewOngoing.setLayoutManager(new GridLayoutManager(this, 2));
        adapterOngoing = new GroupHubAdapter(items, handler);
        binding.viewOngoing.setAdapter(adapterOngoing);
    }

    /**
     * 让其他活动来调用
     * @return
     */
    public static Handler getHandler() {
        return handler;
    }
}

