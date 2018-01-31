package net.cuscatlan.controller;

import java.util.List;
import net.cuscatlan.common.ObjectUtils;
import net.cuscatlan.common.JqgridFilter;
import net.cuscatlan.common.JqgridResponse;
import net.cuscatlan.domain.Rentusuarios;

import net.cuscatlan.repository.RentusuariosRepository;
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
public class RentusuariosController {
    
    @Autowired
    RentusuariosRepository rentusuariosRepository;
    
    
    @RequestMapping("/indexRentusuarios")
    public ModelAndView indexRentusuarios(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("rentusuarios", new Rentusuarios());
        mv.setViewName("Rentusuarios/Rentusuarios.jsp");
        return mv;
    }
    
    @RequestMapping(value = "/saveRentusuarios", method = RequestMethod.POST)
    public @ResponseBody String saveRentusuarios(@ModelAttribute("Rentusuarios") @Validated Rentusuarios rentusuarios ) {
         rentusuariosRepository.save(rentusuarios);
         return null;
     }
    
    @RequestMapping(value = "/deleteRentusuarios", method = RequestMethod.POST)
    public @ResponseBody String deleteRentusuarios(@ModelAttribute("Rentusuarios")  Rentusuarios rentusuarios ) {
         rentusuariosRepository.delete(rentusuarios);
         return null;
     }
    
    @RequestMapping(value = "/gridRentusuarios", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody JqgridResponse<Rentusuarios> gridRentusuarios(
        @RequestParam(value = "filters", required = false) String filters,
        @RequestParam(value = "page", required = false) Integer page,
        @RequestParam(value = "rows", required = false) Integer rows
    ) {
    
        Page<Rentusuarios> list = rentusuariosRepository.findByFilters(
            new PageRequest(page - 1, rows)
           ,JqgridFilter.getField(filters, "idusuario") 
            ,JqgridFilter.getField(filters, "nombreusuario") 
            ,JqgridFilter.getField(filters, "claveusuario") 
            ,JqgridFilter.getField(filters, "rentpersonaDescriptionDelegate") 
        );

       JqgridResponse<Rentusuarios> jqgridRentusuarios = new JqgridResponse<Rentusuarios>();
       return jqgridRentusuarios.jGridFill(list, page, rows);
    }
    
    @RequestMapping(value = "/exportRentusuarios", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void exportRentusuarios(
        HttpServletResponse response,@RequestParam(value = "filters", required = false) String filters
    ) {
    
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet("libro");
        
        List<String> header = new ArrayList<String>();
        header.add("idusuario"); 
        header.add("nombreusuario"); 
        header.add("claveusuario"); 
        header.add("rentpersonaDescriptionDelegate"); 
        
        LayOutDynamic.buildReport(worksheet, "Rentusuarios", header);
        
        List<Object[]> list = rentusuariosRepository.findByFilters(
           JqgridFilter.getField(filters, "idusuario") 
            ,JqgridFilter.getField(filters, "nombreusuario") 
            ,JqgridFilter.getField(filters, "claveusuario") 
            ,JqgridFilter.getField(filters, "rentpersonaDescriptionDelegate") 
        );
        

       LayOutDynamic.fillReport(worksheet, header.size(),list);
       String fileName = "Rentusuarios.xls";
       response.setHeader("Content-Disposition", "inline; filename=" + fileName);
       response.setContentType("application/vnd.ms-excel");
       Writer.write(response, worksheet);
    }
    
    
}

