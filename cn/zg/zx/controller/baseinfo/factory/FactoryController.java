package cn.zg.zx.controller.baseinfo.factory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.zg.util.DownloadUtil;
import cn.zg.util.file.FileUtil;
import cn.zg.zx.controller.BaseController;
import cn.zg.zx.domain.Factory;
import cn.zg.zx.service.FactoryService;
@Controller
public class FactoryController extends BaseController {

	@Autowired
	FactoryService factoryService;

	@RequestMapping("/baseinfo/factory/print.action")
	public String print() {
		return "/baseinfo/factory/test.jsp";
	}
	@RequestMapping("/baseinfo/factory/submit.action")
	public void list(HttpServletRequest request) {
		CommonsMultipartResolver cmr=new CommonsMultipartResolver(request.getSession().getServletContext());
		if(cmr.isMultipart(request)) {
			MultipartHttpServletRequest mhsr= cmr.resolveMultipart(request);
			
			
			for(Iterator it=mhsr.getFileNames();it.hasNext();) {
				MultipartFile file=mhsr.getFile(it.next().toString());
				
				if(file!=null) {
					String path="c:/temp/"+file.getOriginalFilename();
					try {
						file.transferTo(new File(path));
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*CommonsMultipartResolver cmr=new CommonsMultipartResolver(request.getSession().getServletContext());
		if(cmr.isMultipart(request)) {
			MultipartHttpServletRequest mhsr=cmr.resolveMultipart(request); 
			for(Iterator it=mhsr.getFileNames();it.hasNext();) {
				MultipartFile mf=mhsr.getFile(it.next().toString());
				if(mf!=null) {
					String path="c:/temp/"+mf.getOriginalFilename();
					
					try {
						mf.transferTo(new File(path));
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		System.out.println("上传成功");*/
	}
	
	
	
	public void print(HttpServletRequest request,HttpServletResponse response) {
		//生产厂家通讯录
		String[] title= {"序号","公司名","缩写","联系人","电话","手机","传真","备注"};
		int rowNum=0;
		int cellNum=0;
		Workbook wb=new HSSFWorkbook();
		Sheet sheet=wb.createSheet();
		//设置列宽时，30字符宽度默认需要除以256，需要需要设置30个字符时还需乘与256
		Row row=null;
		Cell cell=null;
		sheet.setColumnWidth(1, 30*256);
	
		//大标题
		//如何实现合并单元格
		CellRangeAddress cra=new CellRangeAddress(rowNum, rowNum, cellNum, 7);
		sheet.addMergedRegion(cra);
		row=sheet.createRow(rowNum++);
		cell=row.createCell(cellNum);
		cell.setCellStyle(bigTitleStyle(wb));
		cell.setCellValue("生产厂家通信录");
		/*
		 * 合并单元格的实现原理是：通过生成一个具体范围的区域来覆盖对应改行的第一个单元格位置。
		 */
		//列名
		row=sheet.createRow(rowNum++);
		row.setHeightInPoints(25f);
		for(int i=0;i<title.length;i++) {
			cell=row.createCell(i);
			cell.setCellStyle(titleStyle(wb));
			cell.setCellValue(title[i]);
		}
		//获取工厂信息表内容
		Factory factory=new Factory();
		factory.setState(1);
		List<Factory> list=factoryService.find(factory);
		for(int i=0;i<list.size();i++) {//每个工厂对象对应每一行数据
			row=sheet.createRow(rowNum++);//新建行
			
			//注意：每创建一行后记得将列的编号回归到0
			cellNum=0;
			
			cell=row.createCell(cellNum++);
			cell.setCellStyle(textStyle(wb));
			cell.setCellValue(i+1);
			
			cell=row.createCell(cellNum++);
			cell.setCellStyle(textStyle(wb));
			cell.setCellValue(list.get(i).getFullName());
			
			cell=row.createCell(cellNum++);
			cell.setCellStyle(textStyle(wb));
			cell.setCellValue(list.get(i).getFactoryName());
			
			cell=row.createCell(cellNum++);
			cell.setCellStyle(textStyle(wb));
			cell.setCellValue(list.get(i).getContractor());
			
			cell=row.createCell(cellNum++);
			cell.setCellStyle(textStyle(wb));
			cell.setCellValue(list.get(i).getPhone());
			
			cell=row.createCell(cellNum++);
			cell.setCellStyle(textStyle(wb));
			cell.setCellValue(list.get(i).getMobile());
			
			cell=row.createCell(cellNum++);
			cell.setCellStyle(textStyle(wb));
			cell.setCellValue(list.get(i).getFax());
			
			cell=row.createCell(cellNum++);
			cell.setCellStyle(textStyle(wb));
			cell.setCellValue(list.get(i).getCnote());
		}
		/*注意： Invalid column index (256),该错误的意思是报表的支持excel中的列最多只能有256列
		  注意： Invalid row index (65535),该错误的意思是报表的支持excel总的行醉倒只能有65535行	
		*
		*/
		OutputStream out;
		try {
			//在服务器的项目中保存对应的生产厂家报表
//			String path=request.getSession().getServletContext().getRealPath("/tempFile/");
//			File file=new File(path);
//			if(!file.exists()) {
//				file.mkdir();//创建路径
//			}
//			FileUtil fu=new FileUtil();
			String name="生产厂家通信录.xls";
//			String newName=fu.newFile(path,name);
//			out = new FileOutputStream(path+"/"+newName);
//
			DownloadUtil du=new DownloadUtil();
			ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
			wb.write(byteArrayOutputStream);
			du.download(byteArrayOutputStream, response, name);
			
			byteArrayOutputStream.flush();
			byteArrayOutputStream.close();
			
//			wb.write(out);
//			out.flush();
//			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private CellStyle bigTitleStyle(Workbook wb) {
		CellStyle cs=wb.createCellStyle();
		//旗标 常量
		cs.setAlignment(CellStyle.ALIGN_CENTER);//设置水平居中
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//设置垂直居中
		Font font=wb.createFont();
		font.setFontHeightInPoints((short)36);
		font.setFontName("楷体");
		cs.setFont(font);
		return cs;
	}
	
	public void print2() {
		//生产厂家通讯录
		String[] title= {"序号","公司名","缩写","联系人","电话","手机","传真","备注"};
		int rowNum=0;
		int cellNum=0;
		Workbook wb=new HSSFWorkbook();
		Sheet sheet=wb.createSheet();
		//设置列宽时，30字符宽度默认需要除以256，需要需要设置30个字符时还需乘与256
		sheet.setColumnWidth(1, 30*256);
		Row row=sheet.createRow(rowNum++);
		row.setHeightInPoints(25f);
	
		Cell cell=null;
		
		for(int i=0;i<title.length;i++) {
			cell=row.createCell(i);
			cell.setCellStyle(titleStyle(wb));
			cell.setCellValue(title[i]);
		}
		//获取工厂信息表内容
		Factory factory=new Factory();
		factory.setState(1);
		List<Factory> list=factoryService.find(factory);
		for(int i=0;i<list.size();i++) {//每个工厂对象对应每一行数据
			row=sheet.createRow(rowNum++);//新建行
			
			//注意：每创建一行后记得将列的编号回归到0
			cellNum=0;
			
			cell=row.createCell(cellNum++);
			cell.setCellStyle(textStyle(wb));
			cell.setCellValue(i+1);
			
			cell=row.createCell(cellNum++);
			cell.setCellStyle(textStyle(wb));
			cell.setCellValue(list.get(i).getFullName());
			
			cell=row.createCell(cellNum++);
			cell.setCellStyle(textStyle(wb));
			cell.setCellValue(list.get(i).getFactoryName());
			
			cell=row.createCell(cellNum++);
			cell.setCellStyle(textStyle(wb));
			cell.setCellValue(list.get(i).getContractor());
			
			cell=row.createCell(cellNum++);
			cell.setCellStyle(textStyle(wb));
			cell.setCellValue(list.get(i).getPhone());
			
			cell=row.createCell(cellNum++);
			cell.setCellStyle(textStyle(wb));
			cell.setCellValue(list.get(i).getMobile());
			
			cell=row.createCell(cellNum++);
			cell.setCellStyle(textStyle(wb));
			cell.setCellValue(list.get(i).getFax());
			
			cell=row.createCell(cellNum++);
			cell.setCellStyle(textStyle(wb));
			cell.setCellValue(list.get(i).getCnote());
		}
		/*注意： Invalid column index (256),该错误的意思是报表的支持excel中的列最多只能有256列
		  注意： Invalid row index (65535),该错误的意思是报表的支持excel总的行醉倒只能有65535行	
		*
		*/
		OutputStream out;
		try {
			out = new FileOutputStream("c:/temp/factory.xls");
			wb.write(out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void print1() {
		//工作簿
		Workbook wb=new HSSFWorkbook();
		CellStyle cs = titleStyle(wb);
		//工作表
		Sheet sheet=wb.createSheet();
		//行
		Row row=sheet.createRow(2);
		//单元格
		Cell cell=row.createCell(2);
		//CellStyle对象由谁创建
		cell.setCellStyle(cs);
		cell.setCellValue("优就业");

		CellStyle xcs = textStyle(wb);
		//行
		Row xrow=sheet.createRow(3);
		//单元格
		Cell xcell=xrow.createCell(3);
		//CellStyle对象由谁创建
		xcell.setCellStyle(xcs);
		xcell.setCellValue("www.ujiuye.commm");	
		
		//通过字节输出流将报表输出
		OutputStream out;
		try {
			out = new FileOutputStream("c:/temp/factory.xls");
			wb.write(out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private CellStyle textStyle(Workbook wb) {
		CellStyle xcs=wb.createCellStyle();//设置水平居中
		xcs.setAlignment(CellStyle.ALIGN_CENTER);
		xcs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//设置垂直居中
		Font xfont=wb.createFont();
		xfont.setFontHeightInPoints((short)12);
		xcs.setFont(xfont);
		xcs.setBorderTop(CellStyle.BORDER_THIN);
		xcs.setBorderRight(CellStyle.BORDER_THIN);
		xcs.setBorderBottom(CellStyle.BORDER_THIN);
		xcs.setBorderLeft(CellStyle.BORDER_THIN);
		return xcs;
	}


	private CellStyle titleStyle(Workbook wb) {
		CellStyle cs=wb.createCellStyle();
		//旗标 常量
		cs.setAlignment(CellStyle.ALIGN_CENTER);//设置水平居中
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//设置垂直居中
		Font font=wb.createFont();
		font.setFontHeightInPoints((short)18);
		font.setFontName("微软雅黑");
		cs.setFont(font);
		cs.setBorderTop(CellStyle.BORDER_THIN);
		cs.setBorderRight(CellStyle.BORDER_THIN);
		cs.setBorderBottom(CellStyle.BORDER_THIN);
		cs.setBorderLeft(CellStyle.BORDER_THIN);
		return cs;
	}
	
	
	@RequestMapping("/baseinfo/factory/start.action")
	public String start(String id) {
		changeState(id,1);
		return "redirect:/baseinfo/factory/list.action";
	}

	private void changeState(String id,int state) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("state", state);
		String[] ids=id.split(",");
		map.put("ids", ids);
		factoryService.changeState(map);
	}
	
	@RequestMapping("/baseinfo/factory/stop.action")
	public String stop(String id) {
		changeState(id,0);
		return "redirect:/baseinfo/factory/list.action";
	}
	
	@RequestMapping("/baseinfo/factory/list.action")
	public String list(Factory factory,Model model) {
		List<Factory> dataList=factoryService.find(factory);
		model.addAttribute("dataList",dataList);
		return "/baseinfo/factory/jFactoryList.jsp";
	}
	
	//跳转到新增页面
	@RequestMapping("/baseinfo/factory/tocreate.action")
	public String tocreate() {
		return "/baseinfo/factory/jFactoryCreate.jsp";
	}
	
	//新增操作
	@RequestMapping("/baseinfo/factory/insert.action")
	public String insert(Factory factory) {
		factoryService.insert(factory);
		return "redirect:/baseinfo/factory/list.action";
	}
	
	//跳转到更新页面
	@RequestMapping("/baseinfo/factory/toupdate.action")
	public String toupdate(String id,Model model) {
		Factory obj=factoryService.get(id);//回显
		model.addAttribute("obj", obj);
		return "/baseinfo/factory/jFactoryUpdate.jsp";
	}
	
	//修改操作
	@RequestMapping("/baseinfo/factory/update.action")
	public String update(Factory factory) {
		factoryService.update(factory);
		return "redirect:/baseinfo/factory/list.action";
	}
	
	//删除一个信息（不会做删除，使用update状态列 代替  delete,）
	@RequestMapping("/baseinfo/factory/delete.action")
	public String delete(String id) {
		factoryService.delete(id);
		return "redirect:/baseinfo/factory/list.action";
	}
	
	//批量删除
	@RequestMapping("/baseinfo/factory/deletebatch.action")
	public String deletebatch(String[] id) {
		//String id=0c171fdf-fdb6-11e8-aa57-0050562206e0,12345678
		//String[] ids=id.split(",");
		//String[] id=[Ljava.lang.String;@4331d260
		//注意：表单元素的名称必须与参数名称一致，如果不一致出500错误
		factoryService.delete(id);
		return "redirect:/baseinfo/factory/list.action";
	}
	
	//查询一个厂家信息
	@RequestMapping("/baseinfo/factory/toview.action")
	public String toview(String id,Model model) {
		Factory obj=factoryService.get(id);
		model.addAttribute("obj", obj);
		return "/baseinfo/factory/jFactoryView.jsp";
	}
}