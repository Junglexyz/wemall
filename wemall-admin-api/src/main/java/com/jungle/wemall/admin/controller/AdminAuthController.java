package com.jungle.wemall.admin.controller;

import com.jungle.wemall.admin.util.Permission;
import com.jungle.wemall.admin.util.PermissionUtil;
import com.jungle.wemall.admin.util.ResponseUtil;
import com.jungle.wemall.db.pojo.WemallAdminUser;
import com.jungle.wemall.db.service.WemallAdminUserService;
import com.jungle.wemall.db.service.WemallPermissionService;
import com.jungle.wemall.db.service.WemallRoleService;
import com.jungle.wemall.db.util.FastJsonUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.jungle.wemall.admin.util.AdminResponseCode.ADMIN_INVALID_ACCOUNT;

/**
 * @description: 后台权限管理
 * @author: jungle
 * @date: 2020-02-27 12:07
 */
@RestController
@RequestMapping("/admin/auth")
public class AdminAuthController {
    @Autowired
    private WemallAdminUserService wemallAdminUserService;
    @Autowired
    private WemallRoleService wemallRoleService;
    @Autowired
    private WemallPermissionService wemallPermissionService;

    @PostMapping("/login")
    public Object loginByUserName(@RequestBody String body, HttpServletRequest request){
        System.out.println(body);
        String username = FastJsonUtil.getString(body, "username");
        String password = FastJsonUtil.getString(body, "password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResponseUtil.badArgument();
        }

        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(new UsernamePasswordToken(username, password));
        } catch (UnknownAccountException uae) {
            return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "用户帐号或密码不正确");
        } catch (LockedAccountException lae) {
            return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "用户帐号已锁定不可用");

        } catch (AuthenticationException ae) {
            return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "认证失败");
        }

        currentUser = SecurityUtils.getSubject();
        WemallAdminUser admin = (WemallAdminUser) currentUser.getPrincipal();
//        admin.setLastLoginIp(IpUtil.getIpAddr(request));
//        admin.setLastLoginTime(new Date());
        wemallAdminUserService.updateById(admin);

//        logHelper.logAuthSucceed("登录");

        // userInfo
        Map<String, Object> adminInfo = new HashMap<String, Object>(4);
        adminInfo.put("nickName", admin.getUsername());
        adminInfo.put("avatar", admin.getAvatar());
        System.out.println(admin.getRoleIds());
        Map<Object, Object> result = new HashMap<Object, Object>(4);
        result.put("token", currentUser.getSession().getId());
        result.put("adminInfo", adminInfo);
        return ResponseUtil.ok(result);
        /*MsrAdmin loginResult = msrAdminService.loginByUserName(username, password);
        Map<String, Object> result = new HashMap<>(4);
        if(loginResult != null){
            result.put("token", "222");
            return ResponseUtil.ok(result);
        }
        return ResponseUtil.fail();*/
    }

    @GetMapping("/info")
    public Object info() {
        Subject currentUser = SecurityUtils.getSubject();
        WemallAdminUser admin = (WemallAdminUser) currentUser.getPrincipal();

        Map<String, Object> data = new HashMap<>(4);
        data.put("name", admin.getUsername());
        data.put("avatar", admin.getAvatar());
        System.out.println("info:"+admin.getRoleIds());
//        Integer[] roleIds = admin.getRoleIds();

        String roleIds = admin.getRoleIds();
        // 去空格
        String str = roleIds.replaceAll(" +","");
        String strSub = str.substring(1,str.length()-1);
        String[] strArray = strSub.split(",");
        List<String> rowIdList = Arrays.asList(strArray);
        System.out.println(rowIdList);
        Set<String> roles = wemallRoleService.queryByIds(rowIdList);
        System.out.println(roles);
        Set<String> permissions = wemallPermissionService.queryByRoleIds(rowIdList);
        System.out.println(permissions);
        data.put("roles", roles);
        // NOTE
        // 这里需要转换perms结构，因为对于前端而已API形式的权限更容易理解
        data.put("perms", toApi(permissions));
        System.out.println(data);
        return ResponseUtil.ok(data);
    }
    /**
     * 登录
     */
    /*@PostMapping("/login")
    public Object upload(@RequestBody String body)  {
        String account = FastJsonUtil.getString(body, "account");
        String password = FastJsonUtil.getString(body,"password");
        WemallAdminUser wemallAdminUser = wemallAdminUserService.login(account, password);
        if(null != wemallAdminUser){
            Map<String, Object> data = new HashMap<>(4);
            data.put("token", UserTokenManager.generateToken(account));
            data.put("account", account);
            return ResponseUtil.ok(data);
        }
        if(null == wemallAdminUser){
            return ResponseUtil.fail(1001,"账号或密码错误！");
        }
        return ResponseUtil.fail();
    }*/

    @Autowired
    private ApplicationContext context;
    private HashMap<String, String> systemPermissionsMap = null;

    private Collection<String> toApi(Set<String> permissions) {
        if (systemPermissionsMap == null) {
            systemPermissionsMap = new HashMap<>(4);
            final String basicPackage = "org.jungle.wemall.admin";
            List<Permission> systemPermissions = PermissionUtil.listPermission(context, basicPackage);
            for (Permission permission : systemPermissions) {
                String perm = permission.getRequiresPermissions().value()[0];
                String api = permission.getApi();
                systemPermissionsMap.put(perm, api);
            }
        }

        Collection<String> apis = new HashSet<>();
        for (String perm : permissions) {
            String api = systemPermissionsMap.get(perm);
            apis.add(api);

            if (perm.equals("*")) {
                apis.clear();
                apis.add("*");
                return apis;
            }
        }
        return apis;
    }

    @GetMapping("/401")
    public Object page401() {
        return ResponseUtil.unlogin();
    }

    @GetMapping("/index")
    public Object pageIndex() {
        return ResponseUtil.ok();
    }

    @GetMapping("/403")
    public Object page403() {
        return ResponseUtil.unauthz();
    }
}
