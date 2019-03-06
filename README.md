# DBFrame
项目中偶尔会使用到 `SQLite` 数据库，没有过多的数据库操作因此封装了这个demo，支持简单的增删改查，如果数据库使用的比较频繁建议还是使用网络上的框架；

### 效果图
![insert](https://github.com/hewenyuAndroid/DBFrame/blob/master/screen/insert.gif?raw=true)
![delete](https://github.com/hewenyuAndroid/DBFrame/blob/master/screen/delete.gif?raw=true)
![update](https://github.com/hewenyuAndroid/DBFrame/blob/master/screen/update.gif?raw=true)

### 使用说明
1. 初始化 db 文件，初始化的时候可以指定 db 文件的路径，名称，如果不指定路径和名称，则默认使用内部缓存目录和项目名称，**如果使用的是外部存储路径，需要申请SD卡读写权限(可以使用: [PermissionHelper](https://github.com/hewenyuAndroid/PermissionUtil))**
```Java
DaoSupportFactory.getInstance()
                         .init(mContext,
                                // 指定 db 文件储存的路径，这里缓存在 SD 中
                                 Environment.getExternalStorageDirectory().getAbsolutePath(),
                                 // 设置 db 文件的名称，一个用户名对应一个db名称
                                 "admin");
```
上面的初始化将 db 文件缓存到了 SD 卡中，如下图所示:
![db](https://github.com/hewenyuAndroid/DBFrame/blob/master/screen/db.jpg?raw=true)

2. 在Bean类中用注解标记表名和需要缓存的字段； 
```Java
// 通过反射解析表名，也可以在注解中指定
@SqlTable
public class UserBean {
    // 通过反射解析字段名称和类型，也可以在注解中指定
    @SqlField
    private String userName;
    @SqlField
    private int age;
    @SqlField
    private double salary;
}
```
3. 获取指定类型的 Dao 对象，这里只需要在泛型中指定类型即可；
```Java
mDaoSupport = (DaoSupport<UserBean>) DaoSupportFactory.getInstance().getDao(UserBean.class);
```
4. 调用对应的增删改查方法，参考: [MainActivity](https://github.com/hewenyuAndroid/DBFrame/blob/master/app/src/main/java/com/hwy/dbframe/MainActivity.java)
