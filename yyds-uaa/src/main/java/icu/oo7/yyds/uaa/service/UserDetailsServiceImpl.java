//package icu.oo7.yyds.uaa.service;
//
//import icu.oo7.yyds.common.dto.UserDetailsDTO;
//import icu.oo7.yyds.common.restful.ResponseCode;
//import icu.oo7.yyds.common.restful.ResponseData;
//import icu.oo7.yyds.uaa.feign.RbacFeignService;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
///**
// * 用户认证与授权
// * @author peng.zhao
// */
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final RbacFeignService rbacFeignService;
//
//    public UserDetailsServiceImpl(RbacFeignService rbacFeignService) {
//        this.rbacFeignService = rbacFeignService;
//    }
//
//    // TODO 用户信息处理
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        ResponseData<UserDetailsDTO> response = rbacFeignService.getUserByUsername(username);
//        if (response.getStatus() == ResponseCode.RC200.getCode()) {
//            UserDetailsDTO userDetailsDTO = response.getData();
//
//        }
//        return null;
//    }
//}
