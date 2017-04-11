package com.tarena.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.dao.AdminMapper;
import com.tarena.entity.Admin;
import com.tarena.entity.Module;
import com.tarena.util.ImageUtil;


@Controller
@RequestMapping("/login")
public class LoginController  extends BaseController{
	
	
	public static final int erro_logincode=3;
	public static final int  erro_pwd=2;
	public static final int ok=1;
	public static final int error_img=4;
	@Resource
	private AdminMapper adminMapper;
	
	
	
	@RequestMapping("/toLogin.do")
	public String toLogin(){	
		return "main/login";
	}
	
	@RequestMapping("/checkLogin.do")
	@ResponseBody
	public int check(String name,String pwd,String userCode,
						HttpSession session){
		//У����֤��
		String imageCode=(String)session.getAttribute("imageCode");
		
		if(userCode==null ||
			!userCode.equalsIgnoreCase(imageCode)){
			//��֤�����
			return error_img;
		}
	
		Admin a=adminMapper.findByName(name);
		
		if(a==null){
			return erro_logincode ;
		
		}else if(!a.getPassword().equals(pwd)){
			return erro_pwd;
		}else{
			
			session.setAttribute("admin",a);
			List<Module> list=adminMapper.findByAdminId(a.getAdmin_id());
			//System.out.println(1111111111);
			session.setAttribute("adminModules", list);
			
			return ok;
		}
		
		
	}
	//��ҳ
	@RequestMapping("/toIndex.do")
	public String toIndex(){
	
		return "main/index";
	}
	
	@RequestMapping("/createImage.do")
	public void createImage(HttpSession session,
				HttpServletResponse response) throws IOException{
		//������֤��ͼƬ
		Map<String,BufferedImage> map=ImageUtil.createImage();
		
	
		String code=map.keySet().iterator().next();
		//����֤�����session�������¼ʱУ��
		session.setAttribute("imageCode",code);
		//��ͼƬ���ҳ��
		BufferedImage image=map.get(code);
		
		
		response.setContentType("image/jpeg");
		OutputStream os=response.getOutputStream();
		ImageIO.write(image, "jpeg", os);
		os.close();
		
	}
	
	@RequestMapping("/nopower.do")
	public String noPower(){
	return "main/nopower";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
