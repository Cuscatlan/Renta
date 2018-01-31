package net.cuscatlan.controller;

import java.util.List;
import net.cuscatlan.common.ObjectUtils;
import net.cuscatlan.common.JqgridFilter;
import net.cuscatlan.common.JqgridResponse;
import net.cuscatlan.domain.Renttipotransaccion;

import net.cuscatlan.repository.RenttipotransaccionRepository;
import net.cuscatlan.poi.LayOutDynamic;
import net.cuscatlan.poi.Writer;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import net.cuscatlan.common.CboFilter;
import java.util.Date;

@Controller
@RequestMapping("/")
public class RenttipotransaccionController {
    
    @Autowired
    RenttipotransaccionRepository renttipotransaccionRepository;
    
    
    @RequestMapping("/indexRenttipotransaccion")
    public ModelAndView indexRenttipotransaccion(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("renttipotransaccion", new Renttipotransaccion());
        mv.setViewName("Renttipotransaccion/Renttipotransaccion.jsp");
        return mv;
    }
    
    @RequestMapping(value = "/saveRenttipotransaccion", method = RequestMethod.POST)
    public @ResponseBody String saveRenttipotransaccion(@ModelAttribute("Renttipotransaccion") @Validated Renttipotransaccion renttipotransaccion ) {
         renttipotransaccionRepository.save(renttipotransaccion);
         return null;
     }
    
    @RequestMapping(value = "/deleteRenttipotransaccion", method = RequestMethod.POST)
    public @ResponseBody String deleteRenttipotransaccion(@ModelAttribute("Renttipotransaccion")  Renttipotransaccion renttipotransaccion ) {
         renttipotransaccionRepository.delete(renttipotransaccion);
         return null;
     }
    
    @RequestMapping(value = "/gridRenttipotransaccion", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody JqgridResponse<Renttipotransaccion> gridRenttipotransaccion(
        @RequestParam(value = "filters", required = false) String filters,
        @RequestParam(value = "page", required = false) Integer page,
        @RequestParam(value = "rows", required = false) Integer rows
    ) {
    
        Page<Renttipotransaccion> list = renttipotransaccionRepository.findByFilters(
            new PageRequest(page - 1, rows)
           ,JqgridFilter.getField(filters, "idtipotransaccion") 
            ,JqgridFilter.getField(filters, "nombretipotransaccion") 
        );

       JqgridResponse<Renttipotransaccion> jqgridRenttipotransaccion = new JqgridResponse<Renttipotransaccion>();
       return jqgridRenttipotransaccion.jGridFill(list, page, rows);
    }
    
    @RequestMapping(value = "/exportRenttipotransaccion", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void exportRenttipotransaccion(
        HttpServletResponse response,@RequestParam(value = "filters", required = false) String filters
    ) {
    
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet("libro");
        
        List<String> header = new ArrayList<String>();
        header.add("idtipotransaccion"); 
        header.add("nombretipotransaccion"); 
        
        LayOutDynamic.buildReport(worksheet, "Renttipotransaccion", header);
        
        List<Object[]> list = renttipotransaccionRepository.findByFilters(
           JqgridFilter.getField(filters, "idtipotransaccion") 
            ,JqgridFilter.getField(filters, "nombretipotransaccion") 
        );
        

       LayOutDynamic.fillReport(worksheet, header.size(),list);
       String fileName = "Renttipotransaccion.xls";
       response.setHeader("Content-Disposition", "inline; filename=" + fileName);
       response.setContentType("application/vnd.ms-excel");
       Writer.write(response, worksheet);
    }
    
    @RequestMapping(value = {"/cbofilterRenttipotransaccion"}, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody List<CboFilter> cbofilterRenttipotransaccion() {
        List<Renttipotransaccion> list = renttipotransaccionRepository.findAll();
        List<CboFilter> response = new ArrayList<CboFilter>();
        for (int i = 0; i < list.size(); i++) {
            response.add(new CboFilter(list.get(i).getIdtipotransaccion().toString(), list.get(i).getIdtipotransaccion().toString()));
        }
        return response;
    }
    
}

