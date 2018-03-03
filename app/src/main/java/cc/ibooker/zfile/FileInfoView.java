package cc.ibooker.zfile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 文件视图View
 * Created by 邹峰立 on 2018/3/2.
 */
@SuppressLint("ViewConstructor")
public class FileInfoView extends LinearLayout {
    private ImageView imageView;
    private TextView textView;

    public FileInfoView(Context context, FileInfoData fileInfoData) {
        super(context);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        addView(fileInfoData);
    }

    // 添加View
    private void addView(FileInfoData fileInfoData) {
        imageView = new ImageView(getContext());
        imageView.setImageResource(R.mipmap.ic_launcher);
        imageView.setPadding(8, 12, 6, 12);
        addView(imageView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        textView = new TextView(getContext());
        textView.setText(fileInfoData.getName());
        textView.setPadding(8, 6, 6, 10);
        textView.setTextSize(26);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setSingleLine();
        textView.setEllipsize(TextUtils.TruncateAt.END);
        addView(textView, new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1));
    }

    // 设置文件名
    public void setText(String text) {
        textView.setText(text);
    }

    // 设置图标
    public void setIcon(@DrawableRes int resId) {
        imageView.setImageResource(resId);
    }
}
