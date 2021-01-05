package web;

import bean.SqMember;
import bean.SqShare;
import biz.UserBiz;
import common.biz.BizException;
import common.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@WebServlet("/user.do")
public class UserServlet extends BaseServlet {
    private final UserBiz biz = new UserBiz();

    /**
     * 注册账号
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SqMember sqMember = new SqMember();
        sqMember.setName(req.getParameter("name"));
        sqMember.setPwd(req.getParameter("pwd"));
        sqMember.setEmail(req.getParameter("email"));
        String vcode1 = req.getParameter("vcode1");
        String vcode2 = String.valueOf(req.getSession().getAttribute("vcode2"));

        if (vcode1.equals(vcode2)) {
            try {
                biz.InsertUser(sqMember);
                write(resp, "注册成功 !");
            } catch (BizException e) {
                write(resp, e.getMessage());
            }
        } else {
            write(resp, "验证码输入错误，请重新输入 !");
        }


    }

    public void setVcode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        int vcode = (int) ((Math.random() * 9 + 1) * 100000);//生成验证码

        System.out.println(vcode);
        HttpSession session = req.getSession();
        session.setAttribute("vcode2", vcode);
        try {
            biz.SendMail(email, name, vcode);
            write(resp, "发送成功");
        } catch (BizException e) {
            e.printStackTrace();
            write(resp, e.getMessage());
        }
        System.out.println(vcode + email);
    }

    /**
     * 登录
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void userLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqMember sqMember = new SqMember();
        sqMember.setName(req.getParameter("name"));
        sqMember.setPwd(req.getParameter("pwd"));
        HttpSession session = req.getSession();
        try {
            biz.loginBiz(sqMember);
            write(resp, "登录成功");
            session.setAttribute("name", sqMember.getName());
        } catch (BizException e) {
            write(resp, e.getMessage());
        }

    }

    /**
     * 查询用户信息
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void queryUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String name = String.valueOf(req.getSession().getAttribute("name"));
        //如果session 为空 转为string 则是“null” 字符串 而不是 null
        System.out.println(name);
        if (name == "null" || name.equals("null")) {
            write(resp, "请先登录 !");
            return;
        }
        write(resp, biz.queryByName(name));
    }

    public void changeUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqMember sqMember = new SqMember();
        String id = req.getParameter("id");

        sqMember.setId(Integer.valueOf(id));
        sqMember.setNickname(req.getParameter("nickname"));
        sqMember.setPhone(req.getParameter("phone"));
        sqMember.setEmail(req.getParameter("email"));
        sqMember.setQq(req.getParameter("qq"));

        try {
            biz.change(sqMember);
            write(resp, "修改成功");
        } catch (BizException e) {
            write(resp, e.getMessage());
        }
    }

    //http://localhost:8080/C91_S2_music_war_exploded/user.do?op=userShare&title=
    public void userShare(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqShare sqShare = new SqShare();
        sqShare.setMember(req.getParameter("member"));
        sqShare.setName(req.getParameter("title"));//标题/歌曲名/专辑名
        sqShare.setSingers(req.getParameter("singer"));//歌手, 群星
        sqShare.setTags(req.getParameter("tags"));//请填写分类标签(如周杰伦,无损,WAV)
        sqShare.setType(req.getParameter("category"));//类型: 单曲,专辑,合集
        sqShare.setSrcType(req.getParameter("clazz"));//资源类型:分轨,全轨
        sqShare.setFormat(req.getParameter("type"));//格式: FLAC,WAV,DSD,APE
        sqShare.setIntro(req.getParameter("des"));//详细介绍
        sqShare.setDownUrl(req.getParameter("links"));//下载地址
        System.out.println(sqShare);
        try {
            biz.addShare(sqShare);
            write(resp, "提交成功 !");
        } catch (BizException e) {
            write(resp, e.getMessage());
        }

    }

    public void queryShare(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = String.valueOf(req.getSession().getAttribute("name"));
        //如果session 为空 转为string 则是“null” 字符串 而不是 null
        if (name == "null" || name.equals("null")) {
            write(resp, "请先登录 !");
            return;
        }
        String page_ = req.getParameter("page");
        String pageNums_ = req.getParameter("pageNums");
        int page = Integer.parseInt(page_), pageNums = Integer.parseInt(pageNums_);
        write(resp, biz.queryShareByName(name, page, pageNums));

    }

    public void querySharePages(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = String.valueOf(req.getSession().getAttribute("name"));
        if (name == "null" || name.equals("null")) {
            write(resp, "请先登录 !");
            return;
        }
        int pages = biz.querySharePages(name);
        write(resp, pages);
    }

    public void deleteShear(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        try {
            biz.deleteShearById(id);
        } catch (BizException e) {
            write(resp, e.getMessage());
        }
    }

    /**
     * 修改头像
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void changeHead(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = String.valueOf(req.getSession().getAttribute("name"));
        if (name == "null" || name.equals("null")) {
            write(resp, "请先登录 !");
            return;
        }
        String head = req.getParameter("head");
        try {
            biz.changeUserHead(head, name);
        } catch (BizException e) {
            write(resp, e.getMessage());
        }
    }


    /**
     * 格式化会员时间
     *
     * @param req  把 Jul 8, 2020 12:00:00 AM 格式的时间转换为 2020-07-08 12:00:00 格式的时间
     * @param resp
     * @throws IOException
     */
    public void timeSF(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String time = req.getParameter("time");
        if(time.equals(null) ||time.equals("undefined")|| time.isEmpty()) {
        	 write(resp, "您没有充值过会员");
        }else {
        	  write(resp, parse_date(time, false));
        }
      
    }

    /**
     * 判断 是否还是会员 0 代表是 1 代表不是
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void isVip(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = String.valueOf(req.getSession().getAttribute("name"));
        if (name == "null" || name.equals("null")) {
            write(resp, "请先登录 !");
            return;
        }
        try {
            biz.isVipBiz(name);

        } catch (BizException e) {
            write(resp, e.getMessage());
        }

    }

    public void vipCZ(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String money = req.getParameter("money");
        String name = String.valueOf(req.getSession().getAttribute("name"));
        if (name == "null" || name.equals("null")) {
            write(resp, "请先登录 !");
            return;
        }
        System.out.println("充值了" + money);
        try {
            biz.changeUserVipDate(money, name);
            write(resp, "充值成功");
        } catch (BizException e) {
            write(resp, e.getMessage());
        }
    }


    /**
     * 把 Jul 8, 2020 12:00:00 AM 格式的时间转换为 2020-07-08 12:00:00 格式的时间
     *
     * @param date Jul 8, 2020 12:00:00 AM 格式的时间
     * @param type true 返回年月日格式  false 返回年月日时分秒
     */
    public static String parse_date(String date, boolean type) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy K:m:s a", Locale.ENGLISH);
        Date d2 = null;
        try {
            //把Jul 8, 2020 12:00:00 AM格式转换为常规的Date格式
            d2 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat;
        if (type) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        } else {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        //再把Date的时间转换为需要的格式
        String format = simpleDateFormat.format(d2);
        return format;
    }

    /**
     * 把 2020-07-08 12:00:00 格式的时间转换为 Jul 8, 2020 12:00:00 AM 格式的时间
     *
     * @param date 年月日时分秒 格式的时间
     */
    public static String format_date(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d2 = null;
        try {
            //把 2020-07-08 12:00:00 格式的时间转换为 常规Date时间
            d2 = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy K:m:s a", Locale.ENGLISH);
        //再把 常规Date时间转换回 Jul 8, 2020 12:00:00 AM 格式的时间
        String format = sdf.format(d2);
        return format;
    }


}
