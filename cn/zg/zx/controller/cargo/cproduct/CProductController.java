package cn.zg.zx.controller.cargo.cproduct;

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
import cn.zg.zx.domain.CProduct;
import cn.zg.zx.domain.Contract;
import cn.zg.zx.domain.Factory;
import cn.zg.zx.service.CProductService;
import cn.zg.zx.service.ContractService;
import cn.zg.zx.service.FactoryService;

@Controller
public class CProductController extends BaseController {

	@Autowired
	CProductService cproductService;
	@Autowired
	FactoryService factoryService;
	
	@RequestMapping("/cargo/cproduct/delete.action")
	public String delete(String id) {
		cproductService.delete(id);
		return "redirect:/cargo/cproduct/tocreate.action";
	}
	
	@RequestMapping("/cargo/cproduct/toupdate.action")
	public String toupdate(String id,Model model) {
		CProduct cproduct=cproductService.get(id);//回显
		List<Factory> factoryList=factoryService.combo();
		model.addAttribute("obj", cproduct);
		model.addAttribute("factoryList", factoryList);
		//合同编号需要放置更新页面中，以便后面实现货物更新。
		return "/cargo/cproduct/jCProductUpdate.jsp";
	}
	
	@RequestMapping("/cargo/cproduct/update.action")
	public String update(CProduct cproduct,HttpServletRequest req) {
		try {
			saveCproduct(cproduct,req);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/cargo/cproduct/tocreate.action";
	}
	
	
	@RequestMapping("/cargo/cproduct/tocreate.action")
	public String tocreate(CProduct cproduct,Model model) {
		model.addAttribute("contractId", cproduct.getContractId());
		List<CProduct> dataList=cproductService.find(cproduct);
		List<Factory> factoryList=factoryService.combo();
		model.addAttribute("dataList",dataList);
		model.addAttribute("factoryList",factoryList);
		return "/cargo/cproduct/jCProductCreate.jsp";
	}
	
	@RequestMapping("/cargo/cproduct/insert.action")
	public String insert(CProduct cproduct,HttpServletRequest req) {
		try {
			saveCproduct(cproduct,req);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		cproductService.insert(cproduct);
		return "redirect:/cargo/cproduct/tocreate.action?contractId="+cproduct.getContractId();
	}

	private void saveCproduct(CProduct cproduct,HttpServletRequest req) throws IllegalStateException, IOException{
		MultipartFile file=null;
		//多部分解析器
		CommonsMultipartResolver cmr=new CommonsMultipartResolver(req.getSession().getServletContext());
		//通过上下文获取表单中的普通文本以及/文件数据
		if(cmr.isMultipart(req)) {//判断是否是多部分请求
			//转换为部分请求对象
			MultipartHttpServletRequest request= cmr.resolveMultipart(req);
			cproduct.setId(request.getParameter("id"));
			cproduct.setContractId(request.getParameter("contractId"));
			cproduct.setFactoryId(request.getParameter("factoryId"));
			cproduct.setProductNo(request.getParameter("productNo"));
			cproduct.setProductName(request.getParameter("productName"));
			cproduct.setPackingUnit(request.getParameter("packingUnit"));
			String cnumber=request.getParameter("cnumber");
			cproduct.setCnumber(cnumber==null||cnumber.isEmpty()?0:Integer.valueOf(cnumber));
			String price=request.getParameter("price");
			cproduct.setPrice(price==null||price.isEmpty()?0:Double.valueOf(price));
			String amount=request.getParameter("amount");
			cproduct.setAmount(amount==null||amount.isEmpty()?0:Double.valueOf(amount));
			cproduct.setProductDesc(request.getParameter("productDesc"));
			file=request.getFile("fileImage");
			String name=file.getOriginalFilename();
			if(file!=null&&!name.equals("")) {
				//拿项目的绝对路径
				String root=req.getSession().getServletContext().getRealPath("/");
				File dir=new File(root+"/tempImage/");
				if(!dir.exists()) {
					dir.mkdirs();
				}
				File image=new File(dir,name);
				file.transferTo(image);
				cproduct.setProductImage(name);
			}
			
		}
	}
	
}
