package com.pscube.location.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pscube.location.entities.Location;
import com.pscube.location.service.LocationService;

@Controller
public class LocationController {

	@Autowired
	private LocationService locationService;

	@GetMapping("/showCreate")
	String showCreate() {
		return "createLocation";
	}

	@PostMapping("/saveLoc")
	String saveLocation(@ModelAttribute Location location, ModelMap modelMap) {
		Location savedLocation = locationService.saveLocation(location);
		String msg = "Loaction saved with ID:" + savedLocation.getId();
		modelMap.addAttribute("msg", msg);
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
	String updateLocation(@ModelAttribute Location location, ModelMap modelMap) {
		locationService.updateLocation(location);
		List<Location> allLocations = locationService.getAllLocations();
		modelMap.addAttribute("locations", allLocations);
		return "displayLocations";
	}

}
