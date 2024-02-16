package iss.workshop.androidgrouphub.Activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import iss.workshop.androidgrouphub.R;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        btnExit = findViewById(R.id.btn_exit_map);
        btnExit.setOnClickListener(v -> finish());

        // 找到重置视图按钮并设置点击事件
        Button btnReset = findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(v -> resetCameraToInitialPosition());
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // 允许用户交互，例如移动和缩放地图
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // 获取从 PublishActivity 传递过来的经纬度
        double latitude = getIntent().getDoubleExtra("latitude", -34); // 提供默认值为示例
        double longitude = getIntent().getDoubleExtra("longitude", 151); // 提供默认值为示例

        // 创建位置 LatLng 实例并在地图上添加标记
        LatLng location = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(location).title("Marker in Selected Location"));

        // 移动相机到指定位置
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15)); // 15 是缩放级别
    }
    private void resetCameraToInitialPosition() {
        // 确保 mMap 不为 null
        if (mMap != null) {
            // 获取从 PublishActivity 传递过来的经纬度
            double latitude = getIntent().getDoubleExtra("latitude", -34); // 提供默认值为示例
            double longitude = getIntent().getDoubleExtra("longitude", 151); // 提供默认值为示例
            LatLng initialPosition = new LatLng(latitude, longitude);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialPosition, 15)); // 重新设置相机到初始位置
        }
    }
}
