package tn.esprit.module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/modules")
public class ModuleRestAPI {
    private static final String TITLE = "Hello, I'm the Module Micro-Service";

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/hello")
    public String sayHello() {
        System.out.println(TITLE);
        return TITLE;
    }

    // New endpoint to get all modules
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Module>> getAllModules() {
        List<Module> modules = moduleService.getAllModules();
        return ResponseEntity.ok(modules);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Module> createModule(@RequestBody Module module) {
        Module savedModule = moduleService.saveModule(module);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedModule);
    }

    @PutMapping(
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Module> updateModule(@PathVariable("id") Long id, @RequestBody Module module) {
        if (!id.equals(module.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Module updatedModule = moduleService.updateModule(module);
        if (updatedModule == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedModule);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable("id") Long id) {
        try {
            moduleService.removeModule(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Module> getModule(@PathVariable("id") Long id) {
        Module module = moduleService.getModule(id);
        if (module == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(module);
    }
}