package com.example.test_stoica_robert;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity4 extends AppCompatActivity {
    List<Bitmap> imagini=new ArrayList<>();
    List<ImaginiDomeniu> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        List<String> linkImg=new ArrayList<>();
        linkImg.add("https://i5.walmartimages.com/seo/Dteck-iPhone-11-Case-Ultra-Slim-Fit-Case-Liquid-Silicone-Gel-Cover-Full-Body-Protection-Anti-Scratch-Shockproof-Compatible-6-1-inch-Cherry-Pink_2e9be8f4-8f23-4595-b004-03d88b248aef_1.a7ea5765e67c1ec49d675403cac9ab93.jpeg?odnHeight=2000&odnWidth=2000&odnBg=FFFFFF");
        linkImg.add("https://i5.walmartimages.com/seo/Cell-Phone-Cases-5-4-iPhone-12-Mini-Njjex-Liquid-Silicone-Gel-Rubber-Shockproof-Case-Ultra-Thin-Fit-Mini-Slim-Matte-Surface-Cover-Apple-2020-Red_c3a82875-ed3b-4af9-914a-d5157f7a8ce7.5e579827b2adeaadf86c0505bd721091.jpeg?odnHeight=2000&odnWidth=2000&odnBg=FFFFFF");
        linkImg.add("https://i5.walmartimages.com/seo/ULAK-iPhone-12-Case-iPhone-12-Pro-Case-Sparkle-Heavy-Duty-Shockproof-Hard-Back-Phone-Case-for-Apple-iPhone-12-12-Pro-6-1-2020-Blue-Purple_1ca85981-3270-4dde-948d-9759e820f971.3324955d8a508709e4c29b508a3d655f.jpeg?odnHeight=640&odnWidth=640&odnBg=FFFFFF");
        linkImg.add("https://i5.walmartimages.com/seo/iPhone-SE-3-5G-2022-2-2020-8-7-6S-6-Case-Liquid-Glitter-Phone-Case-Kickstand-Diamond-Ring-Stand-Protective-Pink-Girl-Women-Hot-Pink-Purple_9bc06c76-8b38-4f60-b8da-baf4e505b6be_1.5d948664449c0f7f5c9e25912a4848f9.jpeg?odnHeight=2000&odnWidth=2000&odnBg=FFFFFF");
        linkImg.add("https://i5.walmartimages.com/seo/Samsung-Galaxy-A10e-Phone-Case-Colorful-Design-Hybrid-Armor-Case-Shockproof-Dual-Layer-Protective-Phone-Cover-Gold-and-Purple-Floral_95aad909-0889-4a6d-aa89-e8e9757f6798_1.715ec91551da5802f03f030b859fc6fb.jpeg?odnHeight=2000&odnWidth=2000&odnBg=FFFFFF");

        Executor executor= Executors.newSingleThreadExecutor();
        Handler handler=new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    for (String link : linkImg) {
                        HttpURLConnection con = null;
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        InputStream is = con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                        con.disconnect();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            lista = new ArrayList<>();
                            lista.add(new ImaginiDomeniu("husa roz silicon", imagini.get(0), "https://www.walmart.com/ip/Dteck-iPhone-11-Case-Ultra-Slim-Fit-Case-Liquid-Silicone-Gel-Cover-Full-Body-Protection-Anti-Scratch-Shockproof-Compatible-6-1-inch-Cherry-Pink/474577661"));
                            lista.add(new ImaginiDomeniu("husa rosie silicon", imagini.get(1), "https://www.walmart.com/ip/Cell-Phone-Cases-5-4-iPhone-12-Mini-Njjex-Liquid-Silicone-Gel-Rubber-Shockproof-Case-Ultra-Thin-Fit-Mini-Slim-Matte-Surface-Cover-Apple-2020-Red/963877331"));
                            lista.add(new ImaginiDomeniu("husa transparenta silicon", imagini.get(2), "https://www.walmart.com/ip/iPhone-12-Case-Pro-ULAK-Clear-Sparkle-Heavy-Duty-Shockproof-Rugged-Protective-Cover-TPU-Bumper-Hard-Back-Hybrid-Case-Designed-Apple-6-1-2020-Crystal-/267583512"));
                            lista.add(new ImaginiDomeniu("husa mov sclipici silicon", imagini.get(3), "https://www.walmart.com/ip/iPhone-SE-2020-8-7-Case-Liquid-Floating-Quicksand-Glitter-Phone-Case-Girls-Kickstand-Bling-Diamond-Bumper-Ring-Stand-Protective-Pink-7-8-Girl-Women-H/452698022"));
                            lista.add(new ImaginiDomeniu("husa neagra cu model", imagini.get(4), "https://www.walmart.com/ip/for-Samsung-Galaxy-A10E-Case-Phone-Case-Shock-Proof-Edges-Hybrid-Hard-Back-Slim-Bumper-Cover/834173827"));

                            ListView listView=findViewById(R.id.lvImg);
                            ImgAdapter adapter=new ImgAdapter(imagini,getApplicationContext(),R.layout.layout_list_img);
                            listView.setAdapter(adapter);
                        }
                    });
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        ListView listView=findViewById(R.id.lvImg);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),MainActivity5.class);
                intent.putExtra("link",lista.get(position).getLink());
                startActivity(intent);
            }
        });
    }
}