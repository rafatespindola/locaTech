package br.com.fiap.locatech.locatech.services;

import br.com.fiap.locatech.locatech.entities.Aluguel;
import br.com.fiap.locatech.locatech.repositories.AluguelRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {
    private final AluguelRepository aluguelRepository;

    public AluguelService(AluguelRepository aluguelRepository){
        this.aluguelRepository = aluguelRepository;
    }

    public List<Aluguel> findAllAlugueis(int page, int size){
        int offset = (page - 1) * size;
        return this.aluguelRepository.findAll(size, offset);
    }

    public Optional<Aluguel> findAluguelById(Long id){
        return this.aluguelRepository.findById(id);
    }

    public void saveAluguel(Aluguel alguel){
        var save = this.aluguelRepository.save(alguel);
        Assert.state(save == 1, "Erro ao salvar alguel");
    }

    public void updateAluguel(Aluguel alguel, Long id){
        var update = this.aluguelRepository.update(alguel, id);
        if (update == 0){
            throw new RuntimeException("Aluguel não encontrado");
        }
    }

    public void delete(Long id){
        var delete = this.aluguelRepository.delete(id);
        if (delete == 0){
            throw new RuntimeException("Aluguel não encontrada. Id: " + id);
        }
    }
}
