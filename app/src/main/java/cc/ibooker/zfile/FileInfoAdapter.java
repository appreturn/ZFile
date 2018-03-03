package cc.ibooker.zfile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * 文件信息Adapter
 * Created by 邹峰立 on 2018/3/2.
 */
public class FileInfoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<FileInfoData> mDatas = new ArrayList<>();

    public FileInfoAdapter(Context context, ArrayList<FileInfoData> list) {
        this.context = context;
        this.mDatas = list;
    }

    // 刷新数据
    public void reflahsData(ArrayList<FileInfoData> list) {
        this.mDatas = list;
        this.notifyDataSetChanged();
    }

    // 判断文件是否被选中
    public boolean isSelectable(int position) {
        return mDatas.get(position).isSelectable();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FileInfoView fileInfoView = (FileInfoView) convertView;
        if (fileInfoView == null) {
            fileInfoView = new FileInfoView(context, mDatas.get(position));
        } else {
            fileInfoView.setText(mDatas.get(position).getName());
            fileInfoView.setIcon(mDatas.get(position).getResId());
        }
        return fileInfoView;
    }
}
