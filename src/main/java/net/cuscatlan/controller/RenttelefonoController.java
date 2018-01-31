package net.cuscatlan.controller;

import java.util.List;
import net.cuscatlan.common.ObjectUtils;
import net.cuscatlan.common.JqgridFilter;
import net.cuscatlan.common.JqgridResponse;
import net.cuscatlan.domain.Renttelefono;

import net.cuscatlan.repository.RenttelefonoRepository;
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
public class RenttelefonoController {
    
    @Autowired
    RenttelefonoRepository renttelefonoRepository;
    
    
    @RequestMapping("/indexRenttelefono")
    public ModelAndView indexRenttelefono(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("renttelefono", new Renttelefono());
        mv.setViewName("Renttelefono/Renttelefono.jsp");
        return mv;
    }
    
    @RequestMapping(value = "/saveRenttelefono", method = RequestMethod.POST)
    public @ResponseBody String saveRenttelefono(@ModelAttribute("Renttelefono") @Validated Renttelefono renttelefono ) {
         renttelefonoRepository.save(renttelefono);
         return null;
     }
    
    @RequestMapping(value = "/deleteRenttelefono", method = RequestMethod.POST)
    public @ResponseBody String deleteRenttelefono(@ModelAttribute("Renttelefono")  Renttelefono renttelefono ) {
         renttelefonoRepository.delete(renttelefono);
         return null;
     }
    
    @RequestMapping(value = "/gridRenttelefono", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody JqgridResponse<Renttelefono> gridRenttelefono(
        @RequestParam(value = "filters", required = false) String filters,
        @RequestParam(value = "page", required = false) Integer page,
        @RequestParam(value = "rows", required = false) Integer rows
    ) {
    
        Page<Renttelefono> list = renttelefonoRepository.findByFilters(
            new PageRequest(page - 1, rows)
           ,JqgridFilter.getField(filters, "idtelefono") 
            ,JqgridFilter.getField(filters, "tipotelefono") 
            ,JqgridFilter.getField(filters, "numerotelefono") 
            ,JqgridFilter.getField(filters, "rentpersonaDescriptionDelegate") 
        );

       JqgridResponse<Renttelefono> jqgridRenttelefono = new JqgridResponse<Renttelefono>();
       return jqgridRenttelefono.jGridFill(list, page, rows);
    }
    
    @RequestMapping(value = "/exportRenttelefono", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void exportRenttelefono(
        HttpServletResponse response,@RequestParam(value = "filters", required = false) String filters
    ) {
    
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet("libro");
        
        List<String> header = new ArrayList<String>();
        header.add("idtelefono"); 
        header.add("tipotelefono"); 
        header.add("numerotelefono"); 
        header.add("rentpersonaDescriptionDelegate"); 
        
        LayOutDynamic.buildReport(worksheet, "Renttelefono", header);
        
        List<Object[]> list = renttelefonoRepository.findByFilters(
           JqgridFilter.getField(filters, "idtelefono") 
            ,JqgridFilter.getField(filters, "tipotelefono") 
            ,JqgridFilter.getField(filters, "numerotelefono") 
            ,JqgridFilter.getField(filters, "rentpersonaDescriptionDelegate") 
        );
        

       LayOutDynamic.fillReport(worksheet, header.size(),list);
       String fileName = "Renttelefono.xls";
       response.setHeader("Content-Disposition", "inline; filename=" + fileName);
       response.setContentType("application/vnd.ms-excel");
       Writer.write(response, worksheet);
    }
    
    
}

