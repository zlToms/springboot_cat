package com.tang.zhen.film.comtroller.user.vo;

import com.tang.zhen.film.common.utils.ToolUtils;
import com.tang.zhen.film.comtroller.common.BaseVO;
import com.tang.zhen.film.comtroller.exception.ParamErrorException;
import lombok.Data;

@Data
public class EnrollUserVO  extends BaseVO {

    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;

    @Override
    public void checkParam() throws ParamErrorException {
        if(ToolUtils.isEmpty(this.getUsername())){
            throw new ParamErrorException(400,"用户名不能为空");
        }
        if(ToolUtils.isEmpty(this.getPassword())){
            throw new ParamErrorException(400,"密码不能为空");
        }
        //用户名不能超过20位，且格式。。。。

    }
}
