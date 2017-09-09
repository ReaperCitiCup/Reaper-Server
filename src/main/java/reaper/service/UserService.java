package reaper.service;

import reaper.model.User;
import reaper.util.ResultMessage;

/**
 * Created by Sorumi on 17/5/12.
 */
public interface UserService {

    /**
     * 登录
     *
     * @param username 用户账号
     * @param password 用户密码
     * @return 当前登录状态
     */
    public ResultMessage signIn(String username, String password);

    /**
     * 登出
     *
     * @return 当前登录状态
     */
    public ResultMessage signOut();

    /**
     * 重置密码
     *
     * @param id          用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 重置密码结果状态
     */
    public ResultMessage resetPassword(int id, String oldPassword, String newPassword);

    /**
     * 增加用户
     *
     * @param username 用户账号
     * @param password 用户密码
     * @return 是否增加成功
     */
    public ResultMessage signUp(String username, String password);
//
//    /**
//     * 根据id查找用户
//     *
//     * @param id 用户id
//     * @return 查到的用户
//     */
//    public User findUserById(int id);

    /**
     * 根据username查找用户
     *
     * @param username
     * @return 查到的用户
     */
    public User findUserByUsername(String username);

    /**
     * 更新用户信息
     *
     * @param user 新用户
     * @return 是否更新成功
     */
    public ResultMessage updateUser(User user);

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 是否删除成功
     */
    public ResultMessage deleteUser(int id);

    /**
     * 获得当前登录的用户
     *
     * @return 当前登录用户
     */
    public User getCurrentUser();

}
