package cn.zg.zx.controller.cargo.contract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zg.zx.controller.BaseController;
import cn.zg.zx.domain.Contract;
import cn.zg.zx.print.ContractPrint;
import cn.zg.zx.service.ContractService;
import cn.zg.zx.vo.ContractCombo;
import cn.zg.zx.vo.ContractView;

@Controller
public class ContractController extends BaseController {

	@Autowired
	ContractService contractService;
	//poi打印方法
	@RequestMapping("cargo/contract/print.action")
	public void print(String id,HttpServletRequest request,HttpServletResponse response){
		ContractPrint contractPrint=new ContractPrint();
		String path=request.getSession().getServletContext().getRealPath("/");
		ContractCombo contract=contractService.combo(id);
		try {
			contractPrint.print(contract, path, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/cargo/contract/submit.action")
	public String submit(String[] id) {
		changeState(id, 1);
		return "redirect:/cargo/contract/list.action";
	}
	
	@RequestMapping("/cargo/contract/cancel.action")
	public String cancel(String[] id) {
		changeState(id, 0);
		return "redirect:/cargo/contract/list.action";
	}
	
	private void changeState(String[] ids,int state) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("state", state);
		map.put("ids", ids);
		contractService.changeState(map);
	}
	
	
	@RequestMapping("/cargo/contract/list.action")
	public String list(Contract contract,Model model) {
		List<Contract> dataList=contractService.find(contract);
		model.addAttribute("dataList",dataList);
		return "/cargo/contract/jContractList.jsp";
	}
	
	//转向新增页面
	@RequestMapping("/cargo/contract/tocreate.action")
	public String tocreate(Model model) {
		String contractNo=contractService.getContractNo();
		model.addAttribute("obj", contractNo);
		return "/cargo/contract/jContractCreate.jsp";
	}
	
	@RequestMapping("/cargo/contract/insert.action")
	public String insert(Contract contract) {
		contractService.insert(contract);
		return "redirect:/cargo/contract/list.action";
	}
	
	//转向修改页面 主从页面开始
	@RequestMapping("/cargo/contract/toupdate.action")
	public String toupdate(String id,Model model) {
//		Contract obj=contractService.get(id);
		ContractView obj= (ContractView) contractService.get(id);
		model.addAttribute("obj", obj);
		return "/cargo/contract/jContractUpdate.jsp";
	}
	
	@RequestMapping("/cargo/contract/update.action")
	public String update(Contract contract) {
		contractService.update(contract);
		return "redirect:/cargo/contract/list.action";
	}
	
	@RequestMapping("/cargo/contract/deletebatch.action")
	public String delete(String[] id) {
		contractService.delete(id);
		return "redirect:/cargo/contract/list.action";
	}
	
	@RequestMapping("/cargo/contract/toview.action")
	public String toview(String id,Model model) {
		ContractView obj=(ContractView) contractService.get(id);
		model.addAttribute("obj", obj);
		return "/cargo/contract/jContractView.jsp";
	}
}
