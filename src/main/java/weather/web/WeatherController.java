package weather.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import weather.domain.Observation;
import weather.domain.ObservationDAO;


@Controller
public class WeatherController {
	
    @Autowired
    private ObservationDAO observationDAO;
    
    
    @RequestMapping("/")
	public String home() {
		return "redirect:/observations/1";
	}
    
    @RequestMapping(value="/observations/{id}", method = RequestMethod.GET)
    public String observationList(@PathVariable("id") int cityId, Model model) {	
    		if (cityId <= 5 && cityId >= 1) {
        List<Observation> observations = observationDAO.findByCity(cityId);
        List<Observation> observationstemp = observationDAO.findByCityOrderByTemperatureLast24h(cityId);     
        model.addAttribute("observations", observations);
        model.addAttribute("cityId", cityId);
        if (!observationstemp.isEmpty()) {
        model.addAttribute("rightnow", observations.get(0).getTemp());
        model.addAttribute("lowest", observationstemp.get(0).getTemp());
        model.addAttribute("highest", observationstemp.get(observationstemp.size() -1).getTemp());
        }
        return "observationlist";
    		} 		
    		else {
    			return "error";
    		}
    }
    
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addObservation(Model model){
    	model.addAttribute("observation", new Observation());
    return "addobservation";
    } 
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(@Valid Observation observation, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
        	return "addobservation";
        }
    		observationDAO.save(observation);
        return "success";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long cityId, Model model) {
    observationDAO.delete(cityId);
        return "deletesuccess";
    }  
}
