package com.springboot06shiro.config;

import com.springboot06shiro.mapper.UserMapper;
import com.springboot06shiro.pojo.User;
import com.springboot06shiro.service.UserSevice;
import jdk.nashorn.internal.codegen.SpillObjectCreator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义的用户Realm
public class UserRealm extends AuthorizingRealm {



    @Autowired
    public UserSevice userSevice;


//    注意两个方法是不同的  是有区别的 只是单词长得像而已

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");

        //前面设置了拦截的资源权限 现在我们就来告诉shiro怎么样算是有这user:add权限的  比如我从数据库中的每个用户的判断roles这个字段是否还有user:add这个字符串的值
        //如果有就是当做该用户拥有这个角色权限 此时我们就给与这个用户该user:add权限
        //注意类的名字 是SimpleAuthorizationInfo
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加权限  这里我们判断一下这个用户
        //先拿到当前的登录对象
        Subject subject = SecurityUtils.getSubject();
        //因为我们在下面的new SimpleAuthenticationInfo 传入了一个user 在这里就接收就行了
        User user = (User) subject.getPrincipal();
        if (user!=null){
            //判断用户是否拥有这个权限 有就添加上这个权限 没有就没有  只有这样  才能有什么权限就做什么事
            //由于用户的权限可能不只是一个  所以我们默认存储在数据库是这样存储的 user:add,user:update 那我们就需要对着权限进行拆分
            String[] split = user.getRoles().split(",");
            for (String s : split) {
                if ("user:add".equals(s)){
                    info.addStringPermission("user:add");
                }
                if ("user:update".equals(s)){
                    info.addStringPermission("user:update");
                }
            }

        }



        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {


        System.out.println("认证");

//
//        //假设数据库中的登录的密码是如下的root 和123456
//        String username = "root";
//        String password = "123456";
//
//
        //拿到全局对象authenticationToken  其实就是令牌 将它装换成身份认证令牌 拿到我们刚刚得到的用户名和密码制作的令牌用来和我们写死的数据库进行比对错误拿到相应提示 正确就直接访问index了
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
//        if (!token.getUsername().equals(username)){
//            //会抛出异常
//            return null;
//        }

        User user = userSevice.queryUserByName(token.getUsername());

        if (null==user){
            return null;
        }
        //要选择这个对象 上面那个是一个接口 选择这个实现类返回  其中第一个参数是选择是否将第一个当前的用户作为待会儿授权是够能够得到的信息  这里传了一个user待会儿我们去
        //授权界面直接通过当前用户拿到就行了
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}