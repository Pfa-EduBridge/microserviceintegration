package tn.esprit.module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    public Module saveModule(Module module) {
        return moduleRepository.save(module);
    }
    public Module updateModule(Module module) {
        if (module.getId() == null || !moduleRepository.existsById(module.getId())) {
            throw new IllegalArgumentException("Module not found for the given id.");
        }

        Module existingModule = moduleRepository.findById(module.getId()).orElse(null);

        if (existingModule != null) {
            if (module.getModuleName() != null) {
                existingModule.setModuleName(module.getModuleName());
            }
            // Add other specific fields for Module

            return moduleRepository.save(existingModule);
        }

        return null;
    }

    public Module getModule(Long id) {
        return moduleRepository.findById(id).orElse(null);
    }

    public void removeModule(Long id) {
        moduleRepository.deleteById(id);
    }

}
