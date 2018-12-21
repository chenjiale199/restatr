package cn.zg.zx.controller.cargo.extCproduct;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.zg.zx.controller.BaseController;
import cn.zg.zx.domain.ExtCproduct;
import cn.zg.zx.domain.Contract;
import cn.zg.zx.domain.Factory;
import cn.zg.zx.service.CProductService;
import cn.zg.zx.service.ContractService;
import cn.zg.zx.service.ExtCproductService;
import cn.zg.zx.service.FactoryService;

@Controller
public class ExtCproductController extends BaseController {

	@Autowired
	ExtCproductService extCproductService;
	@Autowired
	FactoryService factoryService;
	
	@RequestMapping("/cargo/extCproduct/delete.action")
	public String delete(String id) {
		extCproductService.delete(id);
		return "redirect:/cargo/extCproduct/tocreate.action";
	}
	
	@RequestMapping("/cargo/extCproduct/toupdate.action")
	public String toupdate(String id,Model model) {
		ExtCproduct extCproduct=extCproductService.get(id);//回显
		List<Factory> factoryList=factoryService.combo();
		model.addAttribute("obj", extCproduct);
		model.addAttribute("factoryList", factoryList);
		//合同编号需要放置更新页面中，以便后面实现货物更新。
		return "/cargo/extCproduct/jExtCproductUpdate.jsp";
	}
	
	@RequestMapping("/cargo/extCproduct/update.action")
	public String update(ExtCproduct extCproduct,HttpServletRequest req) {
		try {
			saveCproduct(extCproduct,req);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/cargo/extCproduct/tocreate.action";
	}
	
	
	@RequestMapping("/cargo/extCproduct/tocreate.action")
	public String tocreate(ExtCproduct extCproduct,Model model) {
		model.addAttribute("contractProductId", extCproduct.getContractProductId());
		model.addAttribute("contractId", extCproduct.getContractId());
		List<ExtCproduct> dataList=extCproductService.find(extCproduct);
		List<Factory> factoryList=factoryService.combo();
		model.addAttribute("dataList",dataList);
		model.addAttribute("factoryList",factoryList);
		return "/cargo/extCproduct/jExtCproductCreate.jsp";
	}
	
	@RequestMapping("/cargo/extCproduct/insert.action")
	public String insert(ExtCproduct extCproduct,HttpServletRequest req) {
		try {
			saveCproduct(extCproduct,req);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		extCproductService.insert(extCproduct);
		return "redirect:/cargo/extCproduct/tocreate.action?contractProductId="+extCproduct.getContractProductId();
	}

	private void saveCproduct(ExtCproduct extCproduct,HttpServletRequest req) throws IllegalStateException, IOException{
		MultipartFile file=null;
		//多部分解析器
		CommonsMultipartResolver cmr=new CommonsMultipartResolver(req.getSession().getServletContext());
		//通过上下文获取表单中的普通文本以及/文件数据
		if(cmr.isMultipart(req)) {//判断是否是多部分请求
			//转换为部分请求对象
			MultipartHttpServletRequest request= cmr.resolveMultipart(req);
			extCproduct.setId(request.getParameter("id"));
			extCproduct.setContractProductId(request.getParameter("contractProductId"));
			extCproduct.setFactoryId(request.getParameter("factoryId"));
			extCproduct.setProductNo(request.getParameter("productNo"));
			extCproduct.setProductName(request.getParameter("productName"));
			extCproduct.setPackingUnit(request.getParameter("packingUnit"));
			String cnumber=request.getParameter("cnumber");
			extCproduct.setCnumber(cnumber==null||cnumber.isEmpty()?0:Integer.valueOf(cnumber));
			String price=request.getParameter("price");
			extCproduct.setPrice(price==null||price.isEmpty()?0:Double.valueOf(price));
			String amount=request.getParameter("amount");
			extCproduct.setAmount(amount==null||amount.isEmpty()?0:Double.valueOf(amount));
			extCproduct.setProductDesc(request.getParameter("productDesc"));
			file=request.getFile("fileImage");
			if(file!=null&&!file.getOriginalFilename().equals("")) {
				//拿项目的绝对路径
				String root=req.getSession().getServletContext().getRealPath("/");
				File dir=new File(root+"/tempImage/");
				if(!dir.exists()) {
					dir.mkdirs();
				}
				File image=new File(dir,file.getName());
				file.transferTo(image);
				extCproduct.setProductImage(file.getName());
			}
			
		}
	}
	
}
