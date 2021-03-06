package cn.qdcares.rpc.common.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Description: 基类控制器
 * @author dongqun
 * @date  2018/6/2 10:39
 * @version 1.0
 */
public abstract class BaseController {
	// 打印log
	protected static Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	//private static final JsonConfig JSON_CONFIG = new JsonConfig();
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/*static{
		JSON_CONFIG.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor(DATE_FORMAT));
		JSON_CONFIG.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor(DATE_FORMAT));
	}*/
	protected static String article_template_path = "template/cmstemplate/article.html";
	protected static String commentlist_template_path = "template/cmstemplate/comment_list.html";
	
	//@Autowired
	//protected SendMail sendMail;
	
	//@Autowired
	//protected UserInfoService userInfoService;
	
	@Autowired
	private HttpServletRequest request;
	
	//@Autowired
	//protected CommonService commonService;

	// 文件服务相关注册 
	//@Autowired
	//protected FileAppService fileAppService;
	
	//@Autowired
	//protected BaseFileService baseFileService;
	
	//@Autowired
	//protected FileService fileService;
	
	 /** 配置文件名称，早config.properties文件中定义，区分不同打包指令后各环境的配置文件内容    *//*
	@Value("${project.environment}")//表示运行环境 dev test pro
	protected String environment;*/

	/**
	 * form表单后台验证
	 * @param request
	 * @param response
	 * @param dto
	 * @return dto
	 * @throws Exception
	 * @date 2016年12月14日 下午11:09:34
	 * @writer junehappylove
	 *//*
	@ModelAttribute
	protected Dto validateForm(HttpServletRequest request, HttpServletResponse response,Dto dto)
			throws Exception {
		// 将参数映射到对应的业务dto中并返回
		fillRequestDto(request, dto);
		return dto;
	}*/
		
	/**
	 * 
	 * @return
	 * @date 2016年12月14日 下午11:38:48
	 * @writer junehappylove
	 */
	protected String getProjectName() {
		String hostname = request.getContextPath();
		return hostname;
	}

	protected String getSessionId() {
		return request.getSession().getId();
	}

	/**
	 * 将前台传过来的map类型的参数映射到dto中
	 * 
	 * @param request
	 * @param dest
	 *//*
	protected void fillRequestDto(HttpServletRequest request, BaseDTO dest) {
		// 注册Date类型
		ConvertUtils.register(new DateConverter(), Date.class);
		// 注册Timestamp类型
		ConvertUtils.register(new TimestampConverter(), Timestamp.class);
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "");
		try {
			BeanUtils.populate(dest, map);
			// PropertyUtilsBean.copyProperties(dest, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}*/
	
	/**
	 * 将dto转换成map形式
	 * 
	 * @author caiyang
	 * @param bean
	 * @return Map <String,Object>
	 */
//	protected void beantoMap(Map<String, Object> returnMap, T bean) {
//		BeanInfo beanInfo = null;
//		try {
//			beanInfo = Introspector.getBeanInfo(bean.getClass());
//		} catch (IntrospectionException e) {
//			e.printStackTrace();
//		}
//		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
//		for (int i = 0; i < propertyDescriptors.length; i++) {
//			PropertyDescriptor descriptor = propertyDescriptors[i];
//			String propertyName = descriptor.getName();
//			if (!propertyName.equals("class")) {
//				Method readMethod = descriptor.getReadMethod();
//				Object result = null;
//				try {
//					result = readMethod.invoke(bean, new Object[0]);
//				} catch (IllegalAccessException e) {
//					e.printStackTrace();
//				} catch (IllegalArgumentException e) {
//					e.printStackTrace();
//				} catch (InvocationTargetException e) {
//					e.printStackTrace();
//				}
//				if (result != null) {
//					returnMap.put(propertyName, result);
//				} else {
//					returnMap.put(propertyName, "");
//				}
//			}
//		}
//	}



	/**
	 * 将图片返回到页面显示
	 * 
	 * @param response
	 * @param filePath
	 * @date 2015年12月16日 上午10:50:51
	 * @writer wjw.happy.love@163.com
	 */
	protected void returnImage(HttpServletResponse response, String filePath) {
		FileInputStream fis = null;
		response.setContentType("image/gif");
		try {
			OutputStream out = response.getOutputStream();
			File file = new File(filePath);
			fis = new FileInputStream(file);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			out.write(b);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}





	/**
	 * 共同的异常处理
	 * 
	 * @param request
	 * @param response
	 * @param e
	 * @return
	 * @date 2015年12月16日 上午10:50:26
	 * @writer wjw.happy.love@163.com
	 */
	/*@ExceptionHandler
	public ModelAndView exception(HttpServletRequest request, HttpServletResponse response, Exception e)
			throws Exception {
		MessageDto messageDto = new MessageDto();
		// 判断是否是ajax请求
		if (isAjaxCall(request)) {
			// String url = request.getRequestURL().toString();
			// String urls[] = url.split("/");
			// String lastUrl = urls[urls.length - 1];
			if (e.getClass().equals(CustomException.class)) {
				String message = e.getMessage();
				String[] messages = message.split(";");
				ArrayList<String> errList = new ArrayList<String>();
				for (int i = 0; i < messages.length; i++) {
					// 添加自己的异常处理逻辑，如日志记录
					logger.error("Exception:" + e + ":" + messages[i]);
					errList.add(messages[i]);
					messageDto.setErrList(errList);
					messageDto.setErrType("error");
				}
			} else {
				// 添加自己的异常处理逻辑，如日志记录
				ArrayList<String> errList = new ArrayList<String>();
				try {
					logger.error("Exception:" + e + ":" + e.getMessage());
					errList.add(Constants.SYS_ERROR);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				messageDto.setErrList(errList);
				messageDto.setErrType("error");
			}
			toJson(messageDto, response);
			// 页面不跳转
			return null;
		} else {
			if (e instanceof FastDFSException) {
				ArrayList<String> errList = new ArrayList<String>();
				logger.error("Exception:" + e + ":" + e.getMessage());
				errList.add(Constants.NO_AVALIABLE_TRACKER);
				messageDto.setErrList(errList);
				messageDto.setErrType("error");
				toJson(messageDto, response);
				// 页面不跳转
				return null;
			} else {
				ModelAndView result = new ModelAndView("error/error");
				logger.error("Exception:" + e + ":" + e.getMessage());
				// result.addObject("error", e.getMessage());
				return result;
			}
		}
	}*/

	/**
	 * 判断是否是ajax请求
	 * 
	 * @param request
	 * @return
	 * @date 2015年12月16日 上午10:50:14
	 * @writer wjw.happy.love@163.com
	 */
	protected boolean isAjaxCall(HttpServletRequest request) {
		return ("XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
	}

	/**
	 * 根据字节流返回图片
	 * @param response
	 * @param b
	 * @date 2016年12月14日 下午9:31:42
	 * @writer junehappylove
	 */
	protected void returnImageByBuffer(HttpServletResponse response, byte[] b) {
		response.setContentType("image/gif");
		try {
			OutputStream out = response.getOutputStream();
			out.write(b);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 日期类型绑定处理 
	 * @param binder
	 * @date 2016年12月14日 下午9:31:51
	 * @writer junehappylove
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Timestamp.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * 获取validate错误消息
	 * 
	 * @throws Exception
	 * @author caiyang
	 * @param bindingResult
	 */
	/*protected MessageDto getValidateError(BindingResult bindingResult) throws Exception {
		MessageDto messageDto = new MessageDto();
		ArrayList<String> errList = new ArrayList<String>();
		List<String> stooges = null;
		if (bindingResult.hasErrors()) {
			List<ObjectError> messageList = bindingResult.getAllErrors();
			for (ObjectError list : messageList) {
				if (list.getDefaultMessage().contains(";")) {
					String[] messageArray = list.getDefaultMessage().split(";");
					String message = messageArray[0];
					stooges = Arrays.asList(messageArray);
					String[] params = null;
					if(stooges.size()>1){
						stooges.remove(0);
						params = (String[]) stooges.toArray();
					}
					stooges.clear();
					errList.add(Message.$VALUE(message, params));
				} else {
					errList.add(list.getDefaultMessage());
				}
			}
			messageDto.setErrList(errList);
			messageDto.setErrType(MESSAGE_ERRO);
		}
		return messageDto;
	}*/

	/**
	 * 获取当前登录的用户
	 * 
	 * @param request
	 * @return
	 */
	/*protected UserInfoDto loginUser(HttpServletRequest request) {
		UserInfoDto userInfoDto = (UserInfoDto) request.getSession().getAttribute("userInfo");
		return userInfoDto;
	}*/

	/**
	 * 获取当前登录的用户
	 * 
	 * @return UserInfoDto
	 */
	/*protected UserInfoDto loginUser() {
		UserInfoDto userInfoDto = (UserInfoDto) request.getSession().getAttribute("userInfo");
		return userInfoDto;
	}*/

	/**
	 * 设置当前操作人
	 *
	 * @param bean
	 * @param request
	 */
	/*protected void setCreater(Dto bean, HttpServletRequest request) {
		UserInfoDto userInfoDto = this.loginUser(request);
		String userId = userInfoDto.getUserId();
		bean.setAddUserId(userId);// 设置操作人id
		bean.setUpdateUserId(userId);// 设置操作人id
		Timestamp time = new Timestamp(System.currentTimeMillis());
		if (bean.getAddTime() == null) {
			bean.setAddTime(time);// 设置时间
		}
		if (bean.getUpdateTime() == null) {
			bean.setUpdateTime(time);// 设置时间
		}
	}*/





	/**
	 * 新页面初始化数据
	 * 
	 * @param page
	 * @param request
	 */
	/*protected void htmlPageInit(ModelAndView page, HttpServletRequest request) {
		UserInfoDto user = this.loginUser(request);
		String userId = user.getUserId();
		String username = user.getUserName();
		page.addObject("addUserName", username);
		page.addObject("updateUserName", username);
		page.addObject("addUserId", userId);
		page.addObject("updateUserId", userId);
		page.addObject("addTime", DateUtil.getTimeOfNow("yyyy-MM-dd HH:mm:ss"));
		page.addObject("updateTime", DateUtil.getTimeOfNow("yyyy-MM-dd HH:mm:ss"));
	}*/

	/**
	 * 处理编辑页面初始化
	 * 
	 * @param page
	 * @param bean
	 */
	/*protected void htmlEditInit(ModelAndView page, Dto bean) {
		UserInfoDto user = userInfoService.getDtoById(new UserInfoDto(bean.getUpdateUserId()));
		page.addObject("updateUserName", user != null ? user.getUserName() : "未知用户");
		// XXX 约定:这里默认使用bean存放，或者开发人员在自己业务里自定义
		Timestamp time = new Timestamp(System.currentTimeMillis());
		if (bean.getUpdateTime() == null) {
			bean.setUpdateTime(time);
		}
		if (bean.getAddTime() == null) {
			bean.setAddTime(time);
		}
		page.addObject("bean", bean);//
	}*/

	/**
	 * 设置其他信息到前台页面中
	 * 
	 * @param page
	 * @param bean
	 * @param name
	 * @date 2015年12月16日 下午3:02:39
	 * @writer wjw.happy.love@163.com
	 */
	protected void setOtherInfo(ModelAndView page, Object bean, String name) {
		page.addObject(name, bean);//
	}

	/**
	 * 保存成功消息
	 * 
	 * @param response
	 * @throws Exception
	 * @datetime 2015年12月8日 下午1:08:32
	 */
	/*protected void messageSaveSuccess(HttpServletResponse response) throws Exception {
		String messages = "save_success";
		String type = MESSAGE_INFO;
		message(response,messages, type);
	}*/

	/**
	 * 发送成功消息
	 * 
	 * @param response
	 * @throws Exception
	 * @datetime 2015年12月8日 下午1:08:45
	 */
	/*protected void messageSendSuccess(HttpServletResponse response) throws Exception {
		String messages = "send_success";
		String type = MESSAGE_INFO;
		message(response,messages, type);
	}*/

	/**
	 * 删除成功消息
	 * 
	 * @param response
	 * @throws Exception
	 * @datetime 2015年12月8日 下午1:09:00
	 */
	/*protected void messageDeleteSuccess(HttpServletResponse response) throws Exception {
		String messages = "delete_success";
		String type = MESSAGE_INFO;
		message(response,messages, type);
	}*/

	/**
	 * 更新数据成功消息
	 * 
	 * @param response
	 * @throws Exception
	 * @date 2015年12月30日 下午2:09:37
	 * @writer wjw
	 */
	/*protected void messageUpdateSuccess(HttpServletResponse response) throws Exception {
		String messages = "update_success";
		String type = MESSAGE_INFO;
		message(response,messages, type);
	}*/
	





	/**
	 * 初始化页面
	 * @param request http请求
	 * @param page jsp页面
	 * @return 视图
	 */
	/*protected ModelAndView initPage(HttpServletRequest request, String page) {
		ModelAndView result = new ModelAndView(page);
		// 获取用户信息
		UserInfoDto userInfoDto = loginUser(request);
		ButtonDto buttonDto = new ButtonDto();
		if (userInfoDto != null) {
			buttonDto.setRoleId(userInfoDto.getRoleId());
			buttonDto.setMenuUrl(request.getServletPath());
			// 根据故障代码角色和初始化的页面获取该页面有权限的操作
			List<ButtonDto> list = commonService.getFunctionByRole(buttonDto);
			for (int i = 0; i < list.size(); i++) {
				result.addObject(list.get(i).getButtonPageId(), "hasAuthority");
			}
		}
		return result;
	}*/

	/*protected void initDto(AbstractDTO dto) {
		dto.setAddTime(new Timestamp(System.currentTimeMillis()));
		dto.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		dto.setAddUserId(this.loginUser().getUserId());
		dto.setUpdateUserId(this.loginUser().getUserId());
	}*/

	/**
	 * 判断是否包含某格式
	 * 
	 * @param type
	 * @return
	 * @date 2016年6月28日 下午2:23:13
	 * @writer wjw.happy.love@163.com
	 */
	protected boolean can(String type) {
		String[] types = { ".jpg", ".gif", ".png", ".bmp", ".mp4", ".swf" };
		type = type.toLowerCase();
		boolean result = false;
		for (String s : types) {
			if (type.contains(s)) {
				result = true;
				break;
			} else {
				continue;
			}
		}
		return result;
	}

	/**
	 * 下载文件
	 * 
	 * @param filePath
	 * @param response
	 * @throws IOException
	 * @date 2016年6月29日 下午6:50:54
	 * @writer wjw.happy.love@163.com
	 */
	protected void download(String filePath, HttpServletResponse response) throws IOException {
		OutputStream out = null;
		InputStream in = null;
		try {
			File file = new File(filePath);
			if (file != null) {
				response.setContentType("application/octet-stream; charset=utf-8");
				response.addHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("gbk"), "iso-8859-1"));
				response.addHeader("Content-Length", "" + file.length());
				ServletOutputStream outputStream = response.getOutputStream();
				// 以流的形式下载文件
				in = new FileInputStream(file);
				out = outputStream;
				byte[] b = new byte[1024];
				int i = 0;
				while ((i = in.read(b)) > 0) {
					out.write(b, 0, i);
				}
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			in.close();
			out.close();
		} // */
	}
	
	/**
	 * 根据业务的id获取此业务对应文件的远程访问地址
	 * @param appid
	 * @param defaultReturn 如果找不到，返回的默认地址
	 * @return
	 * @date 2016年12月20日 下午7:58:52
	 * @writer junehappylove
	 */
	/*protected String getRemoteFilePath(String appid, String defaultReturn) {
		String remote_path = null;
		try{
			if (StringUtils.isNotEmpty(appid)) {
				String md5_code = fileService.getDtoById(new FileDTO(appid)).getFile_md5();
				BaseFile temp = baseFileService.getDtoById(new BaseFile(md5_code));
				if (temp != null) {
					remote_path = temp.getFile_loc() + temp.getFile_name() + temp.getFile_type();
					logger.debug("待查找的用户头像:"+remote_path);
					//判断是否确实存在
					String path = getRealPath()+remote_path.replace("/", "\\");
					File file = new File(path);
					if(!file.exists()){
						remote_path = null;
					}
				}
			}
		}catch (Exception e){
			logger.debug("error in find user head image!");
			remote_path = defaultReturn;
		}
		
		return remote_path == null ? defaultReturn : remote_path;
	}*/
	
	/**
	 * 得到项目的实际物理路径
	 * @return
	 * @date 2016年12月20日 下午8:33:07
	 * @writer junehappylove
	 */
	protected String getRealPath(){
		return request.getSession().getServletContext().getRealPath("/");
	}
	
	/**
	 * 得到项目的实际物理路径
	 * @param request
	 * @return
	 * @date 2016年12月20日 下午8:34:01
	 * @writer junehappylove
	 */
	protected String getRealPath(HttpServletRequest request){
		return request.getSession().getServletContext().getRealPath("/");
	}

	/**
	 * 整理一个目录地址形式<br>
	 * 标准形式格式：/xxx/yyyy/zzzz<br>
	 * 开头/ , 最后没有/
	 * @param ftpPath
	 * @return
	 * @date 2016年7月6日 下午3:33:28
	 * @writer wjw.happy.love@163.com
	 */
	/*protected String $dir(String ftpPath) {
		if(StringUtils.isNotEmpty(ftpPath)){
			ftpPath = ftpPath.replaceAll("//", "/");
			if(ftpPath.charAt(0) != '/'){
				ftpPath = "/"+ftpPath;
			}
			if(ftpPath.charAt(ftpPath.length()-1)=='/'){
				ftpPath = ftpPath.substring(0,ftpPath.length()-1);
			}
			return ftpPath;
		}else
			return "";
	}*/

	/*public void filluser(AbstractDTO dto) {
		dto.setSys_user(this.loginUser().getUserId());
	}*/
		
}
