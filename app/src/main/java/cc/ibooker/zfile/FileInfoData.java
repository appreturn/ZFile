package cc.ibooker.zfile;

import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * 文件信息Bean文件
 * Created by 邹峰立 on 2018/3/2.
 */
public class FileInfoData implements Comparable<FileInfoData> {
    private String name;// 文件名
    private String path;// 文件路径
    private int resId;// 文件图标
    private boolean selectable = true;// 能否选中

    public FileInfoData(String name, String path, int resId) {
        this.name = name;
        this.path = path;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    // 比较两个文件名是否相同 - 根据文件路径
    @Override
    public int compareTo(@NonNull FileInfoData o) {
        if (!TextUtils.isEmpty(o.getPath()))
            return path.compareTo(o.getPath());
        else
            throw new IllegalArgumentException();
    }
}
