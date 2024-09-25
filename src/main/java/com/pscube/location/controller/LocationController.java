package com.pscube.location.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pscube.location.entities.Location;
import com.pscube.location.repos.LocationRepository;
import com.pscube.location.service.LocationService;
import com.pscube.location.util.EmailUtil;
import com.pscube.location.util.ReportUtil;

import jakarta.servlet.ServletContext;

@Controller
public class LocationController {

	@Autowired
	private LocationService locationService;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private ReportUtil reportUtil;

	@Autowired
	private EmailUtil emailUtil;

	@Autowired
	private ServletContext servletContext;

	@GetMapping("/showCreate")
	String showCreate() {
		return "createLocation";
	}

	@PostMapping("/saveLoc")
	String saveLocation(@ModelAttribute Location location, ModelMap modelMap) {
		Location savedLocation = locationService.saveLocation(location);
		String msg = "Loaction saved with ID:" + savedLocation.getId();
		modelMap.addAttribute("msg", msg);
		emailUtil.sendEmail("velocitybatch2jfs@gmail.com", "Location Saved", "Congrats, Location Saved Successfully.");
		return "createLocation";
	}

	@GetMapping("/displayLocations")
	String displayLocations(ModelMap modelMap) {
		List<Location> allLocations = locationService.getAllLocations();
		modelMap.addAttribute("locations", allLocations);
		return "displayLocations";
	}

	@GetMapping("/deleteLocation")
	String deleteLocation(@RequestParam int id, ModelMap modelMap) {
		Location location = new Location();
		location.setId(id);
		locationService.deleteLocation(location);
		List<Location> allLocations = locationService.getAllLocations();
		modelMap.addAttribute("locations", allLocations);
		return "displayLocations";
	}

	@GetMapping("/showUpdate")
	String showUpdate(@RequestParam int id, ModelMap modelMap) {
		Location location = locationService.getLocationById(id);
		locationService.deleteLocation(location);
		modelMap.addAttribute("location", location);
		return "updateLocation";
	}

	@PostMapping("/updateLoc")
	public String updateLocation(@ModelAttribute Location location, ModelMap modelMap) {
		locationService.updateLocation(location);
		List<Location> allLocations = locationService.getAllLocations();
		modelMap.addAttribute("locations", allLocations);
		return "displayLocations";
	}

	@RequestMapping("/generateReport")
	public String generateReport() {
		String path = servletContext.getRealPath("/");
		List<Object[]> data = locationRepository.findTypeAndTypeCount();
		reportUtil.generatePieChart(path, data);
		return "report";
	}

}
