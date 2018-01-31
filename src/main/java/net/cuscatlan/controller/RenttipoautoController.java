package net.cuscatlan.controller;

import java.util.List;
import net.cuscatlan.common.ObjectUtils;
import net.cuscatlan.common.JqgridFilter;
import net.cuscatlan.common.JqgridResponse;
import net.cuscatlan.domain.Renttipoauto;

import net.cuscatlan.repository.RenttipoautoRepository;
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
public class RenttipoautoController {
    
    @Autowired
    RenttipoautoRepository renttipoautoRepository;
    
    
    @RequestMapping("/indexRenttipoauto")
    public ModelAndView indexRenttipoauto(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("renttipoauto", new Renttipoauto());
        mv.setViewName("Renttipoauto/Renttipoauto.jsp");
        return mv;
    }
    
    @RequestMapping(value = "/saveRenttipoauto", method = RequestMethod.POST)
    public @ResponseBody String saveRenttipoauto(@ModelAttribute("Renttipoauto") @Validated Renttipoauto renttipoauto ) {
       
         renttipoautoRepository.save(renttipoauto);
         return null;
     }
    
    @RequestMapping(value = "/deleteRenttipoauto", method = RequestMethod.POST)
    public @ResponseBody String deleteRenttipoauto(@ModelAttribute("Renttipoauto")  Renttipoauto renttipoauto ) {
         renttipoautoRepository.delete(renttipoauto);
         return null;
     }
    
    @RequestMapping(value = "/gridRenttipoauto", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody JqgridResponse<Renttipoauto> gridRenttipoauto(
        @RequestParam(value = "filters", required = false) String filters,
        @RequestParam(value = "page", required = false) Integer page,
        @RequestParam(value = "rows", required = false) Integer rows
    ) {
    
        Page<Renttipoauto> list = renttipoautoRepository.findByFilters(
            new PageRequest(page - 1, rows)
           ,JqgridFilter.getField(filters, "idtipoauto") 
            ,JqgridFilter.getField(filters, "tipoautos") 
        );

       JqgridResponse<Renttipoauto> jqgridRenttipoauto = new JqgridResponse<Renttipoauto>();
       return jqgridRenttipoauto.jGridFill(list, page, rows);
    }
    
    @RequestMapping(value = "/exportRenttipoauto", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void exportRenttipoauto(
        HttpServletResponse response,@RequestParam(value = "filters", required = false) String filters
    ) {
    
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet("libro");
        
        List<String> header = new ArrayList<String>();
        header.add("idtipoauto"); 
        header.add("tipoautos"); 
        
        LayOutDynamic.buildReport(worksheet, "Renttipoauto", header);
        
        List<Object[]> list = renttipoautoRepository.findByFilters(
           JqgridFilter.getField(filters, "idtipoauto") 
            ,JqgridFilter.getField(filters, "tipoautos") 
        );
        

       LayOutDynamic.fillReport(worksheet, header.size(),list);
       String fileName = "Renttipoauto.xls";
       response.setHeader("Content-Disposition", "inline; filename=" + fileName);
       response.setContentType("application/vnd.ms-excel");
       Writer.write(response, worksheet);
    }
    
    @RequestMapping(value = {"/cbofilterRenttipoauto"}, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody List<CboFilter> cbofilterRenttipoauto() {
        List<Renttipoauto> list = renttipoautoRepository.findAll();
        List<CboFilter> response = new ArrayList<CboFilter>();
        for (int i = 0; i < list.size(); i++) {
            response.add(new CboFilter( list.get(i).getIdtipoauto().toString(),list.get(i).getTipoautos()));
        }
        return response;
    }
    
}

