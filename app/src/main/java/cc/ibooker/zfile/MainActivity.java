package cc.ibooker.zfile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import cc.ibooker.zfilelib.FileInfoBean;
import cc.ibooker.zfilelib.FileUtil;

/**
 * 获取存储目录，并实现单击打开文件，长按删除文件功能
 *
 * @author 邹峰立
 */
public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private FileInfoAdapter adapter;
    private ArrayList<FileInfoData> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        // 单击打开文件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0 && position < mDatas.size()) {
                    // 打开文件
                    FileUtil.openFile(MainActivity.this, new File(mDatas.get(position).getPath()));
                }
            }
        });
        // 长按删除文件
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0 && position < mDatas.size()) {
                    File file = new File(mDatas.get(position).getPath());
                    if (file.exists()) {
                        boolean bool;
                        if (file.isDirectory())// 删除目录
                            bool = FileUtil.deleteDir(mDatas.get(position).getPath());
                        else// 删除文件
                            bool = FileUtil.delFile(mDatas.get(position).getPath());
                        if (bool) {// 刷新界面
                            Toast.makeText(MainActivity.this, "删除文件成功", Toast.LENGTH_SHORT).show();
                            mDatas.remove(position);
                            setAdapter();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "待删除文件找不到", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        });

        // 获取数据
        ArrayList<FileInfoBean> fileInfoBeans = FileUtil.getFileInfos(FileUtil.SDPATH);
        for (FileInfoBean fileInfoBean : fileInfoBeans) {
            mDatas.add(new FileInfoData(fileInfoBean.getFileName(), fileInfoBean.getFilePath(), R.mipmap.ic_launcher));
        }

        // 刷新界面
        setAdapter();

    }

    // 自定义setAdapter
    private void setAdapter() {
        if (adapter == null) {
            adapter = new FileInfoAdapter(this, mDatas);
            listView.setAdapter(adapter);
        } else {
            adapter.reflahsData(mDatas);
        }
    }
}
