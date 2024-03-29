package tn.esprit.gestionchambre.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.gestionchambre.entities.Chambre;
import tn.esprit.gestionchambre.entities.TypeChambre;
import tn.esprit.gestionchambre.repositories.ChambreRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChambreService implements IChambreService {


    ChambreRepository chambreRepository;

    @Override
    public Chambre addChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }



    @Override
    public Chambre getChambre(Long id) {
        return chambreRepository.findById(id).orElse(null);
    }

    @Override
    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public void deleteChambre(long idChambre) {
        chambreRepository.deleteById(idChambre);
    }

    @Override
    public Chambre updateChambre(Chambre chambre) {
        Chambre ch = chambreRepository.findById(chambre.getIdChambre()).orElse(null);
        if (ch != null)
            chambreRepository.save(chambre);
        return ch;
    }

    public static Map<TypeChambre, Long> getNombreChambresParType(List<Chambre> chambres) {
        return chambres.stream()
                .collect(Collectors.groupingBy(Chambre::getTypeChambre, Collectors.counting()));
    }



}