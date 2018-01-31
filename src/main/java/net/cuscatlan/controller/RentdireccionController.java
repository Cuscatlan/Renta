package net.cuscatlan.controller;

import java.util.List;
import net.cuscatlan.common.ObjectUtils;
import net.cuscatlan.common.JqgridFilter;
import net.cuscatlan.common.JqgridResponse;
import net.cuscatlan.domain.Rentdireccion;

import net.cuscatlan.repository.RentdireccionRepository;
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
public class RentdireccionController {
    
    @Autowired
    RentdireccionRepository rentdireccionRepository;
    
    
    @RequestMapping("/indexRentdireccion")
    public ModelAndView indexRentdireccion(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("rentdireccion", new Rentdireccion());
        mv.setViewName("Rentdireccion/Rentdireccion.jsp");
        return mv;
    }
    
    @RequestMapping(value = "/saveRentdireccion", method = RequestMethod.POST)
    public @ResponseBody String saveRentdireccion(@ModelAttribute("Rentdireccion") @Validated Rentdireccion rentdireccion ) {
         rentdireccionRepository.save(rentdireccion);
         return null;
     }
    
    @RequestMapping(value = "/deleteRentdireccion", method = RequestMethod.POST)
    public @ResponseBody String deleteRentdireccion(@ModelAttribute("Rentdireccion")  Rentdireccion rentdireccion ) {
         rentdireccionRepository.delete(rentdireccion);
         return null;
     }
    
    @RequestMapping(value = "/gridRentdireccion", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody JqgridResponse<Rentdireccion> gridRentdireccion(
        @RequestParam(value = "filters", required = false) String filters,
        @RequestParam(value = "page", required = false) Integer page,
        @RequestParam(value = "rows", required = false) Integer rows
    ) {
    
        Page<Rentdireccion> list = rentdireccionRepository.findByFilters(
            new PageRequest(page - 1, rows)
           ,JqgridFilter.getField(filters, "iddirecion") 
            ,JqgridFilter.getField(filters, "tipodireccion") 
            ,JqgridFilter.getField(filters, "sdirecion") 
            ,JqgridFilter.getField(filters, "rentpersonaDescriptionDelegate") 
        );

       JqgridResponse<Rentdireccion> jqgridRentdireccion = new JqgridResponse<Rentdireccion>();
       return jqgridRentdireccion.jGridFill(list, page, rows);
    }
    
    @RequestMapping(value = "/exportRentdireccion", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void exportRentdireccion(
        HttpServletResponse response,@RequestParam(value = "filters", required = false) String filters
    ) {
    
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet("libro");
        
        List<String> header = new ArrayList<String>();
        header.add("iddirecion"); 
        header.add("tipodireccion"); 
        header.add("sdirecion"); 
        header.add("rentpersonaDescriptionDelegate"); 
        
        LayOutDynamic.buildReport(worksheet, "Rentdireccion", header);
        
        List<Object[]> list = rentdireccionRepository.findByFilters(
           JqgridFilter.getField(filters, "iddirecion") 
            ,JqgridFilter.getField(filters, "tipodireccion") 
            ,JqgridFilter.getField(filters, "sdirecion") 
            ,JqgridFilter.getField(filters, "rentpersonaDescriptionDelegate") 
        );
        

       LayOutDynamic.fillReport(worksheet, header.size(),list);
       String fileName = "Rentdireccion.xls";
       response.setHeader("Content-Disposition", "inline; filename=" + fileName);
       response.setContentType("application/vnd.ms-excel");
       Writer.write(response, worksheet);
    }
    
    
}

