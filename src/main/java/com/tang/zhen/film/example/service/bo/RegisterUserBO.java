package com.tang.zhen.film.example.service.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor //无参构造函数
@AllArgsConstructor //有参且全部参数的构造方法
@Data
public class RegisterUserBO {
    private  String uuid ;
    private String userName;
    private String userPwd;

}
