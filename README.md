# ZFile
Android文件管理类，包括打开不同后缀文件，创建文件/目录，获取文件/目录大小，复制文件，读取写入文件等。注意：需要添加三个权限 WRITE_EXTERNAL_STORAGE、READ_EXTERNAL_STORAGE、MOUNT_UNMOUNT_FILESYSTEMS。

引入资源，两种方式：

1、在build.gradle文件中添加以下代码：
```
allprojects {
	repositories {
		maven { url 'https://www.jitpack.io' }
	}
}
```
```
dependencies {
	compile 'com.github.zrunker:ZFile:v1.0.4'
}
```
2、使用maven，添加以下代码：
```
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```
```
<dependency>
	<groupId>com.github.zrunker</groupId>
	<artifactId>ZFile</artifactId>
	<version>v1.0.4</version>
</dependency>
```
用法：需要添加三个权限 WRITE_EXTERNAL_STORAGE、READ_EXTERNAL_STORAGE、MOUNT_UNMOUNT_FILESYSTEMS。
```
 /**
 * FileLib测试
 *
 * @author 邹峰立
 */
public class FileLibTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 创建单目录
        String filePath1 = FileUtil.SDPATH + File.separator + "imageFile";
        FileUtil.createSDDir(filePath1);

        // 创建多目录
        String filePath2 = FileUtil.SDPATH + File.separator + "ZFileDemo" + File.separator + "Cache";
        FileUtil.createSDDirs(filePath2);

        // 创建文件-根据当前年月日时分秒（时间戳）生成
        File file1 = FileUtil.createTimeMillisFile();

        // 创建文件-根据文件名生成
        File file2 = FileUtil.createNameFile("test1.txt");

        // 删除文件
        if (file1 != null)
            FileUtil.delFile(file1.getAbsolutePath());

        // 删除文件夹（目录），以及内部所有文件
        FileUtil.deleteDir(filePath2);

        // 读取文件
        String str = FileUtil.readFile(this, "test");
        Log.d("test", str);

        // 写入文件
        FileUtil.writeFile(this, "123456789", "test", MODE_PRIVATE);

        // 复制单个文件（复制文件内容）
        File file3 = FileUtil.createNameFile("ZFileDemo" + File.separator + "test2.txt");
        if (file2 != null && file3 != null)
            FileUtil.copyFile(file2.getAbsolutePath(), file3.getAbsolutePath());

        // 复制整个文件夹内容
        FileUtil.copyFolder(filePath1, filePath2);

        // 判断文件/目录是否存在
        boolean isExists = FileUtil.isFileExist(filePath1);
        Log.d("Log111", isExists + "");

        // 获取文件/文件夹的指定单位的大小
        double fileSize = FileUtil.getFileOrFilesSize(filePath1, FileUtil.SIZETYPE_B);
        Log.d("Log1112", fileSize + "");

        // 调用此方法自动计算文件/文件夹的大小
        String sizeStr = FileUtil.getAutoFileOrFilesSize(filePath1);
        Log.d("Log1113", sizeStr);

        // 获取指定文件大小（B）
        if (file2 != null) {
            long sizeFile = FileUtil.getFileSize(file2);
            Log.d("Log1114", sizeFile + "");
        }

        // 获取指定文件夹大小（B）
        long sizeFiles = FileUtil.getFileSizes(new File(filePath1));
        Log.d("Log1115", sizeFiles + "");

        // 转换文件大小（取最大单位）（保留两位小数）
        String fSize = FileUtil.formatFileSize(25012);
        Log.d("Log1116", fSize);

        // 转换文件大小，指定转换的单位（保留两位小数）
        double fSize2 = FileUtil.formatFileSize(25012, FileUtil.SIZETYPE_KB);
        Log.d("Log1117", fSize2 + "");

        // 清除本应用内部缓存
        FileUtil.clearAllCache(this);

        // 获取本应用内部缓存大小（B）
        long totalCacheSize = FileUtil.getTotalCacheSize(this);
        Log.d("Log1118", totalCacheSize + "");

        // 获取本应用内部缓存大小（格式化）
        String formatCacheSize = FileUtil.getFormatTotalCacheSize(this);
        Log.d("Log1119", formatCacheSize);

        // 清除本应用SharedPreference(/data/data/com.xxx.xxx/sharedprefs)
        FileUtil.cleanSharedPreference(this);

        // 按名字清除本应用数据库
        FileUtil.delDatabaseByName(this, "user");

        // 获取文件目录信息
        ArrayList<FileInfoBean> fileInfoBeans = FileUtil.getFileInfos(FileUtil.SDPATH);
        Log.d("Log11110", fileInfoBeans.toString());

        // 打开文件
        FileUtil.openFile(this, file1);
    }
}
```
