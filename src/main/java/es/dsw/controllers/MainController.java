package es.dsw.controllers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.dsw.TSpringBootProjectDemo3.ApplicationData;



@Controller
public class MainController {

	//[4] - ACCESO A PROPIEDADES DESDE CÓDIGO JAVA
	//Con spring boot, el acceso a cualquier propiedad de cualquier fichero .propierties, se puede realizar de una manera sencilla
	//haciendo uso de las java annotations @Value
	
	@Value("${spring.thymeleaf.cache}")
	private String Cache;
	
	@Value("${server.port}")
	private String Port;
	
	@Value("${server.servlet.session.timeout}")
	private String Sessiontimeout;
	
	
	@GetMapping(value={"/","/index"})
	public String index(Model objModel) { 
	
		//Se crea un modelo denominado DatosProyecto cuyo objeto cotiene propiedades inyectadas desde el fichero "otrosparametros.properties"
		ApplicationData objApplicationData = ApplicationData.getApplicationData();
		objModel.addAttribute("DatosProyecto", objApplicationData);
		
		//Se crean tres modelos con el valor de las propiedades definidas en el fichero "application.properties"
		objModel.addAttribute("Cache", Cache);
		objModel.addAttribute("Port", Port);
		objModel.addAttribute("Sessiontimeout", Sessiontimeout);
		
		//IMPORTANTE: A la hora de especificar propiedades en los ficheros, procure no repetir el identificador (nombre de la propiedad) aunque
		//sean ficheros difernetes. Spring Boot por asi decirlo carga todas las propiedades de los ficheros con extensión .properties.
		
		return "index";   
	}
} 
