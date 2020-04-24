package com.bawei.dianshangjin07.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.dianshangjin07.R;
import com.bawei.dianshangjin07.base.BaseActivity;
import com.bawei.dianshangjin07.base.BasePresenter;
import com.bawei.dianshangjin07.bean.DataBean;
import com.bawei.dianshangjin07.contact.IContact;
import com.bawei.dianshangjin07.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录页
 */
public class LoginActivity extends BaseActivity {
    //定义
    @BindView(R.id.edit_phone)
    protected EditText edit_phone;
    @BindView(R.id.edit_pwd)
    protected EditText edit_pwd;
    //方法实现
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
    @Override
    protected BasePresenter initPresenter() {
        return new LoginPresenter(new IContact.IView() {
            @Override
            public void onViewSuccess(Object result,String message) {
                //提示
                Toast.makeText(LoginActivity.this,message,Toast.LENGTH_LONG).show();
                //跳转
                Intent intent = new Intent(LoginActivity.this, SearchActivity.class);
                startActivity(intent);
            }
            @Override
            public void onViewFail(DataBean dataBean) {
                //提示
                Toast.makeText(LoginActivity.this,"错误提示：" + dataBean.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    //发起请求
    @OnClick(R.id.login_do)
    protected void loginDo(){
        //获取文本
        String phone = edit_phone.getText().toString();
        String pwd = edit_pwd.getText().toString();
        //发起请求
        if(TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)){
            Toast.makeText(this,"错误提示：请输入合法的用户名和密码！",Toast.LENGTH_LONG).show();
        } else {
            getBasePresenter().request(phone,pwd);
        }
    }
    @Override
    protected void initView() {
    }
    @Override
    protected void initDestroy() {
    }
}
