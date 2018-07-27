package cn.qdcares.rpc.web;

import cn.qdcares.rpc.common.web.FunctionUrlMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 权限分配工具类
 * @author dongqun
 * 2018年1月31日下午1:21:25
 */
public class AuthUtil {

	public static final String PACKAGENAME = "cn.qdcares.rpc.controller";
	/**
	 * 初始化各类角色能访问的接口 
	 * 以类名.函数名的形式存储
	 * @author dongqun
	 * 2018年1月31日下午2:01:41
	 * @param packageName
	 * @return
	 */
	public static List<FunctionUrlMap> initAuth(String packageName){
		List<FunctionUrlMap> list = new ArrayList<>();
		try {
			List<String> classes = getClassByPackage(packageName);
			int allid = 1;
			FunctionUrlMap root = new FunctionUrlMap();
			root.setId(0);
			root.setPid(-1);
			root.setOpen(true);
			root.setName("全部功能");
			list.add(root);
			for (int i=0; i <classes.size(); i++){
				String c = classes.get(i);
				String name = packageName+"."+c.substring(0, c.lastIndexOf(".class"));
				Class<?> cls =  Class.forName(name);
				if (!cls.isAnnotationPresent(Controller.class) ) continue;
				RequestMapping urlAnn =  cls.getAnnotation(RequestMapping.class);
				FunctionUrlMap fu = new FunctionUrlMap();
				fu.setId(allid);
				int pid = allid;
				fu.setPid(0);
				fu.setName(cls.getSimpleName());
				String rootPath=null;
				if(urlAnn != null){
					rootPath = urlAnn.value()[0];
					fu.setUrl(urlAnn.value()[0]+"/*");
				}
				fu.setOpen(true);
				list.add(fu);
				allid++;
				Method[] methods = cls.getDeclaredMethods();
				for (Method method : methods) {
					if (!method.isAnnotationPresent(RequestMapping.class)) {
						continue;
					}
					RequestMapping authMethod = method.getAnnotation(RequestMapping.class);
					FunctionUrlMap subfu = new FunctionUrlMap();
					subfu.setId(allid);
					subfu.setPid(pid);
					subfu.setName(method.getName());
					subfu.setUrl(rootPath+authMethod.value()[0]);
					subfu.setOpen(true);
					list.add(subfu);
					allid++;
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private static List<String> getClassByPackage(String packageName){
		List<String> result = new ArrayList<>();
		String pname = packageName.replace(".", "/");
		String path = AuthUtil.class.getClassLoader().getResource(pname).getPath();
		File file = new File(path);
		String[] files = file.list();
		for (String name : files) {
			String subPath = path + "/" + name;
			File subFile = new File(subPath);
			if (subFile.isDirectory()) {
				String[] files1 = subFile.list();
				for (String name1 : files1) {
					String subPath1 = path + "/" + name + "/" +name1;
					File subFile1 = new File(subPath1);
					if (subFile1.getName().endsWith(".class")) {
						String name2 = name + "." + name1;
						result.add(name2);
					}
				}
				
			}else{
				if (subFile.getName().endsWith(".class")) {
					result.add(name);
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		initAuth("cn.qdcares.rpc.controller");
	}

	

}
