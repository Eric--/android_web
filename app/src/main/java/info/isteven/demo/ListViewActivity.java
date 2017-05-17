package info.isteven.demo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import info.isteven.demo.adapter.AnimalAdapter;
import info.isteven.demo.model.Animal;
import info.isteven.demo.model.HttpData;

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnClickListener{

    private List<Animal> mData = null;
    private Context mContext;
    private AnimalAdapter mAdapter = null;
    private ListView list_animal;

    private Button btnAdd;
    private int num = 0;
    private static final String DATA_URL = "http://www.bitonair.com/app/hello";
    private String animalStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter);
        mContext = ListViewActivity.this;
        list_animal = (ListView) findViewById(R.id.list_test);

        btnAdd = (Button)findViewById(R.id.btnOne);
        btnAdd.setOnClickListener(this);

        //动态加载顶部View和底部View
        final LayoutInflater inflater = LayoutInflater.from(this);
        View headView = inflater.inflate(R.layout.view_header, null, false);
        View footView = inflater.inflate(R.layout.view_footer, null, false);


        mData = new LinkedList<Animal>();
        mData.add(new Animal("狗说", "你是狗么?", R.mipmap.ic_launcher));
        mData.add(new Animal("牛说", "你是牛么?", R.mipmap.ic_launcher));

        //添加表头和表尾需要写在setAdapter方法调用之前！！！
        list_animal.addHeaderView(headView);
        list_animal.addFooterView(footView);

        mAdapter = new AnimalAdapter((LinkedList<Animal>) mData, mContext);
        list_animal.setAdapter(mAdapter);
        list_animal.setOnItemClickListener(this);

        new Thread(){
            @Override
            public void run() {
                super.run();
                while (animalStr.length() == 0){
                    try{
                        animalStr = HttpData.getJson(DATA_URL);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                Message msg = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("data", animalStr);
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        }.start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mContext,"你点击了第" + position + "项",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOne:
                Animal item = new Animal("新的"+num, "新来的?", R.mipmap.ic_launcher);
                Toast.makeText(mContext,"add",Toast.LENGTH_SHORT).show();
                num++;
                mAdapter.add(item);
                break;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String data = bundle.getString("data");
            try{
                JSONArray parkings = new JSONArray(data);
                for(int i = 0;i < parkings.length();i++){
                    JSONObject jsonObject = (JSONObject) parkings.get(i);
                    Animal item = new Animal(jsonObject.getString("chineseName"), jsonObject.getString("address"), R.mipmap.ic_launcher);
                    mAdapter.add(item);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };
}
