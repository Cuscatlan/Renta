package net.cuscatlan.controller;

import java.util.List;
import net.cuscatlan.common.ObjectUtils;
import net.cuscatlan.common.JqgridFilter;
import net.cuscatlan.common.JqgridResponse;
import net.cuscatlan.domain.Rentpersona;

import net.cuscatlan.repository.RentpersonaRepository;
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
public class RentpersonaController {
    
    @Autowired
    RentpersonaRepository rentpersonaRepository;
    
    
    @RequestMapping("/indexRentpersona")
    public ModelAndView indexRentpersona(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("rentpersona", new Rentpersona());
        mv.setViewName("Rentpersona/Rentpersona.jsp");
        return mv;
    }
    
    @RequestMapping(value = "/saveRentpersona", method = RequestMethod.POST)
    public @ResponseBody String saveRentpersona(@ModelAttribute("Rentpersona") @Validated Rentpersona rentpersona ) {
         rentpersonaRepository.save(rentpersona);
         return null;
     }
    
    @RequestMapping(value = "/deleteRentpersona", method = RequestMethod.POST)
    public @ResponseBody String deleteRentpersona(@ModelAttribute("Rentpersona")  Rentpersona rentpersona ) {
         rentpersonaRepository.delete(rentpersona);
         return null;
     }
    
    @RequestMapping(value = "/gridRentpersona", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody JqgridResponse<Rentpersona> gridRentpersona(
        @RequestParam(value = "filters", required = false) String filters,
        @RequestParam(value = "page", required = false) Integer page,
        @RequestParam(value = "rows", required = false) Integer rows
    ) {
    
        Page<Rentpersona> list = rentpersonaRepository.findByFilters(
            new PageRequest(page - 1, rows)
           ,JqgridFilter.getField(filters, "idpersona") 
            ,JqgridFilter.getField(filters, "nombrepersona") 
            ,JqgridFilter.getField(filters, "duipersona") 
            ,JqgridFilter.getField(filters, "apellidopersona") 
        );

       JqgridResponse<Rentpersona> jqgridRentpersona = new JqgridResponse<Rentpersona>();
       return jqgridRentpersona.jGridFill(list, page, rows);
    }
    
    @RequestMapping(value = "/exportRentpersona", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void exportRentpersona(
        HttpServletResponse response,@RequestParam(value = "filters", required = false) String filters
    ) {
    
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet("libro");
        
        List<String> header = new ArrayList<String>();
        header.add("idpersona"); 
        header.add("nombrepersona"); 
        header.add("duipersona"); 
        header.add("apellidopersona"); 
        
        LayOutDynamic.buildReport(worksheet, "Rentpersona", header);
        
        List<Object[]> list = rentpersonaRepository.findByFilters(
           JqgridFilter.getField(filters, "idpersona") 
            ,JqgridFilter.getField(filters, "nombrepersona") 
            ,JqgridFilter.getField(filters, "duipersona") 
            ,JqgridFilter.getField(filters, "apellidopersona") 
        );
        

       LayOutDynamic.fillReport(worksheet, header.size(),list);
       String fileName = "Rentpersona.xls";
       response.setHeader("Content-Disposition", "inline; filename=" + fileName);
       response.setContentType("application/vnd.ms-excel");
       Writer.write(response, worksheet);
    }
    
    @RequestMapping(value = {"/cbofilterRentpersona"}, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody List<CboFilter> cbofilterRentpersona() {
        List<Rentpersona> list = rentpersonaRepository.findAll();
        List<CboFilter> response = new ArrayList<CboFilter>();
        for (int i = 0; i < list.size(); i++) {
            response.add(new CboFilter(list.get(i).getIdpersona().toString(), list.get(i).getIdpersona().toString()));
        }
        return response;
    }
    
}

