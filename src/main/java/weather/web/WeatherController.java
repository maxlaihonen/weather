package weather.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        List<Observation> observations = observationDAO.findByCity(cityId);
        List<Observation> observationstemp = observationDAO.findByCityOrderByTemperature(cityId);
       
        model.addAttribute("observations", observations);
        if (!observationstemp.isEmpty()) {
        model.addAttribute("rightnow", observations.get(0).getTemp());
        model.addAttribute("lowest", observationstemp.get(0).getTemp());
        model.addAttribute("highest", observationstemp.get(observationstemp.size() -1).getTemp());
        }
        return "observationlist";
        
    }
    
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addObservation(Model model){
    	model.addAttribute("observation", new Observation());
    return "addobservation";
    } 
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(Observation observation){
        observationDAO.save(observation);
        return "addobservation";
    }
}
