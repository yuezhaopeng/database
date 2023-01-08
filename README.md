---
typora-copy-images-to: ./
---

# database

- main直接调用登陆函数，登录进去输入用户名，密码，跳转到一开始分组的四个模块界面，然后调各自的service
- Service就放各自的业务逻辑
- Entity放实体类
- Dao放接口以及对应实现类
- Utils配置类   工厂放里面
- 尽量勾选上自动导入，DAO的实现类名与自己不太相同的时候，先修改拉下来的DAO接口名称为自己的，然后再重构为原来的名称，可以直接改掉所有的接口类

![image-20230108155820059](D:\code\GitHub\database\image-20230108155820059.png)

两个jar包都在lib文件夹
