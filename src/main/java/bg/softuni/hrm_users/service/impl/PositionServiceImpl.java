package bg.softuni.hrm_users.service.impl;

import bg.softuni.hrm_users.repository.PositionRepository;
import bg.softuni.hrm_users.service.PositionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl implements PositionService {
   private final PositionRepository positionRepository;
   private final ModelMapper mapper;

    public PositionServiceImpl(PositionRepository positionRepository, ModelMapper mapper) {
        this.positionRepository = positionRepository;
        this.mapper = mapper;
    }

    @Override
    public List<String> getAllPositionName() {;
        return positionRepository.findAll().stream()
                .map(position -> position.getPositionName())
                .collect(Collectors.toList());
    }
}
